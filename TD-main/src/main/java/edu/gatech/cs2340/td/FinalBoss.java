package edu.gatech.cs2340.td;

public class FinalBoss extends AbstractEnemy {
    public FinalBoss() {
        this.setImagePath("ClemsonEnemy.png");
        this.setMonumentDamage(1000);
        this.setStepsBetweenMoves(1);
        this.setEnemyHealth(3);
        this.setInitialEnemyHealth(3);
    }
}

