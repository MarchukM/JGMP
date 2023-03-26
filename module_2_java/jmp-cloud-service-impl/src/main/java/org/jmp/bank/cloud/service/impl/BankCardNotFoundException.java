package org.jmp.bank.cloud.service.impl;

public class BankCardNotFoundException extends RuntimeException {
    public BankCardNotFoundException() {
        super("Bank card not found");
    }
}
