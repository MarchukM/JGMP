package org.jgmp.concurrency.taskfive;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgmp.concurrency.taskfive.runnables.CurrencyExchanger;
import org.jgmp.concurrency.taskfive.runnables.RateUpdater;
import org.jgmp.concurrency.taskfive.dataaccess.UserAccountDAO;
import org.jgmp.concurrency.taskfive.model.UserAccount;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.jgmp.concurrency.taskfive.data.Data.*;


public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static Boolean RUN = true;

    public static void main(String[] args) throws InterruptedException {

        logger.info("App started");

        ExecutorService executor = Executors.newFixedThreadPool(9);
        UserAccountDAO userAccountDAO = UserAccountDAO.getInstance();
        userAccountDAO.writeUserAccount(user1);
        userAccountDAO.writeUserAccount(user2);

        executor.submit(new RateUpdater());
        executor.submit(new CurrencyExchanger(user1));
        executor.submit(new CurrencyExchanger(user1));
        executor.submit(new CurrencyExchanger(user1));
        executor.submit(new CurrencyExchanger(user1));
        executor.submit(new CurrencyExchanger(user2));
        executor.submit(new CurrencyExchanger(user2));
        executor.submit(new CurrencyExchanger(user2));
        executor.submit(new CurrencyExchanger(user2));

        Thread.sleep(5000);
        RUN = false;

        executor.shutdown();

        if (executor.awaitTermination(10, TimeUnit.SECONDS)) {
            System.out.println("Data storage:\n");
            System.out.println("=========================\n");
            UserAccount ivanIvanov = userAccountDAO.readUserAccount(user2.getFullName());
            UserAccount vasiliyPupkin = userAccountDAO.readUserAccount(user1.getFullName());
            System.out.println(ivanIvanov.toString());
            System.out.println(vasiliyPupkin.toString());
        }

        logger.info("App is terminated.");
    }
}
