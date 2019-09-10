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

//import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TshirtDaoJdbcTemplateImplTest {

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
    public void addGetDeleteTshirt() {
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("S");
        tshirt.setColor("red");
        tshirt.setDescription("Nintendo tshirt");
        tshirt.setTshirtPrice(20.00);
        tshirt.setTshirtQuantity(200);

        tshirt = tshirtDao.addTshirt(tshirt);

        Tshirt tshirt1 = tshirtDao.getTshirt(tshirt.getTshirtId());
        assertEquals(tshirt1, tshirt);

        tshirtDao.deleteTshirt(tshirt.getTshirtId());
        tshirt1 = tshirtDao.getTshirt(tshirt.getTshirtId());
        assertNull(tshirt1);
    }


    @Test
    public void updateTshirt() {

        Tshirt tshirt = new Tshirt();
        tshirt.setSize("S");
        tshirt.setColor("red");
        tshirt.setDescription("Nintendo tshirt");
        tshirt.setTshirtPrice(20.00);
        tshirt.setTshirtQuantity(200);

        tshirt = tshirtDao.addTshirt(tshirt);

        tshirt.setSize("XL");
        tshirt.setColor("blue");
        tshirt.setDescription("Pokemon Go");
        tshirt.setTshirtPrice(30.00);
        tshirt.setTshirtQuantity(300);
        tshirtDao.updateTshirt(tshirt);

        Tshirt tshirt1 = tshirtDao.getTshirt(tshirt.getTshirtId());
        assertEquals(tshirt1, tshirt);
    }

    @Test
    public void getAllTshirts() {
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("S");
        tshirt.setColor("red");
        tshirt.setDescription("Nintendo tshirt");
        tshirt.setTshirtPrice(20.00);
        tshirt.setTshirtQuantity(200);

        tshirt = tshirtDao.addTshirt(tshirt);


        tshirt = new Tshirt();
        tshirt.setSize("XL");
        tshirt.setColor("blue");
        tshirt.setDescription("Pokemon Go");
        tshirt.setTshirtPrice(30.00);
        tshirt.setTshirtQuantity(300);

        tshirtDao.addTshirt(tshirt);

        List<Tshirt> tList = tshirtDao.getAllTshirts();
        assertEquals(2, tList.size());
    }
}