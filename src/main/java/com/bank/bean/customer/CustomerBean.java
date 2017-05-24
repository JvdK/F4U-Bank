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
     * User name for customer they can use for authentication.
     */
    @Column(name = "user_name", nullable = false, length = 20)
    private String userName;

    /**
     * Password for customer they can use for authentication.
     */
    private String password;

    /**
     * Full first name of the customer.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Full last name of the customer.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Initials of the first names of the customer.
     */
    private String initials;

    /**
     * Date the customer was born.
     */
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    /**
     * Date and time the customer was added to the database.
     */
    @JsonIgnore
    @Column(name = "date_of_creation", updatable = false)
    private Date dateOfCreation;

    /**
     * Full street, house number and optional additions to the house number, currently no validation is implemented.
     */
    private String address;

    /**
     * Phone number of the customer, currently no validation is implemented.
     */
    private String phone;

    /**
     * Postal code or zip code of the address of the customer, currently no validation is implemented.
     */
    @Column(name = "postal_code")
    private String postalCode;

    /**
     * City the customer currently lives in, currently no validation is implemented.
     */
    private String city;

    /**
     * Country of the customer, currently no validation or normalisation is implemented.
     */
    private String country;

    /**
     * Email address of the customer, currently no validation is implemented.
     */
    private String email;

    /**
     * Indicates whether the customer account is active or not.
     */
    @JsonIgnore
    @Column(name = "is_active")
    private boolean isActive = false;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @PrePersist
    void dateOfCreation() {
        this.dateOfCreation = new Date();

    }
}