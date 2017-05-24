package com.bank.service.account;

import com.bank.bean.account.AccountBean;
import com.bank.command.account.AccountCreateCommand;
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

    public void createAccount(AccountCreateCommand command) throws BadRequestException {
        AccountBean bean = new AccountBean();
        bean.setAccountNumber(command.getAccountNumber());
        bean.setActive(true);
        accountRepository.save(bean);
    }
}
