package com.bank.bean.customeraccount;

import java.io.Serializable;

/**
 * Class used for the many-to-many relation on customer-account.
 */
public class CustomerAccountId implements Serializable{
    private int customerId;
    private int accountId;

    public int hashCode() {
        return (int)(customerId + accountId);
    }

    public boolean equals(Object object) {
        if (object instanceof CustomerAccount) {
            CustomerAccountId otherId = (CustomerAccountId) object;
            return (otherId.customerId == this.customerId) && (otherId.accountId == this.accountId);
        }
        return false;
    }
}
