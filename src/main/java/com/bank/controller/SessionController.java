package com.bank.controller;

import com.bank.command.LoginCommand;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest/session")
public class SessionController {


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
    public void login(HttpSession session, @RequestBody LoginCommand loginCommand){
        //TODO implement this
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
