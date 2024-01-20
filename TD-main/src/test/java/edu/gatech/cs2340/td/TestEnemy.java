package edu.gatech.cs2340.td;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TestEnemy extends AbstractEnemy {
    public TestEnemy() {
        this.setMonumentDamage(10);
        this.setStepsBetweenMoves(1);
        this.setEnemyHealth(100);
        this.setInitialEnemyHealth(100);
    }
    // Test if monument gets valid damage from the final boss when the monument gets attacked by
    // the final boss
    @Test
    public void testFinalBossDestroysMonument() {
        FinalBoss f = new FinalBoss();
        Monument m = new Monument();
        m.setHealth(100);
        m.addHealth(-1 * f.getMonumentDamage());
        assertEquals(-900, m.getHealth());
    }
    // Test if final boss gets valid amount of damage from the bio tower every 20 ticks
    @Test
    public void testFinalBossGetDamage() {
        BioTower bioTower = new BioTower();
        GameData.getInstance().getMap().addTower(12, 6, bioTower);
        FinalBoss f = new FinalBoss();
        GameData.getInstance().getMap().setTileAt(12, 5, f);
        for (int i = 0; i < 20; ++i) {
            bioTower.attack(12, 6);
        }
        assertEquals(2, f.getEnemyHealth(), "Test enemy should be hit once");
    }
    public int getHealth() {
        return this.getEnemyHealth();
    }
}
