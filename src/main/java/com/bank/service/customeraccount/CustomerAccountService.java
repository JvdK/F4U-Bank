package com.bank.service.customeraccount;

import com.bank.bean.customeraccount.CustomerAccount;
import com.bank.exception.NoEffectException;
import com.bank.repository.customeraccount.CustomerAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void removeCustomerAccount(int customerId, int accountId) throws NoEffectException {
        if(customerAccountRepository.getFirstByAccountIdAndCustomerId(accountId, customerId) != null){
            customerAccountRepository.deleteByCustomerIdAndAccountId(customerId, accountId);
        }else{
            throw new NoEffectException("Customer not assinged to this account");
        }
    }
}
