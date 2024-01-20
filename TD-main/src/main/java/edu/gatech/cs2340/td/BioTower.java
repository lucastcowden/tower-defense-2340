package edu.gatech.cs2340.td;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class BioTower extends AbstractTower {
    private int ticksBetweenAttacks = 20;
    private static final int DAMAGE_AMOUNT = 1;
    private String imgPath = "tower_bio.png";

    private int ticksBeforeAttack = ticksBetweenAttacks;

    @Override
    public String getImage() {
        return imgPath;
    }

    @Override
    public String getName() {
        return "Biology Tower";
    }

    @Override
    public String getDescription() {
        return "Periodically emits waves of poison gas. Deals a small amount of damage.";
    }

    @Override
    public int getBaseCost() {
        return 150;
    }

    @Override
    public AbstractTower clone() {
        return new BioTower();
    }

    public void attackNearby(int x, int y) {
        Tile tile = GameData.getInstance().getMap().getTileAt(x, y);
        if (tile != null && tile instanceof AbstractEnemy) {
            // only damage enemies
            AbstractEnemy enemy = (AbstractEnemy) tile;
            enemy.addDamage(DAMAGE_AMOUNT);
        }
    }

    public void upgrade() {
        ((AbstractTower) this).setTier(2);
        imgPath = "tower_bio_upgrade.png";
        ((AbstractTower) this).setImage(new Image(AbstractTower.class.getResource(
                getImage()).toString()));
        this.ticksBetweenAttacks = 15;
    }

    @Override
    public void attack(int x, int y) {
        super.attack(x, y);
        if (--ticksBeforeAttack > 5) {
            // nothing to do, return
            return;
        }
        GameData.getInstance().getMap().setChanged(true);
        if (ticksBeforeAttack < 0) {
            // end attack
            ticksBeforeAttack = ticksBetweenAttacks;
            // remove gas cloud
        } else if (ticksBeforeAttack == 3) {
            // attack inner ring
            for (int i = -1; i <= 1; ++i) {
                for (int j = -1; j <= 1; ++j) {
                    if (i != 0 || j != 0) {
                        attackNearby(x + i, y + j);
                    }
                }
            }
        } else if (ticksBeforeAttack == 1) {
            // attack outer ring
            for (int i = -1; i <= 1; ++i) {
                attackNearby(x + i, y - 2);
                attackNearby(x + i, y + 2);
                attackNearby(x - 2, y + i);
                attackNearby(x + 2, y + i);
            }
        }
    }

    @Override
    public void draw(int x, int y, GraphicsContext gc, int layer) {
        super.draw(x, y, gc, layer);
        if (layer == 2) {
            //drawing gas cloud
            if (ticksBeforeAttack < 0 || ticksBeforeAttack > 4) {
                // no gas cloud to draw
                return;
            }
            int distance = setDistance(ticksBeforeAttack);
            int length = setLength(ticksBeforeAttack);

            gc.setFill(Color.DARKSEAGREEN);
            gc.setGlobalAlpha(0.6);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(1);
            // north
            gc.fillRoundRect(x * WIDTH - length / 2 + WIDTH / 2, y * HEIGHT - distance,
                    length, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
            gc.strokeRoundRect(x * WIDTH - length / 2 + WIDTH / 2, y * HEIGHT - distance,
                    length, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
            // south
            gc.fillRoundRect(x * WIDTH - length / 2 + WIDTH / 2, y * HEIGHT + distance + HEIGHT / 2,
                    length, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
            gc.strokeRoundRect(x * WIDTH - length / 2 + WIDTH / 2,
                    y * HEIGHT + distance + HEIGHT / 2,
                    length, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
            // east
            gc.fillRoundRect(x * WIDTH - distance, y * HEIGHT - length / 2 + HEIGHT / 2,
                    WIDTH / 2, length, WIDTH / 2, HEIGHT / 2);
            gc.strokeRoundRect(x * WIDTH - distance, y * HEIGHT - length / 2 + HEIGHT / 2,
                    WIDTH / 2, length, WIDTH / 2, HEIGHT / 2);
            // west
            gc.fillRoundRect(x * WIDTH + distance + HEIGHT / 2, y * HEIGHT - length / 2 + WIDTH / 2,
                    WIDTH / 2, length, WIDTH / 2, HEIGHT / 2);
            gc.strokeRoundRect(x * WIDTH + distance + HEIGHT / 2,
                    y * HEIGHT - length / 2 + WIDTH / 2,
                    WIDTH / 2, length, WIDTH / 2, HEIGHT / 2);
        }
    }

    private int setDistance(int ticks) {
        return WIDTH * (9 - ticks * 2) / 4;
    }

    private int setLength(int ticks) {
        return WIDTH * (19 - ticks * 4) / 4;
    }
}
