package org.jgmp.concurrency.taskfive.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserAccount implements Serializable {
    private final UUID id = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private Map<Currency, BigDecimal> userAmounts = new HashMap<>();

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Map<Currency, BigDecimal> getUserAmounts() {
        return userAmounts;
    }

    public void setUserAmounts(Map<Currency, BigDecimal> userAmounts) {
        this.userAmounts = userAmounts;
    }

    public BigDecimal getAmount(Currency currency) {
        return userAmounts.get(currency);
    }

    public synchronized void setNewAmount(Currency currency, BigDecimal amount) {
        userAmounts.put(currency, amount);
    }

    public String getFullName() {
        return firstName + "_" + lastName;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "\n\tid=" + id +
                "\n\tfirstName='" + firstName + '\'' +
                "\n\tlastName='" + lastName + '\'' +
                "\n\tuserAmounts={" + userAmounts.entrySet().stream()
                .map(e -> "\n\t\t" + e.getKey().getShortName() + ": " + e.getValue().toString())
                .collect(Collectors.joining())
                + "\n}";
    }
}
