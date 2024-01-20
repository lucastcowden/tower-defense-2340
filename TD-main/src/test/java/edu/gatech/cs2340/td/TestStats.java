package edu.gatech.cs2340.td;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStats {

    @BeforeEach
    public void resetGameData() {
        GameData.resetInstance();
    }

    /*
     * Test to make sure stats increment correctly each step
     *
     * Milestone 6 Testcase.
     */
    @Test
    public void testHealthIncrement() {
        Stats.setFinalHealth(60);
        GameData.getInstance().getMap().getMonument().setHealth(55);
        GameData.getInstance().setStartCombat(true);
        GameData.getInstance().setOngoingPurchase(false);
        GameData.getInstance().doStep();
        assertEquals(55, Stats.getFinalHealth());
    }

    /*
     * Test to make sure that the time counter increments correctly, and only in the right interval
     *
     * Milestone 6 Testcase.
     */
    @Test
    public void testTimeIncrement() {
        Stats.setTotalTime(0);
        GameData.getInstance().getMap().getMonument().setHealth(55);
        GameData.getInstance().setStartCombat(true);
        GameData.getInstance().setOngoingPurchase(false);
        GameData.getInstance().doStep();
        GameData.getInstance().doStep();
        GameData.getInstance().doStep();
        assertEquals(0, Stats.getTotalTime());
        GameData.getInstance().doStep();
        assertEquals(1, Stats.getTotalTime());
    }

    /*
     * Test to make sure that the tower counter increments correctly.
     *
     * Milestone 6 Testcase.
     */
    @Test
    public void testTowerIncrement() {
        BioTower bioTower = new BioTower();
        GameData.getInstance().getMap().setTileAt(12, 6, new Grass());
        GameData.getInstance().getMap().addTower(12, 6, bioTower);
        MoneyTower moneyTower = new MoneyTower();
        GameData.getInstance().getMap().setTileAt(15, 2, new Grass());
        GameData.getInstance().getMap().addTower(15, 2, moneyTower);
        GameData.getInstance().doStep();
        assertEquals(2, Stats.getNumTowers());
    }

    /*
     * Test to make sure that the final health of the monument is set to 0 if it is negative.
     *
     * Milestone 6 Testcase.
     */
    @Test
    public void testFinalHealthBelowZero() {
        GameData.getInstance().setStartCombat(true);
        GameData.getInstance().setOngoingPurchase(false);
        GameData.getInstance().getMap().getMonument().setHealth(-10);
        GameData.getInstance().doStep();
        assertEquals(0, Stats.getFinalHealth());
    }
}
