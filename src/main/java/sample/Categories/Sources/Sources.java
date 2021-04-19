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

public class Sources {
    @FXML
    private Button GoBack;
    private Stage stage;
    private Parent root;
    @FXML
    private AnchorPane AnchorR;
    private VBox vbox1=new VBox();
    private Pane[] pane1=new Pane[10];
    private Button[] buttons=new Button[10];
    private Text[] nume=new Text[10];
    private Text[] descriere=new Text[10];
    private Text[] pret=new Text[10];
    private Text[] tip=new Text[10];
    private Text[] garantie=new Text[10];


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

    private void init(){
        for(int i=0; i<10; i++){
            buttons[i] = new Button("Add product");
            buttons[i].setLayoutX(620);
        }

        for(int i=0; i<10; i++){
            pane1[i] = new Pane();
            pane1[i].setLayoutX(700);
            pane1[i].setLayoutY(50);
        }
        for(int i=0; i<10; i++){
            nume[i] = new Text("Product Name");
            pret[i] = new Text("Price");
            descriere[i] = new Text("Description");
            tip[i] = new Text("Type");
            garantie[i] = new Text("guarantee");
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
        }
        for(int i=0; i<10; i++){
            pane1[i].getChildren().addAll(buttons[i],nume[i],pret[i],descriere[i],tip[i],garantie[i]);
        }
        for(int i=0; i<10; i++){
            vbox1.getChildren().add(pane1[i]);
        }
        AnchorR.getChildren().add(vbox1);
    }
    @FXML
    public void initialize(){
        initVBOX();
        init();
    }

}
