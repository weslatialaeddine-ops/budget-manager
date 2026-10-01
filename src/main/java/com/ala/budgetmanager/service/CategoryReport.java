package com.ala.budgetmanager.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ala.budgetmanager.model.Transaction;
import com.ala.budgetmanager.model.TransactionCategory;

public class CategoryReport implements Report {

    @Override
    public Map<TransactionCategory, BigDecimal> generate(List<Transaction> transactions) {
        Map<TransactionCategory, BigDecimal> reportResult = new HashMap<>();
        for (Transaction transaction : transactions) {
           
                 reportResult.merge(transaction.getCategory(), transaction.getSignedAmount(), BigDecimal::add);
        
        }
        return reportResult;
    }
    
}
