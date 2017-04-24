package com.bank.service.card;

import com.bank.bean.card.CardBean;
import com.bank.command.card.CardAddCommand;
import com.bank.repository.account.AccountRepository;
import com.bank.repository.card.CardRepository;
import com.bank.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardCreateService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void addCard(CardAddCommand command){
        CardBean bean = new CardBean();
        bean.setValid(command.isValid());
        bean.setCardNumber(command.getCardNumber());
        bean.setCustomerBean(customerRepository.findOne(command.getCustomerId()));
        bean.setAccountBean(accountRepository.findOne(command.getAccountId()));

        cardRepository.save(bean);
    }
}
