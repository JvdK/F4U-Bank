package com.bank.bean.customer;

import javax.persistence.*;

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
    @Column(name = "is_main")
    private boolean isMain;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "customer_id")
    private CustomerBean customerBean;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "account_id")
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
}
