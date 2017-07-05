package com.bank.controller;

import com.bank.bean.card.CardBean;
import com.bank.bean.customer.CustomerBean;
import com.bank.exception.InvalidParamValueError;
import com.bank.projection.account.AccountOpenProjection;
import com.bank.service.account.AccountCreateService;
import com.bank.service.account.AccountAmountService;
import com.bank.service.account.AccountOpenService;
import com.bank.service.customer.CustomerCreateService;
import com.bank.service.customeraccount.CustomerAccountService;
import com.bank.service.account.AccountDeleteService;
import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
//@JsonRpcService("/api")
//@AutoJsonRpcServiceImpl
public class AccountController {


    @Autowired
    private AccountOpenService accountOpenService;


    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418)
    })
    public AccountOpenProjection openAccount(String name,
                                             String surname,
                                             String initials,
                                             java.util.Date date,
                                             String ssn,
                                             String address,
                                             String telephoneNumber,
                                             String email,
                                             String username,
                                             String password) throws InvalidParamValueError {
        return accountOpenService.openAccount(name, surname, initials, date, ssn, address, telephoneNumber, email, username, password);
    }

}
