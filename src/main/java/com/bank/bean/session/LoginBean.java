package com.bank.bean.session;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class LoginBean {
    /**
     * User name of the customer.
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * Password of the customer.
     */
    private String password;

    /**
     * Internal customer id.
     */
    @Id
    @Column(name = "customer_id")
    private int customerId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}

