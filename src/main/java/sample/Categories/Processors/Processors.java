package sample.Categories.Processors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.MainPage.PopUp;
import sample.DataBase.ProcessorsService;

public class Processors {
    @FXML
    private Button GoBack;
    private Stage stage;
    private Parent root;
    @FXML
    private AnchorPane AnchorPaneRight;
    private static VBox vBox = new VBox();
    private static Button[] buttons = new Button[10];
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

    private void init(){
        for(int i=0; i<10; i++){
            buttons[i] = new Button("Add product");
            buttons[i].setLayoutX(620);
        }

        for(int i=0; i<10; i++){
            panes[i] = new Pane();
            panes[i].setLayoutX(700);
            panes[i].setLayoutY(50);
            // panes[i].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID)));
        }
        for(int i=0; i<10; i++){
            numeProduse[i] = new Text("Product Name");
            Pret[i] = new Text("Price");
            Descriere[i] = new Text("Description");
            Tip[i] = new Text("Type");
            Garantie[i] = new Text("guarantee");
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
        }
        for(int i=0; i<10; i++){
            panes[i].getChildren().addAll(buttons[i],numeProduse[i],Pret[i],Descriere[i],Tip[i],Garantie[i]);
        }
        for(int i=0; i<10; i++){
            vBox.getChildren().add(panes[i]);
        }
        AnchorPaneRight.getChildren().add(vBox);
    }

    /*private void initButtons(){

        for(int i=0; i<10; i++){
            buttons[i] = new Button("Add product");
            buttons[i].setLayoutX(620);
        }
        for(int i=0; i<10; i++){
            panes[i] = new Pane();
            panes[i].setLayoutX(700);
            panes[i].setLayoutY(50);
           // panes[i].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID)));
        }
        for(int i=0; i<10; i++){
            panes[i].getChildren().add(buttons[i]);
        }
        for(int i=0; i<10; i++){
            vBox.getChildren().add(panes[i]);
        }
        for(int i=0; i<10; i++){
            texts[i] = new Text("Here the text");
        }
        for(int i=0; i<10; i++){
            panes[i].getChildren().add(texts[i]);
        }
        AnchorPaneRight.getChildren().add(vBox);
    }*/
    @FXML
    public void initialize(){
        //initVBOX();
        //initButtons();
        init2();
        //Test();
    }

    private static int cnt=0;

    public static void Test(String nume,String descriere,String pret,String tip,String garantie)
    {
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
            buttons[i] = new Button("Add product");
            buttons[i].setLayoutX(620);
            panes[i]=new Pane();
            panes[i].setLayoutX(700);
            panes[i].setLayoutY(50);
            panes[i].getChildren().addAll(numeProduse[i],Pret[i],Descriere[i],Tip[i],Garantie[i],buttons[i]);



        }
        vBox.getChildren().add(panes[PopUp.GetKP()]);
    }

    public void init2()
    {
        vBox=new VBox();
        initVBOX();
        AnchorPaneRight.getChildren().add(vBox);
        cnt=1;
    }

    public void GoBackMainPage(ActionEvent event)throws Exception{
        if(event.getSource() == GoBack){
            stage = (Stage) GoBack.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/FXML/MainPage.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
