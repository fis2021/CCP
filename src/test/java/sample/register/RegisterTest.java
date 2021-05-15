package sample.register;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import sample.DataBase.*;
import sample.exceptions.UsernameAlreadyExistException;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)
class RegisterTest {
    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        ProcessorsService.initDataBaseforProcessors();
        GraphicCardsService.initDataBaseforGraphicCards();
        RAMService.initDataBaseforRAM();
        SourcesService.initDataBaseforSources();
        UserService.initDataBase();
        TempOrderService.initDataBase();
        OrderService.initDataBase();
        FinalStatusService.initDataBase();
    }

    @AfterEach
    void close(){
        UserService.closeDataBase();
        ProcessorsService.closeDataBase();
        GraphicCardsService.closeDataBase();
        RAMService.closeDataBase();
        SourcesService.closeDataBase();
        TempOrderService.closeDataBase();
        OrderService.closeDataBase();
        FinalStatusService.closeDataBase();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));
        primaryStage.setTitle("Home");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Test
    void testRegister(FxRobot robot) throws UsernameAlreadyExistException {
        robot.clickOn("#regist");

        //FIELDS EMPTY
        robot.clickOn("#emailreg");
        robot.clickOn("#usernamereg");
        robot.write("seller");
        robot.clickOn("#passreg");
        robot.write("test");
        robot.clickOn("#passconfreg");
        robot.write("test");
        robot.clickOn("#combo");
        robot.clickOn("Seller");
        robot.clickOn("#checkterms");
        robot.clickOn("#registerB");
        robot.clickOn("#test");

        //PASSWORD DONT MATCH
        robot.clickOn("#emailreg");
        robot.write("t");
        robot.clickOn("#usernamereg");
        robot.write("seller");
        robot.clickOn("#passreg");
        robot.write("test");
        robot.clickOn("#passconfreg");
        robot.write("test1");
        robot.clickOn("#registerB");
        robot.clickOn("#test");

        //TERM NOT SELECTED
        robot.clickOn("#emailreg");
        robot.write("t");
        robot.clickOn("#usernamereg");
        robot.write("seller");
        robot.clickOn("#passreg");
        robot.write("test");
        robot.clickOn("#passconfreg");
        robot.write("test1");
        robot.clickOn("#checkterms");
        robot.clickOn("#registerB");
        robot.clickOn("#test");

        //USERNAME ALREADY EXIST"
        UserService.addUser("seller1","test","t","seller",true,3);
        robot.clickOn("#emailreg");
        robot.write("t");
        robot.clickOn("#usernamereg");
        robot.write("seller1");
        robot.clickOn("#passreg");
        robot.write("test");
        robot.clickOn("#passconfreg");
        robot.write("test");
        robot.clickOn("#checkterms");
        robot.clickOn("#registerB");
        robot.clickOn("#test");

        robot.clickOn("#emailreg");
        robot.write("t");
        robot.clickOn("#usernamereg");
        robot.write("seller");
        robot.clickOn("#passreg");
        robot.write("test");
        robot.clickOn("#passconfreg");
        robot.write("test");
        robot.clickOn("#registerB");
        assertThat(UserService.getAllUsers()).size().isEqualTo(2);
        robot.clickOn("#LogOut");

        robot.clickOn("#register");

        robot.clickOn("#emailreg");
        robot.write("t");
        robot.clickOn("#usernamereg");
        robot.write("customer");
        robot.clickOn("#passreg");
        robot.write("test");
        robot.clickOn("#passconfreg");
        robot.write("test");
        robot.clickOn("#checkterms");
        robot.clickOn("#combo");
        robot.clickOn("Customer");
        robot.clickOn("#registerB");
        assertThat(UserService.getAllUsers()).size().isEqualTo(3);

    }

}