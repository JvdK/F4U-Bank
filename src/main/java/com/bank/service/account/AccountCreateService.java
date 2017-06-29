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

    /**
     * Creates a new account and links it to the given customerAccount. It also creates a new pin and assings it.
     * @param customerBean
     * @param isMain
     * @throws BadRequestException
     */
    public CardBean createAccount(CustomerBean customerBean, boolean isMain) {
        AccountBean accountBean = new AccountBean();
        accountBean.setAccountNumber(ibanGeneratorService.generateIBAN());
        accountRepository.save(accountBean);

        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setAccountBean(accountBean);
        customerAccount.setCustomerBean(customerBean);
        customerAccount.setMain(isMain);

        customerAccountRepository.save(customerAccount);

        // create pin
        return cardCreateService.addCard(customerBean, accountBean);
    }

    public void createAccount(String name,
                              String surname,
                              String initials,
                              Date date,
                              String ssn,
                              String address,
                              String telephoneNumber,
                              String email,
                              String username,
                              String password) {


    }
}
