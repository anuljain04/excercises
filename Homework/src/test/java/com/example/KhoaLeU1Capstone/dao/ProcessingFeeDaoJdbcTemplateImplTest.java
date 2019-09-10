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
public class ProcessingFeeDaoJdbcTemplateImplTest {

    @Autowired
    GameDao gameDao;
    @Autowired
    TshirtDao tshirtDao;
    @Autowired
    ConsoleDao consoleDao;
    @Autowired
    ProcessingFeeDao processingFeeDao;

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

        List<ProcessingFee> processingFees = processingFeeDao.getAllProcessingFees();
        for (ProcessingFee p : processingFees) {
            processingFeeDao.deleteProcessingFee(p.getProductType());
        }
    }

    @Test
    public void addGetDeleteProcessingFee() {
        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProductType("Games");
        processingFee.setFee(1.49);

        processingFee = processingFeeDao.addProcessingFee(processingFee);

//        ProcessingFee processingFee1 = processingFeeDao.getProcessingFee(processingFee);
//        assertEquals(processingFee1,processingFee);

        processingFeeDao.deleteProcessingFee(processingFee.getProductType());
        ProcessingFee processingFee1 = processingFeeDao.getProcessingFee(processingFee.getProductType());
        assertNull(processingFee1);
    }


    @Test
    public void updateProcessingFee() {

        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProductType("Games");
        processingFee.setFee(1.49);

        processingFee = processingFeeDao.addProcessingFee(processingFee);

        processingFee.setProductType("Consoles");
        processingFee.setFee(14.99);

        processingFeeDao.updateProcessingFee(processingFee);
//
//        ProcessingFee processingFee1  = processingFeeDao.getProcessingFee(processingFee.getProductType());
//        assertEquals(processingFee1,processingFee);
    }

    @Test
    public void getAllProcessingFees() {
        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProductType("Games");
        processingFee.setFee(1.49);

        processingFee = processingFeeDao.addProcessingFee(processingFee);

        processingFee = new ProcessingFee();
        processingFee.setProductType("Consoles");
        processingFee.setFee(14.99);
        processingFee = processingFeeDao.addProcessingFee(processingFee);

        List<ProcessingFee> pList = processingFeeDao.getAllProcessingFees();
        assertEquals(2, pList.size());
    }
}