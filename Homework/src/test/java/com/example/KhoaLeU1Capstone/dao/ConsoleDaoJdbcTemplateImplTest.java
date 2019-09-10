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
public class ConsoleDaoJdbcTemplateImplTest {

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
    public void addGetDeleteConsole() {
        Console console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("32GB");
        console.setProcessor("Nintendo");
        console.setConsolePrice(199.00);
        console.setConsoleQuantity(100);

        console = consoleDao.addConsole(console);

        Console console1 = consoleDao.getConsole(console.getConsoleId());
        assertEquals(console1,console);

        consoleDao.deleteConsole(console.getConsoleId());
        console1 = consoleDao.getConsole(console.getConsoleId());
        assertNull(console1);
    }


    @Test
    public void updateConsole() {

        Console console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("32GB");
        console.setProcessor("Nintendo");
        console.setConsolePrice(199.00);
        console.setConsoleQuantity(100);

        console = consoleDao.addConsole(console);

        console.setModel("Switch Lite");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("16GB");
        console.setProcessor("Nintendo");
        console.setConsolePrice(159.00);
        console.setConsoleQuantity(100);
        consoleDao.updateConsole(console);

        Console console1  = consoleDao.getConsole(console.getConsoleId());
        assertEquals(console1,console);
    }

    @Test
    public void getAllCustomers() {
        Console console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("32GB");
        console.setProcessor("Nintendo");
        console.setConsolePrice(199.00);
        console.setConsoleQuantity(100);

        console = consoleDao.addConsole(console);

        console = new Console();
        console.setModel("Switch Lite");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("16GB");
        console.setProcessor("Nintendo");
        console.setConsolePrice(159.00);
        console.setConsoleQuantity(100);

        console = consoleDao.addConsole(console);

        List<Console> cList = consoleDao.getAllConsoles();
        assertEquals(2, cList.size());
    }
}