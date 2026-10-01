package com.ala.budgetmanager.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ala.budgetmanager.model.Transaction;
import com.ala.budgetmanager.model.TransactionCategory;

public interface Report {
    Map<TransactionCategory,BigDecimal> generate(List<Transaction> transactions);
}
