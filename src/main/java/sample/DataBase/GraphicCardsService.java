package sample.DataBase;
import org.dizitart.no2.Nitrite;
import sample.Categories.GraphicCards.GraphicCards;
import sample.Categories.GraphicCards.GraphicCardsBase;

import org.dizitart.no2.objects.ObjectRepository;

import static sample.DataBase.FileSystemService.getPathToFile;


public class GraphicCardsService {

    private static ObjectRepository<GraphicCardsBase> ProcessorsRepository;

    public static void initDataBaseforGraphicCards(){
        Nitrite database = Nitrite.builder()
                .filePath((getPathToFile("Graphic.db")).toFile())
                .openOrCreate("test","test");
    }

    public static void addGraphic(String numeProdus,String Pret,String Descriere,String Tip,String Garantie){
        ProcessorsRepository.insert(new GraphicCardsBase(numeProdus,Pret,Descriere,Tip,Garantie));
    }
}
