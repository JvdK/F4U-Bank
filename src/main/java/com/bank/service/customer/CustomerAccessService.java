package com.bank.service.customer;

import com.bank.projection.customer.CustomerAccountAccesProjection;
import com.bank.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAccessService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerAccountAccesProjection> getUserAccess(int customerId){
        return customerRepository.getCustomerAccess(customerId);
    }

}
