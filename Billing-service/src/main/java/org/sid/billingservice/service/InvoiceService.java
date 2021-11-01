package org.sid.billingservice.service;

import org.sid.billingservice.dto.InvoiceRequesetdto;
import org.sid.billingservice.dto.InvoiceRespansedto;

import java.util.List;

public interface InvoiceService {
    public InvoiceRespansedto save(InvoiceRequesetdto invoiceRequesetdto);
    public InvoiceRespansedto getInvoice(String id);
    public List<InvoiceRespansedto> invoicesByCustomerId(String customerId);

    List<InvoiceRespansedto> allInvoices();
}
