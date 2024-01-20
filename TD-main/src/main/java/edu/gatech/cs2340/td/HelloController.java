package edu.gatech.cs2340.td;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class HelloController {

    //Welcome Screen controls
    @FXML
    private Button enterGame;
    @FXML
    private Button exitButton;
    @FXML
    private Label startWelcomeText;
    @FXML
    private Pane welcomePane;
    @FXML
    protected void enterGame() throws IOException {
        GameData.getInstance().setCurrentScene("configscreen");
    }

    @FXML
    protected void exitGameFromStart() throws IOException {
        Stage stage = (Stage) welcomePane.getScene().getWindow();
        stage.close();
    }

}