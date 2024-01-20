package edu.gatech.cs2340.td;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;

public class GameData {
    private static GameData instance = new GameData();

    private SceneSwitcher sceneSwitch;

    private DifficultyEnum difficulty = DifficultyEnum.EASY;

    private final Player player = new Player();

    private Map map = new Map();

    private Shop shop = new Shop();

    private Canvas canvas = new Canvas(960, 450);

    private AbstractTower currentTower = null;

    private boolean ongoingPurchase = false;

    private SimpleBooleanProperty combatStarted = new SimpleBooleanProperty(false);

    private SimpleStringProperty currentScene = new SimpleStringProperty("");

    private int count = 0;

    public void setSceneSwitch(SceneSwitcher s) {
        this.sceneSwitch = s;
    }

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

    public Player getPlayer() {
        return player;
    }

    public Shop getShop() {
        return shop;
    }

    public boolean isOngoingPurchase() {
        return ongoingPurchase;
    }

    public DifficultyEnum getDifficulty() {
        return difficulty;
    }

    //For testing purposes
    public void setOngoingPurchase(boolean b) {
        this.ongoingPurchase = b;
    }

    public void setDifficulty(DifficultyEnum difficulty) {
        this.difficulty = difficulty;
        map.getMonument().setHealth(difficulty.getStartingHealth());
        map.getMonument().setInitialHealth(difficulty.getStartingHealth());
        player.setMoney(difficulty.getStartingMoney());
    }

    public boolean boughtTower(AbstractTower tower) {
        int cost = tower.getCost(difficulty);
        if (player.getMoney() < cost) {
            return false;
        }
        currentTower = tower.clone();
        int newMoney = player.getMoney() - cost;
        player.setMoney(newMoney);
        ongoingPurchase = true;
        return true;
    }

    public void tileClicked(int x, int y) {
        if (isOngoingPurchase()) {
            Tile tile = map.getTileAt(x, y);
            if (tile instanceof Grass) {
                if (currentTower instanceof BCTower) {
                    System.out.println("Attempting to place BCTower...");
                    if (x == 0 && (y == 6 || y == 8)) {
                        System.out.println("Error: Cannot place BCTower next to start of path!");
                        return;
                    }
                    Direction dir = Direction.NORTH;
                    for (int i = 0; i < 4; i++) {
                        if (this.map.getTileInDirection(this.map.getCoords(x, y), dir)
                                instanceof Path) {
                            if (!map.addTower(x, y, new BCTower(x, y))) {
                                return;
                            }
                            System.out.println("Successfully placed BCTower!");
                            ongoingPurchase = false;
                            return;
                        }
                        dir = dir.turnRight();
                    }
                    System.out.println("Error: Must place BCTower next to path!");
                    return;
                } else if (!map.addTower(x, y, currentTower)) {
                    // failed to add, don't stop placing
                    return;
                }
                ongoingPurchase = false;
            }
        } else {
            Tile tile = map.getTileAt(x, y);
            System.out.println("No ongoing purchase");
            System.out.println(ongoingPurchase);
            if (tile instanceof AbstractTower) {
                System.out.println("Instance of tower");
                if (((AbstractTower) tile).getTier() == 1 && player.getMoney() >= 200) {
                    player.setMoney(player.getMoney() - 200);
                    ((AbstractTower) tile).upgrade();
                }
            }
        }
    }

    public Map getMap() {
        return map;
    }

    public void toggleStartCombat() {
        combatStarted.set(!combatStarted.get());
    }

    public void setStartCombat(Boolean b) {
        combatStarted.set(b);
    }

    public BooleanProperty getCombatStarted() {
        return combatStarted;
    }

    // Call each "step" of the game loop to move enemies, towers fire, etc.
    // When not "active", it just returns
    public void doStep() {
        if (combatStarted.get() && !ongoingPurchase) {
            map.doStep();
            if (map.getMonument().getHealth() > 0) {
                count++;
                Stats.setTotalTime(count / 4);
                Stats.setNumTowers(map.getTowers());
                Stats.setFinalHealth(map.getMonument().getHealth());
            }
            if (map.getMonument().getHealth() <= 0) {
                Stats.setFinalHealth(0);
                if (sceneSwitch != null) {
                    sceneSwitch.loadScene("endscreen", "end-view.fxml");
                }
                count = 0;
                combatStarted.set(false);
                this.setDifficulty(DifficultyEnum.EASY);
                setCurrentScene("endscreen");
            } else if (map.getEnemies().size() == 0 && map.getBoss().getEnemyHealth() == 0) {
                sceneSwitch.loadScene("winscreen", "win-view.fxml");
                count = 0;
                combatStarted.set(false);
                this.setDifficulty(DifficultyEnum.EASY);
                setCurrentScene("winscreen");
            }
        }
    }

    public StringProperty getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(String scene) {
        currentScene.set(scene);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void drawMap() {
        if (currentScene.get().equals("gamescreen")) {
            map.draw(canvas);
        }
    }
}
