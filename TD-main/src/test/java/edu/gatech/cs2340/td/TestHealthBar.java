package edu.gatech.cs2340.td;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHealthBar {
    @BeforeEach
    public void resetGameData() {
        GameData.resetInstance();
    }

    /*
     * Checks that the enemy health bar length is calculated correctly when an enemy loses health.
     *
     * Milestone 5 testcase
     */
    @Test
    public void testEnemyHealthBarCalculation() {
        TestEnemy enemy = new TestEnemy();
        GameData.getInstance().getMap().setTileAt(12, 5, enemy);
        enemy.addDamage(1);
        assertEquals(0.99, (double) enemy.getHealth() / enemy.getInitialEnemyHealth(),
                "Test enemy should have 99% health");
    }

    /*
     * Checks that the monument health bar length is calculated correctly when the monument loses
     * health.
     *
     * Milestone 5 testcase
     */
    @Test
    public void testMonumentHealthBarCalculation() {
        TestEnemy enemy = new TestEnemy();
        Monument monument = GameData.getInstance().getMap().getMonument();
        monument.setHealth(200);
        monument.setInitialHealth(200);
        monument.addHealth(-10);
        assertEquals(0.95, (double) monument.getHealth() / monument.getInitialHealth(),
                "Monument should have 95% health");
    }
}
