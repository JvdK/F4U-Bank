package com.bank.controller;

import com.bank.command.transaction.TransactionAddCommand;
import com.bank.exception.BadRequestException;
import com.bank.exception.InvalidPINException;
import com.bank.exception.InvalidParamValueError;
import com.bank.exception.NotFoundException;
import com.bank.projection.transaction.TransactionInformationProjection;
import com.bank.service.transaction.TransactionGetService;
import com.bank.service.transaction.TransactionCreateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class TransactionController {

    @Autowired
    private TransactionCreateService transactionCreateService;

    public void depositIntoAccount(String IBAN, String pinCard, String pinCode, double amount) throws InvalidParamValueError, InvalidPINException {
        transactionCreateService.depositIntoAccount(IBAN, pinCard, pinCode, amount);
    }

    public void payFromAccount(String sourceIBAN, String targetIBAN, String pinCard, String pinCode, double amount) throws InvalidParamValueError, InvalidPINException {
        transactionCreateService.payFromAccount(sourceIBAN, targetIBAN, pinCard, pinCode, amount);
    }

}
