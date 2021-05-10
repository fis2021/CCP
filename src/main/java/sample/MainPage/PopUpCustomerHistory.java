package sample.MainPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PopUpCustomerHistory {

    @FXML
    private Button Close;
    private Stage stage;

    public void CloseWindow(ActionEvent event){
        stage = (Stage) Close.getScene().getWindow();
        stage.close();
    }

}
