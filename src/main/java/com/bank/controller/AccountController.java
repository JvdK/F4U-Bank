package com.bank.controller;

import com.bank.bean.card.CardBean;
import com.bank.bean.customer.CustomerBean;
import com.bank.exception.AuthenticationException;
import com.bank.exception.InvalidParamValueError;
import com.bank.exception.NoEffectException;
import com.bank.exception.NotAuthorizedException;
import com.bank.projection.account.AccountOpenProjection;
import com.bank.projection.pin.PinProjection;
import com.bank.service.AuthenticationService;
import com.bank.service.account.*;
import com.bank.service.customer.CustomerCreateService;
import com.bank.service.customeraccount.CustomerAccountService;
import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
//@JsonRpcService("/api")
//@AutoJsonRpcServiceImpl
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountOpenService accountOpenService;

    @Autowired
    private AccountCloseService accountCloseService;

    @Autowired
    private AccountAccessService accountAccessService;


    public AccountOpenProjection openAccount(String name,
                                             String surname,
                                             String initials,
                                             java.util.Date date,
                                             String ssn,
                                             String address,
                                             String telephoneNumber,
                                             String email,
                                             String username,
                                             String password) throws InvalidParamValueError {
        return accountOpenService.openAccount(name, surname, initials, date, ssn, address, telephoneNumber, email, username, password);
    }

    public AccountOpenProjection openAdditionalAccount(String authToken) throws NotAuthorizedException {
        try {
            int customerId = (Integer) AuthenticationService.instance.getObject(authToken, AuthenticationService.USERID);
            return accountOpenService.openAdditionalAccount(customerId);
        } catch (AuthenticationException e) {
            throw new NotAuthorizedException("Invalid authToken");
        }
    }

    public void closeAccount(String authToken, String IBAN) throws InvalidParamValueError, NotAuthorizedException {
        try {
            int customerId = (Integer) AuthenticationService.instance.getObject(authToken, AuthenticationService.USERID);
            if (accountService.checkIfIsMainAccountHolder(IBAN, customerId)) {
                accountCloseService.closeAccount(IBAN, customerId);
            } else {
                throw new NotAuthorizedException("Not Authorized");
            }
        } catch (AuthenticationException e) {
            throw new NotAuthorizedException("Not Authorized");
        }
    }

    public PinProjection provideAccess(String authToken, String IBAN, String username) throws InvalidParamValueError, NotAuthorizedException, NoEffectException {
        try {
            int customerId = (Integer) AuthenticationService.instance.getObject(authToken, AuthenticationService.USERID);
            if(accountService.checkIfIsMainAccountHolder(IBAN, customerId)){
                return accountAccessService.provideAccess(IBAN, username);
            }else{
                throw new NotAuthorizedException("Not Authorized");
            }
        } catch (AuthenticationException e) {
            throw new NotAuthorizedException("Not Authorized");
        }
    }

}
