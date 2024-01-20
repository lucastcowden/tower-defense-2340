package edu.gatech.cs2340.td;

public class UGAEnemy extends AbstractEnemy {
    public UGAEnemy() {
        this.setImagePath("UGAEnemy.png");
        this.setMonumentDamage(20);
        this.setStepsBetweenMoves(2);
        this.setEnemyHealth(1);
        this.setInitialEnemyHealth(1);
    }
}
