package com.bank.projection.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerAccountAccessProjection {

    private String IBAN;
    private String owner;

    public CustomerAccountAccessProjection(String IBAN, String owner) {
        this.IBAN = IBAN;
        this.owner = owner;
    }

    @JsonProperty("IBAN")
    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
