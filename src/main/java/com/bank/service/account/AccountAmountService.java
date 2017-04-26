package com.bank.service.account;

import com.bank.projection.account.AccountAmountProjection;
import com.bank.repository.account.AccountAmountRepository;
import com.bank.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountAmountService {

    @Autowired
    AccountAmountRepository accountAmountRepository;

    public AccountAmountProjection getAmountOfAccount(int accountId){
        return accountAmountRepository.findAccountBeanByAccountIdWhereIsActiveTrue(accountId);
    }
}
