package com.bank.bean.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "customer")
public class CustomerBean {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", insertable = false)
    private int customerId;

    @Column(name = "user_name", nullable = false, length = 20)
    private String userName;

    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String initials;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @JsonIgnore
    @Column(name = "date_of_creation", updatable = false)
    private Date dateOfCreation;

    private String address;

    private String phone;

    @Column(name = "postal_code")
    private String postalCode;

    private String city;

    private String country;

    private String email;

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

//    @OneToMany(mappedBy = "customerBean")
//    private List<CustomerAccount> accounts;
//
//    public List<CustomerAccount> getAccounts() {
//        return accounts;
//    }
//
//    public void setAccounts(List<CustomerAccount> accounts) {
//        this.accounts = accounts;
//    }
//
//    public void addAccout(AccountBean accountBean, boolean isMain) {
//        CustomerAccount customerAccount = new CustomerAccount();
//
//        customerAccount.setAccountBean(accountBean);
//        customerAccount.setAccountId(accountBean.getAccountId());
//        customerAccount.setCustomerBean(this);
//        customerAccount.setCustomerId(this.getCustomerId());
//        customerAccount.setMain(isMain);
//        this.accounts.add(customerAccount);
//
//        accountBean.getCustomers().add(customerAccount);
//    }
}
