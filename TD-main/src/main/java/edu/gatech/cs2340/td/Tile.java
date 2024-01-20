package edu.gatech.cs2340.td;

import javafx.scene.canvas.GraphicsContext;

public abstract class Tile {
    private int x;
    private int y;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;

    public abstract void draw(int x, int y, GraphicsContext gc, int layer);

    public void setXVar(int x) {
        this.x = x;
    }

    public void setYVar(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
