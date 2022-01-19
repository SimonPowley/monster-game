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
import java.util.Objects;

public class InfoController {
    Player player;
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

    //  show type info
    public void showTypeInfo(Type type) {
        infoCircle.setFill(type.color);
        infoNameLabel.setText(type.name + " Type");
        //  show any type strengths
        if (type.strongAgainst.size() == 0) {
            strong1Label.setText("None");
            strong2Label.setText("");
            strong3Label.setText("");
        } else {
            //  strength 1
            strong1Label.setText(type.strongAgainst.get(0));
            //  strength 2
            if (type.strongAgainst.size() > 1) {
                strong2Label.setText(type.strongAgainst.get(1));
            } else {
                strong2Label.setText("");
            }
            //  strength 3
            if (type.strongAgainst.size() > 2) {
                strong3Label.setText(type.strongAgainst.get(2));
            } else {
                strong3Label.setText("");
            }
        }
        //  show any type weaknesses
        if (type.weakAgainst.size() == 0) {
            weak1Label.setText("None");
            weak2Label.setText("");
            weak3Label.setText("");
        } else {
            //  weakness 1
            weak1Label.setText(type.weakAgainst.get(0));
            //  weakness 2
            if (type.weakAgainst.size() > 1) {
                weak2Label.setText(type.weakAgainst.get(1));
            } else {
                weak2Label.setText("");
            }
            //  weakness 3
            if (type.weakAgainst.size() > 2) {
                weak3Label.setText(type.weakAgainst.get(2));
            } else {
                weak3Label.setText("");
            }
        }
    }
    //  show type fought, beaten, caught
    public void showTypeStats(Type type) {
        if (Objects.equals(type.name, "Normal")) {
            foughtLabel.setText("Fought: " + player.normalFought);
            beatenLabel.setText("Beaten: " + player.normalBeaten);
            caughtLabel.setText("Caught: " + player.normalCaught);
        } else if (Objects.equals(type.name, "Fire")) {
            foughtLabel.setText("Fought: " + player.fireFought);
            beatenLabel.setText("Beaten: " + player.fireBeaten);
            caughtLabel.setText("Caught: " + player.fireCaught);
        } else if (Objects.equals(type.name, "Water")) {
            foughtLabel.setText("Fought: " + player.waterFought);
            beatenLabel.setText("Beaten: " + player.waterBeaten);
            caughtLabel.setText("Caught: " + player.waterCaught);
        } else if (Objects.equals(type.name, "Earth")) {
            foughtLabel.setText("Fought: " + player.earthFought);
            beatenLabel.setText("Beaten: " + player.earthBeaten);
            caughtLabel.setText("Caught: " + player.earthCaught);
        } else if (Objects.equals(type.name, "Electric")) {
            foughtLabel.setText("Fought: " + player.electricFought);
            beatenLabel.setText("Beaten: " + player.electricBeaten);
            caughtLabel.setText("Caught: " + player.electricCaught);
        } else if (Objects.equals(type.name, "Nature")) {
            foughtLabel.setText("Fought: " + player.natureFought);
            beatenLabel.setText("Beaten: " + player.natureBeaten);
            caughtLabel.setText("Caught: " + player.natureCaught);
        } else if (Objects.equals(type.name, "Wind")) {
            foughtLabel.setText("Fought: " + player.windFought);
            beatenLabel.setText("Beaten: " + player.windBeaten);
            caughtLabel.setText("Caught: " + player.windCaught);
        } else if (Objects.equals(type.name, "Ice")) {
            foughtLabel.setText("Fought: " + player.iceFought);
            beatenLabel.setText("Beaten: " + player.iceBeaten);
            caughtLabel.setText("Caught: " + player.iceCaught);
        }
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
        for (int i = 0; i < player.pc.size(); i++) {
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
    //  show normal type info
    public void onNormalInfoClick() {
        statInfoBox.setVisible(true);
        showTypeInfo(normal);
        showTypeStats(normal);
    }
    @FXML
    //  show fire type info
    public void onFireInfoClick() {
        statInfoBox.setVisible(true);
        showTypeInfo(fire);
        showTypeStats(fire);
    }
    @FXML
    //  show water type info
    public void onWaterInfoClick() {
        statInfoBox.setVisible(true);
        showTypeInfo(water);
        showTypeStats(water);
    }
    @FXML
    //  show earth type info
    public void onEarthInfoClick() {
        statInfoBox.setVisible(true);
        showTypeInfo(earth);
        showTypeStats(earth);
    }
    @FXML
    //  show electric type info
    public void onElectricInfoClick() {
        statInfoBox.setVisible(true);
        showTypeInfo(electric);
        showTypeStats(electric);
    }
    @FXML
    //  show nature type info
    public void onNatureInfoClick() {
        statInfoBox.setVisible(true);
        showTypeInfo(nature);
        showTypeStats(nature);
    }
    @FXML
    //  show wind type info
    public void onWindInfoClick() {
        statInfoBox.setVisible(true);
        showTypeInfo(wind);
        showTypeStats(wind);
    }
    @FXML
    //  show ice type info
    public void onIceInfoClick() {
        statInfoBox.setVisible(true);
        showTypeInfo(ice);
        showTypeStats(ice);
    }

    @FXML
    public void onBackButton() throws IOException {
        setMainScene();
        getMainScene();
    }
}
