package com.bank.projection.customer;

import java.util.Date;

public interface CustomerUpdateProjection {

    int getCustomerId();
    String getUserName();
    String getPassword();
    String getFirstName();
    String getLastName();
    String getInitials();
    Date getDateOfBirth();
    String getAddress();
    String getPhone();
    String getPostalCode();
    String getCity();
    String getEmail();

}
