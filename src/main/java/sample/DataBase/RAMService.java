package sample.DataBase;

import org.dizitart.no2.Nitrite;

import org.dizitart.no2.objects.ObjectRepository;
import sample.Categories.RAM.RAM;
import sample.Categories.RAM.RAMBase;
import sample.Categories.Sources.SourcesBase;

import static sample.DataBase.FileSystemService.getPathToFile;


public class RAMService {

    private static ObjectRepository<RAMBase> RAMRepository;

    public static void initDataBaseforRAM(){
        Nitrite database = Nitrite.builder()
                .filePath((getPathToFile("RAM.db")).toFile())
                .openOrCreate("test","test");
        RAMRepository= database.getRepository(RAMBase.class);
    }

    public static void addRAM(String numeProdus,String Pret,String Descriere,String Tip,String Garantie,int id){
        RAMRepository.insert(new RAMBase(numeProdus,Pret,Descriere,Tip,Garantie,id));
    }

    public static void set(){
        for(RAMBase ramBase : RAMRepository.find()){
            RAM.setareVectori(ramBase.getNumeProdus(),ramBase.getPret(),ramBase.getDescriere(),ramBase.getTip(), ramBase.getGarantie());
        }
    }

}

