package com.bank.controller;

import com.bank.command.account.AccountCreateCommand;
import com.bank.command.account.AccountDeleteCommand;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequestMapping("/rest/account")
public class AccountController {


    @ApiOperation(value = "Used to create a new account. ",
        notes = "Used to create a new account.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account successfully added")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
    public void createAccount(HttpSession session, AccountCreateCommand accountCreateCommand){
        //TODO implement this
    }

    @ApiOperation(value = "Used to delete an account",
        notes = "This does not remove the account from the database. The account will be made inactive. This keeps the " +
                "foreign keys intact.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account successfully deleted")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAccount(HttpSession session, @RequestBody AccountDeleteCommand accountDeleteCommand){
        //TODO implement this
    }

    @ApiOperation(value = "Used to get the amount on the account",
            notes = "Gets the amount currently stored on the given account. ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account successfully deleted")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/amount/{acountId}", method = RequestMethod.GET)
    public void getAmount(HttpSession session, @PathVariable int acountId){
        //TODO implement this
    }

    @ApiOperation(value = "Used to get all the users on this account",
            notes = "Returns a list of all the users currently assigned to this account.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account successfully deleted")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/customers/{acountId}", method = RequestMethod.GET)
    public void getCustomers(){
        //TODO implement this
    }

    @ApiOperation(value = "Used to get all the cards on this account",
            notes = "Returns a list of all the cards currently assigned to this account.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account successfully deleted")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/cards/{acountId}", method = RequestMethod.GET)
    public void getCards(){
        //TODO implement this
    }


}
