package com.bank.controller;

import com.bank.bean.card.CardBean;
import com.bank.command.card.CardAddCommand;
import com.bank.service.card.CardCreateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/card")
public class CardController {

    @Autowired
    private CardCreateService cardCreateService;

    @ApiOperation(value = "Used for creating a new card",
            notes = "Adds a new card to the given account and customer. It is not needed that the customer is owner of the account. " +
                    "In other words it is possible that a user has a card of an account which he does not own.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer successfully added")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
    public void addCard(@RequestBody CardAddCommand command){
        cardCreateService.addCard(command);
    }



}
