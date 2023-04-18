package org.jgmp.concurrency.taskfive.data;

import org.jgmp.concurrency.taskfive.model.Currency;
import org.jgmp.concurrency.taskfive.model.CurrencyExchangeRate;

import java.util.HashMap;
import java.util.Map;

import static org.jgmp.concurrency.taskfive.data.Data.*;

public class ExchangeRatesData {

    public static CurrencyExchangeRate usdRates;
    public static CurrencyExchangeRate rubRates;
    public static CurrencyExchangeRate eurRates;
    public static CurrencyExchangeRate tryRates;

    public static Map<Currency, CurrencyExchangeRate> rates;

    static {
        usdRates = new CurrencyExchangeRate();
        usdRates.setCurrency(usd);
        usdRates.setExchangeRates(new HashMap<Currency, Double>() {{
            put(rub, 81.72d);
            put(eur, 0.92d);
            put(tr, 19.38d);
        }});


        rubRates = new CurrencyExchangeRate();
        rubRates.setCurrency(rub);
        rubRates.setExchangeRates(
                new HashMap<Currency, Double>() {{
                    put(usd, 0.012d);
                    put(eur, 0.011d);
                    put(tr, 0.24d);
                }}
        );
        eurRates = new CurrencyExchangeRate();
        eurRates.setCurrency(eur);
        eurRates.setExchangeRates(
                new HashMap<Currency, Double>() {{
                    put(rub, 89.18d);
                    put(usd, 1.09d);
                    put(tr, 21.16d);
                }}
        );
        tryRates = new CurrencyExchangeRate();
        tryRates.setCurrency(tr);
        tryRates.setExchangeRates(
                new HashMap<Currency, Double>() {{
                    put(usd, 0.052d);
                    put(rub, 4.22d);
                    put(eur, 0.047d);
                }}
        );

        rates = new HashMap<>();
        rates.put(usd, usdRates);
        rates.put(eur, eurRates);
        rates.put(rub, rubRates);
        rates.put(tr, tryRates);
    }
}
