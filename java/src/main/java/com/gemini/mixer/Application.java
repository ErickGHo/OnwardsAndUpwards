package com.gemini.mixer;

import com.gemini.api.clients.GeminiApiClient;
import com.gemini.mixer.strategy.EvenDistributionStrategy;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

/**
 * Entrypoint of JobCoin Mixer
 *
 * @author Erick Ho
 * @version 1.0
 * @since 1.0
 */
public class Application {


    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        GeminiApiClient apiClient = new GeminiApiClient();
        Mixer mixer = new Mixer(apiClient);


        String depositorAddress = null;
        BigDecimal amountToMix = null;
        String[] withdrawAddresses = null;
        String randomDepositAddress = mixer.getRandomDepositAddress();

        boolean confirmedDetails = false;
        do {
            System.out.print("Enter your address e.g., Bob: ");
            depositorAddress = scanner.next();

            System.out.print("How much coins would you like to mix? (up to second decimal) e.g., 10.00: ");
            amountToMix = scanner.nextBigDecimal();

            System.out.print("Enter your withdraw addresses delimiter by comma e.g., BobWithdraw1,BobWithdraw2 ");
            withdrawAddresses = scanner.next().split(",");

            System.out.println();
            System.out.println("=======================================");
            System.out.printf("Your address is identified as %s \n", depositorAddress);
            System.out.printf("Your random deposit address is identified as %s \n", randomDepositAddress);
            System.out.printf("Your withdraw addresses is identified as %s \n", Arrays.toString(withdrawAddresses));
            System.out.printf("Your amount to mix is identified as %s \n", amountToMix.toPlainString());
            System.out.println("=======================================");
            System.out.println();

            System.out.print("Does this sound correct, type yes or no: ");
            confirmedDetails = scanner.next().equalsIgnoreCase("yes");

            System.out.println();
        } while (!confirmedDetails);


        mixer.mixUserCoins(depositorAddress, randomDepositAddress, amountToMix, EvenDistributionStrategy.getInstance(), withdrawAddresses);

    }
}
