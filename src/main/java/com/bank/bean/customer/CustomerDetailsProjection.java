package com.bank.bean.customer;

import java.util.Date;

public interface CustomerDetailsProjection {

    int getCustomerId();
    String getUserName();
    String getFirstName();
    String getLastName();
    String getInitials();
    Date getDateOfBirth();
    Date getDateOfCreation();
    String getAddress();
    String getPhone();
    String getPostalCode();
    String getCity();
    String getEmail();

}
