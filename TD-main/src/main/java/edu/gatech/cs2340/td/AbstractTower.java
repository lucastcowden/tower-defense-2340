package edu.gatech.cs2340.td;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class AbstractTower extends Tile {
    private Image image = null;
    private int tier = 1;

    public int getCost(DifficultyEnum difficulty) {
        return (int) (getBaseCost() * difficulty.getCostScalar());
    }

    @Override
    public void draw(int x, int y, GraphicsContext gc, int layer) {
        if (image == null) {
            image = new Image(AbstractTower.class.getResource(getImage()).toString());
        }
        if (layer == 0) {
            gc.drawImage(image, x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        }
    }

    public void attack(int x, int y) {
        // base implementation does nothing
    }

    public void setImage(Image i) {
        this.image = i;
    }
    public abstract String getName();
    public abstract String getDescription();
    public abstract String getImage();
    public abstract int getBaseCost();
    public abstract AbstractTower clone();
    public abstract void upgrade();
    public int getTier() {
        return this.tier;
    }
    public void setTier(int t) {
        this.tier = t;
    }
}
