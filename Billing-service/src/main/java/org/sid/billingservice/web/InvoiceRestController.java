package org.sid.billingservice.web;


import org.sid.billingservice.dto.InvoiceRequesetdto;
import org.sid.billingservice.dto.InvoiceRespansedto;
import org.sid.billingservice.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class InvoiceRestController {
    private InvoiceService invoiceService;

    public InvoiceRestController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(path = "/invoices/{id}")
    public InvoiceRespansedto getInvoice(@PathVariable(name = "id") String id){
        return invoiceService.getInvoice(id);
    }
    @PostMapping(path = "/invoices/")
    public InvoiceRespansedto saveInvoice(@RequestBody InvoiceRequesetdto invoiceRequesetdto){
        return invoiceService.save(invoiceRequesetdto);
    }
    @GetMapping(path = "/invoices/customer/{customerId}")
    public List<InvoiceRespansedto> getInvoicesByCustomer(@PathVariable(name = "customerId") String customerId){
        return invoiceService.invoicesByCustomerId(customerId);
    }
    @GetMapping(path = "/invoices")
    public List<InvoiceRespansedto> allInvoices(){
        return invoiceService.allInvoices();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionhandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
