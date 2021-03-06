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
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.DataBase.*;

import static sample.DataBase.UserService.returnRole;


public class MainPage {

    @FXML
    private Text WelcomeText,numeprod,nrinteresati,pretprod;
    @FXML
    private Button Processors,GraphicCard,RAM,Sources,ModProfile,GoProfile,Log,Add,Delete,Edit,Make,
            Accept,Status,SellerHistory,CustomerHistory;
    @FXML
    private Circle circle;
    @FXML
    private Label NotificationLabel;
    private Stage stage;
    private Parent root;
    private static int nr;
    private static String username;
    private int max=-1;
    private String goTo;
    private Integer y;

    public void verifyMostInterested(){
        int maxproc,maxGrap,maxRam,maxSource;
        maxproc= ProcessorsService.getMostInterestProduct();
        maxGrap=GraphicCardsService.getMostInterestProduct();
        maxRam=RAMService.getMostInterestProduct();
        maxSource=SourcesService.getMostInterestProduct();
        if(maxproc>maxGrap){
            max=maxproc;
            goTo = "Processors";
        }else{
            max=maxGrap;
            goTo="Graphic";
        }
        if(max<maxRam){
            max=maxRam;
            goTo="Ram";
        }
        if(max<maxSource){
            max=maxSource;
            goTo="Source";
        }
    }

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
            GraphicCardsService.set();
            stage.setTitle("GraphicCard");
        }
        if(event.getSource() == RAM){
            stage = (Stage) RAM.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/FXML/RAM.fxml"));
            RAMService.set();
            stage.setTitle("RAM");
        }
        if(event.getSource() == Sources){
            stage = (Stage) Sources.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/FXML/Sources.fxml"));
            SourcesService.set();
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
            root=FXMLLoader.load(getClass().getResource("/FXML/PopUps/PopUpForEditProfile.fxml"));
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
            root=FXMLLoader.load(getClass().getResource("/FXML/PopUps/PopUpforGoToProfile.fxml"));
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
            root=FXMLLoader.load(getClass().getResource("/FXML/PopUps/PopUpforAddProduct.fxml"));
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

    public static void usernameForMain(String nume){
        username = nume;
    }

    public static String getUsernameFromMain(){
        return username;
    }

    public void DeleteProduct(ActionEvent event)throws Exception{
        if(event.getSource() == Delete){
            nr=4;
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("/FXML/PopUps/PopUpforDeleteProduct.fxml"));
        }

        stage.setTitle("Delete a product");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void EditProduct(ActionEvent event) throws Exception{
        if(event.getSource() == Edit){
            nr=5;
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("/FXML/PopUps/PopUpforEditProduct.fxml"));
        }

        stage.setTitle("Edit the products");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void MakeOrder(ActionEvent event)throws Exception{
        if(event.getSource() == Make){
            nr=6;
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("/FXML/PopUps/PopUpforMakeOrder.fxml"));
            if(TempOrderService.VerifyIfCustomerExist(MainPage.getUsernameFromMain())){
                TempOrderService.set(MainPage.getUsernameFromMain());
            }
        }
        stage.setTitle("Make an order");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void AcceptOrder(ActionEvent event)throws Exception{
        if(event.getSource() == Accept){
            nr=7;
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("/FXML/PopUps/PopUpforAccept.fxml"));
            OrderService.setAccept(MainPage.getUsernameFromMain());
        }
        stage.setTitle("Accept/Decline order");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onStatus(ActionEvent event)throws Exception{
        if(event.getSource() == Status){
            nr=8;
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("/FXML/PopUps/PopUpforOrderStatus.fxml"));
            OrderService.getOrderStatus(MainPage.getUsernameFromMain());
        }
        stage.setTitle("Order status");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onSellerHistory(ActionEvent event)throws Exception {
        if (event.getSource() == SellerHistory) {
            nr = 9;
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("/FXML/PopUps/PopUpforSellerOrderHistory.fxml"));
            FinalStatusService.setSellerOrderHistory(MainPage.getUsernameFromMain());
        }
        stage.setTitle("Order history");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onCustomerHistory(ActionEvent event)throws Exception{
        if(event.getSource() == CustomerHistory){
            nr=10;
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("/FXML/PopUps/PopUpforCustomerHistory.fxml"));
            FinalStatusService.setCustomerOrderHistory(MainPage.getUsernameFromMain());
        }
        stage.setTitle("Order history");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void HideandShow(){
        if(returnRole(username).equals("Customer"))
        {
            Add.setVisible(false);
            Edit.setVisible(false);
            Delete.setVisible(false);
            Make.setVisible(true);
            Accept.setVisible(false);
            circle.setVisible(false);
            NotificationLabel.setVisible(false);
            Status.setVisible(true);
            SellerHistory.setVisible(false);
            CustomerHistory.setVisible(true);
        }
        else
        {
            Add.setVisible(true);
            Edit.setVisible(true);
            Delete.setVisible(true);
            Make.setVisible(false);
            Accept.setVisible(true);
            circle.setVisible(true);
            NotificationLabel.setVisible(true);
            Status.setVisible(false);
            SellerHistory.setVisible(true);
            CustomerHistory.setVisible(false);
        }
    }

    @FXML
    private void initialize(){
        //MovingText();
        verifyMostInterested();
        y=new Integer(max);
        if(goTo.equals("Processors")){
            numeprod.setText(ProcessorsService.setMostInterestedName(max));
            nrinteresati.setText(y.toString());
            pretprod.setText(ProcessorsService.setMostInterestedPret(max));
        }
        if(goTo.equals("Graphic")){
            numeprod.setText(GraphicCardsService.setMostInterestedName(max));
            nrinteresati.setText(y.toString());
            pretprod.setText(GraphicCardsService.setMostInterestedPret(max));
        }
        if(goTo.equals("Ram")){
            numeprod.setText(RAMService.setMostInterestedName(max));
            nrinteresati.setText(y.toString());
            pretprod.setText(RAMService.setMostInterestedPret(max));
        }
        if(goTo.equals("Source")){
            numeprod.setText(SourcesService.setMostInterestedName(max));
            nrinteresati.setText(y.toString());
            pretprod.setText(SourcesService.setMostInterestedPret(max));
        }
        WelcomeText.setText("Welcome "+MainPage.getUsernameFromMain() + "!");

        if(!OrderService.checkifSellerHaveOders(MainPage.getUsernameFromMain())){
            circle.setVisible(false);
            NotificationLabel.setVisible(false);
        }else{
            circle.setVisible(true);
            NotificationLabel.setVisible(true);
        }
        HideandShow();
    }

}
