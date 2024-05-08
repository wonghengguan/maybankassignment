package com.maybank.wonghengguan.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maybank.wonghengguan.model.Transaction;
import com.maybank.wonghengguan.repo.TransactionRepo;
import com.maybank.wonghengguan.service.TransactionService;

import jakarta.transaction.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private final TransactionRepo transactionRepo;
    
    @Autowired
    public TransactionServiceImpl(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public Page<Transaction> getAllTransactions(Pageable pageable) {
        return transactionRepo.findAll(pageable);
    }

    public Page<Transaction> getAllTransactionsByDescription(String description, Pageable pageable) {
        return transactionRepo.findByDescription(description, pageable);
    }

    public Page<Transaction> getAllTransactionsByAccountNumbers(List<Long> accountNumbers, Pageable pageable) {
        return transactionRepo.findByAccountNumberIn(accountNumbers, pageable);
    }
    
    public Page<Transaction> getAllTransactionsByCustomerIds(List<Long> customerIds, Pageable pageable) {
        return transactionRepo.findByCustomerIdIn(customerIds, pageable);
    }    

    @Transactional
    public void batchUpdateDescription(List<Long> transactionIds, String newDescription) {
        for (Long transactionId : transactionIds) {
            Transaction transaction = transactionRepo.findById(transactionId).orElse(null);
            if (transaction != null) {
                transaction.setDescription(newDescription);
                transactionRepo.save(transaction);
            }
        }
    }
}
