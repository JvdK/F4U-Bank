package com.bank.service;

import com.bank.bean.LoginBean;
import com.bank.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionLoginService {

    @Autowired
    private LoginRepository loginRepository;

    /**
     * Checks if the given customer name and password correspond. If so the user id is returned.
     * @param username name of the customer
     * @param password password of the customer
     * @return userId of the customer if name and password correspond, -1 if they do not.
     */
    public int checkLogin(String username, String password){
        LoginBean bean = loginRepository.findByUsername(username);
        if(bean==null){
            return -1;
        }
        if (bean.getPassword().equals(password)){
            return bean.getCustomerId();
        }else{
            return -1;
        }
    }

}
