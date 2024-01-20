package edu.gatech.cs2340.td;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Map {
    public static final int WIDTH = 32;
    public static final int HEIGHT = 15;
    //starting path location
    private static final int START_X = 0;
    private static final int START_Y = 7;
    private static final List<WaveStep> WAVE_STEP_LIST = new ArrayList<>();

    private final Coord2D[][] coords = new Coord2D[WIDTH][HEIGHT];
    private final Tile[][] tileMap = new Tile[WIDTH][HEIGHT];
    private final java.util.Map<AbstractTower, Coord2D> towerMap = new HashMap<>();
    private final List<AbstractTower> towers = new ArrayList<>();
    private final java.util.Map<AbstractEnemy, Coord2D> enemyMap = new HashMap<>();

    private final List<AbstractEnemy> enemies = new ArrayList<>();
    private final Monument monument = new Monument();
    private FinalBoss boss;
    private boolean changed = true;
    private boolean bossEntered = false;

    public Map() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                coords[i][j] = new Coord2D(i, j);
            }
        }
    }

    public int getTowers() {
        return towers.size();
    }

    public static int getStartX() {
        return START_X;
    }
    public static int getStartY() {
        return START_Y;
    }
    public java.util.Map<AbstractEnemy, Coord2D> getEnemyMap() {
        return enemyMap;
    }
    public void initializeMap() {
        towerMap.clear();
        towers.clear();
        enemyMap.clear();
        enemies.clear();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (i <= 8 && j == 7) {
                    //Stretch 1
                    setTileAt(i, j, new Path(i, j));
                } else if (i == 8 && (j >= 3 && j <= 7)) {
                    //Stretch 2
                    setTileAt(i, j, new Path(i, j));
                } else if ((i >= 8 && i <= 12) && j == 2) {
                    //Stretch 3
                    setTileAt(i, j, new Path(i, j));
                } else if (i == 12 && (j >= 3 && j <= 5)) {
                    //Stretch 4
                    setTileAt(i, j, new Path(i, j));
                } else if ((i >= 13 && i <= 21) && j == 5) {
                    //Stretch 5
                    setTileAt(i, j, new Path(i, j));
                } else if (i == 21 && (j >= 2 && j <= 4)) {
                    //Stretch 6
                    setTileAt(i, j, new Path(i, j));
                } else if ((i >= 22 && i <= 27) && j == 2) {
                    //Stretch 7
                    setTileAt(i, j, new Path(i, j));
                } else if (i == 27 && (j >= 3 && j <= 9)) {
                    //Stretch 8
                    setTileAt(i, j, new Path(i, j));
                } else if ((i >= 3 && i <= 26) && j == 9) {
                    //Stretch 9
                    setTileAt(i, j, new Path(i, j));
                } else if (i == 3 && (j >= 10 && j <= 12)) {
                    //Stretch 10
                    setTileAt(i, j, new Path(i, j));
                } else if ((i >= 4 && i <= 19) && j == 12) {
                    //Stretch 11
                    setTileAt(i, j, new Path(i, j));
                } else if (i == 19 && j == 11) {
                    //Stretch 12
                    setTileAt(i, j, new Path(i, j));
                } else if ((i >= 20 && i <= 23) && j == 11) {
                    //Stretch 13
                    setTileAt(i, j, new Path(i, j));
                } else if (i == 23 && (j >= 12 && j <= 13)) {
                    //Stretch 14
                    setTileAt(i, j, new Path(i, j));
                } else if ((i >= 24 && i <= 26) && j == 13) {
                    //Stretch 15
                    setTileAt(i, j, new Path(i, j));
                } else if (i >= 27 && j >= 11) {
                    //Monument
                    setTileAt(i, j, new MonumentTile(i - 27, j - 11, monument));
                } else {
                    //Grass
                    setTileAt(i, j, new Grass());
                }
            }
        }
        WAVE_STEP_LIST.clear();
        // TODO add all the waves here
        for (int i = 0; i < 21; ++i) {
            WAVE_STEP_LIST.add(new WaveStep(new BamaEnemy(), 2));
        }
        for (int i = 0; i < 7; ++i) {
            WAVE_STEP_LIST.add(new WaveStep(new VTEnemy(), 4));
        }
        for (int i = 0; i < 7; ++i) {
            WAVE_STEP_LIST.add(new WaveStep(new UGAEnemy(), 6));
        }
        boss = new FinalBoss();
        WAVE_STEP_LIST.add(new WaveStep(boss, 300));
        for (AbstractEnemy enemy : enemies) {
            if (enemy instanceof FinalBoss) {
                bossEntered = true;
            }
        }
    }

    public Tile getTileAt(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return null;
        }
        return tileMap[x][y];
    }

    public void setTileAt(int x, int y, Tile tile) {
        tileMap[x][y] = tile;
        changed = true;
    }

    public Tile getTileInDirection(Coord2D coord, Direction direction) {
        int x = coord.x + direction.getDeltaX();
        if (x < 0 || x >= WIDTH) {
            return null;
        }
        int y = coord.y + direction.getDeltaY();
        if (y < 0 || y >= HEIGHT) {
            return null;
        }
        return tileMap[x][y];
    }

    private Coord2D getCoordInDirection(Coord2D coord, Direction direction) {
        return coords[coord.x + direction.getDeltaX()][coord.y + direction.getDeltaY()];
    }

    public boolean addTower(int x, int y, AbstractTower tower) {
        Tile oldTile = tileMap[x][y];
        if (!(oldTile instanceof Grass)) {
            return false;
        }
        setTileAt(x, y, tower);
        towers.add(tower);
        towerMap.put(tower, coords[x][y]);
        return true;
    }

    public boolean addEnemy(AbstractEnemy enemy) {
        Tile oldTile = tileMap[START_X][START_Y];
        if (!(oldTile instanceof Path)) {
            return false;
        }
        setTileAt(START_X, START_Y, enemy);
        enemies.add(enemy);
        enemyMap.put(enemy, coords[START_X][START_Y]);
        return true;
    }

    public void removeEnemy(AbstractEnemy enemy) {
        Coord2D currentCoord = enemyMap.get(enemy);
        setTileAt(currentCoord.x, currentCoord.y, new Path(currentCoord.x, currentCoord.y));
        enemies.remove(enemy);
        enemyMap.remove(enemy);
    }

    public Coord2D getCoords(int x, int y) {
        return coords[x][y];
    }

    private void moveEnemy(AbstractEnemy enemy) {
        if (!enemy.readyToMove()) {
            return;
        }
        Coord2D currentCoord = enemyMap.get(enemy);
        Direction nextDirection = enemy.getLastDirection();
        Coord2D newCoord;
        if (getTileInDirection(currentCoord, nextDirection) instanceof MonumentTile) {
            monument.addHealth(-enemy.getMonumentDamage());
            removeEnemy(enemy);
            // don't process rest of move
            return;
        } else if (getTileInDirection(currentCoord, nextDirection) instanceof Path) {
            newCoord = getCoordInDirection(currentCoord, nextDirection);
        } else if (getTileInDirection(currentCoord, nextDirection.turnLeft()) instanceof Path) {
            newCoord = getCoordInDirection(currentCoord, nextDirection.turnLeft());
            nextDirection = nextDirection.turnLeft();
        } else if (getTileInDirection(currentCoord, nextDirection.turnRight()) instanceof Path) {
            newCoord = getCoordInDirection(currentCoord, nextDirection.turnRight());
            nextDirection = nextDirection.turnRight();
        } else {
            //can't move yet, someone in front of us or end
            return;
        }
        enemy.move(nextDirection);
        setTileAt(currentCoord.x, currentCoord.y, new Path(currentCoord.x, currentCoord.y));
        setTileAt(newCoord.x, newCoord.y, enemy);
        enemyMap.put(enemy, newCoord);
    }

    public void doStep() {
        // call move on each enemy, use a cloned array list so you can remove items
        for (AbstractEnemy enemy : new ArrayList<>(enemies)) {
            moveEnemy(enemy);
        }
        // if more waves, check to add next enemy
        if (WAVE_STEP_LIST.size() > 0) {
            WaveStep waveStep = WAVE_STEP_LIST.get(0);
            if (waveStep.readyToAdd()) {
                AbstractEnemy enemy = waveStep.getEnemy();
                if (addEnemy(enemy)) {
                    // enemy successfully added to map, complete waveStep by removing
                    WAVE_STEP_LIST.remove(0);
                }
            }
        }
        // do tower attacks
        for (AbstractTower tower : towers) {
            Coord2D currentCoord = towerMap.get(tower);
            tower.attack(currentCoord.x, currentCoord.y);
        }
    }

    public Monument getMonument() {
        return monument;
    }

    private void drawLayer(GraphicsContext gc, int layer) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                gc.save();
                tileMap[i][j].draw(i, j, gc, layer);
                gc.restore();
            }
        }
    }

    public void draw(Canvas canvas) {
        // only redraw if the map changed
        if (!changed) {
            return;
        }
        //redraw entire map
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // draw base layer
        drawLayer(gc, 0);
        // draw attack layer
        drawLayer(gc, 2);
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public List<AbstractEnemy> getEnemies() {
        return enemies;
    }

    public boolean getBossEntered() {
        return bossEntered;
    }

    public FinalBoss getBoss() {
        return boss;
    }

    private static class Coord2D {
        private final int x;
        private final int y;

        private Coord2D(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Coord2D coord2D = (Coord2D) o;
            return x == coord2D.x && y == coord2D.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
