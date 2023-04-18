package org.jgmp.concurrency.taskfive.runnables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgmp.concurrency.taskfive.Main;
import org.jgmp.concurrency.taskfive.model.Currency;

import java.util.Random;
import java.util.Set;

import static org.jgmp.concurrency.taskfive.data.ExchangeRatesData.rates;

public class RateUpdater implements Runnable {

    private static final Logger logger = LogManager.getLogger(RateUpdater.class);

    private final Random random = new Random();

    @Override
    public void run() {
        while (Main.RUN) {
            StringBuffer ratesString = new StringBuffer();
            ratesString.append("Rates updated:\n");

            rates.forEach((key, eRates) -> {
                Set<Currency> currencies = eRates.getExchangeRates().keySet();
                currencies.forEach(c -> {
                    Double oldRate = eRates.getExchangeRate(c);
                    double newRate;
                    double randomAmount = randomAmount();
                    if (randomPlusOrMinus() > 0) {
                        newRate = oldRate - randomAmount;
                        if (newRate < 0) {
                            newRate = oldRate;
                        }
                        String str = eRates.getCurrency().getShortName() + "->" + c.getShortName() + ": " + newRate + " -" + randomAmount + "\n";
                        ratesString.append(str);
                    } else {
                        newRate = oldRate + randomAmount;
                        String str = eRates.getCurrency().getShortName() + "->" + c.getShortName() + ": " + newRate + " +" + randomAmount + "\n";
                        ratesString.append(str);
                    }
                    eRates.updateExchangeRate(c, newRate);
                });
            });

            System.out.println(ratesString);
            logger.info(ratesString);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private int randomPlusOrMinus() {
        return random.nextInt(2);
    }

    private double randomAmount() {
        return Math.random() / 10;
    }
}