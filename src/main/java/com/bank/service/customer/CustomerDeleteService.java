package com.bank.service.customer;

import com.bank.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDeleteService {

    @Autowired
    CustomerRepository customerRepository;

    public void deleteCustomer(int id){
        customerRepository.deleteCustomer(id);
    }
}
