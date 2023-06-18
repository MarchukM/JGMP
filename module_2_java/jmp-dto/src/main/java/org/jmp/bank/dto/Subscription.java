package org.jmp.bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class Subscription {
    private String bankCard;
    private LocalDate startDate;

    public Subscription(String bankCard) {
        this.bankCard = bankCard;
        this.startDate = LocalDate.now();
    }

    public Subscription(String bankCard, LocalDate startDate) {
        this.bankCard = bankCard;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "bankCard='" + bankCard + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}
