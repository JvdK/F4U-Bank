package com.bank.bean.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * The Account class contains all data values related to a bank account.
 */
@Entity
@Table(name = "account")
public class AccountBean {
    /**
     * Internal account id, unique identifier.
     */
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    /**
     * Account id, unique string.
     */
    @Column(name = "account_number")
    private String accountNumber;

    /**
     * Current balance on the account.
     */
    @JsonIgnore
    private double amount;

    /**
     * Indicates whether the account is active or not.
     */
    @Column(name = "is_active")
    private boolean isActive;

    /**
     * List of customers that are owner of the account.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "accountBean")
    private List<CustomerAccount> customers;

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

    public List<CustomerAccount> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerAccount> customers) {
        this.customers = customers;
    }
}
