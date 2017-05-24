package com.bank.bean.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * This class covers the relation between a customer and a account.
 */
@Entity
@Table(name = "customer_account")
@IdClass(CustomerAccountId.class)
public class CustomerAccount {
    @Id
    @Column(name = "customer_id")
    private int customerId;

    @Id
    @Column(name = "account_id")
    private int accountId;

    /**
     * Indicates whether the referenced customer_id is the main owner of the account.
     */
    @Column(name = "is_main")
    private boolean isMain;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id", updatable = false, insertable = false)
    private CustomerBean customerBean;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id", updatable = false, insertable = false)
    private AccountBean accountBean;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }

    public CustomerBean getCustomerBean() {
        return customerBean;
    }

    public void setCustomerBean(CustomerBean customerBean) {
        this.customerBean = customerBean;
    }

    public AccountBean getAccountBean() {
        return accountBean;
    }

    public void setAccountBean(AccountBean accountBean) {
        this.accountBean = accountBean;
    }

    @Override
    public String toString() {
        return "CustomerAccount{" +
                "customerId=" + customerId +
                ", accountId=" + accountId +
                ", isMain=" + isMain +
                ", customerBean=" + customerBean +
                ", accountBean=" + accountBean +
                '}';
    }
}
