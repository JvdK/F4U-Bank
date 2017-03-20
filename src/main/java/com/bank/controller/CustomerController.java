package com.bank.controller;

import com.bank.bean.CustomerBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest/customer")
public class CustomerController {


    @ApiOperation(value = "Used for creating a new customer",
        notes = "Adds the given user to the database. The given id is ignored and the database generates its own id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer successfully added")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
    public void createCustomer(HttpSession session, @RequestBody CustomerBean customerBean){
        //TODO implement this
    }

    @ApiOperation(value = "Used to get a customer",
        notes = "Used for getting the information about a given customer.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer successful retrieved")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public CustomerBean getCustomer(HttpSession session, @PathVariable int customerId){
        //TODO implement this
        return null;
    }

    public void updateCustomer(HttpSession session){

    }

    public void deleteCustomer(HttpSession session){

    }


}