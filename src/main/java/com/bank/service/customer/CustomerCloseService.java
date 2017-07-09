package com.bank.service.customer;

import com.bank.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerCloseService {

    @Autowired
    private CustomerRepository customerRepository;

    public void closeCustomer(int customerId){
        customerRepository.closeCustomer(customerId);
    }

}
