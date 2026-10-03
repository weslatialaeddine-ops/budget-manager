package com.ala.budgetmanager.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
       List<Transaction> filtredTransactions = new ArrayList<>();
       for(Transaction transaction : listOfTransactions){
        if(transaction.getAccountId().equals(accountId)){
            filtredTransactions.add(transaction);
        }
       }
       return filtredTransactions;
    }

    public List<Transaction> getTransactionsByCategory(TransactionCategory category){
       if(category == null) throw new IllegalArgumentException("invalid category");

       List<Transaction> filtredTransactions = new ArrayList<>();
       for(Transaction transaction : listOfTransactions){
        if(transaction.getCategory().equals(category)){
            filtredTransactions.add(transaction);
        }
       }
       return filtredTransactions;
    }

    public BigDecimal totalTransactionsPerAccount(String accountId){

        List<Transaction> accountTransactions = this.getTransactionsByAccountId(accountId);
        BigDecimal sum = BigDecimal.ZERO;
        for(Transaction tr: accountTransactions){
           sum = sum.add(tr.getSignedAmount());
        }
        return sum;
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
