package com.bank.service.card;

import com.bank.bean.card.CardBean;
import com.bank.exception.InvalidPINException;
import com.bank.repository.card.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardValidateService {
    @Autowired
    private CardRepository cardRepository;

    public CardBean validateCard(int accountId, String pinCard, String pinCode) throws InvalidPINException {
        CardBean bean = cardRepository.getCardBean(accountId, pinCard, pinCode);
        if (bean == null) {
            throw new InvalidPINException("Invalid pin information");
        }
        return bean;
    }
}
