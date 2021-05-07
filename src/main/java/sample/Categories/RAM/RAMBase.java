package sample.Categories.RAM;

public class RAMBase {

    private String numeProdus;
    private String Pret;
    private String Descriere;
    private String Tip;
    private String Garantie;
    private int id;
    private int nrinteresati;

    public RAMBase(){

    }

    public RAMBase(String numeProdus,String Pret,String Descriere,String Tip,String Garantie,int id,int nrinteresati){
        this.numeProdus=numeProdus;
        this.Pret=Pret;
        this.Descriere=Descriere;
        this.Tip=Tip;
        this.Garantie=Garantie;
        this.id=id;
        this.nrinteresati=nrinteresati;
    }

    public void IncrementInterest()
    {
        nrinteresati++;
    }

    public int getNrInteresati(){ return nrinteresati;}
    public String getDescriere() {
        return Descriere;
    }

    public String getGarantie() {
        return Garantie;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public String getPret() {
        return Pret;
    }

    public String getTip() {
        return Tip;
    }

    public int getId(){
        return id;
    }

    public void setDescriere(String Descriere) {
        this.Descriere=Descriere;
    }

    public void setGarantie(String Garantie) {
        this.Garantie=Garantie;
    }

    public void setPret(String Pret) {
        this.Pret=Pret;
    }

    public void setTip(String Tip) {
        this.Tip=Tip;
    }


    @Override
    public String toString(){
        return numeProdus + " "+ Pret + " " + Garantie + " " +Tip + " " + Descriere;
    }
}