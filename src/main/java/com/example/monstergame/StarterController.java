package com.example.monstergame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    @FXML
    Label fireMove1Label;
    @FXML
    Label fireMove2Label;
    @FXML
    Label fireMove3Label;
    @FXML
    Label fireMove4Label;
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
    @FXML
    Label waterMove1Label;
    @FXML
    Label waterMove2Label;
    @FXML
    Label waterMove3Label;
    @FXML
    Label waterMove4Label;
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
    @FXML
    Label natureMove1Label;
    @FXML
    Label natureMove2Label;
    @FXML
    Label natureMove3Label;
    @FXML
    Label natureMove4Label;
    //  other stuff
    @FXML
    TextField monsterNameTextField;
    @FXML
    Label gameLogLabel;
    @FXML
    Button rerollButton;



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
        int hp = randomStat(15, 20);
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
        showFireMoves();
    }
    //  create random water monster
    public void randomWaterMonster() {
        Type water = new Type("Water", 2);
        int hp = randomStat(15, 20);
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
        showWaterMoves();
    }
    //  create random nature monster
    public void randomNatureMonster() {
        Type nature = new Type("Nature", 5);
        int hp = randomStat(15, 20);
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
        showNatureMoves();
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

    //  show fire monster moves
    public void showFireMoves() {
        if (fireMonster.learnedMoves.size() == 0) {
            fireMove1Label.setVisible(false);
            fireMove2Label.setVisible(false);
            fireMove3Label.setVisible(false);
            fireMove4Label.setVisible(false);
        } else {
            //  show move 1
            fireMove1Label.setText(fireMonster.learnedMoves.get(0).name);
            //  show move 2
            if (fireMonster.learnedMoves.size() > 1) {
                fireMove2Label.setVisible(true);
                fireMove2Label.setText(fireMonster.learnedMoves.get(1).name);
            } else {
                fireMove2Label.setVisible(false);
            }
            //  show move 3
            if (fireMonster.learnedMoves.size() > 2) {
                fireMove3Label.setVisible(true);
                fireMove3Label.setText(fireMonster.learnedMoves.get(2).name);
            } else {
                fireMove3Label.setVisible(false);
            }
            //  show move 4
            if (fireMonster.learnedMoves.size() > 3) {
                fireMove4Label.setVisible(true);
                fireMove4Label.setText(fireMonster.learnedMoves.get(3).name);
            } else {
                fireMove4Label.setVisible(false);
            }
        }
    }
    //  show water monster moves
    public void showWaterMoves() {
        if (waterMonster.learnedMoves.size() == 0) {
            waterMove1Label.setVisible(false);
            waterMove2Label.setVisible(false);
            waterMove3Label.setVisible(false);
            waterMove4Label.setVisible(false);
        } else {
            //  show move 1
            waterMove1Label.setText(waterMonster.learnedMoves.get(0).name);
            //  show move 2
            if (waterMonster.learnedMoves.size() > 1) {
                waterMove2Label.setVisible(true);
                waterMove2Label.setText(waterMonster.learnedMoves.get(1).name);
            } else {
                waterMove2Label.setVisible(false);
            }
            //  show move 3
            if (waterMonster.learnedMoves.size() > 2) {
                waterMove3Label.setVisible(true);
                waterMove3Label.setText(waterMonster.learnedMoves.get(2).name);
            } else {
                waterMove3Label.setVisible(false);
            }
            //  show move 4
            if (waterMonster.learnedMoves.size() > 3) {
                waterMove4Label.setVisible(true);
                waterMove4Label.setText(waterMonster.learnedMoves.get(3).name);
            } else {
                waterMove4Label.setVisible(false);
            }
        }
    }
    //  show nature monster moves
    public void showNatureMoves() {
        if (natureMonster.learnedMoves.size() == 0) {
            natureMove1Label.setVisible(false);
            natureMove2Label.setVisible(false);
            natureMove3Label.setVisible(false);
            natureMove4Label.setVisible(false);
        } else {
            //  show move 1
            natureMove1Label.setText(natureMonster.learnedMoves.get(0).name);
            //  show move 2
            if (natureMonster.learnedMoves.size() > 1) {
                natureMove2Label.setVisible(true);
                natureMove2Label.setText(natureMonster.learnedMoves.get(1).name);
            } else {
                natureMove2Label.setVisible(false);
            }
            //  show move 3
            if (natureMonster.learnedMoves.size() > 2) {
                natureMove3Label.setVisible(true);
                natureMove3Label.setText(natureMonster.learnedMoves.get(2).name);
            } else {
                natureMove3Label.setVisible(false);
            }
            //  show move 4
            if (natureMonster.learnedMoves.size() > 3) {
                natureMove4Label.setVisible(true);
                natureMove4Label.setText(natureMonster.learnedMoves.get(3).name);
            } else {
                natureMove4Label.setVisible(false);
            }
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
            player.pc.add(fireMonster);
            player.setLeader();
            setMainScene();
            getMainScene();
        } else if (waterChosen) {
            waterMonster.setName(monsterNameTextField.getText());
            player.pc.add(waterMonster);
            player.setLeader();
            setMainScene();
            getMainScene();
        } else if (natureChosen) {
            natureMonster.setName(monsterNameTextField.getText());
            player.pc.add(natureMonster);
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
