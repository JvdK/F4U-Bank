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

    public void closeAccount(String iBAN, int customerId) {
        // close account
        accountRepository.closeAccount(iBAN);

        // invalidate all pin cards
        int accountId = accountRepository.findAccountBeanByAccountNumber(iBAN).getAccountId();
        cardRepository.invalidatePinCards(accountId);

        // if last account of customer, also invalidate customer
        List<CustomerAccount> customerAccounts = customerAccountRepository.getActiveCustomerAcounts(customerId);
        if (customerAccounts.size() == 0) {
            customerCloseService.closeCustomer(customerId);
        }
    }
}
