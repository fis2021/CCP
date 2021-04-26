package sample.DataBase;
import org.dizitart.no2.Nitrite;
import sample.Categories.GraphicCards.GraphicCards;
import sample.Categories.GraphicCards.GraphicCardsBase;

import org.dizitart.no2.objects.ObjectRepository;
import sample.MainPage.MainPage;
import sample.MainPage.PopUp;


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

    public static void setForDelete(){
        for(GraphicCardsBase graphicBase : GraphicCardsRepository.find()){
            if(UserService.returnId(MainPage.getUsernameFromMain())== graphicBase.getId()){
                PopUp.getDataBase(graphicBase.getNumeProdus(),graphicBase.getPret(),graphicBase.getDescriere(),graphicBase.getTip(), graphicBase.getGarantie());
            }
        }
    }

    public static void addGraphic(String numeProdus,String Pret,String Descriere,String Tip,String Garantie,int id){
        GraphicCardsRepository.insert(new GraphicCardsBase(numeProdus,Pret,Descriere,Tip,Garantie,id));
    }
}
