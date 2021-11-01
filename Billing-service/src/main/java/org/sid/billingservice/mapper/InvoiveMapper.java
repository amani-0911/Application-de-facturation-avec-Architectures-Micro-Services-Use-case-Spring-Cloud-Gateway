package org.sid.billingservice.mapper;

import org.mapstruct.Mapper;
import org.sid.billingservice.dto.InvoiceRequesetdto;
import org.sid.billingservice.dto.InvoiceRespansedto;
import org.sid.billingservice.entities.Invoice;

@Mapper(componentModel = "spring")
public interface InvoiveMapper {
    Invoice fromInvoicequestDTO(InvoiceRequesetdto invoiceRequesetdto);
    InvoiceRespansedto fromInvoice(Invoice invoice);
}
