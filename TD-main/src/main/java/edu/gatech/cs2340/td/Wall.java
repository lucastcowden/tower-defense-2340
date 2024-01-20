package edu.gatech.cs2340.td;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Wall extends Tile {
    private Image currentImg;
    private String wallImg = "wall_img_full.png";
    private String wallImgCracked = "wall_img_cracked.png";
    private Map map = GameData.getInstance().getMap();
    private int x;
    private int y;
    private boolean isPlaced = false;
    private int maxHealth = 20;
    private int health = maxHealth;

    public void draw(int x, int y, GraphicsContext gc, int layer) {
        if (currentImg == null) {
            currentImg = new Image(AbstractEnemy.class.getResource(wallImg).toString());
        } else if (this.health > maxHealth / 2) {
            currentImg = new Image(AbstractEnemy.class.getResource(wallImg).toString());
        } else {
            currentImg = new Image(AbstractEnemy.class.getResource(wallImgCracked).toString());
        }
        if (layer == 0) {
            gc.drawImage(currentImg, x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        }

        gc.setStroke(Color.BLACK);
        gc.strokeRect(x * WIDTH + 5, y * HEIGHT + 20, 20, 5);
        gc.setFill(Color.GREEN);
        gc.fillRect(x * WIDTH + 5, y * HEIGHT + 20, (20)
                * ((double) this.health / maxHealth), 5);
    }
    public String getImage() {
        return "moneytower.png";
    }

    public void setWallNew() {
        this.currentImg = new Image(AbstractEnemy.class.getResource(wallImg).toString());
    }

    public void setWallCracked() {
        this.currentImg = new Image(AbstractEnemy.class.getResource(wallImgCracked).toString());
    }

    public boolean getIsPlaced() {
        return this.isPlaced;
    }

    public void setIsPlaced(boolean bool) {
        this.isPlaced = bool;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHealth(int h) {
        this.health = h;
    }

    public int getHealth() {
        return this.health;
    }

    public void setMaxHealth(int h) {
        this.maxHealth = h;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public void update() {
        Direction dir = Direction.NORTH;
        for (int i = 0; i < 4; i++) {
            if (this.map.getTileInDirection(this.map.getCoords(x, y), dir)
                    instanceof AbstractEnemy) {
                AbstractEnemy enemy = (AbstractEnemy) this.map.getTileInDirection(
                        this.map.getCoords(x, y), dir);
                this.health -= enemy.getMonumentDamage() / 5;
            }
            dir = dir.turnRight();
        }
        if (this.health <= 0) {
            map.setTileAt(this.x, this.y, new Path(this.x, this.y));
            this.health = maxHealth;
            this.isPlaced = false;
        }
    }

}
