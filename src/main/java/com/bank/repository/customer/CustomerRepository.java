package com.bank.repository.customer;

import com.bank.bean.customer.CustomerBean;
import com.bank.projection.customer.CustomerAccountAccesProjection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerBean, Integer> {


    CustomerBean getCustomerBeanByCustomerId(int customerId);

    CustomerBean getCustomerBeanByUsername(String username);

    @Modifying
    @Query("update CustomerBean c set c.isActive = false where c.customerId = ?1")
    void closeCustomer(int customerId);



    @Query("select new com.bank.projection.customer.CustomerAccountAccesProjection(account.accountNumber, customer.username) " +
            "from CustomerBean customer, CustomerAccount customeraccount, CustomerAccount main, AccountBean account " +
            "where customeraccount.customerId = ?1 " +
            "and main.isMain = true " +
            "and customeraccount.accountId = main.accountId " +
            "and customeraccount.accountId = account.accountId " +
            "and main.customerId = customer.customerId")
    List<CustomerAccountAccesProjection> getCustomerAccess(int customerId);

}
