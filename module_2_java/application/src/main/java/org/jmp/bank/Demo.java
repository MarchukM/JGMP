package org.jmp.bank;


import org.jmp.bank.api.IBank;
import org.jmp.bank.cloud.service.IBankService;
import org.jmp.bank.cloud.service.impl.BankCardNotFoundException;
import org.jmp.bank.dto.DataStorageMock;
import org.jmp.bank.dto.Subscription;
import org.jmp.bank.dto.User;
import org.jmp.bank.dto.bankcard.BankCardType;

import java.time.LocalDate;
import java.util.ServiceLoader;
import java.util.function.Predicate;

public class Demo {
    private static final String LINE = "==================================";
    public static final String FORMAT = "\n%s\n%s\n%s\n";

    public static void main(String[] args) {
        printMessage("Create bank card demonstration");
        var bank = getIBank();

        var user1 = new User("Test", "Testovich", LocalDate.of(1999, 12, 31));
        var user2 = new User("Maksim", "Marchuk", LocalDate.of(1989, 9, 30));

        var bankCard1 = bank.createBankCard(user1, BankCardType.CREDIT);
        var bankCard2 = bank.createBankCard(user2, BankCardType.DEBIT);
        System.out.println("New bank cards created:\n" +
                bankCard1 + "\n" +
                bankCard2 + "\n");

        System.out.println("All bank cards:" );
        DataStorageMock.getInstance().getBankCards().forEach(System.out::println);

        printMessage("Subscribe and get subscription demonstration");
        IBankService bankService = getIBankService();

        bankService.subscribe(bankCard1);
        bankService.subscribe(bankCard2);

        System.out.println("New subscriptions have been created for card numbers:\n" +
                bankCard1.getNumber() + "\n" +
                bankCard2.getNumber() + "\n");

        System.out.println("All subscriptions:" );
        DataStorageMock.getInstance().getSubscriptions().forEach(System.out::println);

        printMessage("Get average user age demonstration");
        System.out.println("Average age: " + bankService.getAverageUsersAge());

        printMessage("Is payable user demonstration");
        DataStorageMock.getInstance().getUsers().forEach(Demo::printPayableStatus);

        printMessage("Get subscription exception demonstration");
        try {
            bankService.getSubscriptionByBankCardNumber("0000-0000-0000-0000");
        } catch (BankCardNotFoundException e) {
            System.out.println("Exception caught: ");
            e.printStackTrace(System.out);
        }

        printMessage("Get subscription by condition demonstration");
        Predicate<Subscription> startsAfterTwoThousand = s -> s.getStartDate().isAfter(LocalDate.of(2020, 1, 1));
        var subscriptions = bankService.getAllSubscriptionsByCondition(startsAfterTwoThousand);

        System.out.println("Subscriptions which are start after 2020-01-01");
        subscriptions.forEach(System.out::println);

        System.out.println("\nAttempt to add to unmodifiable list");
        try {
            subscriptions.add(new Subscription("1010-1010-1010-1010"));
        } catch (RuntimeException e) {
            System.out.println("Exception caught: ");
            e.printStackTrace(System.out);
        }

        System.out.println("\nAttempt to add to unsupported null to unmodifiable list");
        try {
            subscriptions.add(null);
        } catch (RuntimeException e) {
            System.out.println("Exception caught: ");
            e.printStackTrace(System.out);
        }

    }

    private static IBank getIBank() {
        ServiceLoader<IBank> serviceLoader = ServiceLoader.load(IBank.class);
        System.out.println("Found " + serviceLoader.stream().count() + " IBank services configured.\n");

        return serviceLoader.findFirst()
                .orElseThrow(() -> new RuntimeException("No implementations of the IBank interface found"));
    }

    private static IBankService getIBankService() {
        ServiceLoader<IBankService> serviceLoader = ServiceLoader.load(IBankService.class);
        System.out.println("Found " + serviceLoader.stream().count() + " IBankService services configured.\n");

        return serviceLoader.findFirst()
                .orElseThrow(() -> new RuntimeException("No implementations of the IBankService interface found"));
    }

    private static void printMessage(String message) {
        System.out.printf(FORMAT, LINE, message, LINE);
    }

    private static void printPayableStatus(User user) {
        System.out.println(user);
        System.out.println("Is payable: " + IBankService.isPayableUser(user));
    }

}