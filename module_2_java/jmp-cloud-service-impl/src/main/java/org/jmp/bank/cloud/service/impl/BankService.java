package org.jmp.bank.cloud.service.impl;

import org.jmp.bank.cloud.service.IBankService;
import org.jmp.bank.dto.DataStorageMock;
import org.jmp.bank.dto.Subscription;
import org.jmp.bank.dto.User;
import org.jmp.bank.dto.bankcard.BankCard;

import java.util.List;
import java.util.function.Predicate;

public class BankService implements IBankService {
    @Override
    public void subscribe(BankCard bankCard) {
        DataStorageMock.getInstance()
                .getSubscriptions()
                .add(new Subscription(bankCard.getNumber()));
    }

    @Override
    public Subscription getSubscriptionByBankCardNumber(String bankCardNumber) {
        return DataStorageMock.getInstance().getSubscriptions()
                .stream()
                .filter(sub -> sub.getBankCard().equals(bankCardNumber))
                .findFirst()
                .orElseThrow(BankCardNotFoundException::new);
    }

    @Override
    public List<User> getAllUsers() {
        return DataStorageMock.getInstance().getUsers();
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition) {
        return DataStorageMock.getInstance().getSubscriptions().stream()
                .filter(condition)
                .toList();
    }
}
