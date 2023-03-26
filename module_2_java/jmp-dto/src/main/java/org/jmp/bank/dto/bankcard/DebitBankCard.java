package org.jmp.bank.dto.bankcard;

import org.jmp.bank.dto.User;

public class DebitBankCard extends BankCard {

    public DebitBankCard(User user) {
        super(user);
    }

    public DebitBankCard(String number, User user) {
        super(number, user);
    }

    @Override
    public BankCardType getBankCardType() {
        return BankCardType.DEBIT;
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "number='" + getNumber() + '\'' +
                ", user=" + getUser() + '\'' +
                ", type=" + getBankCardType().name() +
                '}';
    }
}
