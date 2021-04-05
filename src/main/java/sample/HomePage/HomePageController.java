package sample.HomePage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomePageController {

    @FXML
    private Button LoginFromHome,RegisterFromHome;
    private Parent root;
    private Stage stage;

    public void GoFromHome(ActionEvent event)throws Exception{
        if(event.getSource() == LoginFromHome){
            stage = (Stage) LoginFromHome.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/FXML/LoginPage.fxml"));
            stage.setTitle("Login");
        }
        if(event.getSource() == RegisterFromHome){
            stage = (Stage) RegisterFromHome.getScene().getWindow();
            root =FXMLLoader.load(getClass().getResource("/FXML/register.fxml"));
            stage.setTitle("Register");
        }


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
