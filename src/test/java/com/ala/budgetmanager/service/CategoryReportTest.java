package com.ala.budgetmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ala.budgetmanager.model.Account;
import com.ala.budgetmanager.model.Transaction;
import com.ala.budgetmanager.model.TransactionCategory;
import com.ala.budgetmanager.model.TransactionType;

public class CategoryReportTest {
    private CategoryReport categoryReport;
    private TransactionService transactionService;
    private AccountService accountService;

    @BeforeEach
    void setUp(){
        this.categoryReport = new CategoryReport();
        this.transactionService = new TransactionService();
        this.accountService = new AccountService();
    }

    @Test
    void generateCategroyReport_shouldReturnTotalBalancePerCategory(){
        Account account = accountService.createAccount("Compte test");

        Transaction firstTransaction = new Transaction("salary", TransactionCategory.SALARY, TransactionType.INCOME, LocalDate.now(), new BigDecimal(1200), account.getId());
        Transaction secondTransaction = new Transaction("food", TransactionCategory.FOOD, TransactionType.EXPENSE, LocalDate.now(), new BigDecimal(300), account.getId());
        this.transactionService.addTransaction(firstTransaction);
        this.transactionService.addTransaction(secondTransaction);

        Map<TransactionCategory,BigDecimal> report = this.categoryReport.generate(this.transactionService.getAllTransactions());

        assertEquals(new BigDecimal(1200), report.get(TransactionCategory.SALARY));
        assertEquals(new BigDecimal(-300), report.get(TransactionCategory.FOOD));
    }
}