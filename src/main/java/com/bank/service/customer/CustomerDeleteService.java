package com.bank.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDeleteService {

    @Autowired
    CustomerDeleteService customerDeleteService;

    public void deleteCustomer(int id){
        customerDeleteService.deleteCustomer(id);
    }
}
