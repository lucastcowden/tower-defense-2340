package edu.gatech.cs2340.td;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMoneyTower {

    @BeforeEach
    public void resetGameData() {
        GameData.resetInstance();
    }

    /*
     * Check that money tower gives money to the player correctly upon placing the tower
     */
    @Test
    public void gainMoney() {
        MoneyTower moneyTower = new MoneyTower();
        GameData.getInstance().getMap().addTower(12, 6, moneyTower);
        GameData.getInstance().getPlayer().setMoney(500);
        for (int i = 0; i < 20; i++) {
            moneyTower.attack(12, 6);
            assertEquals(500, GameData.getInstance().getPlayer().getMoney());
        }
        moneyTower.attack(12, 6);
        assertEquals(505, GameData.getInstance().getPlayer().getMoney());
    }

    /*
     * Check that if multiple money towers are placed, money is given for each money tower,
     * not just one
     */

    @Test
    public void gainMoneyMultipleTowers() {
        MoneyTower moneyTower1 = new MoneyTower();
        MoneyTower moneyTower2 = new MoneyTower();

        GameData.getInstance().getMap().addTower(12, 6, moneyTower1);

        GameData.getInstance().getPlayer().setMoney(500);
        for (int i = 0; i < 20; i++) {
            moneyTower1.attack(12, 6);
            assertEquals(500, GameData.getInstance().getPlayer().getMoney());
        }
        moneyTower1.attack(12, 6);
        assertEquals(505, GameData.getInstance().getPlayer().getMoney());

        GameData.getInstance().getMap().addTower(13, 6, moneyTower2);

        for (int i = 0; i < 19; i++) {
            moneyTower1.attack(12, 6);
            moneyTower2.attack(13, 6);
            assertEquals(505, GameData.getInstance().getPlayer().getMoney());
        }
        moneyTower1.attack(12, 6);
        moneyTower2.attack(13, 6);
        assertEquals(510, GameData.getInstance().getPlayer().getMoney());

        moneyTower1.attack(12, 6);
        moneyTower2.attack(13, 6);
        assertEquals(515, GameData.getInstance().getPlayer().getMoney());

    }

    /*
     * Check that an enemy does not get damaged when money tower attacks
     *
     * Milestone 5 testcase
     */
    @Test
    public void testNotDamagingEnemy() {
        MoneyTower mTower = new MoneyTower();
        GameData.getInstance().getMap().addTower(12, 6, mTower);
        TestEnemy enemy = new TestEnemy();
        GameData.getInstance().getMap().setTileAt(12, 5, enemy);
        mTower.attack(12, 5);
        assertEquals(100, enemy.getHealth(), "Test enemy should not be hit");
    }


}
