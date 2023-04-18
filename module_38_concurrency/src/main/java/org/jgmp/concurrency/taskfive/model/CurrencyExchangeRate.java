package org.jgmp.concurrency.taskfive.model;

import java.util.Map;

public class CurrencyExchangeRate {
    private Currency currency;
    private Map<Currency, Double> exchangeRates;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Map<Currency, Double> getExchangeRates() {
        return exchangeRates;
    }

    public void setExchangeRates(Map<Currency, Double> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    public Double getExchangeRate(Currency currency) {
        return exchangeRates.get(currency);
    }

    public void updateExchangeRate(Currency currency, Double newRate) {
        exchangeRates.put(currency, newRate);
    }
}
