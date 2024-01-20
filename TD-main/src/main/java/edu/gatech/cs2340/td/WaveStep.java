package edu.gatech.cs2340.td;

public class WaveStep {
    private AbstractEnemy enemy;
    private int stepsBefore;
    private int current = 0;

    public WaveStep(AbstractEnemy enemy, int stepsBefore) {
        this.enemy = enemy;
        this.stepsBefore = stepsBefore;
    }

    public boolean readyToAdd() {
        if (current < stepsBefore) {
            // not done waiting
            ++current;
            return false;
        }
        return true;
    }

    public AbstractEnemy getEnemy() {
        return enemy;
    }
}
