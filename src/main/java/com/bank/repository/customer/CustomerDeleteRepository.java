package com.bank.repository.customer;

import com.bank.bean.customer.CustomerBean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDeleteRepository extends CrudRepository<CustomerBean, Integer> {

//    @Modifying
//    @Query("update CustomerBean bean set bean.active= false where bean.customerId = :customerId")
//    int deleteCustomer(int customerId);
}
