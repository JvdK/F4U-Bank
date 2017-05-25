package com.bank.service.customer;

import com.bank.bean.customer.CustomerBean;
import com.bank.command.customer.CustomerCreateCommand;
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

    public void createCustomer(CustomerCreateCommand command) throws BadRequestException {
        try {
            CustomerBean bean = new CustomerBean();
            bean.setUserName(command.getUserName());
            bean.setPassword(command.getPassword());
            bean.setFirstName(command.getFirstName());
            bean.setLastName(command.getLastName());
            bean.setInitials(command.getInitials());
            bean.setDateOfBirth(command.getDateOfBirth());
            bean.setAddress(command.getAddress());
            bean.setPhone(command.getPhone());
            bean.setPostalCode(command.getPostalCode());
            bean.setCity(command.getCity());
            bean.setCountry(command.getCountry());
            bean.setEmail(command.getEmail());
            bean.setActive(true);
            customerRepository.save(bean);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException();
        }

    }

}
