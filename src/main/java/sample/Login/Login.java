package sample.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;



public class Login {

    @FXML
    private CheckBox ShowPassword;
    @FXML
    private TextField Password;
    @FXML
    private PasswordField pass_hid;
    @FXML
    private TextField username;
    @FXML
    private Button Login;
    private Alert alert = new Alert(Alert.AlertType.ERROR);
    @FXML
    private Hyperlink GoToRegister;

    private Parent root;
    private Stage stage;

    public void Login(ActionEvent event)throws Exception{
        alert.setTitle("FIELD IS EMPTY!");

        if(event.getSource() == Login){
            if(username.getText().isEmpty() || pass_hid.getText().isEmpty()){
                alert.setHeaderText(null);
                alert.setContentText("Field is EMPTY!");
                alert.showAndWait();
                return;
            }

            stage = (Stage) Login.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/MainPage.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setTitle("CCP MAIN PAGE");
        stage.setScene(scene);
        stage.show();

    }

    public void ShowAndHide(){
        if(ShowPassword.isSelected()){
            Password.setText(pass_hid.getText());
            Password.setVisible(true);
            pass_hid.setVisible(false);
            return;
        }
        pass_hid.setText(Password.getText());
        pass_hid.setVisible(true);
        Password.setVisible(false);
    }

    public void HyperLinkConnection(ActionEvent event)throws Exception{

        if(event.getSource() == GoToRegister){
            stage = (Stage) Login.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/register.fxml"));
        }

        Scene scene = new Scene(root);
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();

    }
}
