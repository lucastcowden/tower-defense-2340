package edu.gatech.cs2340.td;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class BCTower extends AbstractTower {
    private ArrayList<Path> adjacentPaths;
    private Wall wall;
    private Map map;
    //private int x;
    //private int y;
    private static final int TICKS_BETWEEN_BUILD = 20;
    private int ticksBeforeBuild = TICKS_BETWEEN_BUILD;
    private String imgPath = "tower_bc.png";

    public BCTower() {
        super();
    }

    public BCTower(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.map = GameData.getInstance().getMap();
        this.wall = new Wall();
    }

    public void setX(int x) {
        ((Tile) this).setXVar(x);
    }

    public void setY(int y) {
        ((Tile) this).setYVar(y);
    }

    @Override
    public String getImage() {
        return imgPath;
    }

    @Override
    public String getName() {
        return "Building Construction Tower";
    }

    @Override
    public String getDescription() {
        return "Constructs temporary defensive wall.";
    }

    @Override
    public int getBaseCost() {
        return 100;
    }

    public Wall getWall() {
        return wall;
    }

    public void upgrade() {
        ((AbstractTower) this).setTier(2);
        imgPath = "tower_bc_upgrade.png";
        ((AbstractTower) this).setImage(new Image(AbstractTower.class.getResource(
                getImage()).toString()));
        this.getWall().setMaxHealth(40);
    }

    @Override
    public AbstractTower clone() {
        return new BCTower();
    }

    public void buildWall() {
        adjacentPaths = new ArrayList<Path>();
        Direction dir = Direction.NORTH;
        for (int i = 0; i < 4; i++) {
            if (this.map.getTileInDirection(this.map.getCoords(((Tile) this).getX(),
                    ((Tile) this).getY()), dir) instanceof Path) {
                adjacentPaths.add((Path) this.map.getTileInDirection(
                        this.map.getCoords(((Tile) this).getX(),
                        ((Tile) this).getY()), dir));
            }
            dir = dir.turnRight();
        }
        if (adjacentPaths.size() > 0) {
            int randIndex = (int) (Math.random() * adjacentPaths.size());
            Path replaced = adjacentPaths.get(randIndex);
            this.wall.setWallNew();
            this.wall.setX(replaced.getX());
            this.wall.setY(replaced.getY());
            this.wall.setHealth(this.wall.getMaxHealth());
            map.setTileAt(replaced.getX(), replaced.getY(), this.wall);
            this.wall.setIsPlaced(true);
        }
    }

    @Override
    public void attack(int x, int y) {
        super.attack(x, y);
        if (this.wall.getIsPlaced()) {
            this.wall.update();
        } else {
            ticksBeforeBuild--;
            if (ticksBeforeBuild <= 0) {
                buildWall();
                ticksBeforeBuild = TICKS_BETWEEN_BUILD;
            }
        }
        GameData.getInstance().getMap().setChanged(true);
    }
}
