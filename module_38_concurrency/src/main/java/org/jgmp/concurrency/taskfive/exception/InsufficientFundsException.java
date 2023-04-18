package org.jgmp.concurrency.taskfive.exception;

import org.jgmp.concurrency.taskfive.model.Currency;

public class InsufficientFundsException extends BaseExchangeException {

    public static final String MESSAGE = "insufficient funds in the account to perform operation. Exchange from: %s, to: %s, amount: %f";

    public InsufficientFundsException(Currency from, Currency to, Double amount) {
        super(String.format(MESSAGE, from, to, amount));
    }
}
