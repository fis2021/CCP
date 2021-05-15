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
import sample.Categories.Processors.ProcessorsBase;
import sample.DataBase.*;
import sample.exceptions.ProductAlreadyExists;
import sample.exceptions.UsernameAlreadyExistException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class SellerCanEditProductTest {

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
    void testIfSellerCanEditProduct(FxRobot robot)throws UsernameAlreadyExistException, ProductAlreadyExists {
        robot.clickOn("#login");

        UserService.addUser("seller","test","mail","Seller",true,2);
        robot.clickOn("#username");
        robot.write("seller");
        robot.clickOn("#password");
        robot.write("test");
        robot.clickOn("#login");

        ProcessorsService.addProcessors("Processor", "300$", "New", "Proccesor", "1 years", 2, 10);
        ProcessorsService.addProcessors("Processor1", "300$", "New", "Proccesor", "1 years", 2, 10);
        RAMService.addRAM("Ram", "210$", "New", "Rams", "2 years", 2, 10);
        SourcesService.addSource("Power Unit Supply", "400$", "New", "PUS", "2 years", 2, 10);
        GraphicCardsService.addGraphic("Graphic Card", "210$", "New", "Graphics", "2 years", 2, 10);

        robot.clickOn("#Edit");

        robot.clickOn("#combo");
        robot.clickOn("Processors");

        robot.clickOn("#b1");

        robot.clickOn("#price");
        robot.write("444");
        robot.clickOn("#type");
        robot.write("I5");
        robot.clickOn("#descrption");
        robot.write("Stare buna");
        robot.clickOn("#garantie");
        robot.write("3 ani");
        robot.clickOn("#confirm");

        ProcessorsBase base = ProcessorsService.getAllProccesors().get(1);
        assertThat(base.getPret()).isEqualTo("444");
        assertThat(base.getTip()).isEqualTo("I5");
        assertThat(base.getDescriere()).isEqualTo("Stare buna");
        assertThat(base.getGarantie()).isEqualTo("3 ani");
    }

}