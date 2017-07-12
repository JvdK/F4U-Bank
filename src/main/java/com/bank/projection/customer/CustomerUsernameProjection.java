package com.bank.projection.customer;

public class CustomerUsernameProjection {

    private String username;

    public CustomerUsernameProjection(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
