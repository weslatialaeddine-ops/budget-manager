package com.ala.budgetmanager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ala.budgetmanager.model.Account;

public class AccountService {
    private Map<String,Account> accounts;

    public AccountService(){
        this.accounts = new HashMap<>();
    }

    public Account createAccount(String name){
        Account account = new Account(name);
        accounts.put(account.getId(), account);
        return account ;
    }

    public Optional<Account> findById(String accountId){
        if(accountId == null || accountId.isBlank()) throw new IllegalArgumentException("invalid account id");
        Account account = accounts.get(accountId);
        return Optional.ofNullable(account);
    }

    public List<Account> listAllAccounts(){
        List<Account> listOfAccounts = new ArrayList<>(accounts.values());
      
        return listOfAccounts;
    }
}
