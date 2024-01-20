package edu.gatech.cs2340.td;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class MonumentTile extends Tile {
    private int x;
    private int y;
    private Image image = null;
    private Monument monument;

    public MonumentTile(int x, int y, Monument monument) {
        this.x = x;
        this.y = y;
        this.monument = monument;
    }

    @Override
    public void draw(int x, int y, GraphicsContext gc, int layer) {
        if (image == null) {
            String imagePath = MonumentTile.class.getResource("monument.png").toString();
            image = new Image(imagePath);
        }
        if (layer == 0) {
            gc.drawImage(image, this.x * WIDTH, this.y * HEIGHT, WIDTH, HEIGHT,
                    x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        }

        if (x == 27 && y == 14) {
            gc.setStroke(Color.BLACK);
            gc.strokeRect(27 * WIDTH + 5, 14 * HEIGHT + 20, 140, 5);
            gc.setFill(Color.GREEN);
            gc.fillRect(27 * WIDTH + 5, 14 * HEIGHT + 20, 140
                * ((double) monument.getHealth() / monument.getInitialHealth()), 5);
        }
    }
}
