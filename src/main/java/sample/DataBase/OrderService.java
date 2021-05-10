package sample.DataBase;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import sample.User.Order;
import sample.User.User;
import sample.exceptions.UsernameAlreadyExistException;


import static sample.DataBase.FileSystemService.getPathToFile;


public class OrderService {

    private static ObjectRepository<Order> OrderRepository;

    public static void initDataBase(){
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("Order.db").toFile())
                .openOrCreate("test","test");

        OrderRepository = database.getRepository(Order.class);
    }

    public static void Order(String numProdus, String numeSeller,String numeCustomer,int cantitate,boolean delivery){
        OrderRepository.insert(new Order(numeSeller,numeCustomer,numProdus,cantitate,delivery));
    }

    public static void setDeliv(){
        for(Order order : OrderRepository.find()){
            order.setDelivery(true);
            order.setNumeSeller(order.getNumeSeller());
            order.setNumeCustomer(order.getNumeCustomer());
            order.setNumeProduse(order.getNumeProduse());
            order.setCantitate(order.getCantitate());
            OrderRepository.insert(order);
            OrderRepository.remove(ObjectFilters.eq("NumeSeller",order.getNumeSeller()));
        }
    }

}
