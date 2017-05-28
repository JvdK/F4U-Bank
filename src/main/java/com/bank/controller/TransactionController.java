package com.bank.controller;

import com.bank.command.transaction.TransactionAddCommand;
import com.bank.exception.BadRequestException;
import com.bank.exception.NotFoundException;
import com.bank.projection.transaction.TransactionInformationProjection;
import com.bank.service.transaction.TransactionGetService;
import com.bank.service.transaction.TransactionCreateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/transaction")
public class TransactionController {

    @Autowired
    private TransactionCreateService transactionCreateService;

    @Autowired
    private TransactionGetService transactionGetService;

    @ApiOperation(value = "Used for making a transaction",
            notes = "Makes a transaction. The given amount is removed from the source account and added to the target account. " +
                    "No authentication is necessary at this moment, and the card is optionally. ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer successfully added"),
            @ApiResponse(code = 400, message = "Not a correct request"),
            @ApiResponse(code = 404, message = "Unknown source or target account")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
    public void doTransaction(@RequestBody TransactionAddCommand command) throws NotFoundException, BadRequestException {
        transactionCreateService.doTransaction(command);
    }

    @ApiOperation(value = "Used for getting the transactions of an account",
            notes = "Retrieves all transactions related to the given account. ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer successfully added"),
            @ApiResponse(code = 404, message = "Account could not be found")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{accountNumber}", method = RequestMethod.GET)
    public List<TransactionInformationProjection> getTransactions(@PathVariable("accountNumber") String accountNumber) throws NotFoundException {
        return transactionGetService.getTransactionsOfAccount(accountNumber);
    }

}
