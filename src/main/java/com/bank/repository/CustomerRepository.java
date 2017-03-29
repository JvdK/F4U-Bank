package com.bank.repository;

import com.bank.bean.session.customer.CustomerBean;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerBean, Integer> {


}
