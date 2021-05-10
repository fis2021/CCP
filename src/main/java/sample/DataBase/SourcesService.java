package sample.DataBase;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import sample.Categories.GraphicCards.GraphicCardsBase;
import sample.Categories.Processors.Processors;
import sample.Categories.Processors.ProcessorsBase;
import sample.Categories.Sources.SourcesBase;
import sample.Categories.Sources.Sources;
import sample.Categories.Sources.SourcesBase;
import sample.MainPage.MainPage;
import sample.MainPage.PopUp;
import sample.exceptions.ProductAlreadyExists;

import java.util.Objects;

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

    public static void DeleteProduct(String numeProdus){
        for(SourcesBase sourcesBase : SourcesRepository.find())
        {
            if(numeProdus.equals(sourcesBase.getNumeProdus())){
                SourcesRepository.remove(ObjectFilters.eq("numeProdus",numeProdus));
            }
        }
    }

    public static void EditProduct(String numeProdus,String Pret,String Tip,String Garantie,String Descriere) {
        for(SourcesBase sourcesBase : SourcesRepository.find())
        {
            if (numeProdus.equals(sourcesBase.getNumeProdus())) {
                sourcesBase.setDescriere(Descriere);
                sourcesBase.setPret(Pret);
                sourcesBase.setGarantie(Garantie);
                sourcesBase.setTip(Tip);
                DeleteProduct(numeProdus);
                SourcesRepository.insert(sourcesBase);
            }
        }
    }


    public static void addSource(String numeProdus,String Pret,String Descriere,String Tip,String Garantie,int id,int nrinteresati) throws ProductAlreadyExists{
        CheckProductAlreadyExists(numeProdus);
        SourcesRepository.insert(new SourcesBase(numeProdus,Pret,Descriere,Tip,Garantie,id,nrinteresati));
    }

    private static void CheckProductAlreadyExists(String name) throws ProductAlreadyExists {
        for(SourcesBase sourcesBase:SourcesRepository.find())
        {
            if(Objects.equals(name,sourcesBase.getNumeProdus()))
            {
                throw new ProductAlreadyExists(name);
            }
        }
    }

    public static void Increment(String numeProdus,String Pret,String Tip,String Garantie,String Descriere){
        for(SourcesBase sourcesBase : SourcesRepository.find())
        {
            if (numeProdus.equals(sourcesBase.getNumeProdus())) {
                sourcesBase.IncrementInterest();
                sourcesBase.setDescriere(Descriere);
                sourcesBase.setPret(Pret);
                sourcesBase.setGarantie(Garantie);
                sourcesBase.setTip(Tip);
                DeleteProduct(numeProdus);
                SourcesRepository.insert(sourcesBase);
            }
        }
    }

    public static int returnId(String numeProdus){
        for(SourcesBase sourcesBase : SourcesRepository.find())
        {
            if (numeProdus.equals(sourcesBase.getNumeProdus())) {
                return sourcesBase.getId();
            }
        }
        return  -1;

    }

    public static int returnInteresati(String numeProdus){
        for(SourcesBase sourcesBase : SourcesRepository.find())
        {
            if (numeProdus.equals(sourcesBase.getNumeProdus())) {
                return sourcesBase.getNrInteresati();
            }
        }
        return  -1;

    }

}