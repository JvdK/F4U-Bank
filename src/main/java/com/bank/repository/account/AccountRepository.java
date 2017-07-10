package com.bank.repository.account;

import com.bank.bean.account.AccountBean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AccountRepository extends CrudRepository<AccountBean, Integer> {


//    AccountBean findAccountBeanByAccountIdAndIsActiveTrue(int accountId);

    AccountBean findAccountBeanByAccountNumber(String accountNumber);

    @Modifying
    @Query("update AccountBean a set a.isActive = false where a.accountNumber = ?1")
    void closeAccount(String IBAN);
}