package com.bank.controller;

import com.bank.exception.AuthenticationException;
import com.bank.projection.session.SessionAuthTokenProjection;
import com.bank.service.session.SessionLoginService;
import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@JsonRpcService("/api/iets")
//@AutoJsonRpcServiceImpl
public class SessionController {

    @Autowired
    private SessionLoginService loginService;


    @JsonRpcErrors({
            @JsonRpcError(exception = AuthenticationException.class, code = 422)
    })
    public SessionAuthTokenProjection getAuthToken(@JsonRpcParam("username") String username, @JsonRpcParam("password") String password) throws AuthenticationException {
        return loginService.getAuthToken(username, password);
    }


}
