package com.ala.budgetmanager.model;

import java.util.Objects;
import java.util.UUID;

public class Account {
    private String id;
    private String name;

    public Account(String name){
        if(name == null || name.isBlank()) throw new IllegalArgumentException("invalid name");
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
    @Override 
    public String toString(){
        return "name : " + this.name;
    }

    @Override 
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override 
    public boolean equals(Object o){
        if(o==this) return true;
        if(o==null || !(o instanceof Account)) return false;
        Account other = (Account) o;
        return(other.id.equals(this.id)) ;
    }
}
