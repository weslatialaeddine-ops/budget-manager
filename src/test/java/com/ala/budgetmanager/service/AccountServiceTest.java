package com.ala.budgetmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;


import com.ala.budgetmanager.model.Account;

public class AccountServiceTest {
    private AccountService accountService;

    @BeforeEach
    void setUp(){
        accountService = new AccountService();
    }

    @Test
    void createAccount_shouldAddAccountToService(){
        Account created = accountService.createAccount("Compte courant");
        assertEquals("Compte courant",created.getName());
    }

    @Test 
    void findById_shouldReturnAccountWhenExists(){
        Account created = accountService.createAccount("Compte courant");
        Optional<Account> result = accountService.findById(created.getId());
        assertTrue(result.isPresent());
        assertEquals("Compte courant",result.get().getName());
    }


@Test
void findById_shouldReturnEmpty_whenIdDoesNotExist() {
    Optional<Account> result = accountService.findById("id-qui-nexiste-pas");

    assertTrue(result.isEmpty());
}

    @Test
void listAllAccounts_shouldReturnAllCreatedAccounts() {
    accountService.createAccount("Compte courant");
    accountService.createAccount("Espèces");

    List<Account> accounts = accountService.listAllAccounts();

    assertEquals(2, accounts.size());
}

}
