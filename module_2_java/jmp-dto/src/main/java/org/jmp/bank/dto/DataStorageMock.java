package org.jmp.bank.dto;

import org.jmp.bank.dto.bankcard.BankCard;
import org.jmp.bank.dto.bankcard.CreditBankCard;
import org.jmp.bank.dto.bankcard.DebitBankCard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataStorageMock {

    private static final User[] INIT_USERS_ARRAY = {
            new User("John", "Doe", LocalDate.of(1980, 1, 5)),
            new User("Johanna", "Doe", LocalDate.of(1990, 2, 25)),
            new User("Vasiliy", "Pupkin", LocalDate.of(2022, 3, 15)),
            new User("Ivan", "Petrov", LocalDate.of(1980, 8, 10)),
            new User("Kate", "Perry", LocalDate.of(1989, 8, 20))
    };

    private static final String[] INIT_BANK_CARD_NUMBERS = {
            "1111-2222-3333-4444",
            "1234-1234-1234-1234",
            "2222-2222-2222-2222",
            "3333-3333-3333-3333",
            "4321-4321-4321-4321"
    };

    private static DataStorageMock dataStorage;


    private List<User> users = new ArrayList<>(Arrays.asList(INIT_USERS_ARRAY));

    private List<Subscription> subscriptions = new ArrayList<>(Arrays.asList(
            new Subscription(INIT_BANK_CARD_NUMBERS[0], LocalDate.of(2005, 10, 12)),
            new Subscription(INIT_BANK_CARD_NUMBERS[1], LocalDate.of(2007, 1, 1)),
            new Subscription(INIT_BANK_CARD_NUMBERS[2], LocalDate.of(2012, 12, 19)),
            new Subscription(INIT_BANK_CARD_NUMBERS[3], LocalDate.of(2022, 1, 15)),
            new Subscription(INIT_BANK_CARD_NUMBERS[4], LocalDate.of(2020, 3, 16))
    ));
    private List<BankCard> bankCards = new ArrayList<>(Arrays.asList(
            new CreditBankCard(INIT_BANK_CARD_NUMBERS[0], INIT_USERS_ARRAY[0]),
            new CreditBankCard(INIT_BANK_CARD_NUMBERS[1], INIT_USERS_ARRAY[1]),
            new DebitBankCard(INIT_BANK_CARD_NUMBERS[2], INIT_USERS_ARRAY[2]),
            new DebitBankCard(INIT_BANK_CARD_NUMBERS[3], INIT_USERS_ARRAY[3]),
            new CreditBankCard(INIT_BANK_CARD_NUMBERS[4], INIT_USERS_ARRAY[4])
    ));

    private DataStorageMock() {
    }

    public static DataStorageMock getInstance() {
        if (dataStorage == null) {
            dataStorage = new DataStorageMock();
        }
        return dataStorage;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public List<BankCard> getBankCards() {
        return bankCards;
    }
}
