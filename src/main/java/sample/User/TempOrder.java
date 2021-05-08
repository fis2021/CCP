package sample.User;

import java.util.ArrayList;
import java.util.List;

public class TempOrder {

    private String numeSeller;
    private String numeCustomer;
    private String NumeProduse;
    private int cantitate=1;

    public TempOrder(){

    }

    public TempOrder(String numeSeller,String numeCustomer,String numeProdus){
        this.numeSeller=numeSeller;
        this.numeCustomer=numeCustomer;
        this.NumeProduse=numeProdus;
    }

    public void setNumeProduse(String numeProduse){
        this.NumeProduse=numeProduse;
    }

    public String getNumeSeller(){
        return numeSeller;
    }

    public String getNumeCustomer(){
        return numeCustomer;
    }

    public int getCantitate(){
        return cantitate;
    }

    public String getNumeProduse(){
        return NumeProduse;
    }

    public void setNumeSeller(String numeSeller){
        this.numeSeller=numeSeller;
    }
    public void  setNumeCustomer(String numeCustomer){
        this.numeCustomer=numeCustomer;
    }
    public void setCantitate(){
        cantitate++;
    }

}
