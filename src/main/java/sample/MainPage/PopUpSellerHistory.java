package sample.MainPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.DataBase.OrderService;

import java.util.ArrayList;
import java.util.List;


public class PopUpSellerHistory {
    @FXML
    private Button CloseHistory;
    private Stage stage;
    @FXML
    private AnchorPane Anchor;
    private static List<Text> numeProduse=new ArrayList<>();
    private static List<Text> numeCustomer=new ArrayList<>();
    private static List<Text> cantitate=new ArrayList<>();
    private static List<Text> status=new ArrayList<>();
    private static List<Text> nrinteresati=new ArrayList<>();
    private static Pane[] pane=new Pane[10];
    private static VBox vbox=new VBox();
    public void CloseSellerHistoryWindow(ActionEvent event){

        stage = (Stage) CloseHistory.getScene().getWindow();
        stage.close();

    }

    public static void getOrderHistory(String nume,String numeCustomer1,String cantitate1,String status1,String nrinteresati1)
    {
        for(int i=0;i<10;i++)
        {
            numeProduse.add(i,new Text(nume));
            numeProduse.get(i).setLayoutX(0);
            numeProduse.get(i).setLayoutY(3);
            numeCustomer.add(i,new Text(numeCustomer1));
            numeCustomer.get(i).setLayoutX(100);
            numeCustomer.get(i).setLayoutY(3);
            cantitate.add(i,new Text(cantitate1));
            cantitate.get(i).setLayoutX(200);
            cantitate.get(i).setLayoutY(3);
            status.add(i,new Text(status1));
            status.get(i).setLayoutX(300);
            status.get(i).setLayoutY(3);
            nrinteresati.add(i,new Text(nrinteresati1));
            nrinteresati.get(i).setLayoutX(400);
            nrinteresati.get(i).setLayoutY(3);
            pane[i]=new Pane();
            pane[i].setLayoutY(50);
            pane[i].setLayoutX(400);
            pane[i].getChildren().addAll(numeProduse.get(i),numeCustomer.get(i),cantitate.get(i),status.get(i),nrinteresati.get(i));

        }
        vbox.getChildren().add(pane[9]);


    }
    private void initVBOX(){
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(50);
    }

    public void init()
    {
        vbox=new VBox();
        initVBOX();
        Anchor.getChildren().add(vbox);
    }
    @FXML
    public void initialize(){
        initVBOX();
        init();

    }
}
