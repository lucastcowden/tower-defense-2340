package edu.gatech.cs2340.td;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestWaveStep {

    /*
     * Be sure that the wave does not flood enemies and waits the passed in number of steps before
     * saying that the enemy can be added.
     * Milestone 6 unit test
     */
    @Test
    public void testWaveTiming() {
        AbstractEnemy enemy = new VTEnemy();
        WaveStep ws = new WaveStep(enemy, 20);
        for (int i = 0; i < 20; ++i) {
            assertFalse(ws.readyToAdd());
        }
        assertTrue(ws.readyToAdd());
        assertEquals(enemy, ws.getEnemy());
    }
}
