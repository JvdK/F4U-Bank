package com.bank.bean.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
public class AccountBean {
    @Id
    @JsonIgnore
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "account_number")
    private String accountNumber;

    @JsonIgnore
    private double amount;

    @Column(name = "is_active")
    private boolean isActive;

    int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "accountBean")
    private List<CustomerAccount> customers;

    public List<CustomerAccount> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerAccount> customers) {
        this.customers = customers;
    }
}
