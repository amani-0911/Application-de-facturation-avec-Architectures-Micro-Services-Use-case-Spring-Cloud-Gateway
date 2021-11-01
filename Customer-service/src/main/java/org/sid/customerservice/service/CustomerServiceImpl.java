package org.sid.customerservice.service;

import org.sid.customerservice.dto.CustomerRequestDTO;
import org.sid.customerservice.dto.CustomerResponseDTO;
import org.sid.customerservice.entities.Customer;
import org.sid.customerservice.mappers.CustomerMapper;
import org.sid.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements  CustomerService{
   private CustomerRepository customerRepository;
   private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository,CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper=customerMapper;
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        Customer customer=customerMapper.CustomeerRequestDTOTocustomer(customerRequestDTO);
       Customer customerSave=customerRepository.save(customer);
       CustomerResponseDTO customerResponseDTO=customerMapper.customerToCustomerRespenseDTO(customerSave);
        return customerResponseDTO;
    }

    @Override
    public CustomerResponseDTO getCustomer(String id) {
        Customer customer=customerRepository.findById(id).get();
        CustomerResponseDTO customerResponseDTO=customerMapper.customerToCustomerRespenseDTO(customer);
        return customerResponseDTO;

    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO) {
        Customer customer=customerMapper.CustomeerRequestDTOTocustomer(customerRequestDTO);
        Customer customerSave=customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO=customerMapper.customerToCustomerRespenseDTO(customerSave);

        return customerResponseDTO;
    }

    @Override
    public List<CustomerResponseDTO> listCustomers() {
        List<Customer> customers=customerRepository.findAll();
     List<CustomerResponseDTO> customerResponseDTO=
             customers.stream()
                     .map(c->customerMapper.customerToCustomerRespenseDTO(c))
             .collect(Collectors.toList());

        return customerResponseDTO;
    }
}
