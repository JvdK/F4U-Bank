package com.bank.service.customeraccount;

import com.bank.bean.customeraccount.CustomerAccount;
import com.bank.command.customeraccount.CustomerAccountCreateCommand;
import com.bank.projection.account.AccountCustomerDetailsProjection;
import com.bank.repository.customeraccount.CustomerAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAccountService {

    @Autowired
    CustomerAccountRepository customerAccountRepository;

    public List<AccountCustomerDetailsProjection> getCustomersOfAccount(int accountId){
        return customerAccountRepository.findCustomerAccountsByAccountId(accountId);
    }

    public void addCustomerAccount(CustomerAccountCreateCommand command){
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setAccountId(command.getAccountId());
        customerAccount.setCustomerId(command.getCustomerId());
        customerAccount.setMain(command.isMain());
        customerAccountRepository.save(customerAccount);
    }
}
