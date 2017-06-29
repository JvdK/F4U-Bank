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
    @Column(name = "pin_card")
    private String pinCard;

    @Column(name = "pin_code")
    private String pinCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerBean customerBean;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AccountBean accountBean;


    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getPinCard() {
        return pinCard;
    }

    public void setPinCard(String pinCard) {
        this.pinCard = pinCard;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
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
