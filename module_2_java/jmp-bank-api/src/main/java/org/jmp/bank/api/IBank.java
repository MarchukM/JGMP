package org.jmp.bank.api;

import org.jmp.bank.dto.User;
import org.jmp.bank.dto.bankcard.BankCard;
import org.jmp.bank.dto.bankcard.BankCardType;

public interface IBank {
    BankCard createBankCard(User user, BankCardType bankCardType);
}
