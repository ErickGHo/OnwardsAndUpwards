package com.gemini.tests;

import com.gemini.api.clients.GeminiApiClient;
import com.gemini.api.clients.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


public class SendApiTest {

    private GeminiApiClient apiClient = new GeminiApiClient();

    @Test
    void testInsufficientFunds() {
        InsufficientFundsException insufficientFundsException = Assertions.assertThrows(InsufficientFundsException.class, () -> {
            apiClient.sendJobCoins("Bob", "Sarah", new BigDecimal(Integer.MAX_VALUE));
        });
        Assertions.assertInstanceOf(InsufficientFundsException.class, insufficientFundsException);
    }

    @Test
    void testSuccessfulSend() throws InsufficientFundsException {
        boolean success = apiClient.sendJobCoins("Bob", "Sarah", new BigDecimal("1.00"));
        Assertions.assertTrue(success);
    }
}
