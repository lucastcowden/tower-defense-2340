package edu.gatech.cs2340.td;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WinController {
    @FXML
    private Label winText;

    @FXML
    private Button restartGame;

    @FXML
    private Button quitGame;

    @FXML
    private Pane endPane;

    @FXML
    private Text towerStat;

    @FXML
    private Text timeStat;

    @FXML
    private Text healthStat;

    public void initialize() {
        towerStat.textProperty().bind(new SimpleStringProperty("Towers Placed: ")
                .concat(Stats.getNumTowers()));
        timeStat.textProperty().bind(new SimpleStringProperty("Final Time: ")
                .concat(Stats.getTotalTime()).concat(" seconds"));
        healthStat.textProperty().bind(new SimpleStringProperty("Final Monument Health: ")
                .concat(Stats.getFinalHealth()));
    }


    @FXML
    protected void onRestartGameClick() {
        GameData.getInstance().setCurrentScene("Welcomescreen");
    }

    @FXML
    protected void onQuitGameClicked() {
        Stage stage = (Stage) endPane.getScene().getWindow();
        stage.close();
    }
}
