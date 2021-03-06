package com.bank.service.account;

import com.bank.bean.account.AccountBean;
import com.bank.bean.customeraccount.CustomerAccount;
import com.bank.exception.InvalidParamValueException;
import com.bank.repository.account.AccountRepository;
import com.bank.repository.customeraccount.CustomerAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    private AccountRepository accountRepository;

    public boolean checkIfIsMainAccountHolder(String accountNumber, int customerId) throws InvalidParamValueException {
        AccountBean accountBean = accountRepository.findAccountBeanByAccountNumber(accountNumber);
        if (accountBean == null) {
            throw new InvalidParamValueException("Unknown account number");
        }
        CustomerAccount customerAccount = customerAccountRepository.getFirstByAccountIdAndCustomerId(accountBean.getAccountId(), customerId);
        return customerAccount != null && customerAccount.isMain();
    }

    public boolean checkIfAccountHolder(String accountNumber, int customerId) throws InvalidParamValueException {
        AccountBean accountBean = accountRepository.findAccountBeanByAccountNumber(accountNumber);
        if (accountBean == null) {
            throw new InvalidParamValueException("Unknown account number");
        }
        CustomerAccount customerAccount = customerAccountRepository.getFirstByAccountIdAndCustomerId(accountBean.getAccountId(), customerId);
        return customerAccount != null;
    }

    public AccountBean getAccountBeanByAccountNumber(String accountNumber) throws InvalidParamValueException {
        AccountBean bean = accountRepository.findAccountBeanByAccountNumber(accountNumber);
        if (bean == null) {
            throw new InvalidParamValueException("Unknown account number");
        }
        return bean;
    }
}
