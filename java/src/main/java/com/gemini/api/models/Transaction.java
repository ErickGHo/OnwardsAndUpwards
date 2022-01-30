package com.gemini.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {


    private String fromAddress;

    private String toAddress;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "UTC")
    private Date timestamp;

    public BigDecimal getAmount() {
        return amount;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private BigDecimal amount;

    public Date getTimestamp() {
        return timestamp;
    }

    public String getToAddress() {
        return toAddress;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public boolean isFromFaucet() {
        return fromAddress == null;
    }
}
