package com.gemini.api.clients;

import com.gemini.api.clients.exceptions.InsufficientFundsException;
import com.gemini.api.clients.http.GeminiHttpClient;
import com.gemini.general.JsonUtil;
import com.gemini.api.models.Transaction;
import com.gemini.api.models.Address;
import com.gemini.api.models.dto.WithdrawFaucetBody;
import com.gemini.api.models.dto.TransferBody;

import java.math.BigDecimal;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An implementation of {@link ApiClient} that uses the provided API URL
 *
 * @author Erick Ho
 * @version 1.0
 * @since 1.0
 */
public class GeminiApiClient extends ApiClient {


    private Logger logger = Logger.getLogger(getClass().getName());

    private static String BASE_URL = "https://jobcoin.gemini.com/twisting-rarity";
    private static String BASE_API_URL = String.format("%s/api", BASE_URL);

    private static String TRANSACTIONS_API_URL = String.format("%s/transactions", BASE_API_URL);

    private static String ADDRESSES_API_URL = String.format("%s/addresses", BASE_API_URL);

    private static String FAUCET_API_URL = String.format("%s/create", BASE_URL);

    private GeminiHttpClient httpClient;

    public GeminiApiClient() {
        this.httpClient = new GeminiHttpClient();
    }

    public GeminiApiClient(GeminiHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public boolean sendJobCoins(String fromAddress, String toAddress, BigDecimal amount) throws InsufficientFundsException {
        logger.log(Level.INFO, String.format("Attempting to send %s from %s to %s", amount.toPlainString(), fromAddress, toAddress));
        HttpResponse<String> response = httpClient.sendSimplePost(TRANSACTIONS_API_URL, new TransferBody(fromAddress, toAddress, amount));
        if (response == null)
            return false;
        int responseCode = response.statusCode();
        if (responseCode == 422)
            throw new InsufficientFundsException(String.format("Transfer from %s to %s for %s resulted in insufficient funds", fromAddress, toAddress, amount));
        return responseCode == 200;
    }

    @Override
    public Address lookupAddress(String addressName) {
        Address address = JsonUtil.readValue(String.format("%s/%s", ADDRESSES_API_URL, addressName), Address.class);
        if (address == null)
            return null;
        address.setName(addressName);
        return address;
    }

    @Override
    public Transaction[] getAllTransactions() {
        return JsonUtil.readValue(TRANSACTIONS_API_URL, Transaction[].class);
    }

    @Override
    public boolean withdrawFromFaucet(String address) {
        HttpResponse<String> response = httpClient.sendSimplePost(FAUCET_API_URL, new WithdrawFaucetBody(address));
        if (response == null)
            return false;
        return response.statusCode() == 303;
    }
}
