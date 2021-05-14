package sample.DataBase;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.Categories.Sources.SourcesBase;
import sample.exceptions.ProductAlreadyExists;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SourcesServiceTest {

    private static final String numeProdus = "Source";
    private static final String Pret = "300";
    private static final String Descriere = "Produs nou";
    private static final String Tip = "Corsair";
    private static final String Garantie = "3 ani";
    private static final int idProdus = 7;
    private static final int Interesati = 1;

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        SourcesService.initDataBaseforSources();
    }

    @Test
    void testDatabaseIsInitializedAndNoUserIsPersisted() {
        assertThat(SourcesService.getAllProduct()).isNotNull();
        assertThat(SourcesService.getAllProduct()).isEmpty();
    }

    @AfterEach
    void tearDown() {
        SourcesService.closeDataBase();
    }

    @Test
    void testIfProductIsAddedCorectly() throws ProductAlreadyExists {
        SourcesService.addSource(numeProdus, Pret, Descriere, Tip, Garantie, idProdus, Interesati);
        assertThat(SourcesService.getAllProduct()).isNotEmpty();
        assertThat(SourcesService.getAllProduct()).size().isEqualTo(1);
        SourcesBase sourcesBase = SourcesService.getAllProduct().get(0);
        assertThat(sourcesBase).isNotNull();
        assertThat(sourcesBase.getDescriere()).isEqualTo(Descriere);
        assertThat(sourcesBase.getNumeProdus()).isEqualTo(numeProdus);
        assertThat(sourcesBase.getNrInteresati()).isEqualTo(Interesati);
        assertThat(sourcesBase.getPret()).isEqualTo(Pret);
        assertThat(sourcesBase.getGarantie()).isEqualTo(Garantie);
        assertThat(sourcesBase.getId()).isEqualTo(idProdus);
        assertThat(sourcesBase.getTip()).isEqualTo(Tip);
        SourcesService.addSource("Sourc2", "150", "stare buna", "corsair2", "1 an", 55, 150);
        assertThat(SourcesService.getAllProduct()).isNotEmpty();
        assertThat(SourcesService.getAllProduct()).size().isEqualTo(2);
        sourcesBase = SourcesService.getAllProduct().get(1);
        assertThat(sourcesBase).isNotNull();
        assertThat(sourcesBase.getDescriere()).isEqualTo("stare buna");
        assertThat(sourcesBase.getNumeProdus()).isEqualTo("Sourc2");
        assertThat(sourcesBase.getNrInteresati()).isEqualTo(150);
        assertThat(sourcesBase.getPret()).isEqualTo("150");
        assertThat(sourcesBase.getGarantie()).isEqualTo("1 an");
        assertThat(sourcesBase.getId()).isEqualTo(55);
        assertThat(sourcesBase.getTip()).isEqualTo("corsair2");
    }

    @Test
    void testProductCanNotAddedTwice(){
        assertThrows(ProductAlreadyExists.class, ()->{
            SourcesService.addSource(numeProdus, Pret, Descriere, Tip, Garantie, idProdus, Interesati);
            SourcesService.addSource(numeProdus, Pret, Descriere, Tip, Garantie, idProdus, Interesati);
            SourcesService.CheckProductAlreadyExists(numeProdus);
            assertThat(SourcesService.getAllProduct()).isNotEmpty();
            assertThat(SourcesService.getAllProduct()).size().isEqualTo(1);
            SourcesBase sourcesBase = SourcesService.getAllProduct().get(0);
            assertThat(sourcesBase).isNotNull();
            SourcesService.addSource("Sourc2", "150", "stare buna", "corsair2", "1 an", 55, 150);
            SourcesService.addSource("Sourc2", "150", "stare buna", "corsair2", "1 an", 55, 150);
            assertThat(SourcesService.getAllProduct()).isNotEmpty();
            assertThat(SourcesService.getAllProduct()).size().isEqualTo(2);;
        });
    }

    @Test
    void testIfIncrementIsWorking()throws ProductAlreadyExists{
        SourcesService.addSource(numeProdus, Pret, Descriere, Tip, Garantie, idProdus, Interesati);
        SourcesService.Increment(numeProdus,Pret,Tip,Garantie,Descriere);
        assertThat(SourcesService.getAllProduct()).isNotEmpty();
        assertThat(SourcesService.getAllProduct()).size().isEqualTo(1);
        SourcesBase sourcesBase = SourcesService.getAllProduct().get(0);
        assertThat(sourcesBase.getNrInteresati()).isEqualTo(Interesati+1);
        SourcesService.Increment(numeProdus,Pret,Tip,Garantie,Descriere);
        sourcesBase = SourcesService.getAllProduct().get(0);
        assertThat(sourcesBase.getNrInteresati()).isEqualTo(Interesati+2);
    }

    @Test
    void testIfYouCanGetTheMostInterestedProduct()throws ProductAlreadyExists{
        SourcesService.addSource(numeProdus, Pret, Descriere, Tip, Garantie, idProdus, Interesati);
        assertThat(SourcesService.getAllProduct()).isNotEmpty();
        assertThat(SourcesService.getAllProduct()).size().isEqualTo(1);
        SourcesBase sourcesBase = SourcesService.getAllProduct().get(0);
        assertThat(sourcesBase).isNotNull();
        assertThat(SourcesService.getMostInterestProduct()).isEqualTo(Interesati);
        SourcesService.addSource("Sourc2", "150", "stare buna", "corsair2", "1 an", 55, 150);
        assertThat(SourcesService.getAllProduct()).isNotEmpty();
        assertThat(SourcesService.getAllProduct()).size().isEqualTo(2);
        sourcesBase = SourcesService.getAllProduct().get(1);
        assertThat(sourcesBase).isNotNull();
        assertThat(SourcesService.getMostInterestProduct()).isEqualTo(150);
    }

    @Test
    void testIfYouCanGetTheId()throws ProductAlreadyExists{
        SourcesService.addSource(numeProdus, Pret, Descriere, Tip, Garantie, idProdus, Interesati);
        assertThat(SourcesService.getAllProduct()).isNotEmpty();
        assertThat(SourcesService.getAllProduct()).size().isEqualTo(1);
        SourcesBase sourcesBase = SourcesService.getAllProduct().get(0);
        assertThat(sourcesBase).isNotNull();
        assertThat(SourcesService.returnId(numeProdus)).isEqualTo(7);
    }
}