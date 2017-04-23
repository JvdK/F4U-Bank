package com.bank.repository.account;

import com.bank.bean.customer.AccountBean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountBean, Integer> {

    @Query("select a.amount from AccountBean a where a.accountId = ?1")
    double getAmountOfAccount(int accountId);
}