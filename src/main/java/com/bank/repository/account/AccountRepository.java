package com.bank.repository.account;

import com.bank.bean.customer.AccountBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountBean, Integer> {

}