package sample.DataBase;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import sample.Categories.Sources.SourcesBase;

import static sample.DataBase.FileSystemService.getPathToFile;


public class SourcesService {

    private static ObjectRepository<SourcesBase> ProcessorsRepository;

    public static void initDataBaseforGraphicCards(){
        Nitrite database = Nitrite.builder()
                .filePath((getPathToFile("Sources.db")).toFile())
                .openOrCreate("test","test");
    }

    public static void addGraphic(String numeProdus,String Pret,String Descriere,String Tip,String Garantie){
        ProcessorsRepository.insert(new SourcesBase(numeProdus,Pret,Descriere,Tip,Garantie));
    }
}