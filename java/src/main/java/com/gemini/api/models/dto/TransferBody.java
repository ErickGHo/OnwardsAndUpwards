package com.gemini.api.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;

public class TransferBody {

    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private BigDecimal amount;

    private String fromAddress;
    private String toAddress;

    public TransferBody(String fromAddress, String toAddress, BigDecimal amount) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.amount = amount;
    }

    public String getToAddress() {
        return toAddress;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}