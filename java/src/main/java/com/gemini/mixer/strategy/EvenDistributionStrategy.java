package com.gemini.mixer.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

/**
 * The Even Distribution strategy focuses on evenly distributing the mixed coins across multiple addresses.
 * If the amount cannot be evenly distributed evenly, one address will receive the remainder to make it amongst all addresses.
 *
 * @author Erick Ho
 * @version 1.0
 * @since 1.0
 */
public class EvenDistributionStrategy implements MixingStrategy {

    private static EvenDistributionStrategy strategy;

    private BigDecimal ONE_HUNDRED = new BigDecimal("100");

    @Override
    public HashMap<String, BigDecimal> generateDistribution(BigDecimal amount, String[] withdrawAddresses) {


        BigDecimal splitBetweenLength = new BigDecimal(withdrawAddresses.length);

        BigDecimal amountInOnes = amount.multiply(ONE_HUNDRED);
        BigDecimal equalDivisionInOnes = amountInOnes.divide(splitBetweenLength, RoundingMode.FLOOR);

        BigDecimal equalDivision = equalDivisionInOnes.divide(ONE_HUNDRED).setScale(2, RoundingMode.FLOOR);
        BigDecimal remainder = amountInOnes.remainder(splitBetweenLength).divide(ONE_HUNDRED).setScale(2, RoundingMode.FLOOR);


        System.out.println(equalDivisionInOnes.toString());

        HashMap<String, BigDecimal> distribution = new HashMap<>();

        int lastAddressIndex = withdrawAddresses.length - 1;
        for (int i = 0; i < lastAddressIndex; i++) {
            distribution.put(withdrawAddresses[i], equalDivision);
        }

        String lastAddress = withdrawAddresses[lastAddressIndex];
        BigDecimal lastIndexValue = distribution.get(lastAddress);

        if (lastIndexValue != null) // edge case of one witdraw address.
            distribution.put(lastAddress, lastIndexValue.add(remainder).add(equalDivision));
        else
            distribution.put(lastAddress, remainder.add(equalDivision));

        return distribution;
    }


    /**
     * Singleton method for developers to leverage instead of recreating duplicate strategies
     *
     * @return instance of {@link EvenDistributionStrategy}
     */
    public static EvenDistributionStrategy getInstance() {
        if (strategy == null)
            strategy = new EvenDistributionStrategy();
        return strategy;
    }
}
