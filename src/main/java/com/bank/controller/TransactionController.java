package com.bank.controller;

import com.bank.exception.AuthenticationException;
import com.bank.exception.InvalidPINException;
import com.bank.exception.InvalidParamValueException;
import com.bank.exception.NotAuthorizedException;
import com.bank.projection.transaction.TransactionProjection;
import com.bank.service.AuthenticationService;
import com.bank.service.account.AccountService;
import com.bank.service.transaction.TransactionCreateService;
import com.bank.service.transaction.TransactionOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionController {
    @Autowired
    private TransactionCreateService transactionCreateService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionOverviewService transactionOverviewService;

    public void depositIntoAccount(String IBAN, String pinCard, String pinCode, double amount) throws InvalidParamValueException, InvalidPINException {
        transactionCreateService.depositIntoAccount(IBAN, pinCard, pinCode, amount);
    }

    public void payFromAccount(String sourceIBAN, String targetIBAN, String pinCard, String pinCode, double amount) throws InvalidParamValueException, InvalidPINException {
        transactionCreateService.payFromAccount(sourceIBAN, targetIBAN, pinCard, pinCode, amount);
    }

    public void transferMoney(String authToken, String sourceIBAN, String targetIBAN, String targetName, double amount, String description) throws NotAuthorizedException, InvalidParamValueException {
        try {
            int customerId = (Integer) AuthenticationService.instance.getObject(authToken, AuthenticationService.USERID);
            if (accountService.checkIfAccountHolder(sourceIBAN, customerId)) {
                transactionCreateService.transferMoney(sourceIBAN, targetIBAN, targetName, amount, description);
            } else {
                throw new NotAuthorizedException("Not Authorized");
            }
        } catch (AuthenticationException e) {
            throw new NotAuthorizedException("Not Authorized");
        }
    }

    public List<TransactionProjection> getTransactionsOverview(String authToken, String IBAN, int nrOfTransactions) throws InvalidParamValueException, NotAuthorizedException {
        try {
            int customerId = (Integer) AuthenticationService.instance.getObject(authToken, AuthenticationService.USERID);
            if (accountService.checkIfAccountHolder(IBAN, customerId)) {
                return transactionOverviewService.getTransactionOverview(accountService.getAccountBeanByAccountNumber(IBAN).getAccountId(), nrOfTransactions);
            } else {
                throw new NotAuthorizedException("Not Authorized");
            }
        } catch (AuthenticationException e) {
            throw new NotAuthorizedException("Not Authorized");
        }
    }
}