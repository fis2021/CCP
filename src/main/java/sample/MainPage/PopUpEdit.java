package sample.MainPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DataBase.GraphicCardsService;
import sample.DataBase.ProcessorsService;
import sample.DataBase.RAMService;
import sample.DataBase.SourcesService;

public class PopUpEdit {
    @FXML
    private Button Confirm;
    private Stage stage=new Stage();
    @FXML
    private TextField Type,Price,Description,Guarantee;
    @FXML
    private TextArea Name;
    private Alert alert=new Alert(Alert.AlertType.ERROR);
    @FXML
    public void initialize(){
        Name.setText(PopUp.returnNume());
    }

    public void Edit(String nume)
    {
        if(PopUp.GetCount()==1)
        {
            ProcessorsService.EditProduct(nume,Price.getText(),Type.getText(),Guarantee.getText(),Description.getText());
        }
        if(PopUp.GetCount()==2)
        {
            RAMService.EditProduct(nume,Price.getText(),Type.getText(),Guarantee.getText(),Description.getText());
        }
        if(PopUp.GetCount()==3)
        {
            GraphicCardsService.EditProduct(nume,Price.getText(),Type.getText(),Guarantee.getText(),Description.getText());
        }
        if(PopUp.GetCount()==4)
        {
            SourcesService.EditProduct(nume,Price.getText(),Type.getText(),Guarantee.getText(),Description.getText());
        }

    }
    public void CloseEditDetailsWindow(ActionEvent event){
        if(Price.getText().isEmpty() || Type.getText().isEmpty() || Description.getText().isEmpty() || Guarantee.getText().isEmpty())
        {
            alert.setTitle("FIELD IS EMPTY");
            alert.setHeaderText((String) null);
            alert.setContentText("Please complete all the text fields");
            alert.showAndWait();
            return;
        }
        Edit(PopUp.returnNume());
        stage=(Stage) Confirm.getScene().getWindow();
        stage.close();
    }
}
