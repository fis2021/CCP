package sample.MainPage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import sample.DataBase.*;
import sample.exceptions.UsernameAlreadyExistException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)
class CustomerCanViewOrderHistoryTest {

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
    void testIfCustomerCanViewOrderHistory(FxRobot robot)throws UsernameAlreadyExistException{
        robot.clickOn("#login");

        UserService.addUser("customer","test","mail","Customer",true,2);
        robot.clickOn("#username");
        robot.write("customer");
        robot.clickOn("#password");
        robot.write("test");
        robot.clickOn("#login");

        FinalStatusService.FinalOrder("processors","seller","customer",4,false,"Accepted",6);
        FinalStatusService.FinalOrder("processors1","seller","customer",4,false,"Accepted",7);
        FinalStatusService.FinalOrder("processors2","seller","customer",1,false,"Decline",1);
        FinalStatusService.FinalOrder("processors3","seller","customer",2,false,"Decline",22);

        assertThat(FinalStatusService.getAllProduct()).size().isEqualTo(4);

        robot.clickOn("#CustomerHistory");
        robot.clickOn("#close");


    }

}