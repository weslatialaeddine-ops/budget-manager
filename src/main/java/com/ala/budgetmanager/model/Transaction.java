package com.ala.budgetmanager.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Transaction {
    private String id ;
    private String description;
    private TransactionCategory category;
    private TransactionType type;
    private LocalDate  dateOfTransaction;
    private BigDecimal amount;
    private String accountId;

    public Transaction(String description,TransactionCategory category,TransactionType type,LocalDate  dateOfTransaction,BigDecimal amount,String accountId){
        if( description == null || description.isBlank()) throw new IllegalArgumentException("invalid description");
        if( accountId == null || accountId.isBlank()) throw new IllegalArgumentException("account");
        if( category == null ) throw new IllegalArgumentException("invalid category");
        if( type == null ) throw new IllegalArgumentException("invalid type");
        if( dateOfTransaction == null ) throw new IllegalArgumentException("invalid date");
        if( amount ==null || amount.compareTo(BigDecimal.ZERO) <= 0 ) throw new IllegalArgumentException("invalid amount");
        
        this.description = description;
        this.category = category;
        this.type = type;
        this.dateOfTransaction = dateOfTransaction;
        this.amount = amount;
        this.accountId = accountId;
        this.id = UUID.randomUUID().toString();

    }

    public String getId(){
        return id;
    }
    public String getDescription(){
        return description;
    }
    public TransactionCategory getCategory(){
        return category;
    }

    public TransactionType getType(){
        return type;
    }

 public LocalDate getDateOfTransaction(){
        return dateOfTransaction;
    }
    public BigDecimal getAmount(){
        return amount;
    }

    public BigDecimal getSignedAmount(){
        return type==TransactionType.EXPENSE ? amount.negate():amount;
    }

     public String getAccountId(){
        return accountId;
    }

    @Override 
    public int hashCode(){
        return Objects.hash(id);
    }
    
    @Override 
    public String toString(){
        return "{description : " + description + "," +
               "amount : " + amount + "," +
               "date : " + dateOfTransaction + "," +
               "type : " + type + "," +
               "category : " + category + "}" ;
    }
    
    @Override 
    public boolean equals(Object o){
      if(o == null || !(o instanceof Transaction)) return false;
      if(o == this) return true;
      Transaction transaction = (Transaction) o;
      return(transaction.id.equals(this.id)) ;
    }

}
