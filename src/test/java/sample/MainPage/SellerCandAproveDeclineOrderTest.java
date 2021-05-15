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
import sample.User.Order;
import sample.User.User;
import sample.exceptions.ProductAlreadyExists;
import sample.exceptions.UsernameAlreadyExistException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)
class SellerCandAproveDeclineOrderTest {

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
    void testIfSellerCanAproveDeclineOrders(FxRobot robot)throws UsernameAlreadyExistException{
        robot.clickOn("#login");

        UserService.addUser("seller","test","mail","Seller",true,2);
        robot.clickOn("#username");
        robot.write("seller");
        robot.clickOn("#password");
        robot.write("test");
        robot.clickOn("#login");

        OrderService.Order("p1","seller","customer",4, true,"It is processing",5,6,1);
        OrderService.Order("p2","seller","customer2",4, true,"It is processing",5,6,2);

        robot.clickOn("#Accept");
        robot.clickOn("#b1");
        robot.clickOn("#d2");

        Order order = OrderService.getAllOrders().get(0);
        assertThat(order.getStatus()).isEqualTo("Accepted");
        order = OrderService.getAllOrders().get(1);
        assertThat(order.getStatus()).isEqualTo("Declined");


    }

}