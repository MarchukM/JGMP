package org.jmp.bank.dto.bankcard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jmp.bank.dto.User;

import java.util.concurrent.ThreadLocalRandom;

@Setter @Getter
@AllArgsConstructor
public abstract class  BankCard {
    private String number;
    private User user;

    public BankCard() {
    }

    public BankCard(User user) {
        this.number = String.format("%04d-%04d-%04d-%04d",
                ThreadLocalRandom.current().nextInt(10000),
                ThreadLocalRandom.current().nextInt(10000),
                ThreadLocalRandom.current().nextInt(10000),
                ThreadLocalRandom.current().nextInt(10000));
        this.user = user;
    }

    public abstract BankCardType getBankCardType();
}
