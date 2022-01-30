package com.gemini.api.clients;

import com.gemini.api.clients.exceptions.InsufficientFundsException;
import com.gemini.api.models.Address;
import com.gemini.api.models.Transaction;

import java.math.BigDecimal;

/**
 * @author Erick Ho
 * @version 1.0
 * @since 1.0
 */
public abstract class ApiClient {

    /**
     * Sends coins to another address in JobCoin's offchain network
     *
     * @param fromAddress the sender's address
     * @param toAddress   the receiver's address
     * @param amount      the amount to send in XX.XX format
     * @return true if successfuly sent, else false.
     * @throws InsufficientFundsException if the fromAddress does not have sufficient funds
     */
    public abstract boolean sendJobCoins(String fromAddress, String toAddress, BigDecimal amount) throws InsufficientFundsException;

    /**
     * Looks up a specific address in JobCoin's offchain network
     *
     * @param address string version of the address we'd like to look up
     * @return {@link com.gemini.api.models.Address} - the address if successful, else null
     */
    public abstract Address lookupAddress(String address);

    /**
     * Get all transactions in JobCoin's offchain network
     *
     * @return {@link com.gemini.api.models.Transaction} all of the transactions created in the offchain JobCoin network
     */
    public abstract Transaction[] getAllTransactions();

    /**
     * This method was not in the API docs, but was retrieved from network requests to determine endpoint.
     *
     * @param address the address for the faucet to deposit into
     * @return true if successfully withdraw from the wallet; else false
     */
    public abstract boolean withdrawFromFaucet(String address);

}
