package com.example.KhoaLeU1Capstone.dao;

import com.example.KhoaLeU1Capstone.model.*;
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
public class SalesTaxRateDaoJdbcTemplateImplTest {

    @Autowired
    GameDao gameDao;
    @Autowired
    TshirtDao tshirtDao;
    @Autowired
    ConsoleDao consoleDao;
    @Autowired
    SalesTaxRateDao salesTaxRateDao;

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

        List<SalesTaxRate> salesTaxRates = salesTaxRateDao.getAllSalesTaxRates();
        for (SalesTaxRate s : salesTaxRates) {
            salesTaxRateDao.deleteSalesTaxRate(s.getState());
        }
    }

    @Test
    public void addGetDeleteSalesTaxRate() {
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState("NY");
        salesTaxRate.setRate(.06);

        salesTaxRate = salesTaxRateDao.addSalesTaxRate(salesTaxRate);

//        SalesTaxRate salesTaxRate1 = salesTaxRateDao.getSalesTaxRate(salesTaxRate.getState());
//        assertEquals(salesTaxRate1,salesTaxRate);

        salesTaxRateDao.deleteSalesTaxRate(salesTaxRate.getState());
        SalesTaxRate salesTaxRate1 = salesTaxRateDao.getSalesTaxRate(salesTaxRate.getState());
        assertNull(salesTaxRate1);
    }


    @Test
    public void updateSalesTaxRate() {

        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState("NY");
        salesTaxRate.setRate(.06);

        salesTaxRate = salesTaxRateDao.addSalesTaxRate(salesTaxRate);

        salesTaxRate.setState("NJ");
        salesTaxRate.setRate(.05);

        salesTaxRateDao.updateSalesTaxRate(salesTaxRate);

//        SalesTaxRate salesTaxRate1  = salesTaxRateDao.getSalesTaxRate(salesTaxRate.getState());
//        assertEquals(salesTaxRate1,salesTaxRate);
    }

    @Test
    public void getAllSalesTaxRates() {
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState("NY");
        salesTaxRate.setRate(.06);

        salesTaxRate = salesTaxRateDao.addSalesTaxRate(salesTaxRate);

        salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState("NJ");
        salesTaxRate.setRate(.05);
        salesTaxRate = salesTaxRateDao.addSalesTaxRate(salesTaxRate);

        List<SalesTaxRate> sList = salesTaxRateDao.getAllSalesTaxRates();
        assertEquals(2, sList.size());
    }
}
