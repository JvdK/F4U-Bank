package com.bank.bean.card;

import com.bank.bean.account.AccountBean;
import com.bank.bean.customer.CustomerBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "card")
public class CardBean {
    /**
     * Internal card id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "card_id")
    private int cardId;

    /**
     * The number on the card.
     */
    @Column(name = "card_number")
    private int cardNumber;

    /**
     * Date the card expires.
     */
    @JsonIgnore
    @Column(name = "date_of_expiration")
    private Date dateOfExpiration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerBean customerBean;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AccountBean accountBean;

    /**
     * Indicates whether the card is still valid.
     */
    @Column(name = "is_valid")
    private boolean isValid;

    public int getCardId() {
        return cardId;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getDateOfExpiration() {
        return dateOfExpiration;
    }

    public void setDateOfExpiration(Date dateOfExpiration) {
        this.dateOfExpiration = dateOfExpiration;
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

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    @PrePersist
    void dateOfExpiration() {
        this.dateOfExpiration = new Date((long)(new Date().getTime()+(3.1556926e10)));
    }
}
