package sample.DataBase;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import sample.MainPage.PopUpSellerHistory;
import sample.User.FinalStatus;


import static sample.DataBase.FileSystemService.getPathToFile;

public class FinalStatusService {
    private static ObjectRepository<FinalStatus> FinalRepository;

    public static void initDataBase(){
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("Final.db").toFile())
                .openOrCreate("test","test");

        FinalRepository = database.getRepository(FinalStatus.class);
    }

    public static void FinalOrder(String numProdus, String numeSeller,String numeCustomer,int cantitate,boolean delivery,String status,int interesati){
        FinalRepository.insert(new FinalStatus(numeSeller,numeCustomer,numProdus,cantitate,delivery,status,interesati));
    }

    public static void setSellerOrderHistory(String numeSeller)
    {
        for(FinalStatus status: FinalRepository.find()){
            if(status.getNumeSeller().equals(numeSeller))
            {
                Integer i=new Integer(status.getCantitate());
                Integer j=new Integer(status.getInteresati());
                PopUpSellerHistory.getOrderHistory(status.getNumeProduse(),status.getNumeCustomer(),i.toString(), status.getStatus(),j.toString());
            }
        }
    }
}
