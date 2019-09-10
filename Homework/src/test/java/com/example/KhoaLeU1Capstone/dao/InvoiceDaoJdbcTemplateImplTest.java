package com.example.KhoaLeU1Capstone.dao;

import com.example.KhoaLeU1Capstone.model.Console;
import com.example.KhoaLeU1Capstone.model.Game;
import com.example.KhoaLeU1Capstone.model.Invoice;
import com.example.KhoaLeU1Capstone.model.Tshirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoJdbcTemplateImplTest {

    @Autowired
    GameDao gameDao;
    @Autowired
    TshirtDao tshirtDao;
    @Autowired
    ConsoleDao consoleDao;
    @Autowired
    InvoiceDao invoiceDao;

    @Before
    public void setUp() throws Exception {
        List<Game> games = gameDao.getAllGames();
        for (Game it : games) {
            gameDao.deleteGame(it.getGameId());
        }

        List<Tshirt> tshirts = tshirtDao.getAllTshirts();
        for (Tshirt t : tshirts) {
            tshirtDao.deleteTshirt(t.getTshirtId());
        }

        List<Console> consoles = consoleDao.getAllConsoles();
        for (Console c : consoles) {
            consoleDao.deleteConsole(c.getConsoleId());
        }

        List<Invoice> invoices = invoiceDao.getAllInvoices();
        for (Invoice i : invoices) {
            invoiceDao.deleteInvoice(i.getInvoiceId());
        }
    }

    @Test
    public void addGetDeleteInvoice() {
        Invoice invoice = new Invoice();
        invoice.setName("John Doe");
        invoice.setStreet("123 Main Street");
        invoice.setCity("Queens");
        invoice.setState("NY");
        invoice.setZipcode("11355");
        invoice.setItemType("game");
        invoice.setItemId(12);
        invoice.setUnitPrice(5);
        invoice.setQuantity(10);
        invoice.setSubtotal(50);
        invoice.setTax(.06);
        invoice.setProcessingFee(16.98);
        invoice.setTotal(69.98);

        invoice = invoiceDao.addInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());
        assertEquals(invoice1,invoice);

        invoiceDao.deleteInvoice(invoice.getInvoiceId());
        invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());
        assertNull(invoice1);
    }


    @Test
    public void updateConsole() {

        Invoice invoice = new Invoice();
        invoice.setName("John Doe");
        invoice.setStreet("123 Main Street");
        invoice.setCity("Queens");
        invoice.setState("NY");
        invoice.setZipcode("11355");
        invoice.setItemType("game");
        invoice.setItemId(12);
        invoice.setUnitPrice(5);
        invoice.setQuantity(10);
        invoice.setSubtotal(50);
        invoice.setTax(.06);
        invoice.setProcessingFee(16.98);
        invoice.setTotal(69.98);

        invoice = invoiceDao.addInvoice(invoice);

        invoice.setName("Jane Williams");
        invoice.setStreet("567 Olive Street");
        invoice.setCity("Brooklyn");
        invoice.setState("NY");
        invoice.setZipcode("11555");
        invoice.setItemType("game");
        invoice.setItemId(12);
        invoice.setUnitPrice(5);
        invoice.setQuantity(10);
        invoice.setSubtotal(50);
        invoice.setTax(.06);
        invoice.setProcessingFee(16.98);
        invoice.setTotal(69.98);

        invoiceDao.updateInvoice(invoice);

        Invoice invoice1  = invoiceDao.getInvoice(invoice.getInvoiceId());
        assertEquals(invoice1,invoice);
    }

    @Test
    public void getAllInvoices() {
        Invoice invoice = new Invoice();
        invoice.setName("John Doe");
        invoice.setStreet("123 Main Street");
        invoice.setCity("Queens");
        invoice.setState("NY");
        invoice.setZipcode("11355");
        invoice.setItemType("game");
        invoice.setItemId(12);
        invoice.setUnitPrice(5);
        invoice.setQuantity(10);
        invoice.setSubtotal(50);
        invoice.setTax(.06);
        invoice.setProcessingFee(16.98);
        invoice.setTotal(69.98);

        invoice = invoiceDao.addInvoice(invoice);

        invoice = new Invoice();
        invoice.setName("Jane Williams");
        invoice.setStreet("567 Olive Street");
        invoice.setCity("Brooklyn");
        invoice.setState("NY");
        invoice.setZipcode("11555");
        invoice.setItemType("game");
        invoice.setItemId(12);
        invoice.setUnitPrice(5);
        invoice.setQuantity(10);
        invoice.setSubtotal(50);
        invoice.setTax(.06);
        invoice.setProcessingFee(16.98);
        invoice.setTotal(69.98);

        invoice = invoiceDao.addInvoice(invoice);

        List<Invoice> iList = invoiceDao.getAllInvoices();
        assertEquals(2, iList.size());
    }
}