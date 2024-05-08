package com.maybank.wonghengguan.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.maybank.wonghengguan.model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Long>{
    Page<Transaction> findByDescription(String description, Pageable pageable);
    Page<Transaction> findByAccountNumberIn(List<Long> accountNumbers, Pageable pageable);
    Page<Transaction> findByCustomerIdIn(List<Long> customerIds, Pageable pageable);
    Optional<Transaction> findByAccountNumberAndCustomerId(Long accountNumber, Long customerId);
}
