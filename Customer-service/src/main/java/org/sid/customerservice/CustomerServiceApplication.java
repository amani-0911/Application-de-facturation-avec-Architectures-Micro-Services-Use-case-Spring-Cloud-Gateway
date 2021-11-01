package org.sid.customerservice;

import org.sid.customerservice.dto.CustomerRequestDTO;
import org.sid.customerservice.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
   @Bean
    CommandLineRunner start(CustomerService customerService){
      return args -> {
customerService.save(new CustomerRequestDTO("c01","adria","adrai@adria.com"));
customerService.save(new CustomerRequestDTO("c02","nimpleways","nimpleways@nimpleways.com"));


      } ;
   }
}
