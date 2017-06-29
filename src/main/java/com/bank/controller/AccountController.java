package com.bank.controller;

import com.bank.bean.card.CardBean;
import com.bank.bean.customer.CustomerBean;
import com.bank.exception.InvalidParamValueError;
import com.bank.service.account.AccountCreateService;
import com.bank.service.account.AccountAmountService;
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
@JsonRpcService("/api")
@AutoJsonRpcServiceImpl
public class AccountController {

    @Autowired
    private AccountCreateService accountCreateService;

    @Autowired
    private CustomerAccountService accountCustomersService;

    @Autowired
    private AccountAmountService accountAmountService;

    @Autowired
    private AccountDeleteService accountDeleteService;

    @Autowired
    private CustomerCreateService customerCreateService;


    @JsonRpcErrors({
            @JsonRpcError(exception=InvalidParamValueError.class, code=418),
            @JsonRpcError(exception=Throwable.class,code=-187)
    })
    public CardBean openAccount(@JsonRpcParam("name") String name,
                                @JsonRpcParam("surname") String surname,
                                @JsonRpcParam("initials") String initials,
                                @JsonRpcParam("dob") Date date,
                                @JsonRpcParam("ssn") String ssn,
                                @JsonRpcParam("address") String address,
                                @JsonRpcParam("telephoneNumber") String telephoneNumber,
                                @JsonRpcParam("email") String email,
                                @JsonRpcParam("username") String username,
                                @JsonRpcParam("password") String password) throws InvalidParamValueError {

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

        return accountCreateService.createAccount(customerBean, true);

//        accountCreateService.createAccount(name, surname, initials, date, ssn, address, telephoneNumber, email, username, password);

    }


//    @ApiOperation(value = "Used to create a new account. ",
//            notes = "Used to create a new account.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Account successfully added")
//    })
//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(method = RequestMethod.PUT)
//    public void createAccount(HttpSession session, @RequestBody AccountCreateCommand command) throws BadRequestException {
//        accountCreateService.createAccount(command);
//    }

//    @ApiOperation(value = "Used to delete an account",
//            notes = "This does not remove the account from the database. The account will be made inactive. This keeps the " +
//                    "foreign keys intact.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Account successfully deleted"),
//            @ApiResponse(code = 404, message = "Account not found")
//    })
//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(method = RequestMethod.DELETE)
//    public void deleteAccount(HttpSession session, @RequestBody AccountDeleteCommand accountDeleteCommand) throws NotFoundException {
//        accountDeleteService.deleteAccount(accountDeleteCommand);
//    }
//
//    @ApiOperation(value = "Used to get the amount on the account",
//            notes = "Gets the amount currently stored on the given account. ")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Account successfully deleted")
//    })
//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value = "/amount/{accountId}", method = RequestMethod.GET)
//    public AccountAmountProjection getAmount(@PathVariable int accountId) {
//        return accountAmountService.getAmountOfAccount(accountId);
//    }
//
//    @ApiOperation(value = "Used to get all the users on this account",
//            notes = "Returns a list of all the users currently assigned to this account.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Account successfully deleted")
//    })
//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value = "/customers/{accountId}", method = RequestMethod.GET)
//    public List<AccountCustomerDetailsProjection> getCustomers(@PathVariable("accountId") int accountId) {
//        return accountCustomersService.getCustomersOfAccount(accountId);
//    }
//
//    @ApiOperation(value = "Used to get all the cards on this account",
//            notes = "Returns a list of all the cards currently assigned to this account.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Account successfully deleted")
//    })
//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value = "/cards/{accountId}", method = RequestMethod.GET)
//    public void getCards() {
//        //TODO implement this
//    }
//
//    @ApiOperation(value = "Used to add a customer to a given account",
//            notes = "Adds the customer the given account")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Customer successfully added to account")
//    })
//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
//    public void addCustomerToAccount(@RequestBody CustomerAccountCreateCommand command) {
//        accountCustomersService.addCustomerAccount(command);
//    }

}
