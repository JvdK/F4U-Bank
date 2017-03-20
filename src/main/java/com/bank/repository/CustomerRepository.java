package com.bank.repository;

import com.bank.bean.CustomerBean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends CrudRepository<CustomerBean, Integer> {

    @Query("SELECT CONCAT(c.firstName, ' ', c.lastName) FROM CustomerBean c WHERE customer_id = :id")
    String getName(@Param("id") String id);
}
