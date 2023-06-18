package org.jmp.bank.cloud.service;

import org.jmp.bank.dto.Subscription;
import org.jmp.bank.dto.User;
import org.jmp.bank.dto.bankcard.BankCard;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Predicate;

public interface IBankService {
    void subscribe(BankCard bankCard);

    Subscription getSubscriptionByBankCardNumber(String bankCardNumber);

    List<User> getAllUsers();

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition);

    default Double getAverageUsersAge() {
        return getAllUsers().stream()
                .mapToLong(user -> ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()))
                .average()
                .orElseThrow();
    }

    static boolean isPayableUser(User user) {
        return ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()) >= 18;
    }
}
