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

import java.util.ArrayList;
import java.util.List;

public class PopUpStatus {
    @FXML
    private Button Close;
    @FXML
    private AnchorPane Anchor;
    private Stage stage=new Stage();
    private static List<Text> numeProduse=new ArrayList<>();
    private static List<Text> cantitate=new ArrayList<>();
    private static List<Text> status=new ArrayList<>();
    private static Pane[] pane=new Pane[10];
    private static VBox vbox=new VBox();

    public void CloseStatusWindow(ActionEvent event){
        stage=(Stage) Close.getScene().getWindow();
        stage.close();
    }

    public static void getStatus(String nume,String cantitate1,String status1)
    {
        for(int i=0;i<10;i++)
        {
            numeProduse.add(i,new Text(nume));
            numeProduse.get(i).setLayoutX(0);
            numeProduse.get(i).setLayoutY(3);
            cantitate.add(i,new Text(cantitate1));
            cantitate.get(i).setLayoutX(100);
            cantitate.get(i).setLayoutY(3);
            status.add(i,new Text(status1));
            status.get(i).setLayoutX(200);
            status.get(i).setLayoutY(3);
            pane[i]=new Pane();
            pane[i].setLayoutY(50);
            pane[i].setLayoutX(300);
            pane[i].getChildren().addAll(numeProduse.get(i),cantitate.get(i),status.get(i));

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
