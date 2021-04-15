package sample.MainPage;

import com.sun.jdi.event.StepEvent;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MainPage {

    @FXML
    private Text WelcomeText;
    @FXML
    private AnchorPane Anchor1;

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
    @FXML
    private void initialize(){
        //MovingText();
    }

}
