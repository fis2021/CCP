package sample.DataBase;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.User.FinalStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinalStatusServiceTest {
    private static final String numeProdus = "PROCESOR";
    private static final String numeSeller = "seller1";
    private static final String numeCustomer = "customer1";
    private static final int cantitate = 5;
    private static final boolean delivery = true;
    private static final String Status="ACCEPTED";
    private static final int Interesati = 25;

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        FinalStatusService.initDataBase();
    }

    @Test
    void testDatabaseIsInitializedAndNoProductIsPersisted() {
        assertThat(FinalStatusService.getAllProduct()).isNotNull();
        assertThat(FinalStatusService.getAllProduct()).isEmpty();
    }

    @AfterEach
    void tearDown() {
        FinalStatusService.closeDataBase();
    }

    @Test
    void testIfProductIsFinalOrderIsAddedCorectly(){
        FinalStatusService.FinalOrder(numeProdus,numeSeller,numeCustomer,cantitate,delivery,Status,Interesati);
        assertThat(FinalStatusService.getAllProduct()).isNotEmpty();
        assertThat(FinalStatusService.getAllProduct()).size().isEqualTo(1);
        FinalStatus finalStatus = FinalStatusService.getAllProduct().get(0);
        assertThat(finalStatus).isNotNull();
        assertThat(finalStatus.getStatus()).isEqualTo(Status);
        assertThat(finalStatus.getCantitate()).isEqualTo(cantitate);
        assertThat(finalStatus.getInteresati()).isEqualTo(Interesati);
        assertThat(finalStatus.getNumeProduse()).isEqualTo(numeProdus);
        assertThat(finalStatus.getNumeCustomer()).isEqualTo(numeCustomer);
        assertThat(finalStatus.getdelivery()).isEqualTo(delivery);
        assertThat(finalStatus.getNumeSeller()).isEqualTo(numeSeller);
        FinalStatusService.FinalOrder(numeProdus,numeSeller,numeCustomer,cantitate,delivery,Status,Interesati);
        assertThat(FinalStatusService.getAllProduct()).isNotEmpty();
        assertThat(FinalStatusService.getAllProduct()).size().isEqualTo(2);
    }

}