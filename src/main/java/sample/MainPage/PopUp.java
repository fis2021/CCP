package sample.MainPage;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class PopUp {
    @FXML
    private ComboBox comboBox1;

    @FXML
    private void initialize(){
        comboBox1.getItems().add("Processors");
        comboBox1.getItems().add("RAM");
        comboBox1.getItems().add("Graphic Cards");
        comboBox1.getItems().add("Power Supply Unit");
    }
}
