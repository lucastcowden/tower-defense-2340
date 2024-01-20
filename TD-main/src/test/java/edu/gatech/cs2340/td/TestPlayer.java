package edu.gatech.cs2340.td;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestPlayer {
    /*
     * Test That the GameData.setPlayerName throws an exception when passing a null. Current UI
     * does not do this, but this is a requirement from a past milestone
     * Milestone 3 testcase
     */
    @Test
    void testSetPlayerNameZeroLength() {
        Exception thrown = assertThrows(Exception.class, () ->
                        GameData.getInstance().getPlayer().setPlayerName(""),
                "Expected setPlayerName to throw exception for empty name");
        assertEquals("Invalid name", thrown.getMessage());
    }
    /*
     * Test That the GameData.setPlayerName throws an exception when passing a null. Current UI
     * does not do this, but this is a requirement from a past milestone
     * Milestone 3 testcase
     */
    @Test
    void testSetPlayerNameNull() {
        Exception thrown = assertThrows(Exception.class, () ->
                        GameData.getInstance().getPlayer().setPlayerName(null),
                "Expected setPlayerName to throw exception for null name");
        assertEquals("Invalid name", thrown.getMessage());
    }
}
