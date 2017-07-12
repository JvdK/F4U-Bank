package com.bank.controller;

import com.bank.exception.AuthenticationException;
import com.bank.exception.NotAuthorizedException;
import com.bank.projection.customer.CustomerAccountAccessProjection;
import com.bank.service.AuthenticationService;
import com.bank.service.customer.CustomerAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerController {
    @Autowired
    private CustomerAccessService customerAccessService;

    public List<CustomerAccountAccessProjection> getUserAccess(String authToken) throws NotAuthorizedException {
        try {
            int customerId = (Integer) AuthenticationService.instance.getObject(authToken, AuthenticationService.USERID);
            return customerAccessService.getUserAccess(customerId);
        } catch (AuthenticationException e) {
            throw new NotAuthorizedException("Not Authorized");
        }

    }
}
