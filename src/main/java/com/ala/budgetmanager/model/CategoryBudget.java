package com.ala.budgetmanager.model;

import java.math.BigDecimal;
import java.util.Objects;

public class CategoryBudget {
    private TransactionCategory category;
    private BigDecimal limit;

    public CategoryBudget(TransactionCategory category,BigDecimal limit){
        if(category == null) throw new IllegalArgumentException("invalid category");
        if(limit == null || limit.compareTo(BigDecimal.ZERO)<=0) throw new IllegalArgumentException("invalid limit");
        this.category = category;
        this.limit = limit;
    }

    public TransactionCategory getCategory(){
        return category;
    }

    public BigDecimal getLimit(){
        return limit;
    }
    @Override 
    public int hashCode(){
        return Objects.hash(category) ;
    }

    @Override 
    public String toString(){
        return "category: " + category + ",limit: " + limit; 
    }

    @Override 
    public boolean equals(Object o){
        if(o == null || !(o instanceof CategoryBudget)) return false;
        if(o == this) return true;
        CategoryBudget budget = (CategoryBudget) o;
        return (budget.category.equals(this.category));
    }
}
