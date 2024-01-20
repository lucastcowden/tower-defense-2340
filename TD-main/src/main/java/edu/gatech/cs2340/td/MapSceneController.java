package edu.gatech.cs2340.td;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MapSceneController {
    @FXML
    private Text playerNameText;
    @FXML
    private Text healthText;
    @FXML
    private Text moneyText;
    @FXML
    private Button enterShop;
    @FXML
    private Button toggleCombat;
    @FXML
    private GridPane mapGrid = new GridPane();

    private final StringProperty toggleCombatLabel = new SimpleStringProperty("Start Combat");

    @FXML
    protected void goToShop() {
        GameData.getInstance().setCurrentScene("shopscreen");
    }

    @FXML
    protected void toggleCombat() {
        GameData.getInstance().toggleStartCombat();
    }

    public void initialize() {
        GameData.getInstance().getCombatStarted().addListener(
            (observableValue, oldValue, newValue)
                -> toggleCombatLabel.set(newValue ? "Pause Combat" : "Start Combat"));
        playerNameText.textProperty().bind(new SimpleStringProperty("Player: ")
                .concat(GameData.getInstance().getPlayer().playerName()));
        healthText.textProperty().bind(new SimpleStringProperty("Health: ")
                .concat(GameData.getInstance().getMap().getMonument().healthProperty().asString()));
        moneyText.textProperty().bind(new SimpleStringProperty("Money: ")
                .concat(GameData.getInstance().getPlayer().moneyProperty().asString()));
        toggleCombat.textProperty().bind(toggleCombatLabel);
        initializeGridPane(mapGrid);
    }


    @FXML
    private void initializeGridPane(GridPane grid) {
        grid.add(GameData.getInstance().getCanvas(), 0, 0, 32, 15);
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 15; j++) {
                Pane pane = new Pane();
                pane.setStyle("-fx-background-color: transparent;");
                final int x = i;
                final int y = j;
                pane.setOnMouseClicked(mouseEvent -> GameData.getInstance().tileClicked(x, y));
                grid.add(pane, i, j);
            }
        }
    }
}
