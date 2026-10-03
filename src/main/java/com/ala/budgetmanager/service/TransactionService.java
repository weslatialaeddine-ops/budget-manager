package com.ala.budgetmanager.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.ala.budgetmanager.model.Transaction;
import com.ala.budgetmanager.model.TransactionCategory;

public class TransactionService {
    private List<Transaction> listOfTransactions;

    public TransactionService(){
        this.listOfTransactions = new ArrayList<>();
    }
    
    public void addTransaction (Transaction transaction){
        this.listOfTransactions.add(transaction);
    }

    public List<Transaction> getAllTransactions(){
        return List.copyOf(this.listOfTransactions);
    }

      public List<Transaction> getTransactionsByAccountId(String accountId){
       if(accountId == null || accountId.isBlank()) throw new IllegalArgumentException("invalid account id");
     
       return listOfTransactions.stream()
       .filter(transaction->transaction.getAccountId().equals(accountId))
       .collect(Collectors.toList());
   
    }

    public List<Transaction> getTransactionsByCategory(TransactionCategory category){
       if(category == null) throw new IllegalArgumentException("invalid category");

        return listOfTransactions.stream()
       .filter(transaction->transaction.getCategory().equals(category))
       .collect(Collectors.toList());
    }

    public BigDecimal totalTransactionsPerAccount(String accountId){

         List<Transaction> accountTransactions = this.getTransactionsByAccountId(accountId);
      
        return accountTransactions.stream()
        .map(Transaction::getSignedAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Transaction> sortByDate() {
    List<Transaction> copie = new ArrayList<>(this.listOfTransactions);
    Comparator<Transaction> parDate = (t1, t2) -> t1.getDateOfTransaction().compareTo(t2.getDateOfTransaction());
    copie.sort(parDate);
    return copie;
}

    public List<Transaction> sortByAmount(){
        List<Transaction> copie = new ArrayList<>(this.listOfTransactions);
        Comparator<Transaction> perAmount = (t1,t2)->t1.getSignedAmount().compareTo(t2.getSignedAmount());
        copie.sort(perAmount.reversed());
        return copie;
    }
}
