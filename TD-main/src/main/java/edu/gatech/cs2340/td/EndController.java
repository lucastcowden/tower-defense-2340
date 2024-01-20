package edu.gatech.cs2340.td;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class EndController {
    @FXML
    private Label endText;

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
                .concat(Stats.getTotalTime()));
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
