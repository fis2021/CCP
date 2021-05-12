package sample.User;

public class Order {

    private String numeSeller;
    private String numeCustomer;
    private String NumeProduse;
    private int cantitate;
    private boolean delivery;
    private String status;
    private int nrInteresati;
    private int idCustomer;
    private int idComanda;
    public Order(){

    }

    public Order(String numeSeller,String numeCustomer,String numeProdus,int cantitate,boolean delivery,String status,int nrInteresati,int idCustomer,int Comanda){
        this.numeSeller=numeSeller;
        this.numeCustomer=numeCustomer;
        this.NumeProduse=numeProdus;
        this.cantitate=cantitate;
        this.delivery=delivery;
        this.status=status;
        this.nrInteresati = nrInteresati;
        this.idCustomer=idCustomer;
        this.idComanda = Comanda;
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

    public int getNrInteresati(){return nrInteresati;}

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdComanda() {
        return idComanda;
    }

    public void setNumeSeller(String numeSeller){
        this.numeSeller=numeSeller;
    }
    public void  setNumeCustomer(String numeCustomer){
        this.numeCustomer=numeCustomer;
    }
    public void setCantitate(int cant){
        this.cantitate=cant;
    }
    public void setDelivery(boolean dev){
        this.delivery=dev;
    }
    public void setNrInteresati(int nrInteresati){
        this.nrInteresati=nrInteresati;
    }

    public void setStatus(String status){
        this.status=status;
    }
}