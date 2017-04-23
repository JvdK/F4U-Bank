package com.bank.service.account;

import com.bank.bean.customer.CustomerAccount;
import com.bank.bean.customer.CustomerAccountId;
import com.bank.projection.account.AccountCustomerDetailsProjection;
import com.bank.repository.account.AccountGetCustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountGetCustomersService {

    @Autowired
    AccountGetCustomersRepository accountGetCustomersRepository;

    public List<AccountCustomerDetailsProjection> getCustomersOfAccount(int accountId){
        return accountGetCustomersRepository.findCustomerAccountIdsByAccountId(accountId);
    }
}
