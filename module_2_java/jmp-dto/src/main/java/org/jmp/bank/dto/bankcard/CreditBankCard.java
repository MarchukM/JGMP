package org.jmp.bank.dto.bankcard;

import org.jmp.bank.dto.User;

public class CreditBankCard extends BankCard {

    public CreditBankCard(User user) {
        super(user);
    }

    public CreditBankCard(String bankCardNumber, User user) {
        super(bankCardNumber, user);
    }

    @Override
    public BankCardType getBankCardType() {
        return BankCardType.CREDIT;
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
