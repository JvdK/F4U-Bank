package com.bank.bean.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * The Customer class contains all relevant data field for a customer.
 */
@Entity
@Table(name = "customer")
public class CustomerBean {
    /**
     * Internal customer id.
     */
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", insertable = false)
    private int customerId;

    /**
     * First name of the customer
     */
    private String name;
    /**
     * Last name of the customer
     */
    private String surname;
    /**
     * Initials of the customer
     */
    private String initials;
    /**
     * Date-of-birth of the customer
     */
    private Date dob;
    /**
     * ssn of the customer
     */
    private String ssn;

    /**
     * Full street, house number and optional additions to the house number, currently no validation is implemented.
     */
    private String address;
    /**
     * Phone number of the customer, currently no validation is implemented.
     */
    @Column(name = "telephone_number")
    private String telephoneNumber;

    /**
     * Email address of the customer, currently no validation is implemented.
     */
    private String email;
    /**
     * User name for customer they can use for authentication.
     */
    @Column(name = "user_name", unique = true)
    private String username;
    /**
     * Password of the customer
     */
    private String password;


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}