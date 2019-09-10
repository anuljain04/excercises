package com.example.KhoaLeU1Capstone.controller;

import com.example.KhoaLeU1Capstone.exception.NotFoundException;
import com.example.KhoaLeU1Capstone.model.Game;
import com.example.KhoaLeU1Capstone.service.ServiceLayer;
import com.example.KhoaLeU1Capstone.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
//@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    ServiceLayer serviceLayer;

    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public InvoiceViewModel addInvoice(@RequestBody @Valid InvoiceViewModel invoiceViewModel) {
        return serviceLayer.saveInvoice(invoiceViewModel);
    }

    //find invoice
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public InvoiceViewModel getInvoice(@PathVariable(name = "id") int id) {
        InvoiceViewModel invoiceViewModel = serviceLayer.findInvoice(id);
        if (invoiceViewModel == null)
            throw new NoSuchElementException("Invoice could not be retrieve for this id " + id);
        return invoiceViewModel;
    }

    //find All Invoices
    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoices() {
        return serviceLayer.findAllInvoices();
    }

    //update Invoice
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateInvoice(@PathVariable(name = "id") int invoiceId, @Valid @RequestBody InvoiceViewModel invoiceViewModel) {
        if (invoiceViewModel.getId() == 0)
            invoiceViewModel.setId(invoiceId);
        if (invoiceId != invoiceViewModel.getId()) {
            throw new IllegalArgumentException("Invoice Id on the path must match in the Invoice object");
        }
        serviceLayer.updateInvoice(invoiceViewModel);
    }

    //delete invoice

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteInvoice(@PathVariable(name = "id") int id) {
        InvoiceViewModel invoiceViewModel = serviceLayer.findInvoice(id);

        if (invoiceViewModel == null) {
            throw new NotFoundException("No invoice available with this Id. ");
        } else {
            serviceLayer.removeInvoice(id);
        }
    }
}

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public InvoiceViewModel createInvoice(@RequestBody @Valid InvoiceViewModel invoice) {
//        return serviceLayer.saveInvoice(invoice);
//    }
//
//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public InvoiceViewModel getInvoice(@PathVariable int invoiceId) {
//        InvoiceViewModel invoiceViewModel = serviceLayer.findInvoice(invoiceId);
//        if (invoiceViewModel == null)
//            throw new NotFoundException("Invoice could not be retrieved for id " + invoiceId);
//        return invoiceViewModel;
//    }
//
//    @GetMapping
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<InvoiceViewModel> getAllInvoices() {
//        List<InvoiceViewModel> iList = new ArrayList<InvoiceViewModel>();
//        return iList;
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteInvoice(@PathVariable int invoiceId) {
//
//        serviceLayer.removeInvoice(invoiceId);
//    }
//
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateInvoice(@PathVariable int invoiceId, @RequestBody @Valid InvoiceViewModel invoiceViewModel) {
//        if (invoiceViewModel.getId() == 0)
//            invoiceViewModel.setId(invoiceId);
//        if (invoiceId != invoiceViewModel.getId()) {
//            throw new IllegalArgumentException("Invoice ID on path must match the ID in the Invoice object");
//        }
//        serviceLayer.updateInvoice(invoiceViewModel);
//    }
//
//}

/*
{
 "consoleId": 10,
 "model": "1234",
 "manufacturer": "Samsung",
 "memoryAmount": "126GB",
 "processor": "Samsung II",
 "consolePrice": 55,
 "consoleQuantity": 100
}

{
"invoiceId": 11,
"name": "John",
"street": "123 Broadway",
"city": "New York",
"state": "NY",
"zipcode": "10005",
"itemType": "Games",
"itemId": 12,
"quantity": 10
}

 */