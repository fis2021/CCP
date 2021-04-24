package sample.MainPage;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.DataBase.ProcessorsService;


public class MainPage {

    @FXML
    private Text WelcomeText;
    @FXML
    private Button Processors,GraphicCard,RAM,Sources,ModProfile,GoProfile,Log,Add;
    private Stage stage;
    private Parent root;
    private static int nr;

    public void GoToCategories(ActionEvent event)throws Exception{
        if(event.getSource() == Processors){
            stage = (Stage) Processors.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/FXML/Processors.fxml"));
            ProcessorsService.set();
            stage.setTitle("Processors");
        }
        if(event.getSource() == GraphicCard){
            stage = (Stage) GraphicCard.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/FXML/GraphicCard.fxml"));
            stage.setTitle("GraphicCard");
        }
        if(event.getSource() == RAM){
            stage = (Stage) RAM.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/FXML/RAM.fxml"));
            stage.setTitle("RAM");
        }
        if(event.getSource() == Sources){
            stage = (Stage) Sources.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/FXML/Sources.fxml"));
            stage.setTitle("Power Supply Unit");
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void MovingText(){
        AnchorPane root = new AnchorPane(WelcomeText);
        root.setPrefSize(500,70);
        Scene scene = new Scene(root);

        double sceneWidth = scene.getWidth();
        double msgWidth = WelcomeText.getLayoutBounds().getWidth();


        KeyValue initKeyValue = new KeyValue(WelcomeText.translateXProperty(),sceneWidth);
        KeyFrame initFrame = new KeyFrame(Duration.ZERO,initKeyValue);
        KeyValue endKeyValue = new KeyValue(WelcomeText.translateXProperty(),-1.0*msgWidth);
        KeyFrame endFrame = new KeyFrame(Duration.seconds(3),endKeyValue);
        Timeline timeline = new Timeline(initFrame,endFrame);

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    public void ModifyProfile(ActionEvent event) throws Exception
    {
        if(event.getSource()==ModProfile)
        {
            nr=3;
            stage=new Stage();
            root=FXMLLoader.load(getClass().getResource("/FXML/PopUps/PopUp.fxml"));
        }

        // set title for the stage
        stage.setTitle("Modify your profile");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void GoToProfile(ActionEvent event) throws Exception
    {
        if(event.getSource()==GoProfile)
        {
            nr=2;
            stage=new Stage();
            root=FXMLLoader.load(getClass().getResource("/FXML/PopUps/PopUp1.fxml"));
        }

        // set title for the stage
        stage.setTitle("Go to profile");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void LogOut(ActionEvent event) throws Exception
    {
        if(event.getSource()==Log)
        {
            stage=(Stage) Log.getScene().getWindow();
            root=FXMLLoader.load(getClass().getResource("/FXML/LoginPage.fxml"));
        }
        stage.setTitle("Login");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    public void AddProduct(ActionEvent event) throws Exception
    {
        if(event.getSource()==Add)
        {
            nr=1;
            stage=new Stage();
            root=FXMLLoader.load(getClass().getResource("/FXML/PopUps/PopUp2.fxml"));
        }

        // set title for the stage
        stage.setTitle("Add a new product");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static int GetNr()
    {
        return nr;
    }

    @FXML
    private void initialize(){
        //MovingText();
    }

}
