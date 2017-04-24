package com.bank.projection.account;

import com.bank.bean.customer.CustomerBean;
import com.bank.projection.customer.CustomerDetailsProjection;

public interface AccountCustomerDetailsProjection {

    CustomerDetailsProjection getCustomerBean();
    boolean getIsMain();
}
