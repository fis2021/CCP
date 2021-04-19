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
    private VBox vBox = new VBox();
    private Button[] buttons = new Button[10];
    private Pane[] panes = new Pane[10];
    private Text[] numeProduse = new Text[10];
    private Text[] Pret= new Text[10];
    private Text[] Descriere=new Text[10];
    private Text[] Tip=new Text[10];
    private Text[] Garantie=new Text[10];

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

    public void initialize(){
        initVBOX();
        //initButtons();
        init();
    }
}
