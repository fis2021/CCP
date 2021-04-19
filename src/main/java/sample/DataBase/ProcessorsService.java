package sample.DataBase;
import org.dizitart.no2.Nitrite;
import sample.Categories.Processors.ProcessorsBase;

import org.dizitart.no2.objects.ObjectRepository;

import static sample.DataBase.FileSystemService.getPathToFile;


public class ProcessorsService {

    private static ObjectRepository<ProcessorsBase> ProcessorsRepository;

    public static void initDataBaseforProcessors(){
        Nitrite database = Nitrite.builder()
                .filePath((getPathToFile("Processors.db")).toFile())
                .openOrCreate("test","test");
    }

    public static void addProcessors(String numeProdus,String Pret,String Descriere,String Tip,String Garantie){
        ProcessorsRepository.insert(new ProcessorsBase(numeProdus,Pret,Descriere,Tip,Garantie));
    }
}
