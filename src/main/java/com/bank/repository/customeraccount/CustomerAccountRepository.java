package com.bank.repository.customeraccount;

import com.bank.bean.customeraccount.CustomerAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CustomerAccountRepository extends CrudRepository<CustomerAccount, Integer> {
    @Query("select a from CustomerAccount a where a.customerId = ?1 and a.accountBean.isActive = true")
    List<CustomerAccount> getActiveCustomerAcounts(int customerId);

    CustomerAccount getFirstByAccountIdAndCustomerId(int accountId, int customerId);

    void deleteByCustomerIdAndAccountId(int customerId, int accountId);
}
