package sample.DataBase;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.User.Order;
import sample.User.TempOrder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TempOrderServiceTest {

    public static final String numeProd="prod";
    public static final String Seller="seller";
    public static final String Customer="customer";
    public static final int nrinteresati=3;
    public static final int id=2;

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        TempOrderService.initDataBase();
    }

    @Test
    void testDatabaseIsInitializedAndNoGraphicCardsIsPersisted(){
        assertThat(TempOrderService.getAllTempOrders()).isNotNull();
        assertThat(TempOrderService.getAllTempOrders()).isEmpty();
    }

    @AfterEach
    void tearDown() {
        TempOrderService.closeDataBase();
    }

    @Test
    void testIfTempOrderisAddedCorrectly()  {
        TempOrderService.addOrder(Seller,Customer,numeProd,nrinteresati,id);
        assertThat(TempOrderService.getAllTempOrders()).isNotEmpty();
        assertThat(TempOrderService.getAllTempOrders()).size().isEqualTo(1);
        TempOrder base=TempOrderService.getAllTempOrders().get(0);
        assertThat(base).isNotNull();
        assertThat(base.getNumeProduse()).isEqualTo(numeProd);
        assertThat(base.getNumeSeller()).isEqualTo(Seller);
        assertThat(base.getNumeCustomer()).isEqualTo(Customer);
        assertThat(base.getIdCustomer()).isEqualTo(id);
        assertThat(base.getNrinteresati()).isEqualTo(nrinteresati);
        TempOrderService.addOrder(Seller,Customer,numeProd,nrinteresati,id);
        assertThat(TempOrderService.getAllTempOrders()).isNotEmpty();
        assertThat(TempOrderService.getAllTempOrders()).size().isEqualTo(2);
        base=TempOrderService.getAllTempOrders().get(1);
        assertThat(base).isNotNull();
        assertThat(base.getNumeProduse()).isEqualTo(numeProd);
        assertThat(base.getNumeSeller()).isEqualTo(Seller);
        assertThat(base.getNumeCustomer()).isEqualTo(Customer);
        assertThat(base.getIdCustomer()).isEqualTo(id);
        assertThat(base.getNrinteresati()).isEqualTo(nrinteresati);
    }

    @Test
    void TestIfTempOrderISDeleted(){
        TempOrderService.addOrder(Seller,Customer,numeProd,nrinteresati,id);
        assertThat(TempOrderService.getAllTempOrders()).isNotEmpty();
        assertThat(TempOrderService.getAllTempOrders()).size().isEqualTo(1);
        TempOrderService.Delete(numeProd);
        assertThat(TempOrderService.getAllTempOrders()).isEmpty();
        assertThat(TempOrderService.getAllTempOrders()).size().isEqualTo(0);
        TempOrderService.addOrder(Seller,Customer,numeProd,nrinteresati,id);
        TempOrderService.addOrder(Seller,Customer,numeProd,nrinteresati,id);
        TempOrderService.addOrder(Seller,Customer,numeProd,nrinteresati,id);
        TempOrderService.Delete(numeProd);
        assertThat(TempOrderService.getAllTempOrders()).isEmpty();
        assertThat(TempOrderService.getAllTempOrders()).size().isEqualTo(0);
    }

    @Test
    void TestIfQuantityIsIncremented()
    {
        TempOrderService.addOrder(Seller,Customer,numeProd,nrinteresati,id);
        assertThat(TempOrderService.getAllTempOrders()).isNotEmpty();
        assertThat(TempOrderService.getAllTempOrders()).size().isEqualTo(1);
        TempOrder base=TempOrderService.getAllTempOrders().get(0);
        assertThat(base).isNotNull();
        assertThat(TempOrderService.verify(numeProd,Seller,Customer)).isEqualTo(true);
        base=TempOrderService.getAllTempOrders().get(0);
        assertThat(TempOrderService.getAllTempOrders()).isNotEmpty();
        assertThat(TempOrderService.getAllTempOrders()).size().isEqualTo(1);
        assertThat(base.getCantitate()).isEqualTo(2);
        assertThat(TempOrderService.verify(numeProd,Seller,Customer)).isEqualTo(true);
        base=TempOrderService.getAllTempOrders().get(0);
        assertThat(base.getCantitate()).isEqualTo(3);


    }

    @Test
    void TestIfDataBseIsDeleted(){
        TempOrderService.addOrder(Seller,Customer,numeProd,nrinteresati,id);
        assertThat(TempOrderService.getAllTempOrders()).isNotEmpty();
        assertThat(TempOrderService.getAllTempOrders()).size().isEqualTo(1);
        TempOrder base=TempOrderService.getAllTempOrders().get(0);
        assertThat(base).isNotNull();
        TempOrderService.addOrder(Seller,"c",numeProd,nrinteresati,id);
        assertThat(TempOrderService.getAllTempOrders()).isNotEmpty();
        assertThat(TempOrderService.getAllTempOrders()).size().isEqualTo(2);
        base=TempOrderService.getAllTempOrders().get(1);
        assertThat(base).isNotNull();
        TempOrderService.DeleteAllDatabase(Customer);
        assertThat(TempOrderService.getAllTempOrders()).isNotEmpty();
        assertThat(TempOrderService.getAllTempOrders()).size().isEqualTo(1);
        TempOrderService.DeleteAllDatabase("c");
        assertThat(TempOrderService.getAllTempOrders()).isEmpty();
        assertThat(TempOrderService.getAllTempOrders()).size().isEqualTo(0);

    }

    @Test
    void TestIfCustomerExists(){
        TempOrderService.addOrder(Seller,Customer,numeProd,nrinteresati,id);
        assertThat(TempOrderService.getAllTempOrders()).isNotEmpty();
        assertThat(TempOrderService.getAllTempOrders()).size().isEqualTo(1);
        assertThat(TempOrderService.VerifyIfCustomerExist(Customer));
    }
}