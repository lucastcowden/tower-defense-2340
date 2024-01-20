package edu.gatech.cs2340.td;

public enum DifficultyEnum {
    EASY(1.0, 200, 1000), MEDIUM(1.5, 150, 750), HARD(2.0, 100, 500);

    private Double costScalar;
    private int startingHealth;
    private int startingMoney;

    DifficultyEnum(Double costScalar, int startingHealth, int startingMoney) {
        this.costScalar = costScalar;
        this.startingHealth = startingHealth;
        this.startingMoney = startingMoney;
    }

    public Double getCostScalar() {
        return costScalar;
    }

    public int getStartingHealth() {
        return startingHealth;
    }

    public int getStartingMoney() {
        return startingMoney;
    }
}
