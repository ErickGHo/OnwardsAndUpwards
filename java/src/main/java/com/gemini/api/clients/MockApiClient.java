package com.gemini.api.clients;

import com.gemini.api.models.Address;
import com.gemini.api.models.Transaction;

import java.math.BigDecimal;

/**
 * A implementation of {@link ApiClient} that act as a stub for testing purposes.
 *
 * @author Erick Ho
 * @version 1.0
 * @since 1.0
 */
public class MockApiClient extends ApiClient {


    @Override
    public boolean sendJobCoins(String fromAddress, String toAddress, BigDecimal amount) {
        return false;
    }

    @Override
    public Address lookupAddress(String address) {
        return null;
    }

    @Override
    public Transaction[] getAllTransactions() {
        return new Transaction[0];
    }

    @Override
    public boolean withdrawFromFaucet(String address) {
        return false;
    }
}
