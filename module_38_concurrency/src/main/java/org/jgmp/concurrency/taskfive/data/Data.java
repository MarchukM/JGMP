package org.jgmp.concurrency.taskfive.data;

import org.jgmp.concurrency.taskfive.model.Currency;
import org.jgmp.concurrency.taskfive.model.CurrencyExchangeRate;
import org.jgmp.concurrency.taskfive.model.UserAccount;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Data {
    public static Currency usd;
    public static Currency rub;
    public static Currency eur;
    public static Currency tr;

    public static List<Currency> currencyList;

    public static UserAccount user1;
    public static UserAccount user2;

    public static Map<String, Lock> lockMap;

    static {
        usd = new Currency();
        usd.setName("American Dollar");
        usd.setShortName("USD");

        rub = new Currency();
        rub.setName("Russian Ruble");
        rub.setShortName("RUB");

        eur = new Currency();
        eur.setName("Euro");
        eur.setShortName("EUR");

        tr = new Currency();
        tr.setName("Turkish lira");
        tr.setShortName("TRY");


        user1 = new UserAccount();
        user1.setFirstName("Vasiliy");
        user1.setLastName("Pupkin");
        user1.setNewAmount(usd, BigDecimal.valueOf(1532.98));
        user1.setNewAmount(rub, BigDecimal.valueOf(102345.50));
        user1.setNewAmount(eur, BigDecimal.valueOf(83.12));
        user1.setNewAmount(tr, BigDecimal.valueOf(18000.00));

        user2 = new UserAccount();
        user2.setFirstName("Ivan");
        user2.setLastName("Ivanov");
        user2.setNewAmount(usd, BigDecimal.valueOf(12345.22));
        user2.setNewAmount(rub, BigDecimal.valueOf(3000012.50));
        user2.setNewAmount(eur, BigDecimal.valueOf(8300.99));
        user2.setNewAmount(tr, BigDecimal.valueOf(0.00));

        lockMap = new HashMap<>();
        lockMap.put(user1.getFullName(), new ReentrantLock());
        lockMap.put(user2.getFullName(), new ReentrantLock());

        currencyList = Arrays.asList(usd, rub, eur, tr);
    }
}
