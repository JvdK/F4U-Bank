package com.bank.controller;

import com.bank.exception.AuthenticationException;
import com.bank.exception.InvalidParamValueException;
import com.bank.exception.NoEffectException;
import com.bank.exception.NotAuthorizedException;
import com.bank.projection.account.AccountAmountProjection;
import com.bank.projection.account.AccountOpenProjection;
import com.bank.projection.customer.CustomerUsernameProjection;
import com.bank.projection.pin.PinProjection;
import com.bank.service.AuthenticationService;
import com.bank.service.account.*;
import com.bank.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountOpenService accountOpenService;

    @Autowired
    private AccountCloseService accountCloseService;

    @Autowired
    private AccountAccessService accountAccessService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountAmountService accountAmountService;

    public AccountOpenProjection openAccount(String name,
                                             String surname,
                                             String initials,
                                             Date date,
                                             String ssn,
                                             String address,
                                             String telephoneNumber,
                                             String email,
                                             String username,
                                             String password) throws InvalidParamValueException {
        return accountOpenService.openAccount(name, surname, initials, date, ssn, address, telephoneNumber, email, username, password);
    }

    public AccountOpenProjection openAdditionalAccount(String authToken) throws NotAuthorizedException {
        try {
            int customerId = (Integer) AuthenticationService.instance.getObject(authToken, AuthenticationService.USER_ID);
            return accountOpenService.openAdditionalAccount(customerId);
        } catch (AuthenticationException e) {
            throw new NotAuthorizedException("Invalid authToken");
        }
    }

    public void closeAccount(String authToken, String IBAN) throws InvalidParamValueException, NotAuthorizedException {
        try {
            int customerId = (Integer) AuthenticationService.instance.getObject(authToken, AuthenticationService.USER_ID);
            if (accountService.checkIfIsMainAccountHolder(IBAN, customerId)) {
                accountCloseService.closeAccount(IBAN, customerId);
            } else {
                throw new NotAuthorizedException("Not Authorized");
            }
        } catch (AuthenticationException e) {
            throw new NotAuthorizedException("Not Authorized");
        }
    }

    public PinProjection provideAccess(String authToken, String IBAN, String username) throws InvalidParamValueException, NotAuthorizedException, NoEffectException {
        try {
            int customerId = (Integer) AuthenticationService.instance.getObject(authToken, AuthenticationService.USER_ID);
            if (accountService.checkIfIsMainAccountHolder(IBAN, customerId)) {
                return accountAccessService.provideAccess(IBAN, username);
            } else {
                throw new NotAuthorizedException("Not Authorized");
            }
        } catch (AuthenticationException e) {
            throw new NotAuthorizedException("Not Authorized");
        }
    }

    public void revokeAccess(String authToken, String IBAN, String username) throws NotAuthorizedException, InvalidParamValueException, NoEffectException {
        try {
            int customerId = (Integer) AuthenticationService.instance.getObject(authToken, AuthenticationService.USER_ID);
            if (username == null) {
                accountAccessService.revokeAccess(customerId, IBAN);
            } else {
                if (accountService.checkIfIsMainAccountHolder(IBAN, customerId)) {
                    accountAccessService.revokeAccess(customerService.getCustomerBeanByUsername(username).getCustomerId(), IBAN);
                }
            }
        } catch (AuthenticationException e) {
            throw new NotAuthorizedException("Not Authorized");
        }
    }

    public AccountAmountProjection getBalance(String authToken, String IBAN) throws NotAuthorizedException, InvalidParamValueException {
        try {
            int customerId = (Integer) AuthenticationService.instance.getObject(authToken, AuthenticationService.USER_ID);
            if (accountService.checkIfAccountHolder(IBAN, customerId)) {
                return accountAmountService.getBalance(accountService.getAccountBeanByAccountNumber(IBAN).getAccountId());
            } else {
                throw new NotAuthorizedException("Not Authorized");
            }
        } catch (AuthenticationException e) {
            throw new NotAuthorizedException("Not Authorized");
        }
    }

    public List<CustomerUsernameProjection> getBankAccountAccess(String authToken, String IBAN) throws InvalidParamValueException, NotAuthorizedException {
        try {
            int customerId = (Integer) AuthenticationService.instance.getObject(authToken, AuthenticationService.USER_ID);
            if (accountService.checkIfIsMainAccountHolder(IBAN, customerId)) {
                return accountAccessService.getBankAccountAccess(accountService.getAccountBeanByAccountNumber(IBAN).getAccountId());
            } else {
                throw new NotAuthorizedException("Not Authorized");
            }
        } catch (AuthenticationException e) {
            throw new NotAuthorizedException("Not Authorized");
        }
    }
}
