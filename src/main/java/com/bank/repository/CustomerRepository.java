package com.bank.repository;

import com.bank.bean.CustomerBean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends CrudRepository<CustomerBean, Integer> {


}
