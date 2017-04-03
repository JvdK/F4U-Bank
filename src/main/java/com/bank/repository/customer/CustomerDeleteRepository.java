package com.bank.repository.customer;

import com.bank.bean.customer.CustomerBean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerDeleteRepository extends CrudRepository<CustomerBean, Integer> {

    @Modifying
    @Transactional
    @Query("update CustomerBean bean set bean.isActive = false where bean.customerId = :customerId")
    public void deleteCustomer(@Param("customerId") int customerId);
}
