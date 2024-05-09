package com.wonghengguan.maybank.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.wonghengguan.maybank.dto.UpdateTransactionDto;
import com.wonghengguan.maybank.model.Transaction;
import com.wonghengguan.maybank.repo.TransactionRepo;
import com.wonghengguan.maybank.service.TransactionService;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private final TransactionRepo transactionRepo;

    @Autowired
    public TransactionServiceImpl(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Override
    public Page<Transaction> getAllTransactionsByCriteria(Map<String, Object> criteria, Pageable pageable) {
        Specification<Transaction> spec = this.getSpecification(criteria);
        return transactionRepo.findAll(spec, pageable);
    }

    @Override
    @Transactional
    public void batchUpdateDescription(List<UpdateTransactionDto> transactions) {
        for (UpdateTransactionDto dto : transactions) {
            Transaction transaction = transactionRepo.findById(dto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Transaction not found with id: " + dto.getId()));
            transaction.setDescription(dto.getDescription());
            transactionRepo.save(transaction);
        }
    }

    private Specification<Transaction> getSpecification(Map<String, Object> criteria) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (criteria.containsKey("description")) {
                String description = (String) criteria.get("description");
                Expression<String> descriptionExpression = criteriaBuilder.upper(root.get("description"));
                String descriptionUpper = description.toUpperCase();

                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(descriptionExpression, "%" + descriptionUpper + "%"));
            }
            if (criteria.containsKey("accountNumber")) {
                List<?> accountNumbers = (List<?>) criteria.get("accountNumber");
                List<Long> accountNumbersLong = accountNumbers.stream()
                        .map(o -> Long.parseLong(o.toString()))
                        .collect(Collectors.toList());
                predicate = criteriaBuilder.and(predicate, root.get("accountNumber").in(accountNumbersLong));
            }
            if (criteria.containsKey("customerId")) {
                List<?> customerIds = (List<?>) criteria.get("customerId");
                List<Long> customerIdsLong = customerIds.stream()
                        .map(o -> Long.parseLong(o.toString()))
                        .collect(Collectors.toList());
                predicate = criteriaBuilder.and(predicate, root.get("customerId").in(customerIdsLong));
            }
            return predicate;
        };
    }
}
