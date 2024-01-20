package edu.gatech.cs2340.td;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static edu.gatech.cs2340.td.DifficultyEnum.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestGameData {

    @BeforeEach
    void resetInstance() {
        GameData.resetInstance();
    }

    /*
     * Test when the difficulty of gamedata is set to easy, the initial money should be 1000.
     * Milestone 3 testcase
     */
    @Test
    void testSetEasyMoneyValid() {
        GameData.getInstance().setDifficulty(EASY);

        assertEquals(1000, GameData.getInstance().getPlayer().getMoney());
    }

    /*
     * Test when the difficulty of gamedata is set to easy, the initial health should be 200.
     * Milestone 3 testcase
     */
    @Test
    void testSetEasyHealthValid() {
        GameData.getInstance().setDifficulty(EASY);

        assertEquals(200, GameData.getInstance().getMap().getMonument().getHealth());
    }

    /*
     * Test when the difficulty of gamedata is set to medium, the initial money should be 750.
     * Milestone 3 testcase
     */
    @Test
    void testSetMediumMoneyValid() {
        GameData.getInstance().setDifficulty(MEDIUM);

        assertEquals(750, GameData.getInstance().getPlayer().getMoney());
    }

    /*
     * Test when the difficulty of gamedata is set to medium, the initial health should be 150.
     * Milestone 3 testcase
     */
    @Test
    void testSetMediumHealthValid() {
        GameData.getInstance().setDifficulty(MEDIUM);

        assertEquals(150, GameData.getInstance().getMap().getMonument().getHealth());
    }

    /*
     * Test initial health when difficulty is set to hard, initial health should be 100
     */
    @Test
    void testSetHardHealthValid() {
        GameData.getInstance().setDifficulty(HARD);
        assertEquals(100, GameData.getInstance().getMap().getMonument().getHealth());
    }

    /*
     * Test initial money when difficulty is set to hard, initial money should be 500
     */
    @Test
    void testSetHardMoneyValid() {
        GameData.getInstance().setDifficulty(HARD);
        assertEquals(500, GameData.getInstance().getPlayer().getMoney());
    }

    /*
     * Tests to make sure purchase is not initiated for tower 1 if insufficient money
     */
    @Test
    void testTowerOneNotEnoughMoney() {
        GameData.getInstance().setDifficulty(EASY);
        GameData.getInstance().getPlayer().setMoney(0);
        GameData.getInstance().boughtTower(new BCTower(0, 0));
        assertFalse(GameData.getInstance().isOngoingPurchase());
    }

    /*
     * Tests to make sure purchase is not initiated for tower 2 if insufficient money
     */
    @Test
    void testTowerTwoNotEnoughMoney() {
        GameData.getInstance().setDifficulty(EASY);
        GameData.getInstance().getPlayer().setMoney(0);
        GameData.getInstance().boughtTower(new MoneyTower());
        assertFalse(GameData.getInstance().isOngoingPurchase());
    }

    /*
     * Tests to make sure the End Game Scene is switched to when monument health is exactly zero.
     * Milestone 4 Testcase
     */
    @Test
    void testEndScreenTriggersAtZero() {
        GameData.getInstance().getMap().getMonument().setHealth(0);
        GameData.getInstance().setStartCombat(true);
        GameData.getInstance().setOngoingPurchase(false);
        GameData.getInstance().doStep();
        assertEquals("endscreen", GameData.getInstance().getCurrentScene().get());
    }

    /*
    * Tests to make sure that when the monument health is exactly zero, combat is paused.
    * Milestone 4 test case
    */
    @Test
    void testCombatStartedFalseAtZeroHealth() {
        GameData.getInstance().getMap().getMonument().setHealth(0);
        GameData.getInstance().setStartCombat(true);
        GameData.getInstance().setOngoingPurchase(false);
        GameData.getInstance().doStep();
        assertEquals(false, GameData.getInstance().getCombatStarted().get());
    }

    /*
     * Tests to make sure the End Game Scene is switched to when monument health is less than zero.
     * Milestone 4 Testcase
     */
    @Test
    void testEndScreenTriggersLessThanZero() {
        GameData.getInstance().getMap().getMonument().setHealth(-10);
        GameData.getInstance().setStartCombat(true);
        GameData.getInstance().setOngoingPurchase(false);
        GameData.getInstance().doStep();
        assertEquals("endscreen", GameData.getInstance().getCurrentScene().get());
    }

    /*
    * Tests that combat is paused when moment health is less than zero
     */
    @Test
    void testCombatStartedFalseAtNegativeHealth() {
        GameData.getInstance().getMap().getMonument().setHealth(-10);
        GameData.getInstance().setStartCombat(true);
        GameData.getInstance().setOngoingPurchase(false);
        GameData.getInstance().doStep();
        assertEquals(false, GameData.getInstance().getCombatStarted().get());
    }

    /*
     * Tests to make sure that when the start combat button is clicked, the combat starts as we
     * expect.
     * Milestone 4 Testcase
     */
    @Test
    void testStartCombatButtonClick() {
        GameData.getInstance().getMap().getMonument().setHealth(10);
        GameData.getInstance().toggleStartCombat();
        assertEquals(true, GameData.getInstance().getCombatStarted().getValue());
    }


    /*
     * Tests to make sure that when the start combat button is clicked, the enemy starts at valid
     * location.
     * Milestone 4 Testcase
     */
    @Test
    void testStartCombatEnemyStarts() {
        GameData.getInstance().getMap().getMonument().setHealth(10);
        GameData.getInstance().setStartCombat(true);
        GameData.getInstance().getMap().initializeMap();
        UGAEnemy uga = new UGAEnemy();
        GameData.getInstance().getMap().addEnemy(uga);
        assertEquals(GameData.getInstance().getMap().getEnemyMap().get(uga),
                GameData.getInstance().getMap().getCoords(0, 7));
    }
    /*
     *Test start combat works fine when the health is positive
     * Milestone 4 Testcase
     */
    @Test
    void testStartCombatTruePositiveHealth() {
        GameData.getInstance().getMap().getMonument().setHealth(10);
        GameData.getInstance().setStartCombat(true);
        GameData.getInstance().setOngoingPurchase(false);
        GameData.getInstance().doStep();
        assertEquals(true, GameData.getInstance().getCombatStarted().get());
    }

    /*
     * Test that the monument health is set to 200 when combat starts on EASY difficulty.
     * Milestone 4 Testcase
     */
    @Test
    void testDefaultMonumentHealthEasy() {
        GameData.getInstance().setStartCombat(true);
        GameData.getInstance().setOngoingPurchase(false);
        GameData.getInstance().setDifficulty(EASY);
        GameData.getInstance().doStep();
        assertEquals(200, GameData.getInstance().getMap().getMonument().getHealth());
    }

    /*
     * Test that the monument health is set to 150 when combat starts on MEDIUM difficulty.
     * Milestone 4 Testcase
     */
    @Test
    void testDefaultMonumentHealthMedium() {
        GameData.getInstance().setStartCombat(true);
        GameData.getInstance().setOngoingPurchase(false);
        GameData.getInstance().setDifficulty(MEDIUM);
        GameData.getInstance().doStep();
        assertEquals(150, GameData.getInstance().getMap().getMonument().getHealth());
    }
}
