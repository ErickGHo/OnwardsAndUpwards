package com.gemini.general;

import java.security.SecureRandom;

public class RandomUtil {

    private static SecureRandom random;

    private static SecureRandom get() {
        if (random == null)
            random = new SecureRandom();
        return random;
    }

    /**
     * Helper function to generate a psuedo-random int between [min, max]
     *
     * @param min - inclusive minimum
     * @param max - inclusive maxmimum
     * @return a psuedo-random integer in betwee [min, max]
     */
    public static int generateBetween(int min, int max) {
        return get().nextInt((max - min) + 1) + min;
    }
}
