package com.maybank.wonghengguan.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maybank.wonghengguan.model.Transaction;

import jakarta.transaction.Transactional;

@Service
public interface TransactionService {

    public Page<Transaction> getAllTransactions(Pageable pageable);

    public Page<Transaction> getAllTransactionsByDescription(String description, Pageable pageable);

    public Page<Transaction> getAllTransactionsByAccountNumbers(List<Long> accountNumbers, Pageable pageable);
    
    public Page<Transaction> getAllTransactionsByCustomerIds(List<Long> customerIds, Pageable pageable);

    @Transactional
    public void batchUpdateDescription(List<Long> transactionIds, String newDescription);
    
}
