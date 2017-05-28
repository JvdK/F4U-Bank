package com.bank.service.transaction;

import com.bank.bean.account.AccountBean;
import com.bank.exception.NotFoundException;
import com.bank.projection.transaction.TransactionInformationProjection;
import com.bank.repository.account.AccountRepository;
import com.bank.repository.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionGetService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<TransactionInformationProjection> getTransactionsOfAccount(String accountNumber) throws NotFoundException {
        AccountBean bean = accountRepository.findAccountBeanByAccountNumber(accountNumber);
        if(bean == null){
            throw new NotFoundException();
        }
        return transactionRepository.findTransactionBeansByTargetBeanOrSourceBean(bean, bean);
    }

}
