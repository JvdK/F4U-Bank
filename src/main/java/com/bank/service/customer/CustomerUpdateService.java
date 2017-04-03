package com.bank.service.customer;

import com.bank.bean.customer.CustomerBean;
import com.bank.repository.customer.CustomerUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerUpdateService {

    @Autowired
    CustomerUpdateRepository customerUpdateRepository;

    public void updateCustomer(int customerId){
        CustomerBean bean = customerUpdateRepository.findOne(customerId);
        bean.setCountry("DUITSLAND");
        customerUpdateRepository.save(bean);
    }

}
