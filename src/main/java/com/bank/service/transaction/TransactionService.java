package com.bank.service.transaction;

import com.bank.bean.card.CardBean;
import com.bank.bean.customer.AccountBean;
import com.bank.bean.transaction.TransactionBean;
import com.bank.command.transaction.TransactionAddCommand;
import com.bank.exception.BadRequestException;
import com.bank.exception.NotFoundException;
import com.bank.repository.account.AccountRepository;
import com.bank.repository.card.CardRepository;
import com.bank.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TransactionService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;

    @Transactional
    public void doTransaction(TransactionAddCommand command) throws NotFoundException, BadRequestException {
        if(command.getAmount()<0){
            throw new BadRequestException();
        }
        TransactionBean bean = new TransactionBean();
        AccountBean source = accountRepository.findAccountBeanByAccountIdAndIsActiveTrue(command.getSourceId());
        AccountBean target = accountRepository.findAccountBeanByAccountIdAndIsActiveTrue(command.getTargetId());

        if(source==null||target==null){
            throw new NotFoundException();
        }

        source.setAmount(source.getAmount()-command.getAmount());
        target.setAmount(target.getAmount()+command.getAmount());

        if(command.getCardId()!=null) {
            CardBean card = cardRepository.findOne(command.getCardId());
            //check if card is not expired or is not valid
            if(!card.isValid()||card.getDateOfExpiration().getTime() < new Date().getTime()){
                throw new BadRequestException();
            }
            bean.setCard(card);
        }
        bean.setSourceBean(source);
        bean.setTargetBean(target);
        bean.setAmount(command.getAmount());

    }

}
