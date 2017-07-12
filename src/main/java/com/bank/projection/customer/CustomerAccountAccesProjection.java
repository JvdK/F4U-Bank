package com.bank.projection.customer;

public class CustomerAccountAccesProjection {

    private String IBAN;
    private String owner;

    public CustomerAccountAccesProjection(String IBAN, String owner) {
        this.IBAN = IBAN;
        this.owner = owner;
    }

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
