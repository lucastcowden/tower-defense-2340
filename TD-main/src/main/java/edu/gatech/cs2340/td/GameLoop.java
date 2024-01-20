package edu.gatech.cs2340.td;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    private static final long STEP = 250000000; //quarter of a second
    private long lastNow = -1;
    private final StepHandler step;

    public GameLoop(StepHandler step) {
        this.step = step;
    }

    @Override
    public void handle(long now) {
        if (lastNow == -1) {
            // first time through, do nothing but set the last tick
            lastNow = now;
            return;
        }
        while (now - lastNow >= STEP) {
            step.step();
            lastNow += STEP;
        }
    }

    @FunctionalInterface
    public interface StepHandler {
        void step();
    }
}
