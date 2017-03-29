package com.bank.service.customer;

import com.bank.customer.CustomerBean;
import com.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerCreateService {

    @Autowired
    CustomerRepository customerRepository;

    public void createCustomer(CustomerBean customerBean){
        customerRepository.save(customerBean);
    }
}
