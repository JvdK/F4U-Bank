package com.bank.repository;

import com.bank.customer.CustomerBean;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerBean, Integer> {


}
