package org.jgmp.concurrency.taskfive.model;

import java.time.LocalDateTime;

public class OperationInfo {
    private String accountFullName;
    private String from;
    private String to;
    private String exchangeRate;
    private String amount;
    private String previousFrom;
    private String currentFrom;
    private String previousTo;
    private String currentTo;
    private LocalDateTime dateTime;

    public String getAccountFullName() {
        return accountFullName;
    }

    public void setAccountFullName(String accountFullName) {
        this.accountFullName = accountFullName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPreviousFrom() {
        return previousFrom;
    }

    public void setPreviousFrom(String previousFrom) {
        this.previousFrom = previousFrom;
    }

    public String getCurrentFrom() {
        return currentFrom;
    }

    public void setCurrentFrom(String currentFrom) {
        this.currentFrom = currentFrom;
    }

    public String getPreviousTo() {
        return previousTo;
    }

    public void setPreviousTo(String previousTo) {
        this.previousTo = previousTo;
    }

    public String getCurrentTo() {
        return currentTo;
    }

    public void setCurrentTo(String currentTo) {
        this.currentTo = currentTo;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[" + Thread.currentThread().getName() + "] OperationInfo {" +
                "\n\taccountFullName: " + accountFullName +
                "\n\texchange: " + from + "->" + to +
                "\n\texchangeRate: '" + exchangeRate +
                "\n\tamount: " + amount +
                "\n\tbalance before: " + previousFrom + " " + from + ", " + previousTo + " " + to +
                "\n\tbalance now: " + currentFrom + " " + from + ", " + currentTo + " " + to +
                "\n\tdateTime: " + dateTime +
                "\n}";
    }
}
