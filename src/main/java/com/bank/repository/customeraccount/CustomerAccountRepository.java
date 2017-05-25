package com.bank.repository.customeraccount;

import com.bank.bean.customeraccount.CustomerAccount;
import com.bank.projection.account.AccountCustomerDetailsProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAccountRepository extends CrudRepository<CustomerAccount, Integer> {

    List<AccountCustomerDetailsProjection> findCustomerAccountsByAccountId(int accountId);

}
