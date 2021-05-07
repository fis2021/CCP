package sample.Categories.Sources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.MainPage.PopUp;

import java.util.ArrayList;
import java.util.List;

public class Sources {
    @FXML
    private Button GoBack;
    private Stage stage;
    private Parent root;
    @FXML
    private AnchorPane AnchorR;
    private static VBox vbox1=new VBox();
    private static Pane[] pane1=new Pane[10];
    private static Button[] buttons=new Button[10];
    private static List<Button> buttons1=new ArrayList<>(10);
    private static Text[] nume=new Text[10];
    private static Text[] descriere=new Text[10];
    private static Text[] pret=new Text[10];
    private static Text[] tip=new Text[10];
    private static Text[] garantie=new Text[10];


    public void GoBackMainPage(ActionEvent event)throws Exception{
        if(event.getSource() == GoBack){
            stage = (Stage) GoBack.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/FXML/MainPage.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Main Page");
        stage.show();

    }

    private void initVBOX(){
        vbox1.setPadding(new Insets(10,10,10,10));
        vbox1.setSpacing(50);
    }

    public static void SetVectori(String nume1,String descriere1,String pret1,String tip1,String garantie1)
    {
        for(int i=0;i<nume.length;i++)
        {
            nume[i]=new Text(nume1);
            nume[i].setLayoutX(0);
            nume[i].setLayoutY(3);
            pret[i] = new Text(pret1);
            descriere[i] = new Text(descriere1);
            tip[i] = new Text(tip1);
            garantie[i] = new Text(garantie1);
            nume[i].setLayoutX(0);
            nume[i].setLayoutY(3);
            pret[i].setLayoutX(100);
            pret[i].setLayoutY(3);
            descriere[i].setLayoutX(200);
            descriere[i].setLayoutY(25);
            tip[i].setLayoutX(300);
            tip[i].setLayoutY(3);
            garantie[i].setLayoutX(400);
            garantie[i].setLayoutY(3);
            buttons[i] = new Button("Add product");
            buttons[i].setLayoutX(620);
            buttons1.add(i,new Button("Interested"));
            buttons1.get(i).setLayoutX(520);
            pane1[i]=new Pane();
            pane1[i].setLayoutX(700);
            pane1[i].setLayoutY(50);
            pane1[i].getChildren().addAll(nume[i],pret[i],descriere[i],tip[i],garantie[i],buttons[i],buttons1.get(i));
        }
        vbox1.getChildren().add(pane1[PopUp.GetKS()]);
    }

    public void init()
    {
        vbox1=new VBox();
        initVBOX();
        AnchorR.getChildren().add(vbox1);
    }
    @FXML
    public void initialize(){
        initVBOX();
        init();
    }

}
