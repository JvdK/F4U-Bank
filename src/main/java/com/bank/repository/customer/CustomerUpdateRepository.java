package com.bank.repository.customer;

import com.bank.bean.customer.CustomerBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerUpdateRepository extends CrudRepository<CustomerBean, Integer> {

}