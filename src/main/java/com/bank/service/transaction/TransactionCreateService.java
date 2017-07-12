package com.bank.service.transaction;

import com.bank.bean.card.CardBean;
import com.bank.bean.account.AccountBean;
import com.bank.bean.transaction.TransactionBean;
import com.bank.command.transaction.TransactionAddCommand;
import com.bank.exception.BadRequestException;
import com.bank.exception.InvalidPINException;
import com.bank.exception.InvalidParamValueError;
import com.bank.exception.NotFoundException;
import com.bank.repository.account.AccountRepository;
import com.bank.repository.card.CardRepository;
import com.bank.repository.customer.CustomerRepository;
import com.bank.repository.transaction.TransactionRepository;
import com.bank.service.account.AccountService;
import com.bank.service.card.CardValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TransactionCreateService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CardValidateService cardValidateService;

    @Autowired
    private TransactionService transactionService;

    public void depositIntoAccount(String IBAN, String pinCard, String pinCode, double amount) throws InvalidPINException, InvalidParamValueError {
        AccountBean accountBean = accountService.getAccountBeanByAccountNumber(IBAN);
        CardBean cardBean = cardValidateService.validateCard(accountBean.getAccountId(), pinCard, pinCode);
        transactionService.doSingleTransaction(accountBean, cardBean, amount);
    }

    public void payFromAccount(String sourceIBAN, String targetIBAN, String pinCard, String pinCode, double amount) throws InvalidParamValueError, InvalidPINException {
        AccountBean sourceAccountBean = accountService.getAccountBeanByAccountNumber(sourceIBAN);
        AccountBean targetAccountBean = accountService.getAccountBeanByAccountNumber(targetIBAN);

        CardBean cardBean = cardValidateService.validateCard(sourceAccountBean.getAccountId(), pinCard, pinCode);

        transactionService.doTransaction(sourceAccountBean, targetAccountBean, amount, cardBean, "", "");
    }

    public void transferMoney(String sourceIBAN, String targetIBAN, String targetName, double amount, String description) throws InvalidParamValueError {
        AccountBean sourceAccountBean = accountService.getAccountBeanByAccountNumber(sourceIBAN);
        AccountBean targetAccountBean = accountService.getAccountBeanByAccountNumber(targetIBAN);

        transactionService.doTransaction(sourceAccountBean, targetAccountBean, amount, null, description, targetName);
    }

}
