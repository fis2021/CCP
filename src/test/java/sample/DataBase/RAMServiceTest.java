package sample.DataBase;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.Categories.RAM.RAMBase;
import sample.exceptions.ProductAlreadyExists;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RAMServiceTest {

    private static final String numeProdus = "RAM";
    private static final String Pret = "300";
    private static final String Descriere = "Produs nou";
    private static final String Tip = "Corsair";
    private static final String Garantie = "3 ani";
    private static final int idProdus = 4;
    private static final int Interesati = 5;

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        RAMService.initDataBaseforRAM();
    }

    @Test
    void testDatabaseIsInitializedAndNoUserIsPersisted() {
        assertThat(RAMService.getAllProduct()).isNotNull();
        assertThat(RAMService.getAllProduct()).isEmpty();
    }

    @AfterEach
    void tearDown() {
        RAMService.closeDataBase();
    }

    @Test
    void testIfProductIsAddedCorectly() throws ProductAlreadyExists{
        RAMService.addRAM(numeProdus,Pret,Descriere,Tip,Garantie,idProdus,Interesati);
        assertThat(RAMService.getAllProduct()).isNotEmpty();
        assertThat(RAMService.getAllProduct()).size().isEqualTo(1);
        RAMBase ramBase = RAMService.getAllProduct().get(0);
        assertThat(ramBase).isNotNull();
        assertThat(ramBase.getDescriere()).isEqualTo(Descriere);
        assertThat(ramBase.getNumeProdus()).isEqualTo(numeProdus);
        assertThat(ramBase.getNrInteresati()).isEqualTo(Interesati);
        assertThat(ramBase.getPret()).isEqualTo(Pret);
        assertThat(ramBase.getGarantie()).isEqualTo(Garantie);
        assertThat(ramBase.getId()).isEqualTo(idProdus);
        assertThat(ramBase.getTip()).isEqualTo(Tip);
        RAMService.addRAM("Ram2","150","stare buna", "corsair2","1 an",55,150);
        assertThat(RAMService.getAllProduct()).isNotEmpty();
        assertThat(RAMService.getAllProduct()).size().isEqualTo(2);
        ramBase = RAMService.getAllProduct().get(1);
        assertThat(ramBase).isNotNull();
        assertThat(ramBase.getDescriere()).isEqualTo("stare buna");
        assertThat(ramBase.getNumeProdus()).isEqualTo("Ram2");
        assertThat(ramBase.getNrInteresati()).isEqualTo(150);
        assertThat(ramBase.getPret()).isEqualTo("150");
        assertThat(ramBase.getGarantie()).isEqualTo("1 an");
        assertThat(ramBase.getId()).isEqualTo(55);
        assertThat(ramBase.getTip()).isEqualTo("corsair2");


    }

    @Test
    void testProductCaNotBeAddedTwice(){
        assertThrows(ProductAlreadyExists.class, () -> {
            RAMService.addRAM(numeProdus,Pret,Descriere,Tip,Garantie,idProdus,Interesati);
            RAMService.addRAM(numeProdus,Pret,Descriere,Tip,Garantie,idProdus,Interesati);
            RAMService.CheckProductAlreadyExists(numeProdus);
            assertThat(RAMService.getAllProduct()).isNotEmpty();
            assertThat(RAMService.getAllProduct()).size().isEqualTo(1);
            RAMBase ramBase = RAMService.getAllProduct().get(0);
            assertThat(ramBase).isNotNull();
            RAMService.addRAM("Ram2","150","stare buna", "corsair2","1 an",55,150);
            RAMService.addRAM("Ram2","150","stare buna", "corsair2","1 an",55,150);
            assertThat(RAMService.getAllProduct()).isNotEmpty();
            assertThat(RAMService.getAllProduct()).size().isEqualTo(2);
            ramBase = RAMService.getAllProduct().get(1);
            assertThat(ramBase).isNotNull();
        });
    }

    @Test
    void testIfDeleteIsWorking()throws ProductAlreadyExists{
        RAMService.addRAM(numeProdus,Pret,Descriere,Tip,Garantie,idProdus,Interesati);
        RAMService.DeleteProduct(numeProdus);
        assertThat(RAMService.getAllProduct()).isEmpty();
        assertThat(RAMService.getAllProduct()).size().isEqualTo(0);
        RAMService.addRAM(numeProdus,Pret,Descriere,Tip,Garantie,idProdus,Interesati);
        RAMService.addRAM("numeProdus1",Pret,Descriere,Tip,Garantie,idProdus,Interesati);
        RAMService.addRAM("numeProdus2",Pret,Descriere,Tip,Garantie,idProdus,Interesati);
        assertThat(RAMService.getAllProduct()).isNotEmpty();
        assertThat(RAMService.getAllProduct()).size().isEqualTo(3);
        RAMService.DeleteProduct(numeProdus);
        assertThat(RAMService.getAllProduct()).isNotEmpty();
        assertThat(RAMService.getAllProduct()).size().isEqualTo(2);
        RAMService.DeleteProduct("numeProdus2");
        assertThat(RAMService.getAllProduct()).isNotEmpty();
        assertThat(RAMService.getAllProduct()).size().isEqualTo(1);
        RAMService.DeleteProduct("numeProdus1");
        assertThat(RAMService.getAllProduct()).isEmpty();
        assertThat(RAMService.getAllProduct()).size().isEqualTo(0);
    }

    @Test
    void testIfEdidProductIsWorking()throws ProductAlreadyExists{
        RAMService.addRAM(numeProdus,Pret,Descriere,Tip,Garantie,idProdus,Interesati);
        RAMService.EditProduct(numeProdus,"200","corsair3","1an jumate","este din ce in ce mai bun");
        assertThat(RAMService.getAllProduct()).isNotEmpty();
        assertThat(RAMService.getAllProduct()).size().isEqualTo(1);
        RAMBase ramBase = RAMService.getAllProduct().get(0);
        assertThat(ramBase).isNotNull();
        assertThat(ramBase.getDescriere()).isEqualTo("este din ce in ce mai bun");
        assertThat(ramBase.getPret()).isEqualTo("200");
        assertThat(ramBase.getGarantie()).isEqualTo("1an jumate");
        assertThat(ramBase.getTip()).isEqualTo("corsair3");
    }

    @Test
    void testIfIncrementNumeberOfInterestedIsWorking()throws ProductAlreadyExists{
        RAMService.addRAM(numeProdus,Pret,Descriere,Tip,Garantie,idProdus,Interesati);
        RAMService.Increment(numeProdus,Pret,Tip,Garantie,Descriere);
        assertThat(RAMService.getAllProduct()).isNotEmpty();
        assertThat(RAMService.getAllProduct()).size().isEqualTo(1);
        RAMBase ramBase = RAMService.getAllProduct().get(0);
        assertThat(ramBase.getNrInteresati()).isEqualTo(6);
        RAMService.Increment(numeProdus,Pret,Tip,Garantie,Descriere);
        ramBase = RAMService.getAllProduct().get(0);
        assertThat(ramBase.getNrInteresati()).isEqualTo(7);
    }

    @Test
    void testToGetTheMosTinterestedProduct()throws ProductAlreadyExists{
        RAMService.addRAM(numeProdus,Pret,Descriere,Tip,Garantie,idProdus,Interesati);
        assertThat(RAMService.getAllProduct()).isNotEmpty();
        assertThat(RAMService.getAllProduct()).size().isEqualTo(1);
        assertThat(RAMService.getMostInterestProduct()).isEqualTo(Interesati);
    }

    @Test
    void testIfTheReturnIdIsWorking()throws ProductAlreadyExists{
        RAMService.addRAM(numeProdus,Pret,Descriere,Tip,Garantie,idProdus,Interesati);
        assertThat(RAMService.getAllProduct()).isNotEmpty();
        assertThat(RAMService.getAllProduct()).size().isEqualTo(1);
        assertThat(RAMService.returnId(numeProdus)).isEqualTo(idProdus);
    }

}