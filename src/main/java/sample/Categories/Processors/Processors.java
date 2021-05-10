package sample.Categories.Processors;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import sample.DataBase.GraphicCardsService;
import sample.DataBase.TempOrderService;
import sample.DataBase.UserService;
import sample.MainPage.MainPage;
import sample.MainPage.PopUp;
import sample.DataBase.ProcessorsService;

import java.util.ArrayList;
import java.util.List;

import static sample.DataBase.ProcessorsService.Increment;
import static sample.DataBase.UserService.returnRole;

public class Processors {
    @FXML
    private Button GoBack;
    private Stage stage;
    private Parent root;
    @FXML
    private AnchorPane AnchorPaneRight;
    private static VBox vBox = new VBox();
    private static Pane[] pane1=new Pane[10];
    private static List<Button> buttons=new ArrayList<>(10);
    private static List<Button> buttons1=new ArrayList<>(10);
    private static List<Text> nume=new ArrayList<>(10);
    private static List<Text> descriere=new ArrayList<>(10);
    private static List<Text> pret=new ArrayList<>(10);
    private static List<Text> tip=new ArrayList<>(10);
    private static List<Text> garantie=new ArrayList<>(10);
    private void initVBOX(){
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.setSpacing(50);
    }

    @FXML
    public void initialize(){
        //initVBOX();
        //initButtons();
        init2();
        //Test();
    }

    private static int cnt=0;

    public static void Test(String nume1,String descriere1,String pret1,String tip1,String garantie1)
    {
        for(int i=0;i<10;i++)
        {
            nume.add(i,new Text(nume1));
            nume.get(i).setLayoutX(0);
            nume.get(i).setLayoutY(3);
            pret.add(i,new Text(pret1));
            descriere.add(i,new Text(descriere1));
            tip.add(i,new Text(tip1));
            garantie.add(i,new Text(garantie1));
            pret.get(i).setLayoutX(100);
            pret.get(i).setLayoutY(3);
            descriere.get(i).setLayoutX(200);
            descriere.get(i).setLayoutY(25);
            tip.get(i).setLayoutX(300);
            tip.get(i).setLayoutY(3);
            garantie.get(i).setLayoutX(400);
            garantie.get(i).setLayoutY(3);
            buttons.add(i,new Button("Add to cart"));
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
            pane1[i]=new Pane();
            pane1[i].setLayoutX(700);
            pane1[i].setLayoutY(50);
            pane1[i].getChildren().addAll(nume.get(i),pret.get(i),descriere.get(i),tip.get(i),garantie.get(i),buttons.get(i),buttons1.get(i));



        }
        for(int i=0;i< buttons1.size();i++)
        {
            final int nr=i;
            buttons1.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    for(int j=0;j<nume.size();j++)
                    {
                        Increment(nume.get(nr).getText(),pret.get(nr).getText(),tip.get(nr).getText(),garantie.get(nr).getText(),descriere.get(nr).getText());
                        return;

                    }
                }
            });
        }
        for(int i=0; i<buttons.size(); i++){
            final int nr=i;
            buttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for(int j=0; j<nume.size(); j++){
                        if(TempOrderService.verify(nume.get(nr).getText(),UserService.returnNume(GraphicCardsService.returnId(nume.get(nr).getText())),MainPage.getUsernameFromMain())){
                            return;
                        }
                        TempOrderService.addOrder(UserService.returnNume(ProcessorsService.returnId(nume.get(nr).getText())),MainPage.getUsernameFromMain(), nume.get(nr).getText());
                        return;
                    }
                }
            });
        }

        vBox.getChildren().add(pane1[PopUp.GetKP()]);
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
