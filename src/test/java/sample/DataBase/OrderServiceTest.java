package sample.DataBase;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.User.Order;
import sample.exceptions.ProductAlreadyExists;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    public static final String numeProd="prod";
    public static final String Seller="seller";
    public static final String Customer="customer";
    public static final int cantitate=2;
    public static final boolean delivery=true;
    public static final String status="Accepted",status1="It is processing",status2="Declined";
    public static final int nrinteresati=3;
    public static final int id=2;
    public static final int idcomanda=2;

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        OrderService.initDataBase();
    }

    @Test
    void testDatabaseIsInitializedAndNoGraphicCardsIsPersisted(){
        assertThat(OrderService.getAllOrders()).isNotNull();
        assertThat(OrderService.getAllOrders()).isEmpty();
    }

    @AfterEach
    void tearDown() {
        OrderService.closeDataBase();
    }

    @Test
    void testIfOrderisAddedCorrectly()  {
        OrderService.Order(numeProd,Seller,Customer,cantitate,delivery,status,nrinteresati,id,idcomanda);
        assertThat(OrderService.getAllOrders()).isNotEmpty();
        assertThat(OrderService.getAllOrders()).size().isEqualTo(1);
        Order base=OrderService.getAllOrders().get(0);
        assertThat(base).isNotNull();
        assertThat(base.getNumeProduse()).isEqualTo(numeProd);
        assertThat(base.getNumeSeller()).isEqualTo(Seller);
        assertThat(base.getNumeCustomer()).isEqualTo(Customer);
        assertThat(base.getCantitate()).isEqualTo(cantitate);
        assertThat(base.getdelivery()).isEqualTo(delivery);
        assertThat(base.getIdCustomer()).isEqualTo(id);
        assertThat(base.getNrInteresati()).isEqualTo(nrinteresati);
        assertThat(base.getStatus()).isEqualTo(status);
        assertThat(base.getIdComanda()).isEqualTo(idcomanda);
        OrderService.Order(numeProd,Seller,Customer,cantitate,delivery,status,nrinteresati,id,idcomanda);
        assertThat(OrderService.getAllOrders()).isNotEmpty();
        assertThat(OrderService.getAllOrders()).size().isEqualTo(2);
        base=OrderService.getAllOrders().get(1);
        assertThat(base).isNotNull();
        assertThat(base.getNumeProduse()).isEqualTo(numeProd);
        assertThat(base.getNumeSeller()).isEqualTo(Seller);
        assertThat(base.getNumeCustomer()).isEqualTo(Customer);
        assertThat(base.getCantitate()).isEqualTo(cantitate);
        assertThat(base.getdelivery()).isEqualTo(delivery);
        assertThat(base.getIdCustomer()).isEqualTo(id);
        assertThat(base.getNrInteresati()).isEqualTo(nrinteresati);
        assertThat(base.getStatus()).isEqualTo(status);
        assertThat(base.getIdComanda()).isEqualTo(idcomanda);

    }

    @Test
    void TestIfSellerHasOrders(){
        OrderService.Order(numeProd,Seller,Customer,cantitate,delivery,status1,nrinteresati,id,idcomanda);
        assertThat(OrderService.getAllOrders()).isNotEmpty();
        assertThat(OrderService.getAllOrders()).size().isEqualTo(1);
        Order base=OrderService.getAllOrders().get(0);
        assertThat(base).isNotNull();
        assertThat(OrderService.checkifSellerHaveOders(Seller)).isEqualTo(true);


    }

    @Test
    void TestIfStatusIsSettingCorrectly(){
        OrderService.Order(numeProd,Seller,Customer,cantitate,delivery,status1,nrinteresati,id,idcomanda);
        assertThat(OrderService.getAllOrders()).isNotEmpty();
        assertThat(OrderService.getAllOrders()).size().isEqualTo(1);
        OrderService.SetStatusOrder(numeProd,status);
        Order base=OrderService.getAllOrders().get(0);
        assertThat(base).isNotNull();
        assertThat(base.getStatus()).isEqualTo(status);
    }

    @Test
    void TestIfOrderCanBeDeletedCorrectly(){
        OrderService.Order(numeProd,Seller,Customer,cantitate,delivery,status2,nrinteresati,id,idcomanda);
        assertThat(OrderService.getAllOrders()).isNotEmpty();
        assertThat(OrderService.getAllOrders()).size().isEqualTo(1);
        OrderService.DeleteOrder(Customer);
        assertThat(OrderService.getAllOrders()).isEmpty();
        assertThat(OrderService.getAllOrders()).size().isEqualTo(0);
        OrderService.Order(numeProd,Seller,Customer,cantitate,delivery,status2,nrinteresati,id,idcomanda);
        OrderService.Order(numeProd,Seller,"c",cantitate,delivery,status2,nrinteresati,id,idcomanda);
        assertThat(OrderService.getAllOrders()).isNotEmpty();
        assertThat(OrderService.getAllOrders()).size().isEqualTo(2);
        OrderService.DeleteOrder(Customer);
        assertThat(OrderService.getAllOrders()).isEmpty();
        assertThat(OrderService.getAllOrders()).size().isEqualTo(0);


    }

}