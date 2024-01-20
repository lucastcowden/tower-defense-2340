package edu.gatech.cs2340.td;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class ConfigScreenController {
    //Config Screen controls
    @FXML
    private Label welcomeText;
    @FXML
    private TextField enterNameText;
    @FXML
    private RadioButton easyButton;
    @FXML
    private RadioButton mediumButton;
    @FXML
    private RadioButton hardButton;
    @FXML
    private ToggleGroup difficultyGroup;
    @FXML
    private Button selectButton;
    @FXML
    protected void onEasyButtonClick() {
        GameData.getInstance().setDifficulty(DifficultyEnum.EASY);
    }
    @FXML
    protected void onMediumButtonClick() {
        GameData.getInstance().setDifficulty(DifficultyEnum.MEDIUM);
    }
    @FXML
    protected void onHardButtonClick() {
        GameData.getInstance().setDifficulty(DifficultyEnum.HARD);
    }
    @FXML
    protected void onSelectButtonClick() throws IOException {
        try {
            GameData.getInstance().getPlayer().setPlayerName(enterNameText.getText());
            GameData.getInstance().getMap().initializeMap();
            GameData.getInstance().setCurrentScene("gamescreen");
        } catch (Exception ex) {
            // TODO display ex.getMessage() back to user?
            System.out.println(ex);
        }
    }
}
