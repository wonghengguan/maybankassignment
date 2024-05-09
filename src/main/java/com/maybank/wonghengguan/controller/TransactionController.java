package com.maybank.wonghengguan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maybank.wonghengguan.dto.UpdateTransactionDto;
import com.maybank.wonghengguan.model.Transaction;
import com.maybank.wonghengguan.service.TransactionService;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins="http://localhost:4200")
public class TransactionController {
  @Autowired
  private TransactionService transactionService;

  @PostMapping("public/get-transactions")
    public ResponseEntity<Page<Transaction>> getAllTransactionsByCriteria(
            @RequestBody Map<String, Object> criteria,
            Pageable pageable
    ) {
        Page<Transaction> transactions = transactionService.getAllTransactionsByCriteria(criteria, pageable);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

  @PutMapping("private/batch-update-description")
  public void batchUpdateDescription(@RequestBody List<UpdateTransactionDto> transactions) {
      transactionService.batchUpdateDescription(transactions);
  }
}