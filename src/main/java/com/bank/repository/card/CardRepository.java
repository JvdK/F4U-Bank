package com.bank.repository.card;

import com.bank.bean.card.CardBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<CardBean, Integer> {

}
