package sample.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Main;
import sample.User.User;
import sample.DataBase.UserService;
import sample.DataBase.FileSystemService;
import sample.MainPage.MainPage;

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
    private Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    private Alert alert1 = new Alert(Alert.AlertType.ERROR);
    @FXML
    private Hyperlink GoToRegister;
    private Parent root;
    private Stage stage;
    private Button myButton;

    public void Login(ActionEvent event)throws Exception{
        alert.setTitle("FIELD IS EMPTY!");
        alert1.setTitle("Username or password is incorrect!");
        if(Password.getText().isEmpty())
        {
            Password.setText(pass_hid.getText());
        }
        if(event.getSource() == Login){
            if(username.getText().isEmpty() || Password.getText().isEmpty()){
                alert.setHeaderText(null);
                alert.setContentText("Field is EMPTY!");
                myButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                myButton.setId("test");
                alert.showAndWait();
                username.clear();
                Password.clear();
                pass_hid.clear();
                return;
            }
            if(!UserService.Verify(username.getText(),Password.getText()))
            {
                alert1.setHeaderText(null);
                alert1.setContentText("Please try again!");
                myButton = (Button) alert1.getDialogPane().lookupButton(ButtonType.OK);
                myButton.setId("test");
                alert1.showAndWait();
                username.clear();
                Password.clear();
                pass_hid.clear();
                return;
            }
            MainPage.usernameForMain(username.getText());
            stage = (Stage) Login.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/FXML/MainPage.fxml"));
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
            pass_hid.setText(Password.getText());
            return;
        }
        pass_hid.setText(Password.getText());
        pass_hid.setVisible(true);
        Password.setVisible(false);
        Password.setText(pass_hid.getText());
    }

    public void HyperLinkConnection(ActionEvent event)throws Exception{
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/FXML/register.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();

    }

}