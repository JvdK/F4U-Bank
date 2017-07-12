package com.bank.service.account;

import com.bank.bean.account.AccountBean;
import com.bank.bean.card.CardBean;
import com.bank.bean.customer.CustomerBean;
import com.bank.bean.customeraccount.CustomerAccount;
import com.bank.exception.InvalidParamValueError;
import com.bank.exception.NoEffectException;
import com.bank.projection.account.AccountOpenProjection;
import com.bank.projection.pin.PinProjection;
import com.bank.service.card.CardCreateService;
import com.bank.service.customer.CustomerService;
import com.bank.service.customeraccount.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountAccessService {


    @Autowired
    private CardCreateService cardCreateService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerAccountService customerAccountService;

    public PinProjection provideAccess(String accountNumber, String username) throws NoEffectException, InvalidParamValueError {
        CustomerBean customerBean = customerService.getCustomerBeanByUsername(username);
        AccountBean accountBean = accountService.getAccountBeanByAccountNumber(accountNumber);
        if (customerBean == null || accountBean == null) {
            throw new InvalidParamValueError("Invalid account or username");
        }

        // link customer to account
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setMain(false);
        customerAccount.setCustomerBean(customerBean);
        customerAccount.setAccountBean(accountBean);
        customerAccount.setCustomerId(customerBean.getCustomerId());
        customerAccount.setAccountId(accountBean.getAccountId());
        customerAccountService.addCustomerAccount(customerAccount);

        // add pin to customer
        CardBean cardBean = cardCreateService.addCard(customerBean, accountBean);

        PinProjection pinProjection = new PinProjection();
        pinProjection.setPinCard(cardBean.getPinCard());
        pinProjection.setPinCode(cardBean.getPinCode());

        return pinProjection;
    }


    public void revokeAccess(int customerId, String accountNumber) throws InvalidParamValueError, NoEffectException {
        CustomerBean customerBean = customerService.getCustomerBeanById(customerId);
        AccountBean accountBean = accountService.getAccountBeanByAccountNumber(accountNumber);
        if (customerBean == null || accountBean == null) {
            throw new InvalidParamValueError("Invalid account or username");
        }
        customerAccountService.removeCustomerAccount(customerId, accountBean.getAccountId());
    }


}
