package com.bank.repository.session;

import com.bank.bean.customer.CustomerBean;
import com.bank.bean.session.LoginBean;
import com.bank.projection.customer.CustomerLoginProjection;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Boschma on 29-3-2017.
 */
public interface LoginRepository extends CrudRepository<CustomerBean, Integer> {

//    CustomerLoginProjection findByUserNameAndIsActiveTrue(String username);
}
