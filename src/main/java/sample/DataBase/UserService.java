package sample.DataBase;

import org.dizitart.no2.Document;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteBuilder;
import org.dizitart.no2.NitriteCollection;
import org.dizitart.no2.objects.ObjectRepository;
import sample.User.User;

import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import static sample.DataBase.FileSystemService.getPathToFile;
import sample.exceptions.UsernameAlreadyExistException;

public class UserService {

    private static ObjectRepository<User> userRepository;

    public static void initDataBase(){
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("User.db").toFile())
                .openOrCreate("test","test");

        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String username, String password, String email,String role,boolean check) throws UsernameAlreadyExistException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), email,role,check));
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistException, UsernameAlreadyExistException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistException(username);
        }
    }

    private static String encodePassword(String salt,String password){
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));


        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", "");
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

}
