package sample.MainPage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.DataBase.OrderService;
import sample.DataBase.TempOrderService;
import sample.User.TempOrder;

import java.util.ArrayList;
import java.util.List;

public class PopUpAccept {

    private static List<Text> numeProdus = new ArrayList<>();
    private static List<Text> cantitate = new ArrayList<>();
    private static List<Button> AcceptButton = new ArrayList<>();
    private static List<Button> DeclineButton = new ArrayList<>();
    private static VBox vbox1 = new VBox();
    private static Pane[] pane1 = new Pane[10];
    @FXML
    private AnchorPane CentralAnchor;
    @FXML
    private Button Close;
    private Stage stage;


    private void initVBOX(){
        vbox1.setPadding(new Insets(10,10,10,10));
        vbox1.setSpacing(50);
    }

    public static void Test(String numeProduse,int cantitate1,String id1,String id2){

        for(int i=0;i<10;i++)
        {
            AcceptButton.add(i,new Button("Accept"));
            AcceptButton.get(i).setLayoutX(520);
            AcceptButton.get(i).setId(id1);
            AcceptButton.get(i).setStyle("-fx-background-color: #20B2AA; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
            DeclineButton.add(i,new Button("Decline"));
            DeclineButton.get(i).setLayoutX(460);
            DeclineButton.get(i).setId(id2);
            DeclineButton.get(i).setStyle("-fx-background-color: #DC143C; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
        }
        Integer y = new Integer(cantitate1);
        for(int i=0; i<10; i++){
            numeProdus.add(i,new Text(numeProduse));
            numeProdus.get(i).setLayoutX(0);
            numeProdus.get(i).setLayoutY(3);
            cantitate.add(i,new Text(y.toString()));
            cantitate.get(i).setLayoutY(3);
            cantitate.get(i).setLayoutX(100);
            pane1[i]= new Pane();
            pane1[i].setLayoutX(300);
            pane1[i].setLayoutY(50);
            pane1[i].getChildren().addAll(numeProdus.get(i),cantitate.get(i),AcceptButton.get(i),DeclineButton.get(i));
        }

        for (int i = 0; i < AcceptButton.size(); i++) {
            final int nr = i;
            AcceptButton.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for (int j = 0; j < numeProdus.size(); j++) {
                        if (nr == j) {
                            OrderService.SetStatusOrder(numeProdus.get(nr).getText(), "Accepted");
                            DeclineButton.get(nr).setVisible(false);
                            AcceptButton.get(nr).setVisible(false);
                        }
                    }
                }
            });
        }

        for (int i = 0; i < DeclineButton.size(); i++) {
            final int nr = i;
            DeclineButton.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for (int j = 0; j < numeProdus.size(); j++) {
                        if (nr == j) {
                            OrderService.SetStatusOrder(numeProdus.get(nr).getText(), "Declined");
                            DeclineButton.get(nr).setVisible(false);
                            AcceptButton.get(nr).setVisible(false);
                        }
                    }
                }
            });
        }

        vbox1.getChildren().add(pane1[PopUp.GetKP()]);
    }

    public void init(){

        vbox1=new VBox();
        initVBOX();
        CentralAnchor.getChildren().add(vbox1);
    }

    @FXML
    public void initialize(){
        initVBOX();
        init();
    }

    public void CloseWindow(ActionEvent event)throws Exception{
        if(event.getSource() == Close){
            stage = (Stage) Close.getScene().getWindow();
            stage.close();
        }
    }

}
