package com.bank.service.transaction;

import com.bank.bean.account.AccountBean;
import com.bank.bean.card.CardBean;
import com.bank.bean.transaction.TransactionBean;
import com.bank.repository.account.AccountAmountRepository;
import com.bank.repository.transaction.TransactionRepository;
import com.bank.service.account.AccountAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountAmountService accountAmountService;

    public void doTransaction(AccountBean sourceAccountBean, AccountBean targetAccountBean, double amount){
        doTransaction(sourceAccountBean, targetAccountBean, amount, null, "");
    }

    public void doTransaction(AccountBean sourceAccountBean, AccountBean targetAccountBean, double amount, CardBean card, String description){
        TransactionBean transactionBean = new TransactionBean();
        transactionBean.setSourceBean(sourceAccountBean);
        transactionBean.setTargetBean(targetAccountBean);
        transactionBean.setAmount(amount);

        transactionBean.setCard(card);
        transactionBean.setComment(description);

        transactionRepository.save(transactionBean);

        accountAmountService.updateAmount(sourceAccountBean.getAccountId(), targetAccountBean.getAccountId(), amount);
    }

    public void doSingleTransaction(AccountBean targetAccountBean, CardBean card, double amount){
        TransactionBean transactionBean = new TransactionBean();
        transactionBean.setTargetBean(targetAccountBean);
        transactionBean.setAmount(amount);

        transactionBean.setCard(card);
        transactionBean.setComment("");

        transactionRepository.save(transactionBean);

        accountAmountService.updateAmount(targetAccountBean.getAccountId(), amount);
    }


}
