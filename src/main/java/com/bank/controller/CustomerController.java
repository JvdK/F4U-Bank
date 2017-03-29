package com.bank.controller;

import com.bank.customer.CustomerBean;
import com.bank.command.customer.CustomerDeleteCommand;
import com.bank.service.customer.CustomerCreateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest/customer")
public class CustomerController {

    @Autowired
    private CustomerCreateService customerCreateService;

    @ApiOperation(value = "Used for creating a new customer",
        notes = "Adds the given user to the database.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer successfully added")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
    public void createCustomer(HttpSession session, @RequestBody CustomerBean customerBean){
        customerCreateService.createCustomer(customerBean);
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

    @ApiOperation(value = "Used to update a customer",
        notes = "Used to update a given customer. The fields will be overwritten. Fields not supplied will be set to null.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User successfully updated")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateCustomer(HttpSession session){
        //TODO implement this
    }

    @ApiOperation(value = "Used to remove a customer",
        notes = "This does not really deletes the user, but sets the user to inactive. This keeps all the records linked " +
                "to this customer intact.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User successfully removed")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteCustomer(HttpSession session, @RequestBody CustomerDeleteCommand customerDeleteCommand){
        //TODO implement this
    }


}
