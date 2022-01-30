package com.gemini.api.models;

import java.math.BigDecimal;

public class Address {

    private Transaction[] transactions;

    private String name;

    private BigDecimal balance;

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Transaction[] getTransactions() {
        return transactions;
    }

    public void setName(String name) {
        this.name = name;
    }
}
