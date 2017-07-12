package com.bank.repository.customer;

import com.bank.bean.customer.CustomerBean;
import com.bank.projection.customer.CustomerIdProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInformationRepository extends CrudRepository<CustomerBean, Integer> {
    CustomerIdProjection findByUsername(String username);
}
