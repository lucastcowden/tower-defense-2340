package edu.gatech.cs2340.td;

public class BamaEnemy extends AbstractEnemy {
    public BamaEnemy() {
        this.setImagePath("BamaEnemy.png");
        this.setMonumentDamage(10);
        this.setStepsBetweenMoves(1);
        this.setEnemyHealth(1);
        this.setInitialEnemyHealth(1);
    }
}
