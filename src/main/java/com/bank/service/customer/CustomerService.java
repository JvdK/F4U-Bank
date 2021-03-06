package com.bank.service.customer;

import com.bank.bean.customer.CustomerBean;
import com.bank.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerBean getCustomerBeanById(int customerId) {
        return customerRepository.getCustomerBeanByCustomerId(customerId);
    }

    public CustomerBean getCustomerBeanByUsername(String username) {
        return customerRepository.getCustomerBeanByUsername(username);
    }
}
