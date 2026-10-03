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

    @Test 
    void sortByDate_shouldReturnTransactionsSortedByDateAscending(){
        Transaction firsTransaction = new Transaction("salary", TransactionCategory.SALARY, TransactionType.INCOME, LocalDate.of(2025, 12, 24), new BigDecimal(1500), "acount1");
        Transaction secondTransaction = new Transaction("food", TransactionCategory.FOOD, TransactionType.EXPENSE, LocalDate.of(2024, 2, 13), new BigDecimal(300), "acount1");
        Transaction thirdTransaction = new Transaction("health", TransactionCategory.HEALTH, TransactionType.EXPENSE, LocalDate.of(2026, 8, 1), new BigDecimal(1500), "acount1");

        this.transactionService.addTransaction(firsTransaction);
        this.transactionService.addTransaction(secondTransaction);
        this.transactionService.addTransaction(thirdTransaction);

        List<Transaction> lisTransactions = this.transactionService.sortByDate();
        assertEquals(secondTransaction, lisTransactions.get(0));

    }
     @Test
    void sortByAmount_shouldReturnTransactionsSortedByAmountDescending(){
          Transaction firsTransaction = new Transaction("salary", TransactionCategory.SALARY, TransactionType.INCOME, LocalDate.of(2025, 12, 24), new BigDecimal(1600), "acount1");
        Transaction secondTransaction = new Transaction("food", TransactionCategory.FOOD, TransactionType.EXPENSE, LocalDate.of(2024, 2, 13), new BigDecimal(300), "acount1");
        Transaction thirdTransaction = new Transaction("health", TransactionCategory.HEALTH, TransactionType.EXPENSE, LocalDate.of(2026, 8, 1), new BigDecimal(1500), "acount1");

        this.transactionService.addTransaction(firsTransaction);
        this.transactionService.addTransaction(secondTransaction);
        this.transactionService.addTransaction(thirdTransaction);

        List<Transaction> lisTransactions = this.transactionService.sortByAmount();
        assertEquals(firsTransaction, lisTransactions.get(0));
    }
}
