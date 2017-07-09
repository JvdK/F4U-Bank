package com.bank.service.account;

import com.bank.bean.customeraccount.CustomerAccount;
import com.bank.repository.account.AccountRepository;
import com.bank.repository.card.CardRepository;
import com.bank.repository.customeraccount.CustomerAccountRepository;
import com.bank.service.customer.CustomerCloseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountCloseService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    private CustomerCloseService customerCloseService;

    public void closeAccount(String IBAN, int customerId){
        accountRepository.closeAccount(IBAN);
        // invalidate pincards
        int accountId = accountRepository.findAccountBeanByAccountNumber(IBAN).getAccountId();
        cardRepository.invalidatePinCards(accountId);
        // if last account, invalidate customer account
        List<CustomerAccount> customerAccounts = customerAccountRepository.getActiveCustomerAcounts(customerId);
//        boolean stillAccountLeft = false;
//        for(CustomerAccount c : customerAccounts){
//            if(c.getAccountBean().isActive()){
//                stillAccountLeft = true;
//                break;
//            }
//        }
        if(customerAccounts.size()==0){
            customerCloseService.closeCustomer(customerId);
        }

    }

}
