package com.gemini.mixer.strategy;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * An interface to abstract multiple mixing algorithms
 *
 * @author Erick Ho
 * @version 1.0
 * @since 1.0
 */
public interface MixingStrategy {

    /**
     * Creates a hashmap of distribution based off mixing strategy
     *
     * @param amount            - the amount to mix
     * @param withdrawAddresses - addresses that will receive the mixed amount
     * @return - if successful, hashmap containing the address string and value it should receive based off mixing strategy else null
     */
    HashMap<String, BigDecimal> generateDistribution(BigDecimal amount, String[] withdrawAddresses);

}
