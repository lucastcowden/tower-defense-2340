package edu.gatech.cs2340.td;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {
    private final StringProperty playerName = new SimpleStringProperty("Player 1");

    private final IntegerProperty money = new SimpleIntegerProperty();

    public final StringProperty playerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) throws RuntimeException {
        if (playerName == null || playerName.length() == 0 || playerName.trim().length() == 0) {
            throw new RuntimeException("Invalid name");
        }
        this.playerName.setValue(playerName);
    }

    public IntegerProperty moneyProperty() {
        return money;
    }

    public int getMoney() {
        return money.get();
    }

    public void setMoney(int newMoney) {
        moneyProperty().setValue(newMoney);
    }
}
