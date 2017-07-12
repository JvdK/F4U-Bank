package com.bank.service.transaction;

import com.bank.bean.account.AccountBean;
import com.bank.bean.card.CardBean;
import com.bank.exception.InvalidPINException;
import com.bank.exception.InvalidParamValueException;
import com.bank.service.account.AccountService;
import com.bank.service.card.CardValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionCreateService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CardValidateService cardValidateService;

    @Autowired
    private TransactionService transactionService;

    public void depositIntoAccount(String IBAN, String pinCard, String pinCode, double amount) throws InvalidPINException, InvalidParamValueException {
        AccountBean accountBean = accountService.getAccountBeanByAccountNumber(IBAN);
        CardBean cardBean = cardValidateService.validateCard(accountBean.getAccountId(), pinCard, pinCode);
        transactionService.doSingleTransaction(accountBean, cardBean, amount);
    }

    public void payFromAccount(String sourceIBAN, String targetIBAN, String pinCard, String pinCode, double amount) throws InvalidParamValueException, InvalidPINException {
        AccountBean sourceAccountBean = accountService.getAccountBeanByAccountNumber(sourceIBAN);
        AccountBean targetAccountBean = accountService.getAccountBeanByAccountNumber(targetIBAN);

        CardBean cardBean = cardValidateService.validateCard(sourceAccountBean.getAccountId(), pinCard, pinCode);

        transactionService.doTransaction(sourceAccountBean, targetAccountBean, amount, cardBean, "", "");
    }

    public void transferMoney(String sourceIBAN, String targetIBAN, String targetName, double amount, String description) throws InvalidParamValueException {
        AccountBean sourceAccountBean = accountService.getAccountBeanByAccountNumber(sourceIBAN);
        AccountBean targetAccountBean = accountService.getAccountBeanByAccountNumber(targetIBAN);

        transactionService.doTransaction(sourceAccountBean, targetAccountBean, amount, null, description, targetName);
    }

}
