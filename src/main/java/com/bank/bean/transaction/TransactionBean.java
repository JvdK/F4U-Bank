package com.bank.bean.transaction;

import com.bank.bean.card.CardBean;
import com.bank.bean.account.AccountBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "transaction")
public class TransactionBean {
    /**
     * Internal transaction id, unique identifier.
     */
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int transactionId;

    /**
     * Amount to be transferred to the target account.
     */
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account_id")
    private AccountBean sourceBean;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_account_id")
    private AccountBean targetBean;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private CardBean card;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date;

    @Column(name = "target_name")
    private String targetName = "";

    /**
     * Custom comment a customer can give with a transaction.
     */
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
}
