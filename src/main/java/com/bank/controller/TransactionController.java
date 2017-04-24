package com.bank.controller;

import com.bank.command.transaction.TransactionAddCommand;
import com.bank.exception.NotFoundException;
import com.bank.service.transaction.TransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @ApiOperation(value = "Used for making a transaction",
            notes = "Makes a transaction. The given amount is removed from the source account and added to the target account. " +
                    "No authentication is necessary at this moment, and the card is optionally. ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer successfully added"),
            @ApiResponse(code = 404, message = "Unknown source or target account")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
    public void doTransaction(@RequestBody TransactionAddCommand command) throws NotFoundException {
        transactionService.doTransaction(command);
    }

}
