package com.bank.service.customer;

import com.bank.bean.customer.CustomerDetailsProjection;
import com.bank.repository.customer.CustomerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerGetService {

    @Autowired
    CustomerDetailsRepository customerDetailsRepository;

    public CustomerDetailsProjection getCustomerById(int customerId){
        return customerDetailsRepository.findByCustomerId(customerId);
    }
}
