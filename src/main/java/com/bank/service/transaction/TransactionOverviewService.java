package com.bank.service.transaction;

import com.bank.exception.InvalidParamValueException;
import com.bank.projection.transaction.TransactionProjection;
import com.bank.repository.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionOverviewService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<TransactionProjection> getTransactionOverview(int customerId, int amount) throws InvalidParamValueException {
        try {
            Page<TransactionProjection> page = transactionRepository.getListOfXLatestTransactions(new PageRequest(0, amount), customerId);
            return page.getContent();
        }catch (IllegalArgumentException e){
            throw new InvalidParamValueException("Invalid nrOfTransactions");
        }
    }

}
