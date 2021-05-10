package sample.User;

public class FinalStatus {

    private String numeSeller;
    private String numeCustomer;
    private String NumeProduse;
    private int cantitate;
    private boolean delivery;
    private String status;


    public FinalStatus(){

    }

    public FinalStatus(String numeSeller,String numeCustomer,String numeProdus,int cantitate,boolean delivery,String status){
        this.numeSeller=numeSeller;
        this.numeCustomer=numeCustomer;
        this.NumeProduse=numeProdus;
        this.cantitate=cantitate;
        this.delivery=delivery;
        this.status=status;
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

    public boolean getdelivery(){
        return delivery;
    }

    public String getStatus(){
        return status;
    }

    public void setNumeSeller(String numeSeller){
        this.numeSeller=numeSeller;
    }
    public void  setNumeCustomer(String numeCustomer){
        this.numeCustomer=numeCustomer;
    }
    public void setCantitate(int cant){
        cantitate=cant;
    }
    public void setDelivery(boolean dev){
        this.delivery=dev;
    }

    public void setStatus(String status){
        this.status=status;
    }

}