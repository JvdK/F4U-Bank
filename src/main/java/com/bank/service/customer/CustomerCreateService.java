package com.bank.service.customer;

import com.bank.bean.customer.CustomerBean;
import com.bank.exception.InvalidParamValueException;
import com.bank.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CustomerCreateService {
    @Autowired
    private CustomerRepository customerRepository;

    public void createCustomer(CustomerBean customerBean) throws InvalidParamValueException {
        try {
            customerRepository.save(customerBean);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidParamValueException("Username already present");
        }
    }
}
