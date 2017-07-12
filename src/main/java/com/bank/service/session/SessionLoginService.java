package com.bank.service.session;

import com.bank.exception.AuthenticationException;
import com.bank.projection.session.SessionAuthTokenProjection;
import com.bank.projection.session.SessionPasswordProjection;
import com.bank.repository.session.LoginRepository;
import com.bank.service.AuthenticationService;
import com.bank.service.customer.CustomerGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionLoginService {
    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private CustomerGetService customerGetService;

    public boolean checkLogin(String username, String password) {
        SessionPasswordProjection sessionPasswordProjection = loginRepository.findByUsername(username);
        return sessionPasswordProjection != null && sessionPasswordProjection.getPassword() != null && sessionPasswordProjection.getPassword().equals(password);
    }

    public SessionAuthTokenProjection getAuthToken(String username, String password) throws AuthenticationException {
        if (checkLogin(username, password)) {
            SessionAuthTokenProjection projection = new SessionAuthTokenProjection();
            projection.setAuthToken(AuthenticationService.instance.login(customerGetService.getCustomerId(username)));
            return projection;
        } else {
            throw new AuthenticationException("Invalid username password combination");
        }
    }
}
