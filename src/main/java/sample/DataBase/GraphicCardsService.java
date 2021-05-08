package sample.DataBase;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.filters.ObjectFilters;
import sample.Categories.GraphicCards.GraphicCards;
import sample.Categories.GraphicCards.GraphicCardsBase;

import org.dizitart.no2.objects.ObjectRepository;
import sample.Categories.Processors.ProcessorsBase;
import sample.MainPage.MainPage;
import sample.MainPage.PopUp;
import sample.exceptions.ProductAlreadyExists;


import java.util.Objects;

import static sample.DataBase.FileSystemService.getPathToFile;


public class GraphicCardsService {

    private static ObjectRepository<GraphicCardsBase> GraphicCardsRepository;

    public static void initDataBaseforGraphicCards(){
        Nitrite database = Nitrite.builder()
                .filePath((getPathToFile("Graphic.db")).toFile())
                .openOrCreate("test","test");
        GraphicCardsRepository= database.getRepository(GraphicCardsBase.class);
    }

    public static void set()
    {
        for(GraphicCardsBase graphicBase : GraphicCardsRepository.find())
        {
            GraphicCards.Test1(graphicBase.getNumeProdus(),graphicBase.getPret(),graphicBase.getDescriere(),graphicBase.getTip(), graphicBase.getGarantie());
        }

    }

    public static void DeleteProduct(String numeProdus){
        for(GraphicCardsBase graphicBase : GraphicCardsRepository.find())
        {
            if(numeProdus.equals(graphicBase.getNumeProdus())){
                GraphicCardsRepository.remove(ObjectFilters.eq("numeProdus",numeProdus));
            }
        }
    }

    public static void setForDelete(){
        for(GraphicCardsBase graphicBase : GraphicCardsRepository.find()){
            if(UserService.returnId(MainPage.getUsernameFromMain())== graphicBase.getId()){
                PopUp.getDataBase(graphicBase.getNumeProdus(),graphicBase.getPret(),graphicBase.getDescriere(),graphicBase.getTip(), graphicBase.getGarantie());
            }
        }
    }

    public static void EditProduct(String numeProdus,String Pret,String Tip,String Garantie,String Descriere) {
        for(GraphicCardsBase graphicBase : GraphicCardsRepository.find())
        {
            if (numeProdus.equals(graphicBase.getNumeProdus())) {
                graphicBase.setDescriere(Descriere);
                graphicBase.setPret(Pret);
                graphicBase.setGarantie(Garantie);
                graphicBase.setTip(Tip);
                DeleteProduct(numeProdus);
                GraphicCardsRepository.insert(graphicBase);
            }
        }
    }

    public static void addGraphic(String numeProdus,String Pret,String Descriere,String Tip,String Garantie,int id,int nrinteresati) throws ProductAlreadyExists{
        CheckProductAlreadyExists(numeProdus);
        GraphicCardsRepository.insert(new GraphicCardsBase(numeProdus, Pret, Descriere, Tip, Garantie, id, nrinteresati));
    }

    private static void CheckProductAlreadyExists(String name) throws ProductAlreadyExists
    {
        for(GraphicCardsBase graphicBase:GraphicCardsRepository.find())
            {
                if(Objects.equals(name,graphicBase.getNumeProdus()))
                {
                    throw new ProductAlreadyExists(name);
                }
            }
    }

    public static void Increment(String numeProdus,String Pret,String Tip,String Garantie,String Descriere){
        for(GraphicCardsBase graphicBase:GraphicCardsRepository.find())
        {
            if(Objects.equals(numeProdus,graphicBase.getNumeProdus()))
            {
                graphicBase.IncrementInterest();
                graphicBase.setDescriere(Descriere);
                graphicBase.setPret(Pret);
                graphicBase.setGarantie(Garantie);
                graphicBase.setTip(Tip);
                DeleteProduct(numeProdus);
                GraphicCardsRepository.insert(graphicBase);


            }
        }

    }

    public static int returnId(String numeProdus){
        for(GraphicCardsBase graphicBase:GraphicCardsRepository.find())
        {
            if(Objects.equals(numeProdus,graphicBase.getNumeProdus()))
            {
               return graphicBase.getId();
            }
        }
        return -1;
    }
}
