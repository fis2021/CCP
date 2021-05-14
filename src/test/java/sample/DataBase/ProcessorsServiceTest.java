package sample.DataBase;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.Categories.GraphicCards.GraphicCardsBase;
import sample.Categories.Processors.Processors;
import sample.Categories.Processors.ProcessorsBase;
import sample.exceptions.ProductAlreadyExists;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProcessorsServiceTest {

    public static final String numeProd="produs",numeProd1="produs1";
    public static final String Pret="10",Pret1="11";
    public static final String Descriere="Descriere",Descriere1="Descriere1";
    public static final String Tip="Proccesors",Tip1="Proccesors1";
    public static final String Garantie="2 ani",Garantie1="4 ani";
    public static final int id=2,id1=4;
    public static final int nrinteresati=3,nrinteresati1=6;

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        ProcessorsService.initDataBaseforProcessors();
    }

    @Test
    void testDatabaseIsInitializedAndNoProcessorIsPersisted(){
        assertThat(ProcessorsService.getAllProccesors()).isNotNull();
        assertThat(ProcessorsService.getAllProccesors()).isEmpty();
    }

    @AfterEach
    void tearDown() {
        ProcessorsService.closeDataBase();
    }

    @Test
    void testIfProductProcessorisAddedCorrectly() throws ProductAlreadyExists {
        ProcessorsService.addProcessors(numeProd,Pret,Descriere,Tip,Garantie,id,nrinteresati);
        assertThat(ProcessorsService.getAllProccesors()).isNotEmpty();
        assertThat(ProcessorsService.getAllProccesors()).size().isEqualTo(1);
        ProcessorsBase base = ProcessorsService.getAllProccesors().get(0);
        assertThat(base).isNotNull();
        assertThat(base.getNumeProdus()).isEqualTo(numeProd);
        assertThat(base.getPret()).isEqualTo(Pret);
        assertThat(base.getDescriere()).isEqualTo(Descriere);
        assertThat(base.getTip()).isEqualTo(Tip);
        assertThat(base.getGarantie()).isEqualTo(Garantie);
        assertThat(base.getId()).isEqualTo(id);
        assertThat(base.getNrinteresati()).isEqualTo(nrinteresati);
        ProcessorsService.addProcessors(numeProd1,Pret1,Descriere1,Tip1,Garantie1,id1,nrinteresati1);
        assertThat(ProcessorsService.getAllProccesors()).isNotEmpty();
        assertThat(ProcessorsService.getAllProccesors()).size().isEqualTo(2);
        base=ProcessorsService.getAllProccesors().get(1);
        assertThat(base).isNotNull();
        assertThat(base.getNumeProdus()).isEqualTo(numeProd1);
        assertThat(base.getPret()).isEqualTo(Pret1);
        assertThat(base.getDescriere()).isEqualTo(Descriere1);
        assertThat(base.getTip()).isEqualTo(Tip1);
        assertThat(base.getGarantie()).isEqualTo(Garantie1);
        assertThat(base.getId()).isEqualTo(id1);
        assertThat(base.getNrinteresati()).isEqualTo(nrinteresati1);

    }

    @Test
    void TestIfProductCanBeAddedTwice()
    {
        assertThrows(ProductAlreadyExists.class, () -> {
            ProcessorsService.addProcessors(numeProd, Pret, Descriere, Tip, Garantie, id, nrinteresati);
            ProcessorsService.addProcessors(numeProd, Pret, Descriere, Tip, Garantie, id, nrinteresati);
            ProcessorsService.CheckProductAlreadyExists(numeProd);
            assertThat(ProcessorsService.getAllProccesors()).isNotEmpty();
            assertThat(ProcessorsService.getAllProccesors()).size().isEqualTo(1);
            ProcessorsBase base = ProcessorsService.getAllProccesors().get(0);
            assertThat(base).isNotNull();
            ProcessorsService.addProcessors(numeProd1,Pret1,Descriere1,Tip1,Garantie1,id1,nrinteresati1);
            ProcessorsService.addProcessors(numeProd1,Pret1,Descriere1,Tip1,Garantie1,id1,nrinteresati1);
            assertThat(ProcessorsService.getAllProccesors()).isNotEmpty();
            assertThat(ProcessorsService.getAllProccesors()).size().isEqualTo(2);
            base=ProcessorsService.getAllProccesors().get(1);
            assertThat(base).isNotNull();


        });
    }

    @Test
    void testIfProductIsDeleted()throws ProductAlreadyExists{
        ProcessorsService.addProcessors(numeProd,Pret,Descriere,Tip,Garantie,id,nrinteresati);
        ProcessorsService.DeleteProduct(numeProd);
        assertThat(ProcessorsService.getAllProccesors()).isEmpty();
        assertThat(ProcessorsService.getAllProccesors()).size().isEqualTo(0);
        ProcessorsService.addProcessors(numeProd,Pret,Descriere,Tip,Garantie,id,nrinteresati);
        ProcessorsService.addProcessors(numeProd1,Pret1,Descriere1,Tip1,Garantie1,id1,nrinteresati1);
        assertThat(ProcessorsService.getAllProccesors()).isNotEmpty();
        assertThat(ProcessorsService.getAllProccesors()).size().isEqualTo(2);
        ProcessorsService.DeleteProduct(numeProd);
        assertThat(ProcessorsService.getAllProccesors()).isNotEmpty();
        assertThat(ProcessorsService.getAllProccesors()).size().isEqualTo(1);
        ProcessorsService.DeleteProduct(numeProd1);
        assertThat(ProcessorsService.getAllProccesors()).isEmpty();
        assertThat(ProcessorsService.getAllProccesors()).size().isEqualTo(0);

    }

    @Test
    void testIfProductIsEdited()throws ProductAlreadyExists {
        ProcessorsService.addProcessors(numeProd, Pret, Descriere, Tip, Garantie, id, nrinteresati);
        ProcessorsService.EditProduct(numeProd, Pret1, Tip1, Garantie1,Descriere1);
        assertThat(ProcessorsService.getAllProccesors()).isNotEmpty();
        assertThat(ProcessorsService.getAllProccesors()).size().isEqualTo(1);
        ProcessorsBase base = ProcessorsService.getAllProccesors().get(0);
        assertThat(base).isNotNull();
        assertThat(base.getNumeProdus()).isEqualTo(numeProd);
        assertThat(base.getPret()).isEqualTo(Pret1);
        assertThat(base.getDescriere()).isEqualTo(Descriere1);
        assertThat(base.getTip()).isEqualTo(Tip1);
        assertThat(base.getGarantie()).isEqualTo(Garantie1);
    }

    @Test
    void testIfItIsTheMostInterestingProduct() throws ProductAlreadyExists{
        ProcessorsService.addProcessors(numeProd, Pret, Descriere, Tip, Garantie, id, nrinteresati);
        assertThat(ProcessorsService.getAllProccesors()).isNotEmpty();
        assertThat(ProcessorsService.getAllProccesors()).size().isEqualTo(1);
        assertThat(ProcessorsService.getMostInterestProduct()).isEqualTo(nrinteresati);
    }

    @Test
    void testIfIdIsCorrect() throws ProductAlreadyExists{
        ProcessorsService.addProcessors(numeProd, Pret, Descriere, Tip, Garantie, id, nrinteresati);
        assertThat(ProcessorsService.getAllProccesors()).isNotEmpty();
        assertThat(ProcessorsService.getAllProccesors()).size().isEqualTo(1);
        assertThat(ProcessorsService.returnId(numeProd)).isEqualTo(id);
    }

    @Test
    void testIfIncrementsCorrectly() throws ProductAlreadyExists{
        ProcessorsService.addProcessors(numeProd, Pret, Descriere, Tip, Garantie, id, nrinteresati);
        ProcessorsService.Increment(numeProd,Pret,Tip,Garantie,Descriere);
        assertThat(ProcessorsService.getAllProccesors()).isNotEmpty();
        assertThat(ProcessorsService.getAllProccesors()).size().isEqualTo(1);
        ProcessorsBase base = ProcessorsService.getAllProccesors().get(0);
        assertThat(base).isNotNull();
        assertThat(base.getNrinteresati()).isEqualTo(nrinteresati+1);
    }

}