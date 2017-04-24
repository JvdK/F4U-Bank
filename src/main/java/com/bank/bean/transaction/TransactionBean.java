package com.bank.bean.transaction;

import com.bank.bean.card.CardBean;
import com.bank.bean.customer.AccountBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class TransactionBean {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int transactionId;

    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account_id", nullable = false)
    private AccountBean sourceBean;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_account_id", nullable = false)
    private AccountBean targetBean;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private CardBean card;

    @Column(length = 128)
    private String comment;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public AccountBean getSourceBean() {
        return sourceBean;
    }

    public void setSourceBean(AccountBean sourceBean) {
        this.sourceBean = sourceBean;
    }

    public AccountBean getTargetBean() {
        return targetBean;
    }

    public void setTargetBean(AccountBean targetBean) {
        this.targetBean = targetBean;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CardBean getCard() {
        return card;
    }

    public void setCard(CardBean card) {
        this.card = card;
    }
}
