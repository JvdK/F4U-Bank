package com.bank.repository.transaction;

import com.bank.bean.account.AccountBean;
import com.bank.bean.transaction.TransactionBean;
import com.bank.projection.transaction.TransactionInformationProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionBean, Integer> {

    List<TransactionInformationProjection> findTransactionBeansByTargetBeanOrSourceBean(AccountBean target, AccountBean source);

}
