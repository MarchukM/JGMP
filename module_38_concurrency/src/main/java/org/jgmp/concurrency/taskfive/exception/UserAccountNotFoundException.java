package org.jgmp.concurrency.taskfive.exception;

public class UserAccountNotFoundException extends BaseExchangeException {
    public UserAccountNotFoundException() {
        super("User Account not found.");
    }
}
