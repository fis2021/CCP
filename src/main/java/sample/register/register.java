package sample.register;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import sample.DataBase.UserService;
import sample.MainPage.MainPage;
import sample.User.User;
import sample.exceptions.UsernameAlreadyExistException;

import java.util.Random;

public class register {
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField confirm;
    @FXML
    private Button registerB;
    @FXML
    private CheckBox terms,news;
    @FXML
    private CheckBox hide;
    @FXML
    private PasswordField hidpas1;
    @FXML
    private PasswordField hidpas2;
    @FXML
    private Hyperlink gotoLOGIN;
    @FXML
    private ComboBox comboBox;
    private Parent root;
    private Stage stage;
    private Alert alert,alert1,alertUsername,alertPasswordIncorect;
    private static int id=0;
    private Button myButton;

    public register() {
        this.alert = new Alert(AlertType.ERROR);
        this.alert1 = new Alert(AlertType.ERROR);
        this.alertUsername = new Alert(AlertType.ERROR);
        this.alertPasswordIncorect = new Alert(AlertType.ERROR);
    }

    @FXML
    private void initialize(){
        comboBox.getItems().add("Customer");
        comboBox.getItems().add("Seller");
    }
    public static int getId(){
        return id;
    }

    public void validation(ActionEvent event) throws Exception {
        this.alert.setTitle("FIELD IS EMPTY");
        this.alert1.setTitle("TextBox in unchecked");
        boolean isMychoiceEmpty = comboBox.getSelectionModel().isEmpty();
        try {
            if (event.getSource() == this.registerB) {
                if(password.getText().isEmpty() && confirm.getText().isEmpty()){
                    password.setText(hidpas1.getText());
                    confirm.setText(hidpas2.getText());
                }
                if(hidpas1.getText().isEmpty() && hidpas2.getText().isEmpty()){
                    hidpas1.setText(password.getText());
                    hidpas2.setText(confirm.getText());
                }
                if(!password.getText().equals(confirm.getText())){
                    alertPasswordIncorect.setHeaderText((String) null);
                    alertPasswordIncorect.setTitle("Passwords don't match");
                    alertPasswordIncorect.setContentText("Please verify you re password again");
                    myButton = (Button) alertPasswordIncorect.getDialogPane().lookupButton(ButtonType.OK);
                    myButton.setId("test");
                    alertPasswordIncorect.showAndWait();
                    username.clear();
                    password.clear();
                    confirm.clear();
                    hidpas2.clear();
                    hidpas1.clear();
                    email.clear();
                    return;
                }
                if (this.username.getText().isEmpty() || this.email.getText().isEmpty()
                        || this.password.getText().isEmpty() || this.confirm.getText().isEmpty()
                        || !isMychoiceEmpty == false) {
                    this.alert.setHeaderText((String) null);
                    this.alert.setContentText("Please complete all the text fields!");
                    myButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                    myButton.setId("test");
                    this.alert.showAndWait();
                    username.clear();
                    password.clear();
                    confirm.clear();
                    hidpas2.clear();
                    hidpas1.clear();
                    email.clear();
                    return;
                }
                if (!this.terms.isSelected()) {
                    this.alert1.setHeaderText((String) null);
                    this.alert1.setContentText("Please check the terms and conditions");
                    myButton = (Button) alert1.getDialogPane().lookupButton(ButtonType.OK);
                    myButton.setId("test");
                    this.alert1.showAndWait();
                    username.clear();
                    password.clear();
                    confirm.clear();
                    hidpas2.clear();
                    hidpas1.clear();
                    email.clear();
                    return;
                }
                Random rand = new Random();
                int rand_int1 = rand.nextInt(1000);
                if(!UserService.verifyId(rand_int1)){
                    rand_int1 = rand.nextInt(1000);
                }
                UserService.addUser(username.getText(), password.getText(), email.getText(),comboBox.getSelectionModel().getSelectedItem().toString() , news.isSelected(),rand_int1);
                MainPage.usernameForMain(username.getText());
                this.stage = (Stage) this.registerB.getScene().getWindow();
                this.root = (Parent) FXMLLoader.load(this.getClass().getResource("/FXML/MainPage.fxml"));
                Scene scene = new Scene(this.root);
                this.stage.setTitle("CCP-Main Page");
                this.stage.setScene(scene);
                this.stage.show();
                id++;
            }
        }catch(UsernameAlreadyExistException e){
            alertUsername.setTitle("The username " + e.getMessage() + " already exist!");
            alertUsername.setHeaderText((String) null);
            alertUsername.setContentText("Please choose another username!");
            myButton = (Button) alertUsername.getDialogPane().lookupButton(ButtonType.OK);
            myButton.setId("test");
            alertUsername.showAndWait();
            username.clear();
            password.clear();
            confirm.clear();
            hidpas2.clear();
            hidpas1.clear();
            email.clear();
        }

    }

    public void ShowHide() {
        if (this.hide.isSelected()) {
            this.password.setText(this.hidpas1.getText());
            this.confirm.setText(this.hidpas2.getText());
            this.password.setVisible(true);
            this.confirm.setVisible(true);
            this.hidpas1.setVisible(false);
            this.hidpas2.setVisible(false);
        } else {
            this.hidpas1.setText(this.password.getText());
            this.hidpas2.setText(this.confirm.getText());
            this.password.setVisible(false);
            this.confirm.setVisible(false);
            this.hidpas1.setVisible(true);
            this.hidpas2.setVisible(true);
        }
    }

    public void SwitchPage(ActionEvent event) throws Exception {
        if (event.getSource() == this.gotoLOGIN) {
            this.stage = (Stage)this.gotoLOGIN.getScene().getWindow();
            this.root = (Parent)FXMLLoader.load(this.getClass().getResource("/FXML/LoginPage.fxml"));
            Scene scene = new Scene(this.root);
            this.stage.setTitle("Login Page");
            this.stage.setScene(scene);
            this.stage.show();
        }

    }
}
