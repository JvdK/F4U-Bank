package com.bank.repository.transaction;

import com.bank.bean.transaction.TransactionBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionAddRepository extends CrudRepository<TransactionBean, Integer> {


}
