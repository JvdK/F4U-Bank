package com.bank.repository.session;

import com.bank.bean.customer.CustomerBean;
import com.bank.projection.session.SessionPasswordProjection;
import org.springframework.data.repository.CrudRepository;


public interface LoginRepository extends CrudRepository<CustomerBean, Integer> {

    SessionPasswordProjection findByUsername(String username);
}
