package sample.MainPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PopUpStatus {
    @FXML
    private Button Close;
    private Stage stage=new Stage();


    public void CloseStatusWindow(ActionEvent event){
        stage=(Stage) Close.getScene().getWindow();
        stage.close();
    }
}
