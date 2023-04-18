package org.jgmp.concurrency.taskfive.runnables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgmp.concurrency.taskfive.Main;
import org.jgmp.concurrency.taskfive.exception.BaseExchangeException;
import org.jgmp.concurrency.taskfive.model.Currency;
import org.jgmp.concurrency.taskfive.model.OperationInfo;
import org.jgmp.concurrency.taskfive.model.UserAccount;
import org.jgmp.concurrency.taskfive.service.ExchangeService;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class CurrencyExchanger implements Runnable {
    private static final Logger logger = LogManager.getLogger(CurrencyExchanger.class);

    private static ReentrantLock CONSOLE_PRINT_LOCK;

    private final Random random = new Random();
    private final UserAccount account;

    public CurrencyExchanger(UserAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        ExchangeService service = ExchangeService.getInstance();

        while (Main.RUN) {
            List<Currency> currencies = new ArrayList<>(account.getUserAmounts().keySet());
            Currency from = currencies.get(random.nextInt(currencies.size()));
            Currency to;
            do {
                to = currencies.get(random.nextInt(currencies.size()));
            }while (to.equals(from));

            Double amount = random.nextInt(100) + 0.;
            try {
                OperationInfo info = service.exchangeCurrency(account.getFullName(), from, to, amount);
                System.out.println(info);
                logger.info(info);
            } catch (BaseExchangeException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
