package com.bank.controller;

import com.bank.bean.customer.CustomerBean;
import com.bank.command.transaction.TransactionAddCommand;
import com.bank.exception.*;
import com.bank.projection.transaction.TransactionInformationProjection;
import com.bank.service.AuthenticationService;
import com.bank.service.account.AccountService;
import com.bank.service.transaction.TransactionGetService;
import com.bank.service.transaction.TransactionCreateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class TransactionController {

    @Autowired
    private TransactionCreateService transactionCreateService;

    @Autowired
    private AccountService accountService;

    public void depositIntoAccount(String IBAN, String pinCard, String pinCode, double amount) throws InvalidParamValueError, InvalidPINException {
        transactionCreateService.depositIntoAccount(IBAN, pinCard, pinCode, amount);
    }

    public void payFromAccount(String sourceIBAN, String targetIBAN, String pinCard, String pinCode, double amount) throws InvalidParamValueError, InvalidPINException {
        transactionCreateService.payFromAccount(sourceIBAN, targetIBAN, pinCard, pinCode, amount);
    }

    public void transferMoney(String authToken, String sourceIBAN, String targetIBAN, String targetName, double amount, String description) throws NotAuthorizedException, InvalidParamValueError {
        try {
            int customerId = (Integer) AuthenticationService.instance.getObject(authToken, AuthenticationService.USERID);
            if(accountService.checkIfAccountHolder(sourceIBAN, customerId)){
                transactionCreateService.transferMoney(sourceIBAN, targetIBAN, targetName, amount, description);
            }else{
                throw new NotAuthorizedException("Not Authorized");
            }
        } catch (AuthenticationException e) {
            throw new NotAuthorizedException("Not Authorized");
        }
    }

}
