package com.bank.service.transaction;

import com.bank.bean.card.CardBean;
import com.bank.bean.customer.AccountBean;
import com.bank.bean.transaction.TransactionBean;
import com.bank.command.transaction.TransactionAddCommand;
import com.bank.exception.NotFoundException;
import com.bank.repository.account.AccountRepository;
import com.bank.repository.card.CardRepository;
import com.bank.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;

    @Transactional
    public void doTransaction(TransactionAddCommand command) throws NotFoundException {
        TransactionBean bean = new TransactionBean();
        AccountBean source = accountRepository.findOne(command.getSourceId());
        AccountBean target = accountRepository.findOne(command.getTargetId());

        if(source==null||target==null){
            throw new NotFoundException();
        }

        source.setAmount(source.getAmount()-command.getAmount());
        target.setAmount(target.getAmount()+command.getAmount());

        if(command.getCardId()!=null) {
            CardBean card = cardRepository.findOne(command.getCardId());
            bean.setCard(card);
        }
        bean.setSourceBean(source);
        bean.setTargetBean(target);
        bean.setAmount(command.getAmount());

    }

}
