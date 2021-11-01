package org.sid.billingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.billingservice.entities.Customer;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;


@Data @NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequesetdto {
    private BigDecimal amount;
    private String customerId;
}
