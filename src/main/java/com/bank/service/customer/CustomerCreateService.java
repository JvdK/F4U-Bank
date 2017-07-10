package com.bank.service.customer;

import com.bank.bean.customer.CustomerBean;
import com.bank.command.customer.CustomerCreateCommand;
import com.bank.exception.BadRequestException;
import com.bank.exception.InvalidParamValueError;
import com.bank.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CustomerCreateService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerCreateService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public void createCustomer(CustomerBean customerBean) throws InvalidParamValueError {
        try {
            customerRepository.save(customerBean);
        }catch (DataIntegrityViolationException e){
            throw new InvalidParamValueError("Username already present");
        }
    }

}
