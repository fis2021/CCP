package sample.DataBase;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import sample.Categories.GraphicCards.GraphicCardsBase;
import sample.MainPage.MainPage;
import sample.MainPage.PopUpAccept;
import sample.MainPage.PopUpStatus;
import sample.User.Order;
import sample.User.TempOrder;
import sample.User.User;
import sample.exceptions.UsernameAlreadyExistException;


import java.util.List;

import static sample.DataBase.FileSystemService.getPathToFile;


public class OrderService {

    private static ObjectRepository<Order> OrderRepository;
    private static Nitrite database;
    private static int i=0;

    public static void initDataBase(){
        FileSystemService.initDirectory();
        database = Nitrite.builder()
                .filePath(getPathToFile("Order.db").toFile())
                .openOrCreate("test", "test");

        OrderRepository = database.getRepository(Order.class);
    }

    public static void closeDataBase(){
        database.close();
    }

    public static void Order(String numProdus, String numeSeller,String numeCustomer,int cantitate,boolean delivery,String status,int nrinteresati,int idCustomer,int comanda){
        OrderRepository.insert(new Order(numeSeller,numeCustomer,numProdus,cantitate,delivery,status,nrinteresati,idCustomer,comanda));
    }

    public static List<Order> getAllOrders() {
        return OrderRepository.find().toList();
    }

    public static void setAccept(String numeSeller){
        for(Order order : OrderRepository.find()){
            if(numeSeller.equals(order.getNumeSeller()) && order.getStatus().equals("It is processing")){
                i++;
                Integer y = new Integer(order.getCantitate());
                PopUpAccept.Test(order.getNumeProduse(),order.getCantitate(),"b"+i,"d"+i);
            }
        }
    }

    public static boolean checkifSellerHaveOders(String numeSeller){
        for (Order order : OrderRepository.find()){
            if(order.getNumeSeller().equals(numeSeller) && order.getStatus().equals("It is processing")){
                return true;
            }
        }
        return false;
    }

    public static void SetStatusOrder(String nume,String status){
        for(Order order : OrderRepository.find()){
            if(order.getNumeProduse().equals(nume) && order.getStatus().equals("It is processing")){
                order.setStatus(status);
                order.setDelivery(order.getdelivery());
                order.setNumeSeller(order.getNumeSeller());
                order.setNumeCustomer(order.getNumeCustomer());
                order.setNumeProduse(order.getNumeProduse());
                order.setCantitate(order.getCantitate());
                order.setIdCustomer(order.getIdCustomer());
                OrderRepository.remove(ObjectFilters.eq("idComanda",order.getIdComanda()));
                OrderRepository.insert(order);
            }
        }
    }

    public static void getOrderStatus(String nume)
    {
        for(Order order : OrderRepository.find()){
            if(order.getNumeCustomer().equals(nume)){
                Integer y=new Integer(order.getCantitate());
                PopUpStatus.getStatus(order.getNumeProduse(),y.toString(),order.getStatus());

            }
        }

    }

    public static void ReplaceData(){
        for(Order order : OrderRepository.find()){
            if(order.getNumeCustomer().equals(MainPage.getUsernameFromMain())){
                if(order.getStatus().equals("Accepted") || order.getStatus().equals("Declined"))
                {
                    FinalStatusService.FinalOrder(order.getNumeProduse(),order.getNumeSeller(),order.getNumeCustomer(),order.getCantitate(), order.getdelivery(), order.getStatus(),order.getNrInteresati());
                }
            }
        }
    }

    public static void DeleteOrder(String nume){
        OrderRepository.remove(ObjectFilters.eq("status","Accepted"));
        OrderRepository.remove(ObjectFilters.eq("status","Declined"));
    }

}