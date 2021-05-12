package sample.User;

import java.util.ArrayList;
import java.util.List;

public class TempOrder {

    private String numeSeller;
    private String numeCustomer;
    private String NumeProduse;
    private int cantitate=1,nrinteresati;
    private int idCustomer;
    public TempOrder(){

    }

    public TempOrder(String numeSeller,String numeCustomer,String numeProdus,int nrinteresati,int idCustomer){
        this.numeSeller=numeSeller;
        this.numeCustomer=numeCustomer;
        this.NumeProduse=numeProdus;
        this.nrinteresati=nrinteresati;
        this.idCustomer=idCustomer;
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

    public int getNrinteresati() {return nrinteresati;}

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setNumeSeller(String numeSeller){
        this.numeSeller=numeSeller;
    }
    public void setNumeCustomer(String numeCustomer){
        this.numeCustomer=numeCustomer;
    }
    public void setCantitate(){
        cantitate++;
    }
    public void setNrinteresati(int nrinteresati) {this.nrinteresati=nrinteresati;}


}
