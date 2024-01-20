package edu.gatech.cs2340.td;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class AbstractEnemy extends Tile {
    private Direction lastDirection = Direction.EAST;
    private int stepsBetweenMoves;
    private int currentSteps;
    private String imagePath;
    private Image image = null;
    private int monumentDamage;
    private int enemyHealth;
    private int initialEnemyHealth;

    public Direction getLastDirection() {
        return lastDirection;
    }

    public boolean readyToMove() {
        if (currentSteps >= stepsBetweenMoves) {
            return true;
        }
        ++currentSteps;
        return false;
    }

    public void move(Direction newDirection) {
        currentSteps = 0;
        lastDirection = newDirection;
    }

    public int getMonumentDamage() {
        //TODO temporary just damage monument
        return monumentDamage;
    }

    public void setMonumentDamage(int i) {
        this.monumentDamage = i;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setStepsBetweenMoves(int stepsBetweenMoves) {
        this.stepsBetweenMoves = stepsBetweenMoves;
    }

    public void setEnemyHealth(int enemyHealth) {
        this.enemyHealth = enemyHealth;
    }

    public void setInitialEnemyHealth(int initialEnemyHealth) {
        this.initialEnemyHealth = initialEnemyHealth;
    }

    @Override
    public void draw(int x, int y, GraphicsContext gc, int layer) {
        if (image == null) {
            image = new Image(AbstractEnemy.class.getResource(getImagePath()).toString());
        }
        if (layer == 0) {
            gc.drawImage(image, x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        }

        gc.setStroke(Color.BLACK);
        gc.strokeRect(x * WIDTH + 5, y * HEIGHT + 20, 20, 5);
        gc.setFill(Color.RED);
        gc.fillRect(x * WIDTH + 5, y * HEIGHT + 20, (20)
                * ((double) enemyHealth / initialEnemyHealth), 5);
    }

    public void addDamage(int damage) {
        enemyHealth -= damage;
        if (enemyHealth <= 0) {
            int newMoney = GameData.getInstance().getPlayer().getMoney() + 5;
            GameData.getInstance().getPlayer().setMoney(newMoney);
            GameData.getInstance().getMap().removeEnemy(this);
        }
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }

    public int getInitialEnemyHealth() {
        return initialEnemyHealth;
    }
}
