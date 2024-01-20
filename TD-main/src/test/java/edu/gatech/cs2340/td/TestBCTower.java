package edu.gatech.cs2340.td;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBCTower {
    @BeforeEach
    public void resetGameData() {
        GameData.resetInstance();
    }

    @Test
    public void testBCTowerResetsHealthBelowZero() {
        BCTower tower = new BCTower(4, 4);
        tower.getWall().setHealth(-5);
        tower.getWall().update();
        assertEquals(20, tower.getWall().getHealth());
    }

    @Test
    public void testBCTowerResetsHealthAtZero() {
        BCTower tower = new BCTower(4, 4);
        tower.getWall().setHealth(0);
        tower.getWall().update();
        assertEquals(20, tower.getWall().getHealth());
    }

    @Test
    public void testIsWallPlacedAtZero() {
        BCTower tower = new BCTower(4, 4);
        tower.getWall().setIsPlaced(true);
        tower.getWall().setHealth(0);
        tower.getWall().update();
        assertEquals(tower.getWall().getIsPlaced(), false);
    }

    @Test
    public void testIsWallPlacedBelowZero() {
        BCTower tower = new BCTower(4, 4);
        tower.getWall().setIsPlaced(true);
        tower.getWall().setHealth(-2);
        tower.getWall().update();
        assertEquals(tower.getWall().getIsPlaced(), false);
    }

}
