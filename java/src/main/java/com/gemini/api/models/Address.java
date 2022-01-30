package com.gemini.api.models;

public class Address {

    private Transaction[] transactions;

    private String name;

    private double balance;

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public Transaction[] getTransactions() {
        return transactions;
    }

    public void setName(String name) {
        this.name = name;
    }
}
