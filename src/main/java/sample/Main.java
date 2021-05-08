package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;

import sample.DataBase.*;
import sample.User.TempOrder;

public class Main extends Application {

    public static void main(String argv[]){
        launch(argv);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initDirectory();
        ProcessorsService.initDataBaseforProcessors();
        GraphicCardsService.initDataBaseforGraphicCards();
        RAMService.initDataBaseforRAM();
        SourcesService.initDataBaseforSources();
        UserService.initDataBase();
        TempOrderService.initDataBase();
        OrderService.initDataBase();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));
        primaryStage.setTitle("CCP");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void initDirectory(){
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if(!Files.exists(applicationHomePath)){
            applicationHomePath.toFile().mkdirs();
        }
    }

}
