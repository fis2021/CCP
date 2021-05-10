package sample.MainPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.DataBase.OrderService;
import sample.DataBase.TempOrderService;

import java.util.ArrayList;
import java.util.List;

public class PopUpOrder {

    @FXML
    private AnchorPane CentralAnchor;
    private static VBox vbox1 = new VBox();
    private static Pane[] pane1 = new Pane[10];
    private static List<Text> numeProdus = new ArrayList<>();
    private static List<Text> cantitate = new ArrayList<>();
    @FXML
    private Button Place,Delete;
    private Stage stage;
    @FXML
    private CheckBox Delivery;

    private void initVBOX(){
        vbox1.setPadding(new Insets(10,10,10,10));
        vbox1.setSpacing(50);
    }

    public static void Test(String numeProduse,String cantitate1){
        for(int i=0; i<10; i++){
            numeProdus.add(i,new Text(numeProduse));
            numeProdus.get(i).setLayoutX(0);
            numeProdus.get(i).setLayoutY(3);
            cantitate.add(i,new Text(cantitate1));
            cantitate.get(i).setLayoutY(3);
            cantitate.get(i).setLayoutX(100);
            pane1[i]= new Pane();
            pane1[i].setLayoutX(300);
            pane1[i].setLayoutY(50);
            pane1[i].getChildren().addAll(numeProdus.get(i),cantitate.get(i));

        }
        vbox1.getChildren().add(pane1[PopUp.GetKP()]);
    }


    public void init(){

        vbox1=new VBox();
        initVBOX();
        CentralAnchor.getChildren().add(vbox1);
    }

    public void SetOrder(ActionEvent event){
        if(event.getSource() == Place){
            TempOrderService.DeleteAllDatabase(MainPage.getUsernameFromMain());
        }
        if(event.getSource() == Delivery){
            OrderService.setDeliv();
            return;
        }
        stage = (Stage) Delete.getScene().getWindow();
        stage.close();
    }

    public void DeleteOrder(ActionEvent event){
        if(event.getSource() == Delete){
            TempOrderService.DeleteAllDatabase(MainPage.getUsernameFromMain());
        }
        stage = (Stage) Delete.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize(){
        if(Delivery.isSelected()){
            TempOrderService.SetNewDataBase(true);
        }else{
            TempOrderService.SetNewDataBase(false);
        }
        initVBOX();
        init();
    }
}
