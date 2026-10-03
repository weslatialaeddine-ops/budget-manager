package com.ala.budgetmanager.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ala.budgetmanager.model.Transaction;
import com.ala.budgetmanager.model.TransactionCategory;

public class CategoryReport implements Report {

   @Override
public Map<TransactionCategory, BigDecimal> generate(List<Transaction> transactions) {
    return transactions.stream()
        .collect(Collectors.groupingBy(
            Transaction::getCategory,
            Collectors.reducing(BigDecimal.ZERO, Transaction::getSignedAmount, BigDecimal::add)
        ));
}
    
}
