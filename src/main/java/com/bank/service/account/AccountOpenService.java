package com.bank.service.account;

import com.bank.bean.card.CardBean;
import com.bank.bean.customer.CustomerBean;
import com.bank.exception.InvalidParamValueException;
import com.bank.projection.account.AccountOpenProjection;
import com.bank.service.customer.CustomerCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class AccountOpenService {
    @Autowired
    private CustomerCreateService customerCreateService;

    @Autowired
    private AccountCreateService accountCreateService;

    /**
     * Opens an account. This creates a new customer, a new account and a new pin card.
     *
     * @param name
     * @param surname
     * @param initials
     * @param date
     * @param ssn
     * @param address
     * @param telephoneNumber
     * @param email
     * @param username
     * @param password
     * @return projection containing the account id, pin number and pin code.
     * @throws InvalidParamValueException when parameters are not correct.
     */
    public AccountOpenProjection openAccount(String name,
                                             String surname,
                                             String initials,
                                             Date date,
                                             String ssn,
                                             String address,
                                             String telephoneNumber,
                                             String email,
                                             String username,
                                             String password) throws InvalidParamValueException {

        CustomerBean customerBean = new CustomerBean();
        customerBean.setName(name);
        customerBean.setSurname(surname);
        customerBean.setInitials(initials);
        customerBean.setDob(date);
        customerBean.setSsn(ssn);
        customerBean.setAddress(address);
        customerBean.setTelephoneNumber(telephoneNumber);
        customerBean.setEmail(email);
        customerBean.setUsername(username);
        customerBean.setPassword(password);

        customerCreateService.createCustomer(customerBean);

        CardBean cardBean = accountCreateService.createAccount(customerBean.getCustomerId(), true);

        AccountOpenProjection projection = new AccountOpenProjection();
        projection.setiBAN(cardBean.getAccountBean().getAccountNumber());
        projection.setPinCard(cardBean.getPinCard());
        projection.setPinCode(cardBean.getPinCode());
        return projection;
    }

    public AccountOpenProjection openAdditionalAccount(int customerId) {
        CardBean cardBean = accountCreateService.createAccount(customerId, true);
        AccountOpenProjection projection = new AccountOpenProjection();
        projection.setiBAN(cardBean.getAccountBean().getAccountNumber());
        projection.setPinCard(cardBean.getPinCard());
        projection.setPinCode(cardBean.getPinCode());
        return projection;
    }
}
