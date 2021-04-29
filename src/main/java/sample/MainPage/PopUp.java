package sample.MainPage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import sample.exceptions.ProductAlreadyExists;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PopUp {
    @FXML
    private ComboBox comboBox1,comboBox2,comboBox3;
    private static int kp=0,kr=0,kg=0,ks=0,count=0;
    private static String nume;

    @FXML
    private TextField numeprodus,descriere,tip,pret,garantie;
    private Stage stage=new Stage();
    private Parent root;

    @FXML
    private Button addit,CloseWindow,CloseWindow1;
    private Alert alert = new Alert(Alert.AlertType.ERROR);

    @FXML
    private Pane PaneProcessors,PaneGraphic,PaneRAM,PaneSources,PaneProcessors1,PaneGraphic1,PaneRAM1,PaneSources1;
    private static VBox vBox = new VBox();
    private static List<Button> buttons = new ArrayList<Button>();
    private static Pane[] panes = new Pane[10];
    private static List<Text> numeProduse = new ArrayList<Text>(10);
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


    public void addProduct(ActionEvent event) throws Exception
    {
        try {
            if (numeprodus.getText().isEmpty() || pret.getText().isEmpty() || descriere.getText().isEmpty() || tip.getText().isEmpty() || garantie.getText().isEmpty()) {
                alert.setTitle("FIELD ARE EMPTY");
                alert.setHeaderText((String) null);
                alert.setContentText("Please complete all the fields!");
                alert.showAndWait();
            } else {
                if (comboBox1.getSelectionModel().getSelectedItem().toString().equals("Processors")) {
                    ProcessorsService.addProcessors(numeprodus.getText(), pret.getText(), descriere.getText(), tip.getText(), garantie.getText(), UserService.returnId(MainPage.getUsernameFromMain()));
                    kp++;
                }
                if (comboBox1.getSelectionModel().getSelectedItem().toString().equals("Graphic Cards")) {
                    GraphicCardsService.addGraphic(numeprodus.getText(), pret.getText(), descriere.getText(), tip.getText(), garantie.getText(), UserService.returnId(MainPage.getUsernameFromMain()));
                    kg++;
                }
                if (comboBox1.getSelectionModel().getSelectedItem().toString().equals("RAM")) {
                    RAMService.addRAM(numeprodus.getText(), pret.getText(), descriere.getText(), tip.getText(), garantie.getText(), UserService.returnId(MainPage.getUsernameFromMain()));
                    kr++;
                }
                if (comboBox1.getSelectionModel().getSelectedItem().equals("Power Supply Unit")) {
                    SourcesService.addSource(numeprodus.getText(), pret.getText(), descriere.getText(), tip.getText(), garantie.getText(), UserService.returnId(MainPage.getUsernameFromMain()));
                    kp++;
                }
                stage = (Stage) addit.getScene().getWindow();
                stage.close();
            }
        }catch (ProductAlreadyExists e)
        {
            alert.setTitle("The product "+e.getMessage()+" already on stock");
            alert.setHeaderText((String) null);
            alert.setContentText("Please choose another name for your product");
            alert.showAndWait();
        }
    }

    public static void getDataBase(String nume,String descriere,String pret,String tip,String garantie){
        if(MainPage.GetNr()==4)
        {
            for(int i=0;i<10;i++)
            {
                buttons.add(i,new Button("Delete"));
                buttons.get(i).setLayoutX(620);
                buttons.get(i).setId("#" + i);
            }
        }
        else
            if(MainPage.GetNr()==5)
            {
                for(int i=0;i<10;i++)
                {
                    buttons.add(i,new Button("Edit"));
                    buttons.get(i).setLayoutX(620);
                    buttons.get(i).setId("#" + i);
                }
            }
        for(int i=0;i<10;i++)
        {
            numeProduse.add(i,new Text(nume));
            numeProduse.get(i).setLayoutX(0);;
            numeProduse.get(i).setLayoutY(3);;
            Pret[i] = new Text(pret);
            Descriere[i] = new Text(descriere);
            Tip[i] = new Text(tip);
            Garantie[i] = new Text(garantie);
            Pret[i].setLayoutX(100);
            Pret[i].setLayoutY(3);
            Descriere[i].setLayoutX(200);
            Descriere[i].setLayoutY(25);
            Tip[i].setLayoutX(300);
            Tip[i].setLayoutY(3);
            Garantie[i].setLayoutX(400);
            Garantie[i].setLayoutY(3);
            panes[i]=new Pane();
            panes[i].setLayoutX(700);
            panes[i].setLayoutY(50);
            panes[i].getChildren().addAll(numeProduse.get(i),Pret[i],Descriere[i],Tip[i],Garantie[i], buttons.get(i));
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

    private void initforEdit()
    {
        vBox=new VBox();
        initVBOX();
        if(comboBox3.getSelectionModel().getSelectedItem().toString().equals("Processors")){
            PaneProcessors1.getChildren().add(vBox);
            PaneProcessors1.setVisible(true);
            PaneGraphic1.setVisible(false);
            PaneRAM1.setVisible(false);
            PaneSources1.setVisible(false);
        }
        if(comboBox3.getSelectionModel().getSelectedItem().toString().equals("Graphic Cards")){
            PaneProcessors1.setVisible(false);
            PaneGraphic1.getChildren().add(vBox);
            PaneGraphic1.setVisible(true);
            PaneRAM1.setVisible(false);
            PaneSources1.setVisible(false);

        }
        if(comboBox3.getSelectionModel().getSelectedItem().toString().equals("RAM")){
            PaneProcessors1.setVisible(false);
            PaneGraphic1.setVisible(false);
            PaneRAM1.getChildren().add(vBox);
            PaneRAM1.setVisible(true);
            PaneSources1.setVisible(false);
        }
        if(comboBox3.getSelectionModel().getSelectedItem().toString().equals("Power Supply Unit")){
            PaneProcessors1.setVisible(false);
            PaneGraphic1.setVisible(false);
            PaneRAM1.setVisible(false);
            PaneSources1.getChildren().add(vBox);
            PaneSources1.setVisible(true);
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
                        for(int j=0; j<numeProduse.size(); j++){
                            if(nr == j){
                                ProcessorsService.DeleteProduct(numeProduse.get(j).getText());
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
                        for(int j=0; j<numeProduse.size(); j++){
                            if(nr == j){
                                GraphicCardsService.DeleteProduct(numeProduse.get(j).getText());
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
                        for(int j=0; j<numeProduse.size(); j++){
                            if(nr == j){
                                RAMService.DeleteProduct(numeProduse.get(j).getText());
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
                        for(int j=0; j<numeProduse.size(); j++){
                            if(nr == j){
                                SourcesService.DeleteProduct(numeProduse.get(j).getText());
                            }
                        }
                    }
                });
            }
        }
    }

    public void initEditPage(ActionEvent event)
    {
        initforEdit();
        if(comboBox3.getSelectionModel().getSelectedItem().toString().equals("Processors")) {
            count=1;
            ProcessorsService.setForDelete();
            for (int i = 0; i < buttons.size(); i++) {
                final int nr = i;
                buttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event){
                        for(int j=0;j<numeProduse.size();j++){
                            if(nr==j){
                                try {
                                    nume=numeProduse.get(nr).getText();
                                    Stage stage1=new Stage();
                                    root= FXMLLoader.load(getClass().getResource("/FXML/PopUps/PopUpEditProductDetails.fxml"));
                                    Scene scene=new Scene(root);
                                    stage1.setScene(scene);
                                    stage1.setTitle("Edit this product");
                                    stage1.show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
            }

        }
        if(comboBox3.getSelectionModel().getSelectedItem().toString().equals("RAM")) {
            count=2;
            RAMService.setForDelete();
            for (int i = 0; i < buttons.size(); i++) {
                final int nr = i;
                buttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event){
                        for(int j=0;j<numeProduse.size();j++){
                            if(nr==j){
                                try {
                                    nume=numeProduse.get(nr).getText();
                                    Stage stage1=new Stage();
                                    root= FXMLLoader.load(getClass().getResource("/FXML/PopUps/PopUpEditProductDetails.fxml"));
                                    Scene scene=new Scene(root);
                                    stage1.setScene(scene);
                                    stage1.setTitle("Edit this product");
                                    stage1.show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
            }

        }
        if(comboBox3.getSelectionModel().getSelectedItem().toString().equals("Graphic Cards")) {
            count=3;
        GraphicCardsService.setForDelete();
        for (int i = 0; i < buttons.size(); i++) {
            final int nr = i;
            buttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    for(int j=0;j<numeProduse.size();j++){
                        if(nr==j){
                            try {
                                nume=numeProduse.get(nr).getText();
                                Stage stage1=new Stage();
                                root= FXMLLoader.load(getClass().getResource("/FXML/PopUps/PopUpEditProductDetails.fxml"));
                                Scene scene=new Scene(root);
                                stage1.setScene(scene);
                                stage1.setTitle("Edit this product");
                                stage1.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }


    }
        if(comboBox3.getSelectionModel().getSelectedItem().toString().equals("Power Supply Unit")) {
            count=4;
            SourcesService.setForDelete();
            for (int i = 0; i < buttons.size(); i++) {
                final int nr = i;
                buttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event){
                        for(int j=0;j<numeProduse.size();j++){
                            if(nr==j){
                                try {
                                    nume=numeProduse.get(nr).getText();
                                    Stage stage1=new Stage();
                                    root= FXMLLoader.load(getClass().getResource("/FXML/PopUps/PopUpEditProductDetails.fxml"));
                                    Scene scene=new Scene(root);
                                    stage1.setScene(scene);
                                    stage1.setTitle("Edit this product");
                                    stage1.show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
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

    public static int GetCount() {
        return count;
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
