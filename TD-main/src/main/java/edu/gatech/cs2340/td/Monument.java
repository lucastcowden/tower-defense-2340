package edu.gatech.cs2340.td;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Monument {
    private final IntegerProperty health = new SimpleIntegerProperty();

    private int initialHealth;

    public IntegerProperty healthProperty() {
        return health;
    }

    public int getHealth() {
        return health.get();
    }

    public void setHealth(int health) {
        this.health.set(health);
    }

    public void addHealth(int add) {
        healthProperty().set(healthProperty().get() + add);
    }

    public int getInitialHealth() {
        return initialHealth;
    }

    public void setInitialHealth(int health) {
        initialHealth = health;
    }
}
