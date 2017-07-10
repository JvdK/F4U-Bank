package com.bank.service.customeraccount;

import com.bank.bean.customeraccount.CustomerAccount;
import com.bank.command.customeraccount.CustomerAccountCreateCommand;
import com.bank.exception.NoEffectException;
import com.bank.projection.account.AccountCustomerDetailsProjection;
import com.bank.repository.customeraccount.CustomerAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAccountService {

    @Autowired
    CustomerAccountRepository customerAccountRepository;

    public void addCustomerAccount(CustomerAccount customerAccount) throws NoEffectException {
        if(customerAccountRepository.getFirstByAccountIdAndCustomerId(customerAccount.getAccountId(), customerAccount.getCustomerId()) == null){
            customerAccountRepository.save(customerAccount);
        }else{
            throw new NoEffectException("Customer already assigned to this account");
        }
    }
}
