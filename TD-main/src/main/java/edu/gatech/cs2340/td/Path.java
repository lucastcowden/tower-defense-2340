package edu.gatech.cs2340.td;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Path extends Tile {
    private static final Color ROAD_COLOR = Color.web("#AA7A61");
    private int x;
    private int y;

    public Path(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(int x, int y, GraphicsContext gc, int layer) {
        if (layer == 0) {
            gc.setFill(ROAD_COLOR);
            gc.fillRect(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
