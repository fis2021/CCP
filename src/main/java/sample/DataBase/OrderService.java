package sample.DataBase;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
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

    public static void Order(String numProdus, String numeSeller,String numeCustomer){
        OrderRepository.insert(new Order(numeSeller,numeCustomer,numProdus));
    }


}
