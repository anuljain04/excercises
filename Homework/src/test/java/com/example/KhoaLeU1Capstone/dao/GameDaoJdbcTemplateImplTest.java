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
public class GameDaoJdbcTemplateImplTest {

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
    public void addGetDeleteGame() {
        Game game = new Game();
        game.setTitle("Super Mario");
        game.setEsrbRating("E");
        game.setDescription("Unleash your creativity");
        game.setGamePrice(59.99);
        game.setStudio("Nintendo");
        game.setGameQuantity(100);

        game = gameDao.addGame(game);

        Game game1 = gameDao.getGame(game.getGameId());
        assertEquals(game1,game);

        gameDao.deleteGame(game.getGameId());
        game1 = gameDao.getGame(game.getGameId());
        assertNull(game1);
    }


    @Test
    public void updateGame() {

        Game game = new Game();
        game.setTitle("Super Mario");
        game.setEsrbRating("E");
        game.setDescription("Unleash your creativity");
        game.setGamePrice(59.99);
        game.setStudio("Nintendo");
        game.setGameQuantity(100);

        game = gameDao.addGame(game);

        game.setTitle("Pokemon Sword and Shield");
        game.setEsrbRating("E");
        game.setDescription("Prepare for the battle");
        game.setGamePrice(69.99);
        game.setStudio("Nintendo");
        game.setGameQuantity(200);
        gameDao.updateGame(game);

        Game game1  = gameDao.getGame(game.getGameId());
        assertEquals(game1,game);
    }

    @Test
    public void getAllGames() {
        Game game = new Game();
        game.setTitle("Super Mario");
        game.setEsrbRating("E");
        game.setDescription("Unleash your creativity");
        game.setGamePrice(59.99);
        game.setStudio("Nintendo");
        game.setGameQuantity(100);

        game = gameDao.addGame(game);

        game = new Game();
        game.setTitle("Pokemon Sword and Shield");
        game.setEsrbRating("E");
        game.setDescription("Prepare for the battle");
        game.setGamePrice(69.99);
        game.setStudio("Nintendo");
        game.setGameQuantity(200);

        gameDao.addGame(game);

        List<Game> gList = gameDao.getAllGames();
        assertEquals(2, gList.size());
    }
}