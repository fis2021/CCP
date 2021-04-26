package sample.DataBase;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import sample.Categories.Processors.Processors;
import sample.Categories.Sources.SourcesBase;
import sample.Categories.Sources.Sources;
import sample.Categories.Sources.SourcesBase;
import sample.MainPage.MainPage;
import sample.MainPage.PopUp;

import static sample.DataBase.FileSystemService.getPathToFile;


public class SourcesService {

    private static ObjectRepository<SourcesBase> SourcesRepository;

    public static void initDataBaseforSources(){
        Nitrite database = Nitrite.builder()
                .filePath((getPathToFile("Sources.db")).toFile())
                .openOrCreate("test","test");
        SourcesRepository= database.getRepository(SourcesBase.class);
    }

    public static void set(){
        for(SourcesBase sourcesBase : SourcesRepository.find())
        {
            Sources.SetVectori(sourcesBase.getNumeProdus(),sourcesBase.getPret(),sourcesBase.getDescriere(),sourcesBase.getTip(), sourcesBase.getGarantie());
        }
    }

    public static void setForDelete(){
        for(SourcesBase sourcesBase : SourcesRepository.find())
        {
            if(UserService.returnId(MainPage.getUsernameFromMain()) == sourcesBase.getId()){
                PopUp.getDataBase(sourcesBase.getNumeProdus(),sourcesBase.getPret(),sourcesBase.getDescriere(),sourcesBase.getTip(), sourcesBase.getGarantie());
            }
        }
    }


    public static void addSource(String numeProdus,String Pret,String Descriere,String Tip,String Garantie,int id){
        SourcesRepository.insert(new SourcesBase(numeProdus,Pret,Descriere,Tip,Garantie,id));
    }
}