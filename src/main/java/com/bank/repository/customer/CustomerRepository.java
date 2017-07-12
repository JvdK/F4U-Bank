package com.bank.repository.customer;

import com.bank.bean.customer.CustomerBean;
import com.bank.projection.customer.CustomerAccountAccessProjection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerBean, Integer> {


    CustomerBean getCustomerBeanByCustomerId(int customerId);

    CustomerBean getCustomerBeanByUsername(String username);

    @Modifying
    @Query("update CustomerBean c set c.isActive = false where c.customerId = ?1")
    void closeCustomer(int customerId);



    @Query("select new com.bank.projection.customer.CustomerAccountAccessProjection(account.accountNumber, customer.username) " +
            "from CustomerBean customer, CustomerAccount customeraccount, CustomerAccount main, AccountBean account " +
            "where customeraccount.customerId = ?1 " +
            "and main.isMain = true " +
            "and customeraccount.accountId = main.accountId " +
            "and customeraccount.accountId = account.accountId " +
            "and main.customerId = customer.customerId")
    List<CustomerAccountAccessProjection> getCustomerAccess(int customerId);

}
