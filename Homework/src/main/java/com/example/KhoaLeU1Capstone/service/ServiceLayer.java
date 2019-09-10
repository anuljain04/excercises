package com.example.KhoaLeU1Capstone.service;

import com.example.KhoaLeU1Capstone.dao.*;
import com.example.KhoaLeU1Capstone.exception.NotFoundException;
import com.example.KhoaLeU1Capstone.model.*;
import com.example.KhoaLeU1Capstone.viewmodel.ConsoleViewModel;
import com.example.KhoaLeU1Capstone.viewmodel.GameViewModel;
import com.example.KhoaLeU1Capstone.viewmodel.InvoiceViewModel;
import com.example.KhoaLeU1Capstone.viewmodel.TshirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ServiceLayer {
    private InvoiceDao invoiceDao;
    private ConsoleDao consoleDao;
    private GameDao gameDao;
    private TshirtDao tshirtDao;
    private SalesTaxRateDao salesTaxRateDao;
    private ProcessingFeeDao processingFeeDao;

    InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
    Invoice invoice = new Invoice();
    Game game = new Game();
    Tshirt tshirt = new Tshirt();
    Console console = new Console();
    SalesTaxRate salesTaxRate = new SalesTaxRate();
    ProcessingFee processingFee = new ProcessingFee();

    @Autowired
    public ServiceLayer(InvoiceDao invoiceDao, ConsoleDao consoleDao, GameDao gameDao, TshirtDao tshirtDao, SalesTaxRateDao salesTaxRateDao, ProcessingFeeDao processingFeeDao) {
        this.invoiceDao = invoiceDao;
        this.consoleDao = consoleDao;
        this.gameDao = gameDao;
        this.tshirtDao = tshirtDao;
        this.salesTaxRateDao = salesTaxRateDao;
        this.processingFeeDao = processingFeeDao;
    }

    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel viewModel){

        //checking if invoice quantity smaller than inventory
        if((
                viewModel.getItemType().equals("Games") && game.getGameQuantity() > viewModel.getQuantity()) ||
                viewModel.getItemType().equals("T-Shirts") && tshirt.getTshirtQuantity() > viewModel.getQuantity() ||
                viewModel.getItemType().equals("Consoles") && console.getConsoleQuantity() > viewModel.getQuantity())
            throw new IllegalArgumentException("Not enough inventory");

        //checking if state is valid
        Optional<SalesTaxRate> rate = salesTaxRateDao.getAllSalesTaxRates()
                .stream()
                .filter(x -> x.getState().equals(viewModel.getState()))
                .findAny();

        if(!rate.isPresent()) {
            throw new NotFoundException("invalid state entered");
        }

        Invoice i = new Invoice();
        i.setName(viewModel.getName());
        i.setStreet(viewModel.getStreet());
        i.setCity(viewModel.getCity());
        i.setState(viewModel.getState());
        i.setZipcode(viewModel.getZipcode());
        i.setItemType(viewModel.getItemType());
        i.setItemId(viewModel.getItemId());
        i.setQuantity(viewModel.getQuantity());

        //getting unit price depending on item type
        switch (viewModel.getItemType()) {
            case "Games":
                game = gameDao.getGame(i.getItemId());
                i.setUnitPrice(game.getGamePrice());
                break;
            case "T-Shirts":
                tshirt = tshirtDao.getTshirt(i.getItemId());
                i.setUnitPrice(tshirt.getTshirtPrice());
                break;
            default:
                console = consoleDao.getConsole(i.getItemId());
                i.setUnitPrice(console.getConsolePrice());
        }

        //calculating subtotal, tax, fee, total
        double subtotal = i.getUnitPrice() * i.getQuantity();
        i.setSubtotal(subtotal);

        salesTaxRate = salesTaxRateDao.getSalesTaxRate(i.getState());
        double tax = salesTaxRateDao.getSalesTaxRate(salesTaxRate.getState()).getRate();
        i.setTax(tax);

        processingFee = processingFeeDao.getProcessingFee(i.getItemType());
        double fee = processingFeeDao.getProcessingFee(processingFee.getProductType()).getFee();
        i.setProcessingFee(fee);

        double total = subtotal + (tax * 100) + fee;
        i.setTotal(i.getQuantity() > 10 ? total + 15.49 : total);

        i = invoiceDao.addInvoice(i);
        viewModel.setId(i.getInvoiceId());

        //updating quantity after issuing an invoice
        switch (viewModel.getItemType()) {
            case "Games":
                game = gameDao.getGame(i.getItemId());
                game.setGameQuantity(game.getGameQuantity() - viewModel.getQuantity());
                break;
            case "T-Shirts":
                tshirt = tshirtDao.getTshirt(i.getItemId());
                tshirt.setTshirtQuantity(tshirt.getTshirtQuantity() - viewModel.getQuantity());
                break;
            default:
                console = consoleDao.getConsole(i.getItemId());
                console.setConsoleQuantity(console.getConsoleQuantity() - viewModel.getQuantity());
        }

        return viewModel;
    }

    public InvoiceViewModel findInvoice(int id) {
        Invoice invoice = invoiceDao.getInvoice(id);
        if (invoice == null) {
            return null;
        }
        return buildInvoiceViewModel(invoice);
    }

    public List<InvoiceViewModel> findAllInvoices() {
        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        List<InvoiceViewModel> ivmList = new ArrayList<>();
        for (Invoice invoice : invoiceList) {
            InvoiceViewModel ivm = buildInvoiceViewModel(invoice);
            ivmList.add(ivm);
        }
        return ivmList;
    }

    @Transactional
    public void updateInvoice(InvoiceViewModel viewModel) {
        Invoice i = new Invoice();
        i.setInvoiceId(viewModel.getId());
        i.setName(viewModel.getName());
        i.setStreet(viewModel.getStreet());
        i.setCity(viewModel.getCity());
        i.setState(viewModel.getState());
        i.setZipcode(viewModel.getZipcode());
        i.setItemType(viewModel.getItemType());
        i.setItemId(viewModel.getItemId());
        i.setUnitPrice(viewModel.getUnitPrice());
        i.setQuantity(viewModel.getQuantity());
        i.setSubtotal(viewModel.getSubtotal());
        i.setTax(viewModel.getTax());
        i.setProcessingFee(viewModel.getProcessingFee());
        i.setTotal(viewModel.getTotal());
        invoiceDao.updateInvoice(i);

    }

    @Transactional
    public void removeInvoice(int invoiceId) {

        invoiceDao.deleteInvoice(invoiceId);
    }

    //  CONSOLE

    @Transactional
    public ConsoleViewModel addConsole(ConsoleViewModel viewModel) {
        Console i = new Console();

        i.setModel(viewModel.getModel());
        i.setManufacturer(viewModel.getManufacturer());
        i.setProcessor(viewModel.getProcessor());
        i.setConsolePrice(viewModel.getConsolePrice());
        i.setConsoleQuantity(viewModel.getConsoleQuantity());
        i = consoleDao.addConsole(i);
        viewModel.setConsoleId(i.getConsoleId());

        return viewModel;
    }

    public ConsoleViewModel getConsole(int id) {
        Console console = consoleDao.getConsole(id);
        if (console == null) {
            return null;
        }
        return buildConsoleViewModel(console);
    }

    public List<ConsoleViewModel> getConsolesByManufacturer(String manufacturer) {
        List<Console> cList = consoleDao.getConsolesByManufacturer(manufacturer);
        List<ConsoleViewModel> cvmList = new ArrayList<>();
        for (Console console : cList) {
            ConsoleViewModel cvm = buildConsoleViewModel(console);
            cvmList.add(cvm);
        }
        return cvmList;
    }

    public List<ConsoleViewModel> getAllConsoles() {
        List<Console> cList = consoleDao.getAllConsoles();
        List<ConsoleViewModel> cvmList = new ArrayList<>();
        for (Console console : cList) {
            ConsoleViewModel cvm = buildConsoleViewModel(console);
            cvmList.add(cvm);
        }
        return cvmList;
    }

    @Transactional
    public void updateConsole(ConsoleViewModel viewModel) {
        Console i = new Console();
        i.setConsoleId(viewModel.getConsoleId());
        i.setModel(viewModel.getModel());
        i.setManufacturer(viewModel.getManufacturer());
        i.setProcessor(viewModel.getProcessor());
        i.setConsolePrice(viewModel.getConsolePrice());
        i.setConsoleQuantity(viewModel.getConsoleQuantity());

        consoleDao.updateConsole(i);

    }

    @Transactional
    public void deleteConsole(int consoleId) {

        consoleDao.deleteConsole(consoleId);
    }

    //GAME

    @Transactional
    public GameViewModel saveGame(GameViewModel viewModel) {
        Game i = new Game();

        i.setTitle(viewModel.getTitle());
        i.setEsrbRating(viewModel.getEsrbRating());
        i.setDescription(viewModel.getDescription());
        i.setGamePrice(viewModel.getGamePrice());
        i.setStudio(viewModel.getStudio());
        i.setGameQuantity(viewModel.getGameQuantity());
        i = gameDao.addGame(i);
        viewModel.setGameId(i.getGameId());

        return viewModel;
    }

    public GameViewModel findGame(int id) {
        Game game = gameDao.getGame(id);
        if (game == null) {
            return null;
        }
        return buildGameViewModel(game);
    }

    public List<GameViewModel> findGamesByTitle(String title) {
        List<Game> gList = gameDao.getGamesByTitle(title);
        List<GameViewModel> gvmList = new ArrayList<>();
        for (Game game : gList) {
            GameViewModel gvm = buildGameViewModel(game);
            gvmList.add(gvm);
        }
        return gvmList;
    }

    public List<GameViewModel> findGamesByEsrbRating(String esrbRating) {
        List<Game> gList = gameDao.getGamesByEsrbRating(esrbRating);
        List<GameViewModel> gvmList = new ArrayList<>();
        for (Game game : gList) {
            GameViewModel gvm = buildGameViewModel(game);
            gvmList.add(gvm);
        }
        return gvmList;
    }

    public List<GameViewModel> findGamesByStudio(String studio) {
        List<Game> gList = gameDao.getGamesByStudio(studio);
        List<GameViewModel> gvmList = new ArrayList<>();
        for (Game game : gList) {
            GameViewModel gvm = buildGameViewModel(game);
            gvmList.add(gvm);
        }
        return gvmList;
    }

    public List<GameViewModel> findAllGames() {
        List<Game> gList = gameDao.getAllGames();
        List<GameViewModel> gvmList = new ArrayList<>();
        for (Game game : gList) {
            GameViewModel gvm = buildGameViewModel(game);
            gvmList.add(gvm);
        }
        return gvmList;
    }

    @Transactional
    public void updateGame(GameViewModel viewModel) {
        Game i = new Game();
        i.setGameId(viewModel.getGameId());
        i.setTitle(viewModel.getTitle());
        i.setEsrbRating(viewModel.getEsrbRating());
        i.setDescription(viewModel.getDescription());
        i.setGamePrice(viewModel.getGamePrice());
        i.setStudio(viewModel.getStudio());
        i.setGameQuantity(viewModel.getGameQuantity());

        gameDao.updateGame(i);

    }

    @Transactional
    public void removeGame(int gameId) {

        gameDao.deleteGame(gameId);
    }

    //T-SHIRT


    @Transactional
    public TshirtViewModel saveTshirt(TshirtViewModel viewModel) {
        Tshirt i = new Tshirt();

        i.setSize(viewModel.getSize());
        i.setColor(viewModel.getColor());
        i.setDescription(viewModel.getDescription());
        i.setTshirtPrice(viewModel.getTshirtPrice());
        i.setTshirtQuantity(viewModel.getTshirtQuantity());
        i = tshirtDao.addTshirt(i);
        viewModel.setTshirtId(i.getTshirtId());

        return viewModel;
    }

    public TshirtViewModel findTshirt(int id) {
        Tshirt tshirt = tshirtDao.getTshirt(id);
        if (tshirt == null) {
            return null;
        }
        return buildTshirtViewModel(tshirt);
    }

    public List<TshirtViewModel> findTshirtsByColor(String color) {
        List<Tshirt> tList = tshirtDao.getTshirtsByColor(color);
        List<TshirtViewModel> tvmList = new ArrayList<>();
        for (Tshirt tshirt : tList) {
            TshirtViewModel tvm = buildTshirtViewModel(tshirt);
            tvmList.add(tvm);
        }
        return tvmList;
    }

    public List<TshirtViewModel> findTshirtsBySize(String size) {
        List<Tshirt> tList = tshirtDao.getTshirtsByColor(size);
        List<TshirtViewModel> tvmList = new ArrayList<>();
        for (Tshirt tshirt : tList) {
            TshirtViewModel tvm = buildTshirtViewModel(tshirt);
            tvmList.add(tvm);
        }
        return tvmList;
    }

    public List<TshirtViewModel> findAllTshirts() {
        List<Tshirt> tList = tshirtDao.getAllTshirts();
        List<TshirtViewModel> tvmList = new ArrayList<>();
        for (Tshirt tshirt : tList) {
            TshirtViewModel tvm = buildTshirtViewModel(tshirt);
            tvmList.add(tvm);
        }
        return tvmList;
    }

    @Transactional
    public void updateTshirt(TshirtViewModel viewModel) {
        Tshirt i = new Tshirt();
        i.setTshirtId(viewModel.getTshirtId());
        i.setSize(viewModel.getSize());
        i.setColor(viewModel.getColor());
        i.setDescription(viewModel.getDescription());
        i.setTshirtPrice(viewModel.getTshirtPrice());
        i.setTshirtQuantity(viewModel.getTshirtQuantity());

        tshirtDao.updateTshirt(i);

    }

    @Transactional
    public void removeTshirt(int tshirtId) {

        tshirtDao.deleteTshirt(tshirtId);
    }

    // Helper Methods
    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setId(invoice.getInvoiceId());
        invoiceViewModel.setName(invoice.getName());
        invoiceViewModel.setStreet(invoice.getStreet());
        invoiceViewModel.setCity(invoice.getCity());
        invoiceViewModel.setState(invoice.getState());
        invoiceViewModel.setZipcode(invoice.getZipcode());
        invoiceViewModel.setQuantity(invoice.getQuantity());
        invoiceViewModel.setItemId((invoice.getItemId()));
        invoiceViewModel.setItemType(invoice.getItemType());
        invoiceViewModel.setUnitPrice(invoice.getUnitPrice());
        invoiceViewModel.setTax(invoice.getTax());
        invoiceViewModel.setProcessingFee(invoice.getProcessingFee());
        invoiceViewModel.setSubtotal(invoice.getSubtotal());
        invoiceViewModel.setTotal(invoice.getTotal());

        return invoiceViewModel;
    }

    private ConsoleViewModel buildConsoleViewModel(Console console) {

        ConsoleViewModel consoleViewModel = new ConsoleViewModel();
        consoleViewModel.setConsoleId(console.getConsoleId());
        consoleViewModel.setModel(console.getModel());
        consoleViewModel.setManufacturer(console.getManufacturer());
        consoleViewModel.setProcessor(console.getProcessor());
        consoleViewModel.setConsolePrice(console.getConsolePrice());
        consoleViewModel.setConsoleQuantity(console.getConsoleQuantity());

        return consoleViewModel;
    }

    private GameViewModel buildGameViewModel(Game game) {

        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setGameId(game.getGameId());
        gameViewModel.setTitle(game.getTitle());
        gameViewModel.setEsrbRating(game.getEsrbRating());
        gameViewModel.setDescription(game.getDescription());
        gameViewModel.setGamePrice(game.getGamePrice());
        gameViewModel.setStudio(game.getStudio());
        gameViewModel.setGameQuantity(game.getGameQuantity());

        return gameViewModel;
    }

    private TshirtViewModel buildTshirtViewModel(Tshirt tshirt) {

        TshirtViewModel tshirtViewModel = new TshirtViewModel();
        tshirtViewModel.setTshirtId(tshirt.getTshirtId());
        tshirtViewModel.setSize(tshirt.getSize());
        tshirtViewModel.setColor(tshirt.getColor());
        tshirtViewModel.setDescription(tshirt.getDescription());
        tshirtViewModel.setTshirtPrice(tshirt.getTshirtPrice());
        tshirtViewModel.setTshirtQuantity(tshirt.getTshirtQuantity());

        return tshirtViewModel;
    }
}







