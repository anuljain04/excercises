package com.example.KhoaLeU1Capstone.controller;

import com.example.KhoaLeU1Capstone.exception.NotFoundException;
import com.example.KhoaLeU1Capstone.model.Console;
import com.example.KhoaLeU1Capstone.service.ServiceLayer;
import com.example.KhoaLeU1Capstone.viewmodel.ConsoleViewModel;
import com.example.KhoaLeU1Capstone.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
//@RequestMapping("/console")
public class ConsoleController {

    @Autowired
    ServiceLayer serviceLayer;

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ConsoleViewModel addConsole(@RequestBody @Valid ConsoleViewModel console) {
//
//        return console;
//    }
//
//    @GetMapping
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<ConsoleViewModel> getAllConsoles() {
//
//        List<ConsoleViewModel> consoleList = new ArrayList<ConsoleViewModel>();
//        return consoleList;
//
//    }
//
//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public ConsoleViewModel getConsole(@PathVariable int id) {
//        ConsoleViewModel consoleViewModel = serviceLayer.getConsole(id);
//        if (consoleViewModel == null)
//            throw new NotFoundException("Invoice could not be retrieved for id " + id);
//        return consoleViewModel;
//    }
//
//    @RequestMapping(value = "/console1/{manufacturer}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public List<ConsoleViewModel> getConsolesByManufacturer(@PathVariable String manufacturer) {
//
//        List<ConsoleViewModel> consoleViewModel = serviceLayer.getConsolesByManufacturer(manufacturer);
//        if (consoleViewModel == null)
//            throw new NotFoundException("Invoice could not be retrieved for manufacturer " + manufacturer);
//        return consoleViewModel;
//    }
//
// //   @DeleteMapping("/{id}")
//    @RequestMapping(value = "/console/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteConsole(@PathVariable int id) {
//        deleteConsole(id);
//    }
//
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateConsole(@PathVariable int consoleId, @RequestBody @Valid Console console) {
//        if (console.getConsoleId() == 0)
//            console.setConsoleId(consoleId);
//        if (consoleId != console.getConsoleId()) {
//            throw new IllegalArgumentException("Console ID on path must match the ID in the Console object");
//        }
//        updateConsole(consoleId, console);
//    }





   // @PostMapping
    @RequestMapping(value = "/console", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ConsoleViewModel addConsole(@RequestBody @Valid ConsoleViewModel consoleViewModel){
        serviceLayer.addConsole(consoleViewModel);
        return consoleViewModel;
    }

  //  @GetMapping("/{id}")
  @RequestMapping(value = "/console/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public ConsoleViewModel getConsole(@PathVariable int id){
        ConsoleViewModel consoleViewModel = serviceLayer.getConsole(id);
        if(consoleViewModel == null)
            throw new NoSuchElementException("Console could not be retrieve for this id " + id);
        return consoleViewModel;
    }

    @RequestMapping(value = "/console", method = RequestMethod.GET)
    //@GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<ConsoleViewModel> getAllConsoles(){

        return serviceLayer.getAllConsoles();
    }

    @RequestMapping(value = "/console/{id}", method = RequestMethod.PUT)
 //   @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateConsole(@PathVariable (name = "id") int consoleId, @Valid @RequestBody ConsoleViewModel consoleViewModel){
        if (consoleViewModel.getConsoleId()== 0)
            consoleViewModel.setConsoleId(consoleId);
        if(consoleId != consoleViewModel.getConsoleId()){
            throw new IllegalArgumentException("Console Id on the path must match in the Console object ");
        }
        serviceLayer.updateConsole(consoleViewModel);
    }

    @RequestMapping(value = "/console/{id}", method = RequestMethod.DELETE)
  //  @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteConsole(@PathVariable (name = "id") int id){

        ConsoleViewModel consoleViewModel = serviceLayer.getConsole(id);

        if(consoleViewModel == null) {
            throw new NotFoundException("No console with this Id");
        } else {
            serviceLayer.deleteConsole(id);
        }
    }

    @RequestMapping(value = "/console/manufacturer/{manufacturer}", method = RequestMethod.GET)
//    @GetMapping("/manufacturer/{manufacturer}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ConsoleViewModel> getConsolesByManufacturer(@PathVariable (name = "manufacturer") String manufacturer) throws IllegalArgumentException{
        List<ConsoleViewModel> consoleViewModelList = serviceLayer.getConsolesByManufacturer(manufacturer);
        if (consoleViewModelList == null) throw new NotFoundException("This manufacturer doesn't have any game");
        return consoleViewModelList;
    }
}
