package sample.DataBase;

import org.dizitart.no2.Document;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteBuilder;
import org.dizitart.no2.NitriteCollection;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import sample.Categories.RAM.RAMBase;
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

    private static Nitrite database;

    public static void initDataBase(){

        FileSystemService.initDirectory();
        database = Nitrite.builder()
                .filePath(getPathToFile("User.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);

    }

    public static void closeDataBase(){
        database.close();
    }

    public static void addUser(String username, String password, String email,String role,boolean check,int id) throws UsernameAlreadyExistException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), email,role,check,id));
    }

    public static List<User> getAllUsers() {
        return userRepository.find().toList();
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistException, UsernameAlreadyExistException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistException(username);
        }
    }

    public static String encodePassword(String salt,String password){
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

    public static boolean Verify(String username,String password)
    {
        String password1=encodePassword(username,password);
        for(User user: userRepository.find())
        {
            if (Objects.equals(username, user.getUsername()))
            {
                if(password1.equals(user.getPassword()))
                    return true;
            }

        }
        return false;
    }

    public static boolean verifyId(int id){
        for(User user: userRepository.find())
        {
            if (id==user.getId())
            {
                return true;
            }

        }
        return false;
    }


    public static int returnId(String name){

        for(User user : userRepository.find()){
            if(name.equals(user.getUsername())){
                return user.getId();
            }
        }
        return -1;
    }

    public static String returnRole(String name){
        for(User user : userRepository.find()){
            if(Objects.equals(name,user.getUsername())){
                return user.getRole();
            }
        }
        return null;
    }

    public static String returnNume(int id){

        for(User user : userRepository.find()){
            if(id==user.getId() && user.getRole().equals("Seller")){
                return user.getUsername();
            }
        }
        return null;
    }

    public static String returnGmail(String username){
        for(User user : userRepository.find()){
            if(user.getUsername().equals(username)){
                return user.getGmail();
            }
        }
        return null;
    }

    public static String getRole(String username){
        for(User user : userRepository.find()){
            if(user.getUsername().equals(username)){
                return user.getRole();
            }
        }
        return null;
    }

    public static void updateProfile(String numeCurent,String username,String parola,String gmail)throws UsernameAlreadyExistException{
        checkUserDoesNotAlreadyExist(username);
        for(User user : userRepository.find()){
            if(numeCurent.equals(user.getUsername())){
                user.setUsername(username);
                user.setGmail(gmail);
                user.setPassword(encodePassword(username,parola));
                userRepository.remove(ObjectFilters.eq("username",numeCurent));
                userRepository.insert(user);
            }
        }
    }
}
