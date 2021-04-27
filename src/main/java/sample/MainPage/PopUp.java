package sample.MainPage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Categories.GraphicCards.GraphicCards;
import sample.Categories.RAM.RAM;
import sample.Categories.Sources.Sources;
import sample.DataBase.*;
import sample.MainPage.MainPage;
import sample.Categories.Processors.Processors;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PopUp {
    @FXML
    private ComboBox comboBox1,comboBox2,comboBox3;
    private static int kp=0,kr=0,kg=0,ks=0;
    private static String nume;

    @FXML
    private TextField numeprodus,descriere,tip,pret,garantie;
    private Stage stage=new Stage();

    @FXML
    private Button addit,CloseWindow,CloseWindow1;
    private Alert alert = new Alert(Alert.AlertType.ERROR);

    @FXML
    private Pane PaneProcessors,PaneGraphic,PaneRAM,PaneSources;
    private static VBox vBox = new VBox();
    private static List<Button> buttons = new ArrayList<Button>();
    private static Pane[] panes = new Pane[10];
    private final  static Text[] numeProduse = new Text[10];
    private static Text[] Pret= new Text[10];
    private static Text[] Descriere=new Text[10];
    private static Text[] Tip=new Text[10];
    private static Text[] Garantie=new Text[10];

    @FXML
    private void initialize(){
        if(MainPage.GetNr()==1) {
            comboBox1.getItems().add("Processors");
            comboBox1.getItems().add("RAM");
            comboBox1.getItems().add("Graphic Cards");
            comboBox1.getItems().add("Power Supply Unit");
        }
        if(MainPage.GetNr()==4){
            comboBox2.getItems().add("Processors");
            comboBox2.getItems().add("RAM");
            comboBox2.getItems().add("Graphic Cards");
            comboBox2.getItems().add("Power Supply Unit");
            //init();
        }
        if(MainPage.GetNr()==5) {
            comboBox3.getItems().add("Processors");
            comboBox3.getItems().add("RAM");
            comboBox3.getItems().add("Graphic Cards");
            comboBox3.getItems().add("Power Supply Unit");
        }
    }

    public static String returnNume()
    {
        return nume;
    }


    public void addProduct(ActionEvent event)
    {

        if(numeprodus.getText().isEmpty() || pret.getText().isEmpty() || descriere.getText().isEmpty() || tip.getText().isEmpty() || garantie.getText().isEmpty()){
            alert.setTitle("FIELD ARE EMPTY");
            alert.setHeaderText((String) null);
            alert.setContentText("Please complete all the fields!");
            alert.showAndWait();
        }else{
            if(comboBox1.getSelectionModel().getSelectedItem().toString().equals("Processors"))
            {
                ProcessorsService.addProcessors(numeprodus.getText(),pret.getText(),descriere.getText(),tip.getText(),garantie.getText(),UserService.returnId(MainPage.getUsernameFromMain()));
                kp++;
            }
            if(comboBox1.getSelectionModel().getSelectedItem().toString().equals("Graphic Cards"))
            {
                GraphicCardsService.addGraphic(numeprodus.getText(), pret.getText(), descriere.getText(), tip.getText(), garantie.getText(), UserService.returnId(MainPage.getUsernameFromMain()));
                kg++;
            }
            if(comboBox1.getSelectionModel().getSelectedItem().toString().equals("RAM")){
                RAMService.addRAM(numeprodus.getText(), pret.getText(), descriere.getText(), tip.getText(), garantie.getText(), UserService.returnId(MainPage.getUsernameFromMain()));
                kr++;
            }
            if(comboBox1.getSelectionModel().getSelectedItem().equals("Power Supply Unit")){
                SourcesService.addSource(numeprodus.getText(), pret.getText(), descriere.getText(), tip.getText(), garantie.getText(), UserService.returnId(MainPage.getUsernameFromMain()));
                kp++;
            }
            stage=(Stage) addit.getScene().getWindow();
            stage.close();
        }
    }

    public static void getDataBase(String nume,String descriere,String pret,String tip,String garantie){
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
            buttons.add(i,new Button("Delete"));
            buttons.get(i).setLayoutX(620);
            panes[i]=new Pane();
            panes[i].setLayoutX(700);
            panes[i].setLayoutY(50);
            panes[i].getChildren().addAll(numeProduse[i],Pret[i],Descriere[i],Tip[i],Garantie[i], buttons.get(i));
        }
        vBox.getChildren().add(panes[PopUp.GetKP()]);
    }

    private void initVBOX(){
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.setSpacing(50);
    }



    private void init()
    {
        vBox=new VBox();
        initVBOX();
        if(comboBox2.getSelectionModel().getSelectedItem().toString().equals("Processors")){
            PaneProcessors.getChildren().add(vBox);
            PaneProcessors.setVisible(true);
            PaneGraphic.setVisible(false);
            PaneRAM.setVisible(false);
            PaneSources.setVisible(false);
        }
        if(comboBox2.getSelectionModel().getSelectedItem().toString().equals("Graphic Cards")){
            PaneProcessors.setVisible(false);
            PaneGraphic.getChildren().add(vBox);
            PaneGraphic.setVisible(true);
            PaneRAM.setVisible(false);
            PaneSources.setVisible(false);

        }
        if(comboBox2.getSelectionModel().getSelectedItem().toString().equals("RAM")){
            PaneProcessors.setVisible(false);
            PaneGraphic.setVisible(false);
            PaneRAM.getChildren().add(vBox);
            PaneRAM.setVisible(true);
            PaneSources.setVisible(false);
        }
        if(comboBox2.getSelectionModel().getSelectedItem().toString().equals("Power Supply Unit")){
            PaneProcessors.setVisible(false);
            PaneGraphic.setVisible(false);
            PaneRAM.setVisible(false);
            PaneSources.getChildren().add(vBox);
            PaneSources.setVisible(true);
        }
    }


    public void initDeletePage(ActionEvent event){
        init();
        if(comboBox2.getSelectionModel().getSelectedItem().toString().equals("Processors")){
            ProcessorsService.setForDelete();
            for(int i=0; i<buttons.size(); i++){
                final int nr=i;
                buttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        for(int j=0; j<numeProduse.length; j++){
                            if(nr == j){
                                ProcessorsService.DeleteProduct(numeProduse[j].getText());
                            }
                        }
                    }
                });
            }
        }
        if(comboBox2.getSelectionModel().getSelectedItem().toString().equals("Graphic Cards")){
            GraphicCardsService.setForDelete();
            for(int i=0; i<buttons.size(); i++){
                final int nr=i;
                buttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        for(int j=0; j<numeProduse.length; j++){
                            if(nr == j){
                                GraphicCardsService.DeleteProduct(numeProduse[j].getText());
                            }
                        }
                    }
                });
            }

        }
        if(comboBox2.getSelectionModel().getSelectedItem().toString().equals("RAM")){
            RAMService.setForDelete();
            for(int i=0; i<buttons.size(); i++){
                final int nr=i;
                buttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        for(int j=0; j<numeProduse.length; j++){
                            if(nr == j){
                                RAMService.DeleteProduct(numeProduse[j].getText());
                            }
                        }
                    }
                });
            }
        }
        if(comboBox2.getSelectionModel().getSelectedItem().toString().equals("Power Supply Unit")){
            SourcesService.setForDelete();
            for(int i=0; i<buttons.size(); i++){
                final int nr=i;
                buttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        for(int j=0; j<numeProduse.length; j++){
                            if(nr == j){
                                SourcesService.DeleteProduct(numeProduse[j].getText());
                            }
                        }
                    }
                });
            }
        }
    }

    public void CloseDeleteWindow(ActionEvent event){
        stage=(Stage) CloseWindow.getScene().getWindow();
        stage.close();
    }

    public void CloseEditWindow(ActionEvent event){
        stage=(Stage) CloseWindow1.getScene().getWindow();
        stage.close();
    }

    public static int GetKP()
    {
        return kp;
    }
    public static int GetKS()
    {
        return ks;
    }
    public static int GetKG()
    {
        return kg;
    }
    public static int GetKR()
    {
        return kr;
    }

}
