package sample.DataBase;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;;
import sample.User.User;
import sample.exceptions.UsernameAlreadyExistException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

class UserServiceTest {

    public static final String SELLER = "seller";
    public static final String PAROLA = "test";
    public static final String CUSTOMER = "customer";
    public static final String MAIL = "MAILMAIL";
    public static final String ROLESELLER = "Seller";
    public static final String ROLECUSTOMER = "Customer";
    public static final boolean check = true;
    public static final int IdSeller = 5;
    public static final int IdCustomer = 3;

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDataBase();
    }

    @Test
    void testDatabaseIsInitializedAndNoUserIsPersisted() {
        assertThat(UserService.getAllUsers()).isNotNull();
        assertThat(UserService.getAllUsers()).isEmpty();
    }

    @AfterEach
    void tearDown() {
       UserService.closeDataBase();
    }

    @Test
    void testIfUserIsAddedCorectly() throws UsernameAlreadyExistException {
        UserService.addUser(SELLER,PAROLA,MAIL,ROLESELLER,check,IdSeller);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = UserService.getAllUsers().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(SELLER);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(SELLER, PAROLA));
        assertThat(user.getRole()).isEqualTo(ROLESELLER);
        assertThat(user.getGmail()).isEqualTo(MAIL);
        assertThat(user.getId()).isEqualTo(IdSeller);
        UserService.addUser(CUSTOMER,PAROLA,MAIL,ROLECUSTOMER,check,IdCustomer);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(2);
        user = UserService.getAllUsers().get(1);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(CUSTOMER);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(CUSTOMER, PAROLA));
        assertThat(user.getRole()).isEqualTo(ROLECUSTOMER);
        assertThat(user.getGmail()).isEqualTo(MAIL);
        assertThat(user.getId()).isEqualTo(IdCustomer);

    }

    @Test
    void testUserCanNotBeAddedTwice() {
        assertThrows(UsernameAlreadyExistException.class, () -> {
            UserService.addUser(SELLER,PAROLA,MAIL,ROLESELLER,check,IdSeller);
            UserService.addUser(SELLER,PAROLA,MAIL,ROLESELLER,check,IdSeller);
            UserService.addUser(CUSTOMER,PAROLA,MAIL,ROLECUSTOMER,check,IdCustomer);
            UserService.addUser(CUSTOMER,PAROLA,MAIL,ROLECUSTOMER,check,IdCustomer);
        });
    }

    @Test
    void testIfPassowordIsCorectly()throws UsernameAlreadyExistException{
        UserService.addUser(SELLER,PAROLA,MAIL,ROLESELLER,check,IdSeller);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        assertThat(UserService.Verify(SELLER,PAROLA)).isEqualTo(true);
    }

    @Test
    void testIfFuctionReturnGmailWork() throws  UsernameAlreadyExistException{
        UserService.addUser(SELLER,PAROLA,MAIL,ROLESELLER,check,IdSeller);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        assertThat(UserService.returnGmail(SELLER)).isEqualTo(MAIL);
    }

    @Test
    void testIfIdIsVerified()throws UsernameAlreadyExistException{
        UserService.addUser(SELLER,PAROLA,MAIL,ROLESELLER,check,IdSeller);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        assertThat(UserService.verifyId(UserService.returnId(SELLER))).isEqualTo(true);
    }

    @Test
    void testIfReturnRoleWorkCorectly()throws UsernameAlreadyExistException{
        UserService.addUser(SELLER,PAROLA,MAIL,ROLESELLER,check,IdSeller);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        assertThat(UserService.returnRole(SELLER)).isEqualTo(ROLESELLER);
    }

    @Test
    void testifReturnNumeWorkCorectly()throws UsernameAlreadyExistException{
        UserService.addUser(SELLER,PAROLA,MAIL,ROLESELLER,check,IdSeller);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        assertThat(UserService.returnNume(UserService.returnId(SELLER))).isEqualTo(SELLER);
    }

    @Test
    void testIfUpdateWorkCorectly()throws UsernameAlreadyExistException{
        UserService.addUser(SELLER,PAROLA,MAIL,ROLESELLER,check,IdSeller);
        UserService.updateProfile(SELLER,"seller1","test","seller1@gmail.com");
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = UserService.getAllUsers().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("seller1");
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword("seller1", "test"));
        assertThat(user.getGmail()).isEqualTo("seller1@gmail.com");
    }



}