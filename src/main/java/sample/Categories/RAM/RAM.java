package sample.Categories.RAM;

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
import sample.MainPage.MainPage;
import sample.MainPage.PopUp;

import java.util.ArrayList;
import java.util.List;

import static sample.DataBase.UserService.returnRole;

public class RAM {
    @FXML
    private Button GoBack;
    private Stage stage;
    private Parent root;

    public void GoBackMainPage(ActionEvent event)throws Exception{
        if(event.getSource() == GoBack){
            stage = (Stage) GoBack.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/FXML/MainPage.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private AnchorPane AnchorPaneRight;
    private static VBox vBox = new VBox();
    private static List<Button> buttons=new ArrayList<>(10);
    private static List<Button> buttons1=new ArrayList<>(10);
    private static Pane[] panes = new Pane[10];
    private static Text[] numeProduse = new Text[10];
    private static Text[] Pret= new Text[10];
    private static Text[] Descriere=new Text[10];
    private static Text[] Tip=new Text[10];
    private static Text[] Garantie=new Text[10];

    private void initVBOX(){
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.setSpacing(50);
    }

    public static void setareVectori(String nume,String descriere,String pret,String tip,String garantie){
        for(int i=0;i<numeProduse.length;i++)
        {
            numeProduse[i]=new Text(nume);
            numeProduse[i].setLayoutX(0);
            numeProduse[i].setLayoutY(3);
            Pret[i] = new Text(pret);
            Descriere[i] = new Text(descriere);
            Tip[i] = new Text(tip);
            Garantie[i] = new Text(garantie);
            numeProduse[i].setLayoutX(0);
            numeProduse[i].setLayoutY(3);
            Pret[i].setLayoutX(100);
            Pret[i].setLayoutY(3);
            Descriere[i].setLayoutX(200);
            Descriere[i].setLayoutY(25);
            Tip[i].setLayoutX(300);
            Tip[i].setLayoutY(3);
            Garantie[i].setLayoutX(400);
            Garantie[i].setLayoutY(3);
            buttons.add(i,new Button("Add product"));
            buttons.get(i).setLayoutX(620);
            buttons1.add(i,new Button("Interested"));
            buttons1.get(i).setLayoutX(520);
            if(returnRole(MainPage.getUsernameFromMain()).equals("Seller")){
                buttons.get(i).setVisible(false);
                buttons1.get(i).setVisible(false);
            }
            else
            {
                buttons.get(i).setVisible(true);
                buttons1.get(i).setVisible(true);
            }
            panes[i]=new Pane();
            panes[i].setLayoutX(700);
            panes[i].setLayoutY(50);
            panes[i].getChildren().addAll(numeProduse[i],Pret[i],Descriere[i],Tip[i],Garantie[i],buttons.get(i),buttons1.get(i));

        }
        vBox.getChildren().add(panes[PopUp.GetKR()]);
    }

    private void init(){
        vBox = new VBox();
        initVBOX();
        AnchorPaneRight.getChildren().add(vBox);
    }

    public void initialize(){
        initVBOX();
        //initButtons();
        init();
    }
}
