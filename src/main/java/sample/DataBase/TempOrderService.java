package sample.DataBase;

import javafx.scene.text.Text;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.filters.Filters;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import sample.MainPage.PopUpOrder;
import sample.User.TempOrder;
import sample.exceptions.UsernameAlreadyExistException;

import java.util.List;

import static sample.DataBase.FileSystemService.getPathToFile;

public class TempOrderService {

    private static ObjectRepository<TempOrder> TempOrderRepository;

    public static void initDataBase(){
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("TempOrder.db").toFile())
                .openOrCreate("test","test");

        TempOrderRepository = database.getRepository(TempOrder.class);
    }

    public static void addOrder(String numeSeller,String numeCustomer, String numeProdus){
        TempOrderRepository.insert(new TempOrder(numeSeller,numeCustomer,numeProdus));
    }

    public static void Delete(String numeProdus){
        for(TempOrder tempOrder : TempOrderRepository.find()){
            if(tempOrder.getNumeProduse().equals(numeProdus)){
                TempOrderRepository.remove(ObjectFilters.eq("NumeProduse",numeProdus));

            }
        }
    }

    public static void set(){
        for(TempOrder tempOrder : TempOrderRepository.find()){
            Integer x = new Integer(tempOrder.getCantitate());
            PopUpOrder.Test(tempOrder.getNumeProduse(),x.toString());
        }
    }

    public static boolean verify(String numeProdus,String numeSeller,String numeCustomer){
        for(TempOrder tempOrder : TempOrderRepository.find()){
            if(tempOrder.getNumeProduse().equals(numeProdus)){
                tempOrder.setCantitate();
                tempOrder.setNumeCustomer(numeCustomer);
                tempOrder.setNumeSeller(numeSeller);
                Delete(numeProdus);
                TempOrderRepository.insert(tempOrder);
                return true;
            }
        }
        return false;
    }

    public static void DeleteAllDatabase(List<Text> nume){
        for(TempOrder tempOrder : TempOrderRepository.find()){
            for(int i=0; i<10; i++){
                if(nume.get(i).getText().equals(tempOrder.getNumeProduse())){
                    TempOrderRepository.remove(ObjectFilters.ALL);

                }
            }
        }
    }

    public static void SetNewDataBase(){
        for(TempOrder tempOrder : TempOrderRepository.find()){
            OrderService.Order(tempOrder.getNumeSeller(),tempOrder.getNumeCustomer(),tempOrder.getNumeProduse());
        }
    }

}
