package com.bank.service.customer;

import com.bank.repository.customer.CustomerDeleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDeleteService {

    @Autowired
    CustomerDeleteRepository customerDeleteRepository;

    public void deleteCustomer(int id){
        customerDeleteRepository.deleteCustomer(id);
    }
}
