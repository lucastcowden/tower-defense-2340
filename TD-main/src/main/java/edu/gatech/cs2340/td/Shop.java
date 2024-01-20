package edu.gatech.cs2340.td;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shop {
    private final List<AbstractTower> towers = new ArrayList<>();

    public Shop() {
        towers.add(new BCTower());
        towers.add(new MoneyTower());
        towers.add(new BioTower());
    }

    public List<AbstractTower> getTowers() {
        return Collections.unmodifiableList(towers);
    }
}
