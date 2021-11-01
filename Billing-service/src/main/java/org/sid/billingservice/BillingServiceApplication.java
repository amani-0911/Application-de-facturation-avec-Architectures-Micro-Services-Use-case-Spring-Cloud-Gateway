package org.sid.billingservice;

import org.sid.billingservice.dto.InvoiceRequesetdto;
import org.sid.billingservice.service.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(InvoiceService invoiceService){
        return args -> {
            invoiceService.save(new InvoiceRequesetdto(BigDecimal.valueOf(2000),"c01"));
            invoiceService.save(new InvoiceRequesetdto(BigDecimal.valueOf(40278),"c02"));


        } ;
    }
}
