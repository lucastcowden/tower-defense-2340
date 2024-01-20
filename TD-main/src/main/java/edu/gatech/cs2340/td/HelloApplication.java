package edu.gatech.cs2340.td;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        SceneSwitcher sceneswitch = new SceneSwitcher(stage,
                GameData.getInstance().getCurrentScene());

        GameData.getInstance().setSceneSwitch(sceneswitch);

        sceneswitch.loadScene("Welcomescreen", "startGame.fxml");
        sceneswitch.loadScene("configscreen", "hello-view.fxml");
        sceneswitch.loadScene("gamescreen", "map-view.fxml");
        sceneswitch.loadScene("shopscreen", "shop-view.fxml");


        stage.setTitle("Hello!");

        GameData.getInstance().setCurrentScene("Welcomescreen");

        new GameLoop(() -> {
            GameData.getInstance().doStep();
            Platform.runLater(() -> GameData.getInstance().drawMap());
        }).start();

        stage.show();

    }
    public static void main(String[] args) {

        launch();
    }
}