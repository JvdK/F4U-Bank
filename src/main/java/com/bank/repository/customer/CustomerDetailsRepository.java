package com.bank.repository.customer;

import com.bank.bean.customer.CustomerBean;
import com.bank.projection.customer.CustomerDetailsProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDetailsRepository extends CrudRepository<CustomerBean, Integer> {

    CustomerDetailsProjection findByCustomerId(int customerId);

}