package org.jmp.bank.cloud.impl;

import org.jmp.bank.api.IBank;
import org.jmp.bank.dto.DataStorageMock;
import org.jmp.bank.dto.User;
import org.jmp.bank.dto.bankcard.BankCard;
import org.jmp.bank.dto.bankcard.BankCardType;
import org.jmp.bank.dto.bankcard.CreditBankCard;
import org.jmp.bank.dto.bankcard.DebitBankCard;

import java.util.Optional;

public class Bank implements IBank {
    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) {

        // It's done this way just to use constructor reference (task №23)
        var userOptional = Optional.of(user);
        var bankCard = switch (bankCardType) {
            case DEBIT ->  userOptional.map(DebitBankCard::new).get();
            case CREDIT -> userOptional.map(CreditBankCard::new).get();
        };

        DataStorageMock.getInstance().getBankCards().add(bankCard);
        return bankCard;
    }
}
