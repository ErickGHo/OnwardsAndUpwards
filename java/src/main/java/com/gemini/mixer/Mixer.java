package com.gemini.mixer;

import com.gemini.api.clients.ApiClient;

import com.gemini.mixer.strategy.MixingStrategy;
import com.gemini.general.RandomUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulate the main business logic of mixing coins and provide an easy to use API for developers to leverage to mix coins
 *
 * @author Erick Ho
 * @version 1.0
 * @since 1.0
 */
public class Mixer {

    private ApiClient client;

    private static final String[] DEPOSIT_ADDRESSES = {"JobDeposit1", "JobDeposit2", "JobDeposit3", "JobDeposit4", "JobDeposit5"};

    private static final String HOUSE_ADDRESS = "JobHouseDeposit";

    public Mixer(ApiClient client) {
        this.client = client;
    }


    /**
     * Helper method to return a random address from predefined deposit addresses
     *
     * @return a random deposit address string
     */
    public String getRandomDepositAddress() {
        return DEPOSIT_ADDRESSES[RandomUtil.generateBetween(0, DEPOSIT_ADDRESSES.length - 1)];
    }

    /**
     * Handles the logic end to end for mixing coins.
     *
     * @param depositorAddress  the sender's address
     * @param depositAddress    the house's random deposit address to verify the user's transaction
     * @param amount            the amount to mix
     * @param mixingStrategy    the mixing strategy to distribute the coins
     * @param withdrawAddresses the addresses to send the mixed coins to
     * @return true if coins was successfully mixed and sent, else early terminated and returns false
     */
    public boolean mixUserCoins(String depositorAddress, String depositAddress, BigDecimal amount, MixingStrategy mixingStrategy, String... withdrawAddresses) {

        HashMap<String, BigDecimal> addressesDistribution = mixingStrategy.generateDistribution(amount, withdrawAddresses);


        if (addressesDistribution == null) // distribution failed
            return false;

        if (!client.sendJobCoins(depositorAddress, depositAddress, amount))
            return false; // Depositor -> Deposit address

        if (!client.sendJobCoins(depositAddress, HOUSE_ADDRESS, amount))
            return false; // Deposit Address -> House Address

        for (Map.Entry<String, BigDecimal> addressDistribution : addressesDistribution.entrySet()) {
            if (!client.sendJobCoins(HOUSE_ADDRESS, addressDistribution.getKey(), addressDistribution.getValue()))
                return false;
            // House Address -> Withdraw Address
        }
        return true;
    }

    /**
     * This method automatically generates the deposit address for the depositor to deposit in
     * {@see #mixUserCoins}
     */
    public boolean mixUserCoins(String depositorAddress, BigDecimal amount, MixingStrategy mixingStrategy, String... withdrawAddresses) {
        return mixUserCoins(depositorAddress, getRandomDepositAddress(), amount, mixingStrategy, withdrawAddresses);
    }

}
