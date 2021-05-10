package sample.DataBase;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
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

    public static void FinalOrder(String numProdus, String numeSeller,String numeCustomer,int cantitate,boolean delivery,String status){
        FinalRepository.insert(new FinalStatus(numeSeller,numeCustomer,numProdus,cantitate,delivery,status));
    }
}
