package com.bank.repository.card;

import com.bank.bean.card.CardBean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CardRepository extends CrudRepository<CardBean, Integer> {

    @Modifying
    @Query("update CardBean c set c.isActive = false where c.accountBean.accountId = ?1")
    void invalidatePinCards(int accountId);

}
