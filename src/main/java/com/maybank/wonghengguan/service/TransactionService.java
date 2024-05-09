package com.maybank.wonghengguan.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maybank.wonghengguan.dto.UpdateTransactionDto;
import com.maybank.wonghengguan.model.Transaction;

import jakarta.transaction.Transactional;

@Service
public interface TransactionService {

    Page<Transaction> getAllTransactionsByCriteria(Map<String, Object> criteria, Pageable pageable);
    
    @Transactional
    public void batchUpdateDescription(List<UpdateTransactionDto> transactions);
    
}
