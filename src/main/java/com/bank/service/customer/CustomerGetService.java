package com.bank.service.customer;

import com.bank.projection.customer.CustomerDetailsProjection;
import com.bank.repository.customer.CustomerInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerGetService {

    @Autowired
    CustomerInformationRepository customerInformationRepository;

    public CustomerDetailsProjection getCustomerById(int customerId){
        return customerInformationRepository.findByCustomerId(customerId);
    }


    public int getCustomerId(String username){
        return customerInformationRepository.findByUsername(username).getCustomerId();
    }
}
