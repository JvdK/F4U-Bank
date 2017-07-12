package com.bank.repository.transaction;

import com.bank.bean.account.AccountBean;
import com.bank.bean.transaction.TransactionBean;
import com.bank.projection.transaction.TransactionInformationProjection;
import com.bank.projection.transaction.TransactionProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionBean, Integer> {

    List<TransactionInformationProjection> findTransactionBeansByTargetBeanOrSourceBean(AccountBean target, AccountBean source);

    @Query("select new com.bank.projection.transaction.TransactionProjection(t.sourceBean.accountNumber, t.targetBean.accountNumber, t.targetName, t.date, t.amount, t.comment) " +
            "from TransactionBean t " +
            "where t.sourceBean.accountId = ?1 or t.targetBean.accountId = ?1 " +
            "order by date desc")
    Page<TransactionProjection> getListOfXLatestTransactions(Pageable pageable, int accountId);
}
