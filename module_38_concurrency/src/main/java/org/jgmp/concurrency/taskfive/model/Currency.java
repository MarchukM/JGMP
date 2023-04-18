package org.jgmp.concurrency.taskfive.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Currency implements Serializable {
    private final UUID id = UUID.randomUUID();
    private String name;
    private String shortName;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        if (!Objects.equals(id, currency.id)) return false;
        if (!Objects.equals(name, currency.name)) return false;
        return Objects.equals(shortName, currency.shortName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return shortName;
    }
}
