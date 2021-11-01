package org.sid.billingservice.service;

import org.sid.billingservice.dto.InvoiceRequesetdto;
import org.sid.billingservice.dto.InvoiceRespansedto;
import org.sid.billingservice.entities.Customer;
import org.sid.billingservice.entities.Invoice;
import org.sid.billingservice.exception.CutommerNotFoundException;
import org.sid.billingservice.mapper.InvoiveMapper;
import org.sid.billingservice.openFeign.CustomerRestClient;
import org.sid.billingservice.repository.InvoceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
   private InvoceRepository invoceRepository;
   private InvoiveMapper invoiveMapper;
   private CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoceRepository invoceRepository, InvoiveMapper invoiveMapper, CustomerRestClient customerRestClient) {
        this.invoceRepository = invoceRepository;
        this.invoiveMapper = invoiveMapper;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public InvoiceRespansedto save(InvoiceRequesetdto invoiceRequesetdto) {
        Invoice invontory=invoiveMapper.fromInvoicequestDTO(invoiceRequesetdto);
        Customer customer=null;
    try {
         customer = customerRestClient.getCustomer(invontory.getCustomerId());
    }catch(Exception e){
       throw new CutommerNotFoundException("Customer not found");
    }
        invontory.setId(UUID.randomUUID().toString());
        invontory.setDate(new Date());

        Invoice invontorysave=invoceRepository.save(invontory);
        invontorysave.setCustomer(customer);
        return invoiveMapper.fromInvoice(invontorysave);
    }

    @Override
    public InvoiceRespansedto getInvoice(String id) {
        Invoice invoice= invoceRepository.findById(id).get();
        Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
         invoice.setCustomer(customer);
        return invoiveMapper.fromInvoice(invoice);
    }

    @Override
    public List<InvoiceRespansedto> invoicesByCustomerId(String customerId) {
        List<Invoice> invoices=invoceRepository.findBycustomerId(customerId);
       invoices.forEach(invoice -> {
           Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
           invoice.setCustomer(customer);
       });

        return invoices.stream().map(invoice ->invoiveMapper.fromInvoice(invoice)).collect(Collectors.toList());
    }

    @Override
    public List<InvoiceRespansedto> allInvoices() {
        List<Invoice> invoices=invoceRepository.findAll();
        invoices.forEach(invoice -> {
            Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
        });

        return invoices.stream().map(invoice ->invoiveMapper.fromInvoice(invoice)).collect(Collectors.toList());

    }
}
