package sample.DataBase;

import org.apache.commons.io.FileUtils;
import org.dizitart.no2.Nitrite;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.Categories.GraphicCards.GraphicCards;
import sample.Categories.GraphicCards.GraphicCardsBase;
import sample.User.User;
import sample.exceptions.ProductAlreadyExists;
import sample.exceptions.UsernameAlreadyExistException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GraphicCardsServiceTest  {

    public static final String numeProd="produs",numeProd1="produs1";
    public static final String Pret="10",Pret1="11";
    public static final String Descriere="Descriere",Descriere1="Descriere1";
    public static final String Tip="Graphic Card",Tip1="Graphic Card1";
    public static final String Garantie="2 ani",Garantie1="4 ani";
    public static final int id=2,id1=4;
    public static final int nrinteresati=3,nrinteresati1=6;

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        GraphicCardsService.initDataBaseforGraphicCards();
    }

    @Test
    void testDatabaseIsInitializedAndNoGraphicCardsIsPersisted(){
        assertThat(GraphicCardsService.getAllGraphicCards()).isNotNull();
        assertThat(GraphicCardsService.getAllGraphicCards()).isEmpty();
    }

    @AfterEach
    void tearDown() {
        GraphicCardsService.closeDataBase();
    }

    @Test
    void testIfProductGraphicCardsisAddedCorrectly() throws ProductAlreadyExists {
        GraphicCardsService.addGraphic(numeProd,Pret,Descriere,Tip,Garantie,id,nrinteresati);
        assertThat(GraphicCardsService.getAllGraphicCards()).isNotEmpty();
        assertThat(GraphicCardsService.getAllGraphicCards()).size().isEqualTo(1);
        GraphicCardsBase base = GraphicCardsService.getAllGraphicCards().get(0);
        assertThat(base).isNotNull();
        assertThat(base.getNumeProdus()).isEqualTo(numeProd);
        assertThat(base.getPret()).isEqualTo(Pret);
        assertThat(base.getDescriere()).isEqualTo(Descriere);
        assertThat(base.getTip()).isEqualTo(Tip);
        assertThat(base.getGarantie()).isEqualTo(Garantie);
        assertThat(base.getId()).isEqualTo(id);
        assertThat(base.getNrInteresati()).isEqualTo(nrinteresati);
        GraphicCardsService.addGraphic(numeProd1,Pret1,Descriere1,Tip1,Garantie1,id1,nrinteresati1);
        assertThat(GraphicCardsService.getAllGraphicCards()).isNotEmpty();
        assertThat(GraphicCardsService.getAllGraphicCards()).size().isEqualTo(2);
        base=GraphicCardsService.getAllGraphicCards().get(1);
        assertThat(base).isNotNull();
        assertThat(base.getNumeProdus()).isEqualTo(numeProd1);
        assertThat(base.getPret()).isEqualTo(Pret1);
        assertThat(base.getDescriere()).isEqualTo(Descriere1);
        assertThat(base.getTip()).isEqualTo(Tip1);
        assertThat(base.getGarantie()).isEqualTo(Garantie1);
        assertThat(base.getId()).isEqualTo(id1);
        assertThat(base.getNrInteresati()).isEqualTo(nrinteresati1);

    }

    @Test
    void TestIfProductCanbeAddedTwice()

    {
        assertThrows(ProductAlreadyExists.class, () -> {
            GraphicCardsService.addGraphic(numeProd, Pret, Descriere, Tip, Garantie, id, nrinteresati);
            GraphicCardsService.addGraphic(numeProd, Pret, Descriere, Tip, Garantie, id, nrinteresati);
            GraphicCardsService.CheckProductAlreadyExists(numeProd);
            assertThat(GraphicCardsService.getAllGraphicCards()).isNotEmpty();
            assertThat(GraphicCardsService.getAllGraphicCards()).size().isEqualTo(1);
            GraphicCardsBase base=GraphicCardsService.getAllGraphicCards().get(0);
            assertThat(base).isNotNull();
            GraphicCardsService.addGraphic(numeProd1,Pret1,Descriere1,Tip1,Garantie1,id1,nrinteresati1);
            GraphicCardsService.addGraphic(numeProd1,Pret1,Descriere1,Tip1,Garantie1,id1,nrinteresati1);
            assertThat(GraphicCardsService.getAllGraphicCards()).isNotEmpty();
            assertThat(GraphicCardsService.getAllGraphicCards()).size().isEqualTo(2);
            base=GraphicCardsService.getAllGraphicCards().get(1);
            assertThat(base).isNotNull();


        });
    }

    @Test
    void testIdProductIsDeleted()throws ProductAlreadyExists{
        GraphicCardsService.addGraphic(numeProd,Pret,Descriere,Tip,Garantie,id,nrinteresati);
        GraphicCardsService.DeleteProduct(numeProd);
        assertThat(GraphicCardsService.getAllGraphicCards()).isEmpty();
        assertThat(GraphicCardsService.getAllGraphicCards()).size().isEqualTo(0);
        GraphicCardsService.addGraphic(numeProd,Pret,Descriere,Tip,Garantie,id,nrinteresati);
        GraphicCardsService.addGraphic(numeProd1,Pret1,Descriere1,Tip1,Garantie1,id1,nrinteresati1);
        assertThat(GraphicCardsService.getAllGraphicCards()).isNotEmpty();
        assertThat(GraphicCardsService.getAllGraphicCards()).size().isEqualTo(2);
        GraphicCardsService.DeleteProduct(numeProd);
        assertThat(GraphicCardsService.getAllGraphicCards()).isNotEmpty();
        assertThat(GraphicCardsService.getAllGraphicCards()).size().isEqualTo(1);
        GraphicCardsService.DeleteProduct(numeProd1);
        assertThat(GraphicCardsService.getAllGraphicCards()).isEmpty();
        assertThat(GraphicCardsService.getAllGraphicCards()).size().isEqualTo(0);

    }

    @Test
    void testIfProductIsEdited()throws ProductAlreadyExists {
        GraphicCardsService.addGraphic(numeProd, Pret, Descriere, Tip, Garantie, id, nrinteresati);
        GraphicCardsService.EditProduct(numeProd, Pret1, Tip1, Garantie1,Descriere1);
        assertThat(GraphicCardsService.getAllGraphicCards()).isNotEmpty();
        assertThat(GraphicCardsService.getAllGraphicCards()).size().isEqualTo(1);
        GraphicCardsBase base= GraphicCardsService.getAllGraphicCards().get(0);
        assertThat(base).isNotNull();
        assertThat(base.getNumeProdus()).isEqualTo(numeProd);
        assertThat(base.getPret()).isEqualTo(Pret1);
        assertThat(base.getDescriere()).isEqualTo(Descriere1);
        assertThat(base.getTip()).isEqualTo(Tip1);
        assertThat(base.getGarantie()).isEqualTo(Garantie1);
    }

    @Test
    void testIfItIsTheMostInterestingProduct() throws ProductAlreadyExists{
        GraphicCardsService.addGraphic(numeProd, Pret, Descriere, Tip, Garantie, id, nrinteresati);
        assertThat(GraphicCardsService.getAllGraphicCards()).isNotEmpty();
        assertThat(GraphicCardsService.getAllGraphicCards()).size().isEqualTo(1);
        assertThat(GraphicCardsService.getMostInterestProduct()).isEqualTo(nrinteresati);
    }

    @Test
    void testIfIdIsCorrect() throws ProductAlreadyExists{
        GraphicCardsService.addGraphic(numeProd, Pret, Descriere, Tip, Garantie, id, nrinteresati);
        assertThat(GraphicCardsService.getAllGraphicCards()).isNotEmpty();
        assertThat(GraphicCardsService.getAllGraphicCards()).size().isEqualTo(1);
        assertThat(GraphicCardsService.returnId(numeProd)).isEqualTo(id);
    }

    @Test
    void testIfIncrementsCorrectly() throws ProductAlreadyExists{
        GraphicCardsService.addGraphic(numeProd, Pret, Descriere, Tip, Garantie, id, nrinteresati);
        GraphicCardsService.Increment(numeProd,Pret,Tip,Garantie,Descriere);
        assertThat(GraphicCardsService.getAllGraphicCards()).isNotEmpty();
        assertThat(GraphicCardsService.getAllGraphicCards()).size().isEqualTo(1);
        GraphicCardsBase base= GraphicCardsService.getAllGraphicCards().get(0);
        assertThat(base).isNotNull();
        assertThat(base.getNrInteresati()).isEqualTo(nrinteresati+1);
    }
}