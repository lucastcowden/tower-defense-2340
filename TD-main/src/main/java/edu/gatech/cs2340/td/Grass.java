package edu.gatech.cs2340.td;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Grass extends Tile {

    @Override
    public void draw(int x, int y, GraphicsContext gc, int layer) {
        if (layer == 0) {
            gc.setFill(Color.LIMEGREEN);
            gc.fillRect(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
            gc.setStroke(Color.BLACK);
            gc.strokeRect(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        }
    }
}
