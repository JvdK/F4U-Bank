package com.bank.projection.transaction;

import org.springframework.beans.factory.annotation.Value;

public interface TransactionInformationProjection {

//    double amount;
//    String sourceAccountNumber;
//    String targetAccountNumber;
//    int cardNumber;
//    String comment;

    double getAmount();
    String getComment();
    @Value("#{target.getSourceBean().getAccountNumber()}")
    String getSourceAccountNumber();
    @Value("#{target.getTargetBean().getAccountNumber()}")
    String getTargetAccountNumber();
    //int getCardNumber();
}
