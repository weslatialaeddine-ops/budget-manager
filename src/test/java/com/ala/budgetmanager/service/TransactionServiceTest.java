package com.ala.budgetmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ala.budgetmanager.model.Transaction;
import com.ala.budgetmanager.model.TransactionCategory;
import com.ala.budgetmanager.model.TransactionType;

public class TransactionServiceTest {
    private TransactionService transactionService;

    @BeforeEach
    void setUp(){
        transactionService = new TransactionService();
    }

    @Test 
    void addTransaction_shouldAddTransaction(){
        Transaction transaction = new Transaction("Tets", TransactionCategory.FOOD, TransactionType.EXPENSE, LocalDate.now(), new BigDecimal("50"), "1");
        this.transactionService.addTransaction(transaction);
        List<Transaction> listOfTransactions = this.transactionService.getAllTransactions();
        assertEquals(transaction, listOfTransactions.get(0));

    }

   

       @Test
    void getTransactionsByCategory_shouldFilterByCategory(){
        Transaction transaction = new Transaction("Tets", TransactionCategory.FOOD, TransactionType.EXPENSE, LocalDate.now(), new BigDecimal("50"), "1");
        Transaction secondtransaction = new Transaction("Test2", TransactionCategory.HEALTH, TransactionType.EXPENSE, LocalDate.now(), new BigDecimal("150"), "2");
        this.transactionService.addTransaction(transaction);
        this.transactionService.addTransaction(secondtransaction);

        List<Transaction> filteredTransaction = this.transactionService.getTransactionsByCategory(TransactionCategory.FOOD);
        assertEquals(1, filteredTransaction.size());
        assertEquals(transaction, filteredTransaction.get(0));
    }
    
@Test 
void getTransactionByAccountId_shouldReturnlistOfAccountTransactions(){
    Transaction transaction = new Transaction("Tets", TransactionCategory.FOOD, TransactionType.EXPENSE, LocalDate.now(), new BigDecimal("50"), "1");
    Transaction secondtransaction = new Transaction("Test2", TransactionCategory.HEALTH, TransactionType.EXPENSE, LocalDate.now(), new BigDecimal("150"), "2");
    this.transactionService.addTransaction(transaction);
    this.transactionService.addTransaction(secondtransaction);

    List<Transaction> filteredTransactions = this.transactionService.getTransactionsByAccountId("2");
    
    assertEquals(1, filteredTransactions.size());
    assertEquals(secondtransaction, filteredTransactions.get(0));
}

 @Test 
    void totalTransactionsPerAccount_shouldComputeCorrectBalance(){
        Transaction transaction = new Transaction("Tets", TransactionCategory.FOOD, TransactionType.EXPENSE, LocalDate.now(), new BigDecimal("50"), "1");
        Transaction secondtransaction = new Transaction("Test2", TransactionCategory.SALARY, TransactionType.INCOME, LocalDate.now(), new BigDecimal("150"), "1");
        this.transactionService.addTransaction(transaction);
        this.transactionService.addTransaction(secondtransaction);

        BigDecimal total = this.transactionService.totalTransactionsPerAccount("1");
        assertEquals( new BigDecimal(100),total);

    }
}
