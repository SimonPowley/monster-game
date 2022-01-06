package com.example.monstergame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class BattleController {
    Player player;
    Battle battle;
    int mode;   //  1 = catching, 2 = battle ended, 3 = from team, 4 = from bag
    private Scene mainScene;
    private Scene teamScene;
    private Scene bagScene;
    @FXML
    private Label battleLogLabel;
    @FXML
    private Button fightButton;
    @FXML
    private Button teamButton;
    @FXML
    private Button bagButton;
    @FXML
    private Button runButton;
    @FXML
    private Circle playerCircle;
    @FXML
    private Label playerHealthLabel;
    @FXML
    private Circle enemyCircle;
    @FXML
    private Label enemyHealthLabel;
    @FXML
    private ProgressBar playerHealthBar;
    @FXML
    private ProgressBar enemyHealthBar;
    @FXML
    private TextField nameMonsterTextField;
    @FXML
    private ProgressBar playerXpBar;
    @FXML
    private Circle trainerCircle1;
    @FXML
    private Circle trainerCircle2;
    @FXML
    private Circle trainerCircle3;
    @FXML
    private Circle trainerCircle4;
    @FXML
    private Circle trainerCircle5;
    @FXML
    private Circle trainerCircle6;


    //  set player
    public void setPlayer(Player player, Battle battle) {
        this.player = player;
        this.player.setLeader();
        this.player.inBattle = true;
        this.nameMonsterTextField.setVisible(false);
        this.battle = battle;
        //  trainer battle
        if (this.battle.trainerBattle) {
            setBattleLog(this.battle.questInfo + "\n" +
                    this.battle.enemyTrainer.name + " sent out " + this.battle.enemyMonster.name + "!");
        }
        //  wild monster battle
        else {
            setBattleLog("Wild " + this.battle.enemyMonster.type.name + " Monster appeared!");
        }
        setMonsterCircles();
        setTrainerCircles();
    }

    //  set battle log label
    public void setBattleLog(String text) {
        //  set battle log text
        battleLogLabel.setText(text);
    }
    //  get battle log label in team screen
    public Label getBattleLog() {
        //  get battle log text
        return battleLogLabel;
    }

    //  set player and enemy, hp and xp bars
    public void setBars() {
        double playerHealth = (double) player.teamLeader.hpCurr / player.teamLeader.hpMax;
        double playerXp = (double) player.teamLeader.xpCurr / player.teamLeader.xpToLevelUp;
        playerXpBar.setProgress(playerXp);
        playerHealthBar.setProgress(playerHealth);
        if (playerHealth >= .5) {
            playerHealthBar.setStyle("-fx-accent: #24ff24;");
        } else if (playerHealth < .5 && playerHealth > .2) {
            playerHealthBar.setStyle("-fx-accent: #ffde24");
        }
        if (playerHealth <= .2 || this.player.teamLeader.hpCurr <= 3) {
            playerHealthBar.setStyle("-fx-accent: #ff2424");
        }
        //  enemy hp bar
        double enemyHealth = (double) battle.enemyMonster.hpCurr / battle.enemyMonster.hpMax;
        enemyHealthBar.setProgress(enemyHealth);
        if (enemyHealth >= .5) {
            enemyHealthBar.setStyle("-fx-accent: #24ff24;");
        } else if (enemyHealth < .5 && playerHealth > .2) {
            enemyHealthBar.setStyle("-fx-accent: #ffde24");
        }
        if (enemyHealth <= .2 || battle.enemyMonster.hpCurr <= 3) {
            enemyHealthBar.setStyle("-fx-accent: #ff2424");
        }
    }

    //  display trainer team list
    public void setTrainerCircles() {
        if (player.trainer != null) {
            //  team circle 1
            if (player.trainer.pc.get(0) != null) {
                trainerCircle1.setFill(player.trainer.pc.get(0).type.color);
            } else {
                trainerCircle1.setVisible(false);
            }
            //  team circle 2
            if (player.trainer.pc.get(1) != null) {
                trainerCircle2.setFill(player.trainer.pc.get(1).type.color);
            } else {
                trainerCircle2.setVisible(false);
            }
            //  team circle 3
            if (player.trainer.pc.get(2) != null) {
                trainerCircle3.setFill(player.trainer.pc.get(2).type.color);
            } else {
                trainerCircle3.setVisible(false);
            }
            //  team circle 4
            if (player.trainer.pc.get(3) != null) {
                trainerCircle4.setFill(player.trainer.pc.get(3).type.color);
            } else {
                trainerCircle4.setVisible(false);
            }
            //  team circle 5
            if (player.trainer.pc.get(4) != null) {
                trainerCircle5.setFill(player.trainer.pc.get(4).type.color);
            } else {
                trainerCircle5.setVisible(false);
            }
            //  team circle 6
            if (player.trainer.pc.get(5) != null) {
                trainerCircle6.setFill(player.trainer.pc.get(5).type.color);
            } else {
                trainerCircle6.setVisible(false);
            }
        } else {
            trainerCircle1.setVisible(false);
            trainerCircle2.setVisible(false);
            trainerCircle3.setVisible(false);
            trainerCircle4.setVisible(false);
            trainerCircle5.setVisible(false);
            trainerCircle6.setVisible(false);
        }
    }


    //  set player and enemy monster circles
    public void setMonsterCircles() {
        //  set player monster
        playerCircle.setFill(player.teamLeader.type.color);
        playerHealthLabel.setText(player.teamLeader.name + "   Lvl: " + player.teamLeader.level + "    HP: " + player.teamLeader.hpCurr + "/" + player.teamLeader.hpMax);
        //  set enemy monster
        enemyCircle.setFill(battle.enemyMonster.type.color);
        enemyHealthLabel.setText(battle.enemyMonster.name + "    Lvl: " +battle.enemyMonster.level + "   HP: " + battle.enemyMonster.hpCurr + "/" + battle.enemyMonster.hpMax);
        setBars();
    }



    @FXML
    //  attempt to capture monster
    public void catchMonster() {
        //  if player uses ball on monster: if successful: player chooses name, battle ends
        //  else: battle resumes
        if (player.itemInUse == player.bag.get(1)) {
            if (player.useBall(battle.enemyMonster, battleLogLabel)) {
                fightButton.setVisible(false);
                teamButton.setVisible(false);
                bagButton.setVisible(false);
                runButton.setVisible(false);
                nameMonsterTextField.setVisible(true);
                setBattleLog(battleLogLabel.getText() + "\nEnter name for monster");
                mode = 1;
            } else {
                enableButtonsCatch();
                mode = 2;
            }
        }
    }
    //  change battle buttons to catch buttons
    public void disableButtonsCatch() {
        mode = 4;
        fightButton.setText("Catch");
        teamButton.setVisible(false);
        bagButton.setText("Back");
        runButton.setVisible(false);
    }
    //  change catch buttons to battle buttons
    public void enableButtonsCatch() {
        fightButton.setText("Fight");
        teamButton.setVisible(true);
        bagButton.setText("Bag");
        runButton.setVisible(true);
    }

    @FXML
    //  after player names caught monster, change to main menu scene
    public void onNameEntered() throws IOException {
        player.pc.get(player.pc.size()-1).setName(nameMonsterTextField.getText());
        setMainScene();
        getMainScene();
    }

    //  set team scene
    public void setTeamScene() throws IOException {
        teamScene = loadTeamScene();
    }
    //  get team scene
    public void getTeamScene() {
        player.setScene(teamScene);
        Stage stage = (Stage) teamButton.getScene().getWindow();
        stage.setScene(player.scene);
    }
    //  load team scene
    public Scene loadTeamScene() throws IOException {
        FXMLLoader teamLoader = new FXMLLoader(getClass().getResource("team.fxml"));
        Parent team = teamLoader.load();
        TeamController teamController = teamLoader.getController();
        teamController.setPlayer(player, 2);
        teamController.setBattleController(this);
        return new Scene(team);
    }

    //  set bag scene
    public void setBagScene() throws IOException {
        bagScene = loadBagScene();
    }
    //  get bag scene
    public void getBagScene() {
        player.setScene(bagScene);
        Stage stage = (Stage) bagButton.getScene().getWindow();
        stage.setScene(player.scene);
    }
    //  load bag scene
    public Scene loadBagScene() throws IOException {
        FXMLLoader bagLoader = new FXMLLoader(getClass().getResource("bag.fxml"));
        Parent bag = bagLoader.load();
        BagController bagController = bagLoader.getController();
        bagController.setPlayer(player, 1);
        bagController.setBattleController(this);
        return new Scene(bag);
    }

    //  set main menu scene
    public void setMainScene() throws IOException {
        mainScene = loadMainScene();
    }
    //  get main menu scene
    public void getMainScene() {
        player.setScene(mainScene);
        Stage stage = (Stage) runButton.getScene().getWindow();
        stage.setScene(player.scene);
    }
    //  load main menu scene
    public Scene loadMainScene() throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent main = mainLoader.load();
        MainController mainController = mainLoader.getController();
        mainController.setPlayer(player);
        if (mode == 2) {
            mainController.setLoseButtons();
        }
        return new Scene(main);
    }



    @FXML
    //  player chooses to attack enemy monster
    public void onFightButtonClicked() {
        catchMonster();
        if (mode == 1 || mode == 2) {
            mode = 9;
            return;
        }
        int leaderLevel = player.teamLeader.level;
        int pcSize = player.pcSizeLimit;
        player.itemInUse = null;
        battle.checkSpeed(this);
        setMonsterCircles();
        setBars();
        setTrainerCircles();
        //  if player or enemy monster faints: if player's last eligible monster faints: game over
        //  else if last eligible enemy monster faints: player wins, monster gains xp,
        //  possible level ups and team size upgrades
        if (battle.enemyMonster.hpCurr <= 0 || player.teamLeader.hpCurr <= 0) {
            if (player.teamLeader.hpCurr <= 0) {
                battleLogLabel.setText(player.name + " lost!\nGame Over\nScore: " + player.score);
                mode = 2;
            } else {
                battleLogLabel.setText(battleLogLabel.getText() + "\n" + player.name + " won! " + player.teamLeader.name + " gained " + battle.enemyMonster.xpYield + " xp!");
                player.trainer = null;
                if (player.teamLeader.level > leaderLevel) {
                    battleLogLabel.setText(battleLogLabel.getText() + " " + player.teamLeader.name + " leveled up!");
                }
                if (player.pcSizeLimit > pcSize) {
                    battleLogLabel.setText(battleLogLabel.getText() + "\nTeam size expanded!");
                }
            }
            fightButton.setVisible(false);
            teamButton.setVisible(false);
            bagButton.setVisible(false);
            runButton.setText("Back");
        }
        player.setLeader();
    }

    @FXML
    //  player enters team organization scene
    public void onTeamButtonClicked() throws IOException {
        if (mode == 1 || mode == 2) {
            return;
        }
        player.itemInUse = null;
        setTeamScene();
        getTeamScene();
    }

    @FXML
    //  player enters bag scene
    public void onBagButtonClicked() throws IOException {
        if (mode == 1 || mode == 2) {
            return;
        }
        player.itemInUse = null;
        setBagScene();
        getBagScene();
    }

    @FXML
    //  player runs from battle, enters main menu scene
    public void onRunButtonClicked() throws IOException {
        player.itemInUse = null;
        player.trainer = null;
        setMainScene();
        getMainScene();
    }
}
