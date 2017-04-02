package com.bank.repository.session;

import com.bank.bean.session.LoginBean;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Boschma on 29-3-2017.
 */
public interface LoginRepository extends CrudRepository<LoginBean, Integer> {

    LoginBean findByUsername(String username);
}
