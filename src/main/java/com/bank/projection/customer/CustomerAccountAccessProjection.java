package com.bank.projection.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerAccountAccessProjection {
    private String iBAN;
    private String owner;

    public CustomerAccountAccessProjection(String iBAN, String owner) {
        this.iBAN = iBAN;
        this.owner = owner;
    }

    @JsonProperty("iBAN")
    public String getiBAN() {
        return iBAN;
    }

    public void setiBAN(String iBAN) {
        this.iBAN = iBAN;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
