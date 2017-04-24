package com.bank.service.account;

import com.bank.bean.customer.AccountBean;
import com.bank.exception.BadRequestException;
import com.bank.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountCreateService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountCreateService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createAccount(AccountBean accountBean) throws BadRequestException {
        accountRepository.save(accountBean);
    }
}
