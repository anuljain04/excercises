package com.example.KhoaLeU1Capstone.service;

import com.example.KhoaLeU1Capstone.dao.*;
import com.example.KhoaLeU1Capstone.model.*;
import com.example.KhoaLeU1Capstone.viewmodel.ConsoleViewModel;
import com.example.KhoaLeU1Capstone.viewmodel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class InvoiceServiceLayerTest {

    GameDao gameDao;
    TshirtDao tshirtDao;
    ConsoleDao consoleDao;
    InvoiceDao invoiceDao;
    SalesTaxRateDao salesTaxRateDao;
    ProcessingFeeDao processingFeeDao;
    ServiceLayer serviceLayer;

    @Before
    public void setUp() throws Exception {

        setUpGameDaoMock();
        setUpTshirtDaoMock();
        setUpConsoleDaoMock();
        setUpInvoiceDaoMock();
        setUpSalesTaxRateMock();
        setUpProcessingFeeMock();

        serviceLayer = new ServiceLayer(invoiceDao, consoleDao, gameDao, tshirtDao, salesTaxRateDao, processingFeeDao);
    }

    //          MOCKS


    private void setUpGameDaoMock() {

        gameDao = mock(GameDaoJdbcTemplateImpl.class);

        Game game = new Game();
        game.setGameId(12);
        game.setTitle("Super Mario");
        game.setEsrbRating("E");
        game.setDescription("Unleash your creativity");
        game.setGamePrice(59.99);
        game.setStudio("Nintendo");
        game.setGameQuantity(100);

        Game game1 = new Game();

        game1.setTitle("Super Mario");
        game1.setEsrbRating("E");
        game1.setDescription("Unleash your creativity");
        game1.setGamePrice(59.99);
        game1.setStudio("Nintendo");
        game1.setGameQuantity(100);

        List<Game> gameList = new ArrayList<>();
        gameList.add(game);

        doReturn(game).when(gameDao).addGame(game1);
        doReturn(game).when(gameDao).getGame(12);
        doReturn(gameList).when(gameDao).getAllGames();
    }

    private void setUpInvoiceDaoMock() {
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(40);
        invoice.setName("John Doe");
        invoice.setStreet("123 Main Street");
        invoice.setCity("Queens");
        invoice.setState("NY");
        invoice.setZipcode("11355");
        invoice.setItemType("Games");
        invoice.setItemId(12);
        invoice.setUnitPrice(59.99);
        invoice.setQuantity(100);
        invoice.setSubtotal(5999);
        invoice.setTax(.06);
        invoice.setProcessingFee(1.49);
        invoice.setTotal(6021.98);

        Invoice invoice1 = new Invoice();
        invoice1.setName("John Doe");
        invoice1.setStreet("123 Main Street");
        invoice1.setCity("Queens");
        invoice1.setState("NY");
        invoice1.setZipcode("11355");
        invoice1.setItemType("Games");
        invoice1.setItemId(12);
        invoice1.setUnitPrice(59.99);
        invoice1.setQuantity(100);
        invoice1.setSubtotal(5999);
        invoice1.setTax(.06);
        invoice1.setProcessingFee(1.49);
        invoice1.setTotal(6021.98);

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        doReturn(invoice).when(invoiceDao).addInvoice(invoice1);
        doReturn(invoice).when(invoiceDao).getInvoice(40);
        doReturn(invoiceList).when(invoiceDao).getAllInvoices();

        Invoice invoiceUpdate = new Invoice();
        invoiceUpdate.setInvoiceId(11);
        invoiceUpdate.setName("Adam Lee");
        invoiceUpdate.setStreet("123 Broadway");
        invoiceUpdate.setCity("New York");
        invoiceUpdate.setState("NY");
        invoiceUpdate.setZipcode("10005");
        invoiceUpdate.setItemType("Games");
        invoiceUpdate.setItemId(12);
        invoiceUpdate.setUnitPrice(50);
        invoiceUpdate.setQuantity(2);
        invoiceUpdate.setSubtotal(100);
        invoiceUpdate.setTax(.06);
        invoiceUpdate.setProcessingFee(1.49);
        invoiceUpdate.setTotal(107.49);

        doNothing().when(invoiceDao).updateInvoice(invoiceUpdate);
        doReturn(invoiceUpdate).when(invoiceDao).getInvoice(11);

        Invoice invoiceDelete = new Invoice();
        invoiceDelete.setInvoiceId(43);
        invoiceDelete.setName("Eve Lee");
        invoiceDelete.setStreet("456 5th Ave");
        invoiceDelete.setCity("New York");
        invoiceDelete.setState("NY");
        invoiceDelete.setZipcode("10005");
        invoiceDelete.setItemType("Games");
        invoiceDelete.setItemId(12);
        invoiceDelete.setUnitPrice(50);
        invoiceDelete.setQuantity(2);
        invoiceDelete.setSubtotal(100);
        invoiceDelete.setTax(.06);
        invoiceDelete.setProcessingFee(1.49);
        invoiceDelete.setTotal(107.49);

        doNothing().when(invoiceDao).deleteInvoice(43);
        doReturn(null).when(invoiceDao).getInvoice(43);

    }

    private void setUpTshirtDaoMock() {
        tshirtDao = mock(TshirtDaoJdbcTemplateImpl.class);

        Tshirt tshirt = new Tshirt();
        tshirt.setTshirtId(4);
        tshirt.setSize("S");
        tshirt.setColor("red");
        tshirt.setDescription("Nintendo tshirt");
        tshirt.setTshirtPrice(20);
        tshirt.setTshirtQuantity(200);


        Tshirt tshirt1 = new Tshirt();
        tshirt1.setSize("S");
        tshirt1.setColor("red");
        tshirt1.setDescription("Nintendo tshirt");
        tshirt1.setTshirtPrice(20);
        tshirt1.setTshirtQuantity(200);


        List<Tshirt> tshirtList = new ArrayList<>();
        tshirtList.add(tshirt);

        doReturn(tshirt).when(tshirtDao).addTshirt(tshirt1);
        doReturn(tshirt).when(tshirtDao).getTshirt(4);
        doReturn(tshirtList).when(tshirtDao).getAllTshirts();
    }

    private void setUpConsoleDaoMock() {
        consoleDao = mock(ConsoleDaoJdbcTemplateImpl.class);

        Console console = new Console();
        console.setConsoleId(7);
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("32GB");
        console.setProcessor("Nintendo");
        console.setConsolePrice(199);
        console.setConsoleQuantity(100);

        Console console1 = new Console();
        console1.setModel("Switch");
        console1.setManufacturer("Nintendo");
        console1.setMemoryAmount("32GB");
        console1.setProcessor("Nintendo");
        console1.setConsolePrice(199);
        console1.setConsoleQuantity(100);

        List<Console> cList = new ArrayList<>();
        cList.add(console);

        doReturn(console).when(consoleDao).addConsole(console1);
        doReturn(console).when(consoleDao).getConsole(7);
        doReturn(cList).when(consoleDao).getAllConsoles();

        Console consoleUpdate = new Console();
        consoleUpdate.setConsoleId(42);
        consoleUpdate.setModel("PS3");
        consoleUpdate.setManufacturer("Microsoft");
        consoleUpdate.setMemoryAmount("64");
        consoleUpdate.setProcessor("35ghz");
        consoleUpdate.setConsolePrice(200);
        consoleUpdate.setConsoleQuantity(10);

        doNothing().when(consoleDao).updateConsole(consoleUpdate);
        doReturn(consoleUpdate).when(consoleDao).getConsole(42);

        Console consoleDelete = new Console();
        consoleDelete.setConsoleId(43);
        consoleDelete.setModel("PS1");
        consoleDelete.setManufacturer("Microsoft");
        consoleDelete.setMemoryAmount("16");
        consoleDelete.setProcessor("35ghz");
        consoleDelete.setConsolePrice(100);
        consoleDelete.setConsoleQuantity(5);

        doNothing().when(consoleDao).deleteConsole(43);
        doReturn(null).when(consoleDao).getConsole(43);
    }

    private void setUpSalesTaxRateMock() {
        salesTaxRateDao = mock(SalesTaxRateDaoJdbcTemplateImpl.class);

        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState("NY");
        salesTaxRate.setRate(.06);

        SalesTaxRate salesTaxRate1 = new SalesTaxRate();
        salesTaxRate1.setState("NJ");
        salesTaxRate1.setRate(.05);

        List<SalesTaxRate> tList = new ArrayList<>();
        tList.add(salesTaxRate);

        doReturn(salesTaxRate).when(salesTaxRateDao).addSalesTaxRate(salesTaxRate1);
        doReturn(salesTaxRate).when(salesTaxRateDao).getSalesTaxRate("NY");
        doReturn(tList).when(salesTaxRateDao).getAllSalesTaxRates();
    }

    private void setUpProcessingFeeMock() {
        processingFeeDao = mock(ProcessingFeeDaoJdbcTemplateImpl.class);

        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProductType("Games");
        processingFee.setFee(1.49);

        ProcessingFee processingFee1 = new ProcessingFee();
        processingFee1.setProductType("Consoles");
        processingFee1.setFee(14.99);

        List<ProcessingFee> pList = new ArrayList<>();
        pList.add(processingFee);

        doReturn(processingFee).when(processingFeeDao).addProcessingFee(processingFee1);
        doReturn(processingFee).when(processingFeeDao).getProcessingFee("Games");
        doReturn(pList).when(processingFeeDao).getAllProcessingFees();
    }


//    @Test
//    public void saveInvoice() {
//        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
//
//        invoiceViewModel.setName("John Doe");
//        invoiceViewModel.setStreet("123 Main Street");
//        invoiceViewModel.setCity("Queens");
//        invoiceViewModel.setState("NY");
//        invoiceViewModel.setZipcode("11355");
//        invoiceViewModel.setItemType("Games");
//        invoiceViewModel.setItemId(12);
//        invoiceViewModel.setUnitPrice(59.99);
//        invoiceViewModel.setQuantity(100);
//        invoiceViewModel.setSubtotal(5999);
//        invoiceViewModel.setTax(.06);
//        invoiceViewModel.setProcessingFee(1.49);
//        invoiceViewModel.setTotal(6021.98);
//
//        invoiceViewModel = serviceLayer.saveInvoice(invoiceViewModel);
//
//        InvoiceViewModel fromService = serviceLayer.findInvoice(invoiceViewModel.getId());
//        assertEquals(invoiceViewModel, fromService);
//    }

    //          INVOICE TESTS

    @Test
    public void saveFindInvoice() {
        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setName("John Doe");
        invoice.setStreet("123 Main Street");
        invoice.setCity("Queens");
        invoice.setState("NY");
        invoice.setZipcode("11355");
        invoice.setItemType("Games");
        invoice.setItemId(12);
        invoice.setUnitPrice(59.99);
        invoice.setQuantity(100);
        invoice.setSubtotal(5999);
        invoice.setTax(.06);
        invoice.setProcessingFee(1.49);
        invoice.setTotal(6021.98);

        invoice = serviceLayer.saveInvoice(invoice);
        InvoiceViewModel fromService = serviceLayer.findInvoice(invoice.getId());
        assertEquals(invoice, fromService);
    }

    @Test
    public void findAllInvoices() {

        List<InvoiceViewModel> fromService = serviceLayer.findAllInvoices();

        assertEquals(1, fromService.size());
    }

    @Test
    public void updateInvoice(){

        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setId(11);
        invoice.setName("Adam Lee");
        invoice.setStreet("123 Broadway");
        invoice.setCity("New York");
        invoice.setState("NY");
        invoice.setZipcode("10005");
        invoice.setItemType("Games");
        invoice.setItemId(12);
        invoice.setUnitPrice(50);
        invoice.setQuantity(2);
        invoice.setSubtotal(100);
        invoice.setTax(.06);
        invoice.setProcessingFee(1.49);
        invoice.setTotal(107.49);

        serviceLayer.updateInvoice(invoice);

        InvoiceViewModel invoice2 = serviceLayer.findInvoice(invoice.getId());
        assertEquals(invoice2, invoice);

    }

    @Test
    public void removeInvoice(){

        serviceLayer.removeInvoice(43);
        InvoiceViewModel invoice2 = serviceLayer.findInvoice(43);
        assertNull(invoice2);
    }


    //         CONSOLE TESTS

//    @Test
//    public void saveFindConsole() {
//        ConsoleViewModel console = new ConsoleViewModel();
//
//        console.setModel("Switch");
//        console.setManufacturer("Nintendo");
//        console.setMemoryAmount("32GB");
//        console.setProcessor("Nintendo");
//        console.setConsolePrice(199);
//        console.setConsoleQuantity(100);
//
//        console = serviceLayer.addConsole(console);
//        ConsoleViewModel fromService = serviceLayer.getConsole(console.getConsoleId());
//        assertEquals(console, fromService);
//    }
//
//    @Test
//    public void getConsolesByManufacturer() {
//        ConsoleViewModel consoleViewModel = new ConsoleViewModel();
//        consoleViewModel.setModel("Switch");
//        consoleViewModel.setManufacturer("Nintendo");
//        consoleViewModel.setMemoryAmount("32GB");
//        consoleViewModel.setProcessor("Nintendo");
//        consoleViewModel.setConsolePrice(199);
//        consoleViewModel.setConsoleQuantity(100);
//
//        consoleViewModel = serviceLayer.addConsole(consoleViewModel);
//        List<ConsoleViewModel> consoles = serviceLayer.getConsolesByManufacturer("Nintendo");
//
//        assertEquals(1, consoles.size());
//        assertEquals(consoleViewModel, consoles.get(0));
//    }
//
//        @Test
//        public void updateConsole(){
//
//            ConsoleViewModel console = new ConsoleViewModel();
//            console.setConsoleId(42);
//            console.setModel("PS3");
//            console.setManufacturer("Microsoft");
//            console.setMemoryAmount("64");
//            console.setProcessor("35ghz");
//            console.setConsolePrice(200);
//            console.setConsoleQuantity(10);
//
//            serviceLayer.updateConsole(console);
//
//            ConsoleViewModel console2 = serviceLayer.getConsole(console.getConsoleId());
//            assertEquals(console2, console);
//
//        }

        @Test
        public void deleteConsole(){

            serviceLayer.deleteConsole(43);
            ConsoleViewModel console2 = serviceLayer.getConsole(43);
            assertNull(console2);
        }


}
