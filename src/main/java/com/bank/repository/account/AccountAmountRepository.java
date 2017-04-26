package com.bank.repository.account;

import com.bank.bean.customer.AccountBean;
import com.bank.projection.account.AccountAmountProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountAmountRepository extends CrudRepository<AccountBean, Integer> {

    AccountAmountProjection findAccountBeanByAccountIdWhereIsActiveTrue(int accountId);


}