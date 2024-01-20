package edu.gatech.cs2340.td;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMonument {

    /*
     * Be sure that adding health changes the health and that an event is triggered.
     * Milestone 6 unit test
     */
    @Test
    public void testAddHealthObservable() {
        Monument m = new Monument();
        m.setHealth(100);
        m.setInitialHealth(100);
        ObserveInteger oi = new ObserveInteger();
        m.healthProperty().addListener(oi);
        m.addHealth(-10);
        assertEquals(oi.newValue, 90);
        assertEquals(oi.oldValue, 100);
        assertEquals(oi.called, 1);
    }

    private static class ObserveInteger implements ChangeListener<Number> {
        private int called = 0;
        private Observable observed = null;
        private int oldValue = 0;
        private int newValue = 0;

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue,
                            Number newValue) {
            ++called;
            observed = observable;
            this.oldValue = oldValue.intValue();
            this.newValue = newValue.intValue();
        }
    }
}
