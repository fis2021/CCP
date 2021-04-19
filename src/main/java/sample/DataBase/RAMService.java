package sample.DataBase;

import org.dizitart.no2.Nitrite;
import sample.Categories.Processors.ProcessorsBase;

import org.dizitart.no2.objects.ObjectRepository;

import static sample.DataBase.FileSystemService.getPathToFile;


public class RAMService {

    private static ObjectRepository<ProcessorsBase> RAMRepository;

    public static void initDataBaseforRAM(){
        Nitrite database = Nitrite.builder()
                .filePath((getPathToFile("Processors.db")).toFile())
                .openOrCreate("test","test");
    }

    public static void addRAM(String numeProdus,String Pret,String Descriere,String Tip,String Garantie){
        RAMRepository.insert(new ProcessorsBase(numeProdus,Pret,Descriere,Tip,Garantie));
    }
}
