package com.bank.service.customer;

import com.bank.repository.customer.CustomerInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerGetService {
    @Autowired
    CustomerInformationRepository customerInformationRepository;

    public int getCustomerId(String username) {
        return customerInformationRepository.findByUsername(username).getCustomerId();
    }
}
