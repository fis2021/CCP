package sample.MainPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.DataBase.OrderService;


public class PopUpSellerHistory {
    @FXML
    private Button CloseHistory;
    private Stage stage;
    public void CloseSellerHistoryWindow(ActionEvent event){

        stage = (Stage) CloseHistory.getScene().getWindow();
        stage.close();

    }
}
