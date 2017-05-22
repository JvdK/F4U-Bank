package com.bank.service.account;

import com.bank.bean.customer.CustomerAccount;
import com.bank.command.account.CustomerAccountCreateCommand;
import com.bank.projection.account.AccountCustomerDetailsProjection;
import com.bank.repository.account.AccountCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountCustomerService {

    @Autowired
    AccountCustomerRepository accountCustomerRepository;

    public List<AccountCustomerDetailsProjection> getCustomersOfAccount(int accountId){
        return accountCustomerRepository.findCustomerAccountsByAccountId(accountId);
    }

    public void addCustomerAccount(CustomerAccountCreateCommand command){
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setAccountId(command.getAccountId());
        customerAccount.setCustomerId(command.getCustomerId());
        customerAccount.setMain(command.isMain());
        accountCustomerRepository.save(customerAccount);
    }
}
