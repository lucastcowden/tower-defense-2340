package edu.gatech.cs2340.td;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGameLoop {
    /*
     * Make sure that the step method gets called once after 1/4 a second.
     * Milestone 4 testcase
     */
    @Test
    void testSingleStep() {
        StepCounter stepCounter = new StepCounter();
        GameLoop gameLoop = new GameLoop(stepCounter);
        gameLoop.handle(0);
        assertEquals(0, stepCounter.count, "Initial value");
        gameLoop.handle(100000000); // less than a step
        assertEquals(0, stepCounter.count, "After 1/10 second");
        gameLoop.handle(250000000); // exactly one step
        assertEquals(1, stepCounter.count, "After 1/4 second");
    }
    /*
     * Make sure that if 1/2+ second happened between animation loops that the step handler
     * gets called twice.
     * Milestone 4 testcase
     */
    @Test
    void testDoubleStep() {
        StepCounter stepCounter = new StepCounter();
        GameLoop gameLoop = new GameLoop(stepCounter);
        gameLoop.handle(0);
        assertEquals(0, stepCounter.count, "Initial value");
        gameLoop.handle(250000000 * 2 + 1); // two steps plus some
        assertEquals(2, stepCounter.count, "After 1/4 second");
    }

    private static class StepCounter implements GameLoop.StepHandler {
        private int count = 0;

        @Override
        public void step() {
            ++count;
        }
    }
}
