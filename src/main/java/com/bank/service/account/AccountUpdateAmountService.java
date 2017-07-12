package com.bank.service.account;

import com.bank.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountUpdateAmountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void updateAmount(int sourceAccountId, int targetAccountId, double amount){
        accountRepository.updateAmount(sourceAccountId, -amount);
        accountRepository.updateAmount(targetAccountId, amount);
    }

    public void updateAmount(int targetAccountId, double amount){
        accountRepository.updateAmount(targetAccountId, amount);
    }

}
