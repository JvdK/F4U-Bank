package com.bank.repository.customer;

import com.bank.bean.customer.CustomerBean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerBean, Integer> {


    CustomerBean getCustomerBeanByCustomerId(int customerId);

    @Modifying
    @Query("update CustomerBean c set c.isActive = false where c.customerId = ?1")
    void closeCustomer(int customerId);

}
