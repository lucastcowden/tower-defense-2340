package edu.gatech.cs2340.td;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBioTower {

    @BeforeEach
    public void resetGameData() {
        GameData.resetInstance();
    }

    /*
     * Check that an emeny only gets hits one per cycle (20).
     *
     * Milestone 5 testcase
     */
    @Test
    public void testDamagingEnemy() {
        BioTower bioTower = new BioTower();
        GameData.getInstance().getMap().addTower(12, 6, bioTower);
        TestEnemy enemy = new TestEnemy();
        GameData.getInstance().getMap().setTileAt(12, 5, enemy);
        for (int i = 0; i < 20; ++i) {
            bioTower.attack(12, 6);
        }
        assertEquals(99, enemy.getHealth(), "Test enemy should be hit once");
    }

    /*
     * Check that an emeny only gets hits one per cycle (20).
     *
     * Milestone 5 testcase
     */
    @Test
    public void testNotDamagingEnemy() {
        BioTower bioTower = new BioTower();
        GameData.getInstance().getMap().addTower(12, 6, bioTower);
        TestEnemy enemy = new TestEnemy();
        GameData.getInstance().getMap().setTileAt(12, 3, enemy);
        for (int i = 0; i < 20; ++i) {
            bioTower.attack(12, 6);
        }
        assertEquals(100, enemy.getHealth(), "Test enemy should be hit once");
    }
    /*
     * Check that an enemy gets valid damage when bioTower attacks nearby.
     *
     * Milestone 5 testcase
     */
    @Test
    public void testAttackNearbyWhenEnemyNearby() {
        BioTower bioTower = new BioTower();
        GameData.getInstance().getMap().addTower(12, 6, bioTower);
        TestEnemy enemy = new TestEnemy();
        GameData.getInstance().getMap().setTileAt(11, 5, enemy);
        bioTower.attackNearby(11, 5);
        assertEquals(99, enemy.getHealth(), "Test enemy should be hit once");

    }
}
