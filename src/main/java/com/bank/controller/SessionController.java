package com.bank.controller;

import com.bank.command.session.LoginCommand;
import com.bank.constant.SessionConstant;
import com.bank.exception.AuthenticationException;
import com.bank.service.session.SessionLoginService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest/session")
public class SessionController {

    @Autowired
    private SessionLoginService sessionLoginService;

    @ApiOperation(value = "Used to login",
        notes = "Checks whether the supplied username and password match. If so, in the session data the customer id is set. " +
                "A 200 is returned if login was successful, 403 is returned if login failed. Furthermore a cookie with " +
                "a JSESSIONID field is set on the client side. ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login successful"),
            @ApiResponse(code = 403, message = "Login failed")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public void login(HttpSession session, @RequestBody LoginCommand loginCommand) throws AuthenticationException {
        int customerId = sessionLoginService.checkLogin(loginCommand.getUsername(), loginCommand.getPassword());
        if(customerId!=-1){
            session.setAttribute(SessionConstant.LOGIN, true);
            session.setAttribute(SessionConstant.CUSTOMER_ID, customerId);
            System.out.println("login successfull");
        }else{
            throw new AuthenticationException("Customer name or password do not correspond");
        }


    }

    @ApiOperation(value = "Used to logout",
            notes = "Invalidates the session if it exists. It also removes the cookie with the session id at the client side")
    @ApiResponse(code=200, message = "Successfully logged out")
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public void logout(HttpSession session){
        if(session != null) {
            session.invalidate();
        }
    }

}
