package com.bank.repository.account;

import com.bank.bean.account.AccountBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountBean, Integer> {


//    AccountBean findAccountBeanByAccountIdAndIsActiveTrue(int accountId);

    AccountBean findAccountBeanByAccountNumber(String accountNumber);
}