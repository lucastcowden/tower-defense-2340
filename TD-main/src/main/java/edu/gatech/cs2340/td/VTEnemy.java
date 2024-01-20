package edu.gatech.cs2340.td;

public class VTEnemy extends AbstractEnemy {
    public VTEnemy() {
        this.setImagePath("VTEnemy.png");
        this.setMonumentDamage(10);
        this.setStepsBetweenMoves(2);
        this.setEnemyHealth(2);
        this.setInitialEnemyHealth(2);
    }
}
