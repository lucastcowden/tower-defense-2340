package edu.gatech.cs2340.td;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.io.IOException;

public class ShopController {
    @FXML
    private HBox shopPanels;

    public void initialize() {
        for (AbstractTower tower : GameData.getInstance().getShop().getTowers()) {
            VBox purchasePanel = new VBox();
            purchasePanel.setPrefHeight(200);
            purchasePanel.setPrefWidth(200);
            String imagePath = AbstractTower.class.getResource(tower.getImage()).toString();
            ImageView imageView = new ImageView(imagePath);
            imageView.setFitHeight(185);
            imageView.setFitWidth(200);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(true);
            purchasePanel.getChildren().add(imageView);
            Label descriptionLabel = new Label(tower.getDescription());
            descriptionLabel.setPrefHeight(119);
            descriptionLabel.setPrefWidth(200);
            descriptionLabel.setWrapText(true);
            purchasePanel.getChildren().add(descriptionLabel);
            Button purchaseButton = new Button("Buy " + tower.getName() + " for "
                    + tower.getCost(GameData.getInstance().getDifficulty()));
            purchaseButton.setMnemonicParsing(false);
            purchaseButton.setPrefHeight(100);
            purchaseButton.setPrefWidth(200);
            purchaseButton.setWrapText(true);
            purchaseButton.setTextAlignment(TextAlignment.CENTER);
            final AbstractTower t = tower;
            purchaseButton.setOnAction((ActionEvent) -> {
                if (!GameData.getInstance().boughtTower(t)) {
                    alertMessage();
                } else {
                    GameData.getInstance().setCurrentScene("gamescreen");
                }
            });
            purchasePanel.getChildren().add(purchaseButton);
            shopPanels.getChildren().add(purchasePanel);
        }
    }

    @FXML
    protected void purchaseCanceled() throws IOException {
        GameData.getInstance().setCurrentScene("gamescreen");
    }

    private void alertMessage() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Oops!");
        alert.setContentText("You don't have enough money to buy this tower");
        alert.show();
    }
}