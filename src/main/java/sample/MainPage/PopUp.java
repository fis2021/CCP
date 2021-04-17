package sample.MainPage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import sample.MainPage.MainPage;

public class PopUp {
    @FXML
    private ComboBox comboBox1;


    @FXML
    private void initialize(){
        if(MainPage.GetNr()==1) {
            comboBox1.getItems().add("Processors");
            comboBox1.getItems().add("RAM");
            comboBox1.getItems().add("Graphic Cards");
            comboBox1.getItems().add("Power Supply Unit");
        }
    }
}
