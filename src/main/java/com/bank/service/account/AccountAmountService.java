package com.bank.service.account;

import com.bank.bean.account.AccountBean;
import com.bank.projection.account.AccountAmountProjection;
import com.bank.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountAmountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountAmountProjection getBalance(int accountId){
        AccountBean accountBean = accountRepository.findAccountBeansByAccountId(accountId);
        AccountAmountProjection projection = new AccountAmountProjection();
        projection.setBalance(accountBean.getAmount());
        return projection;
    }

}
