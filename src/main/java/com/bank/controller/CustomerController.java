package com.bank.controller;

import com.bank.bean.customer.CustomerBean;
import com.bank.projection.customer.CustomerDetailsProjection;
import com.bank.command.customer.CustomerDeleteCommand;
import com.bank.constant.SessionConstant;
import com.bank.exception.BadRequestException;
import com.bank.exception.NotLoggedInException;
import com.bank.projection.customer.CustomerUpdateProjection;
import com.bank.service.customer.CustomerCreateService;
import com.bank.service.customer.CustomerDeleteService;
import com.bank.service.customer.CustomerGetService;
import com.bank.service.customer.CustomerUpdateService;
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

    @Autowired
    private CustomerGetService customerGetService;

    @Autowired
    private CustomerDeleteService customerDeleteService;

    @Autowired
    private CustomerUpdateService customerUpdateService;

    @ApiOperation(value = "Used for creating a new customer",
            notes = "Adds the given user to the database.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer successfully added"),
            @ApiResponse(code = 403, message = "Username not available")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
    public void createCustomer(HttpSession session, @RequestBody CustomerBean customerBean) throws BadRequestException {
        customerCreateService.createCustomer(customerBean);
    }

    @ApiOperation(value = "Used to get customer details",
            notes = "Used for getting the information about a given customer.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer successful retrieved")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public CustomerDetailsProjection getCustomer(HttpSession session, @PathVariable int customerId) {
        return customerGetService.getCustomerById(customerId);
    }

    @ApiOperation(value = "Used to get the details of the logged in customer",
            notes = "Returns the details of the logged in customer.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer successful retrieved"),
            @ApiResponse(code = 403, message = "Customer not logged in")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public CustomerDetailsProjection getLoggedInCustomer(HttpSession session) throws NotLoggedInException {
        if(session.getAttribute(SessionConstant.CUSTOMER_ID) != null) {
            return customerGetService.getCustomerById((Integer) session.getAttribute(SessionConstant.CUSTOMER_ID));
        }else{
            throw new NotLoggedInException();
        }
    }

    @ApiOperation(value = "Used to update a customer",
            notes = "Used to update a given customer. The fields will be overwritten. Fields not supplied will be set to null.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User successfully updated")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateCustomer(HttpSession session, @RequestBody CustomerUpdateProjection customerUpdateProjection) {
        //TODO implement this
        customerUpdateService.updateCustomer(1);
    }

    @ApiOperation(value = "Used to remove a customer",
            notes = "This does not really deletes the user, but sets the user to inactive. This keeps all the records linked " +
                    "to this customer intact.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User successfully removed")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteCustomer(HttpSession session, @RequestBody CustomerDeleteCommand customerDeleteCommand) {
        customerDeleteService.deleteCustomer(customerDeleteCommand.getCustomerId());
    }


}
