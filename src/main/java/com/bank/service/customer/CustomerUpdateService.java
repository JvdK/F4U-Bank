package com.bank.service.customer;

import com.bank.bean.customer.CustomerBean;
import com.bank.command.customer.CustomerUpdateCommand;
import com.bank.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerUpdateService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Updates the customer with the customerId given. If fields are not set, they are not updated in the database
     *
     * @param command update command
     */
    public void updateCustomer(CustomerUpdateCommand command) {
        CustomerBean bean = customerRepository.findOne(command.getCustomerId());
        if (command.getFirstName() != null) {
            bean.setFirstName(command.getFirstName());
        }
        if (command.getLastName() != null) {
            bean.setLastName(command.getLastName());
        }
        if (command.getInitials() != null) {
            bean.setInitials(command.getInitials());
        }
        if (command.getAddress() != null) {
            bean.setAddress(command.getAddress());
        }
        if (command.getPhone() != null) {
            bean.setPhone(command.getPhone());
        }
        if (command.getPostalCode() != null) {
            bean.setPostalCode(command.getPostalCode());
        }
        if (command.getCity() != null) {
            bean.setCity(command.getCity());
        }
        if (command.getCountry() != null) {
            bean.setCountry(command.getCountry());
        }
        if (command.getEmail() != null) {
            bean.setEmail(command.getEmail());
        }
        customerRepository.save(bean);
    }

}
