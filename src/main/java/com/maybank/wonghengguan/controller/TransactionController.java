package com.maybank.wonghengguan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maybank.wonghengguan.model.Transaction;
import com.maybank.wonghengguan.service.TransactionService;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins="http://localhost:4200")
public class TransactionController {
  @Autowired
  private TransactionService transactionService;

  @GetMapping("public/get-transactions")
  public Page < Transaction > retrieveTransactions(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page, size);
    return transactionService.getAllTransactions(pageable);
  }

  @GetMapping("public/get-transactions/description")
  public Page < Transaction > retrieveTransactionsByDescription(
    @RequestParam String description,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page, size);
    return transactionService.getAllTransactionsByDescription(description, pageable);
  }

  @GetMapping("public/get-transactions/accountNumbers")
  public Page < Transaction > retrieveTransactionsByAccountNumber(
    @RequestParam List<Long> accountNumber,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page, size);
    return transactionService.getAllTransactionsByAccountNumbers(accountNumber, pageable);
  }

  @GetMapping("public/get-transactions/customerIds")
  public Page < Transaction > retrieveTransactionsByCustomerId(
    @RequestParam List<Long> customerId,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page, size);
    return transactionService.getAllTransactionsByCustomerIds(customerId, pageable);
  }

  // @PutMapping("/update-description")
  // public void updateDescription(@RequestBody UpdateDescriptionRequest request) {
  //     transactionService.batchUpdateDescription(request.getTransactionIds(), request.getNewDescription());
  // }
}