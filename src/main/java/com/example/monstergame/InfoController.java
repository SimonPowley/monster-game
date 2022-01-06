package com.example.monstergame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class InfoController {
    Player player;
    MainController mainController;
    Scene mainScene;
    @FXML
    Circle normalCircle;
    @FXML
    Circle fireCircle;
    @FXML
    Circle waterCircle;
    @FXML
    Circle earthCircle;
    @FXML
    Circle electricCircle;
    @FXML
    Circle natureCircle;
    @FXML
    Circle windCircle;
    @FXML
    Circle iceCircle;
    @FXML
    Circle infoCircle;
    @FXML
    Label infoNameLabel;
    @FXML
    Label strong1Label;
    @FXML
    Label strong2Label;
    @FXML
    Label strong3Label;
    @FXML
    Label weak1Label;
    @FXML
    Label weak2Label;
    @FXML
    Label weak3Label;
    @FXML
    Label foughtLabel;
    @FXML
    Label beatenLabel;
    @FXML
    Label caughtLabel;
    @FXML
    HBox statInfoBox;
    @FXML
    Button backButton;

    //  monster types
    transient Type normal = new Type("Normal", 0);
    transient Type fire = new Type("Fire", 1);
    transient Type water = new Type("Water", 2);
    transient Type earth = new Type("Earth", 3);
    transient Type electric = new Type("Electric", 4);
    transient Type nature = new Type("Nature", 5);
    transient Type wind = new Type("Wind", 6);
    transient Type ice = new Type("Ice", 7);


    //  set player
    public void setPlayer(Player player) {
        this.player = player;
        setTypeCircles();
        statInfoBox.setVisible(false);
    }

    //  set type circles
    public void setTypeCircles() {
        normalCircle.setFill(normal.color);
        fireCircle.setFill(fire.color);
        waterCircle.setFill(water.color);
        earthCircle.setFill(earth.color);
        electricCircle.setFill(electric.color);
        natureCircle.setFill(nature.color);
        windCircle.setFill(wind.color);
        iceCircle.setFill(ice.color);
    }

    //  set main scene
    public void setMainScene() throws IOException {
        mainScene = loadMainScene();
    }
    //  get main scene
    public void getMainScene() {
        player.setScene(mainScene);
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(player.scene);
    }
    //  load main scene
    public Scene loadMainScene() throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent main = mainLoader.load();
        MainController mainController = mainLoader.getController();
        mainController.setPlayer(player);
        int eligibleCount = 0;
        for (int i = 0; i < player.pc.size; i++) {
            if (!player.pc.get(i).fainted) {
                eligibleCount++;
            }
        }
        if (eligibleCount == 0) {
            mainController.setLoseButtons();
        } else {
            mainController.resetLoseButtons();
        }
        return new Scene(main);
    }



    @FXML
    public void onBackButton() throws IOException {
        setMainScene();
        getMainScene();
    }
}
