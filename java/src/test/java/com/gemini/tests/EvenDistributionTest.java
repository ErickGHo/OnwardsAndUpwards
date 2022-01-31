package com.gemini.tests;


import com.gemini.mixer.Mixer;
import com.gemini.mixer.strategy.EvenDistributionStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class EvenDistributionTest {


    @Test
    public void testEvenSplit() {
        BigDecimal amountToSplit = new BigDecimal("10.77");
        BigDecimal expectedSplit = new BigDecimal("3.59");
        String[] parties = {"Bob1", "Bob2", "Bob3"};
        HashMap<String, BigDecimal> distribution = EvenDistributionStrategy.getInstance().generateDistribution(amountToSplit, parties);
        Assertions.assertNotNull(distribution);
        distribution.forEach((index, split) -> Assertions.assertEquals(split, expectedSplit));
    }

    @Test
    public void testUnevenSplit() {
        BigDecimal amountToSplit = new BigDecimal("10.78");
        List<BigDecimal> expectedSplitValues = Arrays.asList(new BigDecimal("3.59"), new BigDecimal("3.60"));
        String[] parties = {"Bob1", "Bob2", "Bob3"};
        HashMap<String, BigDecimal> distribution = EvenDistributionStrategy.getInstance().generateDistribution(amountToSplit, parties);
        Assertions.assertNotNull(distribution);

        BigDecimal totalSplit = new BigDecimal("0.00");
        for (BigDecimal split : distribution.values()) {
            totalSplit = totalSplit.add(split);
            Assertions.assertTrue(expectedSplitValues.contains(split));
        }

        Assertions.assertEquals(totalSplit, amountToSplit);
    }
}
