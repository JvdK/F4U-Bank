package com.bank.service.account;

import com.bank.bean.account.AccountBean;
import com.bank.command.account.AccountDeleteCommand;
import com.bank.exception.NotFoundException;
import com.bank.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDeleteService {

    @Autowired
    private AccountRepository accountRepository;

    public void deleteAccount(AccountDeleteCommand command) throws NotFoundException {
        AccountBean bean = accountRepository.findOne(command.getAccountId());
        if(bean == null){
            throw new NotFoundException();
        }
        bean.setActive(false);
        accountRepository.save(bean);
    }

}
