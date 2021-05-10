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

    public static void set(String numeCustomer){
        for(TempOrder tempOrder : TempOrderRepository.find()){
            if(tempOrder.getNumeCustomer().equals(numeCustomer)){
                Integer x = new Integer(tempOrder.getCantitate());
                PopUpOrder.Test(tempOrder.getNumeProduse(),x.toString());
            }
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

    public static void DeleteAllDatabase(String numeCustomer){
        for(TempOrder tempOrder : TempOrderRepository.find()){
                TempOrderRepository.remove(ObjectFilters.eq("numeCustomer",numeCustomer));

        }
    }

    public static void SetNewDataBase(boolean delivery){
        for(TempOrder tempOrder : TempOrderRepository.find()){
            OrderService.Order(tempOrder.getNumeProduse(),tempOrder.getNumeSeller(),tempOrder.getNumeCustomer(),tempOrder.getCantitate(),delivery,"It is processing");
        }
    }

    public static boolean VerifyIfCustomerExist(String numeCustomer){
        for(TempOrder tempOrder : TempOrderRepository.find()){
            if(numeCustomer.equals(tempOrder.getNumeCustomer())){
                return true;
            }
        }
        return false;
    }

}
