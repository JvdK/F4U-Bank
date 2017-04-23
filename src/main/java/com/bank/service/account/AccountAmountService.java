package com.bank.service.account;

import com.bank.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountAmountService {

    @Autowired
    AccountRepository accountRepository;

    public double getAmountOfAccount(int acountId){
        return accountRepository.getAmountOfAccount(acountId);
    }
}
