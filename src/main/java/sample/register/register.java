package sample.register;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

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
    private CheckBox terms;
    @FXML
    private CheckBox hide;
    @FXML
    private PasswordField hidpas1;
    @FXML
    private PasswordField hidpas2;
    @FXML
    private Hyperlink gotoLOGIN;
    private Parent root;
    private Stage stage;
    private Alert alert;
    private Alert alert1;

    public register() {
        this.alert = new Alert(AlertType.ERROR);
        this.alert1 = new Alert(AlertType.ERROR);
    }

    public void validation(ActionEvent event) throws Exception {
        this.alert.setTitle("FIELD IS EMPTY");
        this.alert1.setTitle("TextBox in unchecked");
        if (event.getSource() == this.registerB) {
            this.password.setText(this.hidpas1.getText());
            this.confirm.setText(this.hidpas2.getText());
            if (this.username.getText().isEmpty() || this.email.getText().isEmpty() || this.password.getText().isEmpty() || this.confirm.getText().isEmpty()) {
                this.alert.setHeaderText((String)null);
                this.alert.setContentText("Please complete all the text fields!");
                this.alert.showAndWait();
                return;
            }

            if (!this.terms.isSelected()) {
                this.alert1.setHeaderText((String)null);
                this.alert1.setContentText("Please check the terms and conditions");
                this.alert1.showAndWait();
                return;
            }

            this.stage = (Stage)this.registerB.getScene().getWindow();
            this.root = (Parent)FXMLLoader.load(this.getClass().getResource("/MainPage.fxml"));
            Scene scene = new Scene(this.root);
            this.stage.setTitle("CCP-Main Page");
            this.stage.setScene(scene);
            this.stage.show();
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
            this.root = (Parent)FXMLLoader.load(this.getClass().getResource("/LoginPage.fxml"));
            Scene scene = new Scene(this.root);
            this.stage.setTitle("Login Page");
            this.stage.setScene(scene);
            this.stage.show();
        }

    }
}
