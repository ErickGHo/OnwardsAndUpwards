package com.gemini.api.clients.exceptions;

public class InsufficientFundsException extends Exception {

    InsufficientFundsException(String message) {
        super(message);
    }
}