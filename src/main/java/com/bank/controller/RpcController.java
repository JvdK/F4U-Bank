package com.bank.controller;


import com.bank.exception.*;
import com.bank.service.account.AccountOpenService;
import com.bank.util.EmptyJsonResponse;
import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@JsonRpcService("/api")
@AutoJsonRpcServiceImpl
public class RpcController {

    /**
     * Account Module
     */
    @Autowired
    AccountController accountController;

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418)
    })
    public Object openAccount(@JsonRpcParam("name") String name,
                              @JsonRpcParam("surname") String surname,
                              @JsonRpcParam("initials") String initials,
                              @JsonRpcParam("dob") Date date,
                              @JsonRpcParam("ssn") String ssn,
                              @JsonRpcParam("address") String address,
                              @JsonRpcParam("telephoneNumber") String telephoneNumber,
                              @JsonRpcParam("email") String email,
                              @JsonRpcParam("username") String username,
                              @JsonRpcParam("password") String password) throws InvalidParamValueError {
        return accountController.openAccount(name, surname, initials, date, ssn, address, telephoneNumber, email, username, password);
    }

    @JsonRpcErrors({
            @JsonRpcError(exception = NotAuthorizedException.class, code = 419)
    })
    public Object openAdditionalAccount(@JsonRpcParam("authToken") String authToken) throws NotAuthorizedException {
        return accountController.openAdditionalAccount(authToken);
    }

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedException.class, code = 419)
    })
    public Object closeAccount(@JsonRpcParam("authToken") String authToken, @JsonRpcParam("IBAN") String IBAN) throws InvalidParamValueError, NotAuthorizedException {
        accountController.closeAccount(authToken, IBAN);
        return new EmptyJsonResponse();
    }


    /**
     * Acces module
     */
    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedException.class, code = 418),
            @JsonRpcError(exception = NoEffectException.class, code = 420)
    })
    public Object provideAccess(@JsonRpcParam("authToken") String authToken, @JsonRpcParam("IBAN") String IBAN, @JsonRpcParam("username") String username) throws NotAuthorizedException, InvalidParamValueError, NoEffectException {
        return accountController.provideAccess(authToken, IBAN, username);
    }

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedException.class, code = 418),
            @JsonRpcError(exception = NoEffectException.class, code = 420)
    })
    public Object revokeAccess(@JsonRpcParam("authToken") String authToken, @JsonRpcParam("IBAN") String IBAN, @JsonRpcParam("username") String username) throws NotAuthorizedException, InvalidParamValueError, NoEffectException {
        accountController.revokeAccess(authToken, IBAN, username);
        return new EmptyJsonResponse();
    }

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedException.class, code = 418),
            @JsonRpcError(exception = NoEffectException.class, code = 420)
    })
    public Object revokeAccess(@JsonRpcParam("authToken") String authToken, @JsonRpcParam("IBAN") String IBAN) throws NotAuthorizedException, InvalidParamValueError, NoEffectException {
        accountController.revokeAccess(authToken, IBAN, null);
        return new EmptyJsonResponse();
    }

    /**
     * Transfer Module
     */

    @Autowired
    private TransactionController transactionController;

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = InvalidPINException.class, code = 421)
    })
    public Object depositIntoAccount(@JsonRpcParam("IBAN") String IBAN, @JsonRpcParam("pinCard") String pinCard, @JsonRpcParam("pinCode") String pinCode, @JsonRpcParam("amount") double amount) throws InvalidParamValueError, InvalidPINException {
        transactionController.depositIntoAccount(IBAN, pinCard, pinCode, amount);
        return new EmptyJsonResponse();
    }

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = InvalidPINException.class, code = 421)
    })
    public Object payFromAccount(@JsonRpcParam("sourceIBAN") String sourceIBAN, @JsonRpcParam("targetIBAN") String targetIBAN, @JsonRpcParam("pinCard") String pinCard, @JsonRpcParam("pinCode") String pinCode, @JsonRpcParam("amount") double amount) throws InvalidParamValueError, InvalidPINException {
        transactionController.payFromAccount(sourceIBAN, targetIBAN, pinCard, pinCode, amount);
        return new EmptyJsonResponse();
    }


    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedException.class, code = 419)
    })
    public Object transferMoney(@JsonRpcParam("authToken") String authToken, @JsonRpcParam("sourceIBAN") String sourceIBAN, @JsonRpcParam("targetIBAN") String targetIBAN, @JsonRpcParam("targetName") String targetName, @JsonRpcParam("amount") double amount, @JsonRpcParam("description") String description) throws NotAuthorizedException, InvalidParamValueError {
        transactionController.transferMoney(authToken, sourceIBAN, targetIBAN, targetName, amount, description);
        return new EmptyJsonResponse();
    }

    /**
     * Authentication Module
     */

    @Autowired
    private SessionController sessionController;

    @JsonRpcErrors({
            @JsonRpcError(exception = AuthenticationException.class, code = 422)
    })
    public Object getAuthToken(@JsonRpcParam("username") String username, @JsonRpcParam("password") String password) throws AuthenticationException {
        return sessionController.getAuthToken(username, password);
    }


    /**
     * Info Module
     */

    @Autowired
    private CustomerController customerController;

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedException.class, code = 419)
    })
    public Object getBalance(@JsonRpcParam("authToken") String authToken, @JsonRpcParam("IBAN") String IBAN) throws NotAuthorizedException, InvalidParamValueError {
        return accountController.getBalance(authToken, IBAN);
    }

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedException.class, code = 419)
    })
    public Object getTransactionsOverview(@JsonRpcParam("authToken") String authToken, @JsonRpcParam("IBAN") String IBAN, @JsonRpcParam("nrOfTransactions") int nrOfTransactions) throws InvalidParamValueError, NotAuthorizedException {
        return transactionController.getTransactionsOverview(authToken, IBAN, nrOfTransactions);
    }

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedException.class, code = 419)
    })
    public Object getUserAccess(@JsonRpcParam("authToken") String authToken) throws NotAuthorizedException {
        return customerController.getUserAccess(authToken);
    }

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedException.class, code = 419)
    })
    public Object getBankAccountAcces(@JsonRpcParam("authToken") String authToken, @JsonRpcParam("IBAN") String IBAN){
        return null;
    }



}
