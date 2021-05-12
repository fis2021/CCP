package sample.DataBase;

import javafx.scene.text.Text;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.filters.Filters;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import sample.MainPage.PopUpOrder;
import sample.User.Order;
import sample.User.TempOrder;
import sample.exceptions.UsernameAlreadyExistException;

import java.util.List;

import java.util.Random;

import static sample.DataBase.FileSystemService.getPathToFile;

public class TempOrderService {

    private static ObjectRepository<TempOrder> TempOrderRepository;

    public static void initDataBase(){
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("TempOrder.db").toFile())
                .openOrCreate("test","test");

        TempOrderRepository = database.getRepository(TempOrder.class);
    }

    public static void addOrder(String numeSeller,String numeCustomer, String numeProdus, int interesati,int idcustomer){
        TempOrderRepository.insert(new TempOrder(numeSeller,numeCustomer,numeProdus,interesati,idcustomer));
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
        Random rand = new Random();
        for(TempOrder tempOrder : TempOrderRepository.find()){
            int rand_int1 = rand.nextInt(1000);
            if(!UserService.verifyId(rand_int1)){
                rand_int1 = rand.nextInt(1000);
            }
            OrderService.Order(tempOrder.getNumeProduse(),tempOrder.getNumeSeller(),tempOrder.getNumeCustomer(),tempOrder.getCantitate(),delivery,"It is processing", tempOrder.getNrinteresati(), tempOrder.getIdCustomer(),rand_int1);
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
