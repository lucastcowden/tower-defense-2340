package edu.gatech.cs2340.td;
import java.io.IOException;
import java.util.HashMap;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitcher {
    private HashMap<String, Scene> sceneMap = new HashMap<String, Scene>();
    private Stage stage;
    public SceneSwitcher(Stage stage, StringProperty currentScene) {
        this.stage = stage;
        currentScene.addListener((observable, oldValue, newValue) -> {
            goToScene(newValue);
        });
    }
    public void goToScene(String name) {
        Scene goToScene = sceneMap.get(name);
        stage.setScene(goToScene);
    }
    public void loadScene(String name, String sceneLocation)  {
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource(sceneLocation));
            Scene scene = new Scene(root, 960, 540);
            sceneMap.put(name,  scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Stage getStage() {
        return this.stage;
    }
    public HashMap<String, Scene> getSceneMap() {
        return this.sceneMap;
    }

}
