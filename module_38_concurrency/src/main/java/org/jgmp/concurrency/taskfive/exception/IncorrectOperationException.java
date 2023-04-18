package org.jgmp.concurrency.taskfive.exception;

import org.jgmp.concurrency.taskfive.model.Currency;

public class IncorrectOperationException extends BaseExchangeException {

    public static final String MESSAGE = "Incorrect operation. Exchange from: %s, to: %s, amount: %f";

    public IncorrectOperationException(Currency from, Currency to, Double amount) {
        super(String.format(MESSAGE, from, to, amount));
    }
}
