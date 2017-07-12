package com.bank.projection.transaction;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class TransactionProjection {


    private String sourceIBAN;
    private String targetIBAN;
    private String targetName;
    private Date date;
    private double amount;
    private String description;

    public TransactionProjection(String sourceIBAN, String targetIBAN, String targetName, Date date, double amount, String description) {
        this.sourceIBAN = sourceIBAN;
        this.targetIBAN = targetIBAN;
        this.targetName = targetName;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public String getSourceIBAN() {
        return sourceIBAN;
    }

    public void setSourceIBAN(String sourceIBAN) {
        this.sourceIBAN = sourceIBAN;
    }

    public String getTargetIBAN() {
        return targetIBAN;
    }

    public void setTargetIBAN(String targetIBAN) {
        this.targetIBAN = targetIBAN;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
