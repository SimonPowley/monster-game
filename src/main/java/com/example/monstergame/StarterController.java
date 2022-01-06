package com.example.monstergame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class StarterController {
    Player player;
    Scene mainScene;
    Monster fireMonster;
    Monster waterMonster;
    Monster natureMonster;
    boolean fireChosen;
    boolean waterChosen;
    boolean natureChosen;
    //  fire monster stuff
    @FXML
    Circle fireMonsterCircle;
    @FXML
    Label fireNameLabel;
    @FXML
    Label fireHpLabel;
    @FXML
    Label fireAttackLabel;
    @FXML
    Label fireDefenseLabel;
    @FXML
    Label fireSpeedLabel;
    @FXML
    Label fireHpBoostLabel;
    @FXML
    Label fireAttackBoostLabel;
    @FXML
    Label fireDefenseBoostLabel;
    @FXML
    Label fireSpeedBoostLabel;
    //  water monster stuff
    @FXML
    Circle waterMonsterCircle;
    @FXML
    Label waterNameLabel;
    @FXML
    Label waterHpLabel;
    @FXML
    Label waterAttackLabel;
    @FXML
    Label waterDefenseLabel;
    @FXML
    Label waterSpeedLabel;
    @FXML
    Label waterHpBoostLabel;
    @FXML
    Label waterAttackBoostLabel;
    @FXML
    Label waterDefenseBoostLabel;
    @FXML
    Label waterSpeedBoostLabel;
    //  nature monster stuff
    @FXML
    Circle natureMonsterCircle;
    @FXML
    Label natureNameLabel;
    @FXML
    Label natureHpLabel;
    @FXML
    Label natureAttackLabel;
    @FXML
    Label natureDefenseLabel;
    @FXML
    Label natureSpeedLabel;
    @FXML
    Label natureHpBoostLabel;
    @FXML
    Label natureAttackBoostLabel;
    @FXML
    Label natureDefenseBoostLabel;
    @FXML
    Label natureSpeedBoostLabel;
    //  other stuff
    @FXML
    TextField monsterNameTextField;
    @FXML
    Label gameLogLabel;



    //  set player
    public void setPlayer(Player player) {
        this.player = player;
        setStarters();
    }

    //  set starters
    public void setStarters() {
        randomFireMonster();
        randomWaterMonster();
        randomNatureMonster();
        hideNameField();
        gameLogLabel.setText("Choose a Monster");
        gameLogLabel.setTextFill(Paint.valueOf("#000000"));
    }
    //  create random fire monster
    public void randomFireMonster() {
        Type fire = new Type("Fire", 1);
        int hp = randomStat(10, 15);
        int att = randomStat(10, 15);
        int def = randomStat(10, 15);
        int spe = randomStat(10, 15);
        fireMonster = new Monster("Fire Monster", fire, 1, hp, att, def, spe, true);
        for (int i = 0; i < 4; i++) {
            fireMonster.setLevel(1);
        }
        fireNameLabel.setText(fireMonster.name);
        fireHpLabel.setText("HP: " + fireMonster.hpCurr + "/" + fireMonster.hpMax);
        fireAttackLabel.setText("Att: " + fireMonster.attack);
        fireDefenseLabel.setText("Def: " + fireMonster.defense);
        fireSpeedLabel.setText("Spe: " + fireMonster.speed);
        showFireStatAffinities();
    }
    //  create random water monster
    public void randomWaterMonster() {
        Type water = new Type("Water", 2);
        int hp = randomStat(10, 15);
        int att = randomStat(10, 15);
        int def = randomStat(10, 15);
        int spe = randomStat(10, 15);
        waterMonster = new Monster("Water Monster", water, 1, hp, att, def, spe, true);
        for (int i = 0; i < 4; i++) {
            waterMonster.setLevel(1);
        }
        waterNameLabel.setText(waterMonster.name);
        waterHpLabel.setText("HP: " + waterMonster.hpCurr + "/" + waterMonster.hpMax);
        waterAttackLabel.setText("Att: " + waterMonster.attack);
        waterDefenseLabel.setText("Def: " + waterMonster.defense);
        waterSpeedLabel.setText("Spe: " + waterMonster.speed);
        showWaterStatAffinities();
    }
    //  create random nature monster
    public void randomNatureMonster() {
        Type nature = new Type("Nature", 5);
        int hp = randomStat(10, 15);
        int att = randomStat(10, 15);
        int def = randomStat(10, 15);
        int spe = randomStat(10, 15);
        natureMonster = new Monster("Nature Monster", nature, 1, hp, att, def, spe, true);
        for (int i = 0; i < 4; i++) {
            natureMonster.setLevel(1);
        }
        natureNameLabel.setText(natureMonster.name);
        natureHpLabel.setText("HP: " + natureMonster.hpCurr + "/" + natureMonster.hpMax);
        natureAttackLabel.setText("Att: " + natureMonster.attack);
        natureDefenseLabel.setText("Def: " + natureMonster.defense);
        natureSpeedLabel.setText("Spe: " + natureMonster.speed);
        showNatureStatAffinities();
    }

    //  generate random stats within range
    public int randomStat(int min, int max) {
        Random rand = new Random();
        int stat = rand.nextInt(max);
        if (stat < min) {
            stat = min + 1;
        }
        return stat;
    }

    //  show hidden name field
    public void showNameField() {
        monsterNameTextField.setVisible(true);
    }
    //  hide name field
    public void hideNameField() {
        monsterNameTextField.setVisible(false);
    }

    //  show fire monster stat affinities
    public void showFireStatAffinities() {
        //  show hp affinity
        fireHpBoostLabel.setVisible(true);
        if (fireMonster.hpStatBoost > 0) {
            fireHpBoostLabel.setText("+" + fireMonster.hpStatBoost + "%");
        } else if (fireMonster.hpStatBoost < 0) {
            fireHpBoostLabel.setText(fireMonster.hpStatBoost + "%");
        } else {
            fireHpBoostLabel.setVisible(false);
        }
        //  show attack affinity
        fireAttackBoostLabel.setVisible(true);
        if (fireMonster.attackStatBoost > 0) {
            fireAttackBoostLabel.setText("+" + fireMonster.attackStatBoost + "%");
        } else if (fireMonster.attackStatBoost < 0) {
            fireAttackBoostLabel.setText(fireMonster.attackStatBoost + "%");
        } else {
            fireAttackBoostLabel.setVisible(false);
        }
        //  show defense affinity
        fireDefenseBoostLabel.setVisible(true);
        if (fireMonster.defenseStatBoost > 0) {
            fireDefenseBoostLabel.setText("+" + fireMonster.defenseStatBoost + "%");
        } else if (fireMonster.defenseStatBoost < 0) {
            fireDefenseBoostLabel.setText(fireMonster.defenseStatBoost + "%");
        } else {
            fireDefenseBoostLabel.setVisible(false);
        }
        //  show speed affinity
        fireSpeedBoostLabel.setVisible(true);
        if (fireMonster.speedStatBoost > 0) {
            fireSpeedBoostLabel.setText("+" + fireMonster.speedStatBoost + "%");
        } else if (fireMonster.speedStatBoost < 0) {
            fireSpeedBoostLabel.setText(fireMonster.speedStatBoost + "%");
        } else {
            fireSpeedBoostLabel.setVisible(false);
        }
    }
    //  show water monster stat affinities
    public void showWaterStatAffinities() {
        //  show hp affinity
        waterHpBoostLabel.setVisible(true);
        if (waterMonster.hpStatBoost > 0) {
            waterHpBoostLabel.setText("+" + waterMonster.hpStatBoost + "%");
        } else if (waterMonster.hpStatBoost < 0) {
            waterHpBoostLabel.setText(waterMonster.hpStatBoost + "%");
        } else {
            waterHpBoostLabel.setVisible(false);
        }
        //  show attack affinity
        waterAttackBoostLabel.setVisible(true);
        if (waterMonster.attackStatBoost > 0) {
            waterAttackBoostLabel.setText("+" + waterMonster.attackStatBoost + "%");
        } else if (waterMonster.attackStatBoost < 0) {
            waterAttackBoostLabel.setText(waterMonster.attackStatBoost + "%");
        } else {
            waterAttackBoostLabel.setVisible(false);
        }
        //  show defense affinity
        waterDefenseBoostLabel.setVisible(true);
        if (waterMonster.defenseStatBoost > 0) {
            waterDefenseBoostLabel.setText("+" + waterMonster.defenseStatBoost + "%");
        } else if (waterMonster.defenseStatBoost < 0) {
            waterDefenseBoostLabel.setText(waterMonster.defenseStatBoost + "%");
        } else {
            waterDefenseBoostLabel.setVisible(false);
        }
        //  show speed affinity
        waterSpeedBoostLabel.setVisible(true);
        if (waterMonster.speedStatBoost > 0) {
            waterSpeedBoostLabel.setText("+" + waterMonster.speedStatBoost + "%");
        } else if (waterMonster.speedStatBoost < 0) {
            waterSpeedBoostLabel.setText(waterMonster.speedStatBoost + "%");
        } else {
            waterSpeedBoostLabel.setVisible(false);
        }
    }
    //  show nature monster stat affinities
    public void showNatureStatAffinities() {
        //  show hp affinity
        natureHpBoostLabel.setVisible(true);
        if (natureMonster.hpStatBoost > 0) {
            natureHpBoostLabel.setText("+" + natureMonster.hpStatBoost + "%");
        } else if (natureMonster.hpStatBoost < 0) {
            natureHpBoostLabel.setText(natureMonster.hpStatBoost + "%");
        } else {
            natureHpBoostLabel.setVisible(false);
        }
        //  show attack affinity
        natureAttackBoostLabel.setVisible(true);
        if (natureMonster.attackStatBoost > 0) {
            natureAttackBoostLabel.setText("+" + natureMonster.attackStatBoost + "%");
        } else if (natureMonster.attackStatBoost < 0) {
            natureAttackBoostLabel.setText(natureMonster.attackStatBoost + "%");
        } else {
            natureAttackBoostLabel.setVisible(false);
        }
        //  show defense affinity
        natureDefenseBoostLabel.setVisible(true);
        if (natureMonster.defenseStatBoost > 0) {
            natureDefenseBoostLabel.setText("+" + natureMonster.defenseStatBoost + "%");
        } else if (natureMonster.defenseStatBoost < 0) {
            natureDefenseBoostLabel.setText(natureMonster.defenseStatBoost + "%");
        } else {
            natureDefenseBoostLabel.setVisible(false);
        }
        //  show speed affinity
        natureSpeedBoostLabel.setVisible(true);
        if (natureMonster.speedStatBoost > 0) {
            natureSpeedBoostLabel.setText("+" + natureMonster.speedStatBoost + "%");
        } else if (natureMonster.speedStatBoost < 0) {
            natureSpeedBoostLabel.setText(natureMonster.speedStatBoost + "%");
        } else {
            natureSpeedBoostLabel.setVisible(false);
        }
    }

    //  set main menu scene
    public void setMainScene() throws IOException {
        mainScene = loadMainScene();
    }
    //  get main menu scene
    public void getMainScene() {
        player.setScene(mainScene);
        Stage stage = (Stage) fireMonsterCircle.getScene().getWindow();
        stage.setScene(player.scene);
    }
    //  load main menu scene
    public Scene loadMainScene() throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent main = mainLoader.load();
        MainController mainController = mainLoader.getController();
        mainController.setPlayer(player);
        return new Scene(main);
    }

    @FXML
    //  choose fire monster name
    public void onFireMonsterChosen() {
        waterChosen = false;
        natureChosen = false;
        fireChosen = true;
        gameLogLabel.setText("Fire Monster chosen.\nEnter name to confirm.");
        gameLogLabel.setTextFill(fireMonster.type.color);
        showNameField();
    }

    @FXML
    //  choose water monster name
    public void onWaterMonsterChosen() {
        fireChosen = false;
        natureChosen = false;
        waterChosen = true;
        gameLogLabel.setText("Water monster chosen.\nEnter name to confirm.");
        gameLogLabel.setTextFill(waterMonster.type.color);
        showNameField();
    }

    @FXML
    //  choose nature monster name
    public void onNatureMonsterChosen() {
        fireChosen = false;
        waterChosen = false;
        natureChosen = true;
        gameLogLabel.setText("Nature monster chosen.\nEnter name to confirm.");
        gameLogLabel.setTextFill(natureMonster.type.color);
        showNameField();
    }

    @FXML
    //  add chosen monster to player's party
    public void onMonsterChosen() throws IOException {
        if (fireChosen) {
            fireMonster.setName(monsterNameTextField.getText());
            player.pc.add(fireMonster.name, fireMonster);
            player.setLeader();
            setMainScene();
            getMainScene();
        } else if (waterChosen) {
            waterMonster.setName(monsterNameTextField.getText());
            player.pc.add(waterMonster.name, waterMonster);
            player.setLeader();
            setMainScene();
            getMainScene();
        } else if (natureChosen) {
            natureMonster.setName(monsterNameTextField.getText());
            player.pc.add(natureMonster.name, natureMonster);
            player.setLeader();
            setMainScene();
            getMainScene();
        }
    }

    @FXML
    //  reroll starter monsters
    public void onRerollButton() {
        setStarters();
    }
}
