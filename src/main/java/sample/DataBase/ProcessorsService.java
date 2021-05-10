package sample.DataBase;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.filters.ObjectFilters;
import sample.Categories.GraphicCards.GraphicCardsBase;
import sample.Categories.Processors.Processors;
import sample.Categories.Processors.ProcessorsBase;

import org.dizitart.no2.objects.ObjectRepository;
import sample.MainPage.MainPage;
import sample.MainPage.PopUp;
import sample.exceptions.ProductAlreadyExists;

import java.util.Objects;

import static sample.DataBase.FileSystemService.getPathToFile;


public class ProcessorsService {

    private static ObjectRepository<ProcessorsBase> ProcessorsRepository;

    public static void initDataBaseforProcessors(){
        Nitrite database = Nitrite.builder()
                .filePath((getPathToFile("Processors.db")).toFile())
                .openOrCreate("test","test");
        ProcessorsRepository= database.getRepository(ProcessorsBase.class);
    }

    public static void set()
    {
        for(ProcessorsBase processorsBase : ProcessorsRepository.find())
        {
            Processors.Test(processorsBase.getNumeProdus(),processorsBase.getPret(),processorsBase.getDescriere(),processorsBase.getTip(), processorsBase.getGarantie());
        }

    }

    public static void setForDelete(){
        for(ProcessorsBase processorsBase : ProcessorsRepository.find())
        {
            if(UserService.returnId(MainPage.getUsernameFromMain()) == processorsBase.getId()){
                PopUp.getDataBase(processorsBase.getNumeProdus(),processorsBase.getPret(),processorsBase.getDescriere(),processorsBase.getTip(), processorsBase.getGarantie());
            }
        }
    }

    public static void DeleteProduct(String numeProdus){
        for(ProcessorsBase processorsBase : ProcessorsRepository.find())
        {
            if(numeProdus.equals(processorsBase.getNumeProdus())){
                ProcessorsRepository.remove(ObjectFilters.eq("numeProdus",numeProdus));
            }
        }
    }

    public static void EditProduct(String numeProdus,String Pret,String Tip,String Garantie,String Descriere) {
        for(ProcessorsBase processorsBase : ProcessorsRepository.find())
        {
            if (numeProdus.equals(processorsBase.getNumeProdus())) {
                 processorsBase.setDescriere(Descriere);
                 processorsBase.setPret(Pret);
                 processorsBase.setGarantie(Garantie);
                 processorsBase.setTip(Tip);
                 DeleteProduct(numeProdus);
                 ProcessorsRepository.insert(processorsBase);
            }
        }
    }

    public static void addProcessors(String numeProdus,String Pret,String Descriere,String Tip,String Garantie,int id,int nr) throws ProductAlreadyExists{
        CheckProductAlreadyExists(numeProdus);
        ProcessorsRepository.insert(new ProcessorsBase(numeProdus,Pret,Descriere,Tip,Garantie,id,nr));
    }

    private static void CheckProductAlreadyExists(String name) throws ProductAlreadyExists{
        for(ProcessorsBase processorsBase:ProcessorsRepository.find())
        {
            if(Objects.equals(name,processorsBase.getNumeProdus()))
            {
                throw new ProductAlreadyExists(name);
            }
        }
    }

    public static void Increment(String numeProdus,String Pret,String Tip,String Garantie,String Descriere){
        for(ProcessorsBase processorsBase : ProcessorsRepository.find())
        {
            if (numeProdus.equals(processorsBase.getNumeProdus())) {
                processorsBase.IncrementInterest();
                processorsBase.setDescriere(Descriere);
                processorsBase.setPret(Pret);
                processorsBase.setGarantie(Garantie);
                processorsBase.setTip(Tip);
                DeleteProduct(numeProdus);
                ProcessorsRepository.insert(processorsBase);
            }
        }

    }

    public static int returnId(String numeProdus){
        for(ProcessorsBase processorsBase : ProcessorsRepository.find())
        {
            if (numeProdus.equals(processorsBase.getNumeProdus())) {
                return processorsBase.getId();
            }
        }
        return  -1;

    }

    public static int returnInteresati(String numeProdus){
        for(ProcessorsBase processorsBase : ProcessorsRepository.find())
        {
            if (numeProdus.equals(processorsBase.getNumeProdus())) {
                return processorsBase.getNrinteresati();
            }
        }
        return  -1;

    }

}
