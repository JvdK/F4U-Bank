package com.bank.controller;

import com.bank.bean.customer.AccountBean;
import com.bank.bean.customer.CustomerAccount;
import com.bank.command.account.AccountDeleteCommand;
import com.bank.exception.BadRequestException;
import com.bank.projection.account.AccountCustomerDetailsProjection;
import com.bank.service.account.AccountCreateService;
import com.bank.service.account.AccountAmountService;
import com.bank.service.account.AccountCustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/rest/account")
public class AccountController {
    private final AccountCreateService accountCreateService;

    @Autowired
    public AccountController(AccountCreateService accountCreateService) {
        this.accountCreateService = accountCreateService;
    }

    @Autowired
    private AccountCustomerService accountCustomersService;

    @Autowired
    private AccountAmountService accountAmountService;

    @ApiOperation(value = "Used to create a new account. ",
            notes = "Used to create a new account.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account successfully added")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
    public void createAccount(HttpSession session, @RequestBody AccountBean accountBean) throws BadRequestException {
        accountCreateService.createAccount(accountBean);
    }

    @ApiOperation(value = "Used to delete an account",
            notes = "This does not remove the account from the database. The account will be made inactive. This keeps the " +
                    "foreign keys intact.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account successfully deleted")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAccount(HttpSession session, @RequestBody AccountDeleteCommand accountDeleteCommand) {
        //TODO implement this
    }

    @ApiOperation(value = "Used to get the amount on the account",
            notes = "Gets the amount currently stored on the given account. ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account successfully deleted")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/amount/{accountId}", method = RequestMethod.GET)
    public double getAmount(@PathVariable int accountId) {
        return accountAmountService.getAmountOfAccount(accountId);
    }

    @ApiOperation(value = "Used to get all the users on this account",
            notes = "Returns a list of all the users currently assigned to this account.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account successfully deleted")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/customers/{accountId}", method = RequestMethod.GET)
    public List<AccountCustomerDetailsProjection> getCustomers(@PathVariable("accountId") int accountId) {
        return accountCustomersService.getCustomersOfAccount(accountId);
    }

    @ApiOperation(value = "Used to get all the cards on this account",
            notes = "Returns a list of all the cards currently assigned to this account.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account successfully deleted")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/cards/{accountId}", method = RequestMethod.GET)
    public void getCards() {
        //TODO implement this
    }

    @ApiOperation(value = "Used to add a customer to a given account",
            notes = "Adds the customer the given account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer successfully added to account")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    public void addCustomerToAccount(@RequestBody CustomerAccount customerAccount) {
        accountCustomersService.addCustomerAccount(customerAccount);
    }

}
