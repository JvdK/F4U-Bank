package com.bank.command.customeraccount;

import javax.persistence.Column;
import javax.persistence.Id;

public class CustomerAccountCreateCommand {


    private int customerId;

    private int accountId;

    private boolean isMain;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }
}
