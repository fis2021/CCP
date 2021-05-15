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
class AddingProductTest {

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
    void testIfTheProductIsAdded(FxRobot robot)throws UsernameAlreadyExistException {
        robot.clickOn("#login");

        UserService.addUser("seller","test","mail","Seller",true,2);
        robot.clickOn("#username");
        robot.write("seller");
        robot.clickOn("#password");
        robot.write("test");
        robot.clickOn("#login");

        robot.clickOn("#ADD");
        robot.clickOn("#combo");
        robot.clickOn("Processors");
        robot.clickOn("#numeprod1");
        robot.write("p1");
        robot.clickOn("#type");
        robot.write("T1");
        robot.clickOn("#Description");
        robot.write("pppp");
        robot.clickOn("#price");
        robot.write("3213");
        robot.clickOn("#garantie");
        robot.write("2ani");


        robot.clickOn("#add");

        assertThat(ProcessorsService.getAllProccesors()).size().isEqualTo(1);

        robot.clickOn("#ADD");
        robot.clickOn("#combo");
        robot.clickOn("Graphic Cards");
        robot.clickOn("#numeprod1");
        robot.write("p2");
        robot.clickOn("#type");
        robot.write("T1");
        robot.clickOn("#Description");
        robot.write("pppp");
        robot.clickOn("#price");
        robot.write("3213");
        robot.clickOn("#garantie");
        robot.write("2ani");
        robot.clickOn("#add");

        assertThat(GraphicCardsService.getAllGraphicCards()).size().isEqualTo(1);

    }
}