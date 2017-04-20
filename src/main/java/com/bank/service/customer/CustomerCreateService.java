package com.bank.service.customer;

import com.bank.bean.customer.AccountBean;
import com.bank.bean.customer.CustomerBean;
import com.bank.exception.BadRequestException;
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

    public void createCustomer(CustomerBean customerBean) throws BadRequestException {
        try {
            customerRepository.save(customerBean);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException();
        }

    }

    public void linkCustomerAccount(CustomerBean customerBean, AccountBean accountBean) {
        customerBean.addAccout(accountBean, true);
        customerRepository.save(customerBean);
    }
}
