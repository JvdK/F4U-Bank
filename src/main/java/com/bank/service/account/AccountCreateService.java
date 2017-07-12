package com.bank.service.account;

import com.bank.bean.account.AccountBean;
import com.bank.bean.card.CardBean;
import com.bank.bean.customer.CustomerBean;
import com.bank.bean.customeraccount.CustomerAccount;
import com.bank.command.account.AccountCreateCommand;
import com.bank.exception.BadRequestException;
import com.bank.repository.account.AccountRepository;
import com.bank.repository.customeraccount.CustomerAccountRepository;
import com.bank.service.IBANGeneratorService;
import com.bank.service.card.CardCreateService;
import com.bank.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountCreateService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountCreateService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Autowired
    private IBANGeneratorService ibanGeneratorService;

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    private CardCreateService cardCreateService;

    @Autowired
    private CustomerService customerService;

    /**
     * Creates a new account and links it to the given customerAccount. It also creates a new pin and assings it.
     * @param isMain
     * @throws BadRequestException
     */
    public CardBean createAccount(int customerId, boolean isMain) {
        AccountBean accountBean = new AccountBean();
        accountBean.setAccountNumber(ibanGeneratorService.generateIBAN());
        accountRepository.save(accountBean);

        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setAccountId(accountBean.getAccountId());
        customerAccount.setCustomerId(customerId);

        customerAccount.setMain(isMain);

        customerAccountRepository.save(customerAccount);

        // create pin
        return cardCreateService.addCard(customerService.getCustomerBeanById(customerId), accountBean);
    }

}
