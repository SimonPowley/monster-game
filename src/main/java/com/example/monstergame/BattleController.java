package com.example.monstergame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

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
    private Label playerNameLabel;
    @FXML
    private Label playerHealthLabel;
    @FXML
    private Circle enemyCircle;
    @FXML
    private Label enemyNameLabel;
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
    @FXML
    private Button move1Button;
    @FXML
    private Button move2Button;
    @FXML
    private Button move3Button;
    @FXML
    private Button move4Button;
    @FXML
    private Button discard1Button;
    @FXML
    private Button discard2Button;
    @FXML
    private Button discard3Button;
    @FXML
    private Button discard4Button;
    @FXML
    private Button discard5Button;
    @FXML
    private Button discard6Button;
    @FXML
    private Label learnMoveLabel;
    @FXML
    private Label moveNameLabel;
    @FXML
    private Label moveTypeLabel;
    @FXML
    private Label moveDamageLabel;
    @FXML
    private Label moveInfoLabel;


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
            //  if boss battle
            if (this.battle.enemyMonster.bossMonster) {
                setBattleLog("Strong looking " + this.battle.enemyMonster.name + " appeared!");
            } else {
                setBattleLog("Wild " + this.battle.enemyMonster.type.name + " Monster appeared!");
            }
        }
        setMonsterCircles();
        setTrainerCircles();
        hideDiscardMoves();
        hideMoveInfo();
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

    //  show moves to discard when learning 5th move
    public void showDiscardMoves() {
        learnMoveLabel.setVisible(true);
        //  move 1
        discard1Button.setVisible(true);
        discard1Button.setText(player.teamLeader.learnedMoves.get(0).name);
        //  move 2
        discard2Button.setVisible(true);
        discard2Button.setText(player.teamLeader.learnedMoves.get(1).name);
        //  move 3
        discard3Button.setVisible(true);
        discard3Button.setText(player.teamLeader.learnedMoves.get(2).name);
        //  move 4
        discard4Button.setVisible(true);
        discard4Button.setText(player.teamLeader.learnedMoves.get(3).name);
        //  new move 1
        discard5Button.setVisible(true);
        discard5Button.setText(player.teamLeader.learnedMoves.get(4).name);
        //  new move 2
        discard6Button.setVisible(false);
        if (player.teamLeader.learnedMoves.size() == 6) {
            discard6Button.setVisible(true);
            discard6Button.setText(player.teamLeader.learnedMoves.get(5).name);
        }
    }
    //  hide moves to discard after discarding 5th move
    public void hideDiscardMoves() {
        learnMoveLabel.setVisible(false);
        discard1Button.setVisible(false);
        discard2Button.setVisible(false);
        discard3Button.setVisible(false);
        discard4Button.setVisible(false);
        discard5Button.setVisible(false);
        discard6Button.setVisible(false);
    }
    //  show move info when discarding moves
    public void showMoveInfo(int moveIndex) {
        moveNameLabel.setVisible(true);
        moveNameLabel.setText(player.teamLeader.learnedMoves.get(moveIndex).name);
        moveTypeLabel.setVisible(true);
        moveTypeLabel.setText("Type: " + player.teamLeader.learnedMoves.get(moveIndex).type.name);
        moveDamageLabel.setVisible(true);
        moveDamageLabel.setText("Power: " + player.teamLeader.learnedMoves.get(moveIndex).damage);
        moveInfoLabel.setVisible(true);
        moveInfoLabel.setWrapText(true);
        moveInfoLabel.setText(player.teamLeader.learnedMoves.get(moveIndex).info);
    }
    //  hide move info
    public void hideMoveInfo() {
        moveNameLabel.setVisible(false);
        moveTypeLabel.setVisible(false);
        moveDamageLabel.setVisible(false);
        moveInfoLabel.setVisible(false);
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
            if (player.trainer.pc.size() > 0) {
                trainerCircle1.setFill(player.trainer.pc.get(0).type.color);
            }
            //  team circle 2
            if (player.trainer.pc.size() > 1) {
                trainerCircle2.setFill(player.trainer.pc.get(1).type.color);
            } else {
                trainerCircle2.setVisible(false);
            }
            //  team circle 3
            if (player.trainer.pc.size() > 2) {
                trainerCircle3.setFill(player.trainer.pc.get(2).type.color);
            } else {
                trainerCircle3.setVisible(false);
            }
            //  team circle 4
            if (player.trainer.pc.size() > 3) {
                trainerCircle4.setFill(player.trainer.pc.get(3).type.color);
            } else {
                trainerCircle4.setVisible(false);
            }
            //  team circle 5
            if (player.trainer.pc.size() > 4) {
                trainerCircle5.setFill(player.trainer.pc.get(4).type.color);
            } else {
                trainerCircle5.setVisible(false);
            }
            //  team circle 6
            if (player.trainer.pc.size() > 5) {
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
        playerNameLabel.setText(player.teamLeader.name);
        playerHealthLabel.setText("Lvl: " + player.teamLeader.level + "   HP: " + player.teamLeader.hpCurr + "/" + player.teamLeader.hpMax);
        //  set enemy monster
        enemyCircle.setFill(battle.enemyMonster.type.color);
        enemyNameLabel.setText(battle.enemyMonster.name);
        enemyHealthLabel.setText("Lvl: " +battle.enemyMonster.level + "   HP: " + battle.enemyMonster.hpCurr + "/" + battle.enemyMonster.hpMax);
        setBars();
        hideMoves();
    }



    @FXML
    //  attempt to capture monster
    public void catchMonster() {
        //  if player uses ball on monster: if successful: player chooses name, battle ends
        //  else: battle resumes
        if (player.useBall(battle.enemyMonster, battleLogLabel)) {
            //  hide battle buttons, show monster name field
            fightButton.setVisible(false);
            teamButton.setVisible(false);
            bagButton.setVisible(false);
            runButton.setVisible(false);
            nameMonsterTextField.setVisible(true);
            setBattleLog(battleLogLabel.getText() + "\nEnter name for monster");
            //  complete quest if boss monster
            if (battle.enemyMonster.bossMonster) {
                for (Quest quest : player.quests) {
                    if (quest.bossMonster != null && Objects.equals(quest.bossMonster.name, battle.enemyMonster.name)) {
                        quest.decreaseRemaining();
                    }
                }
            }
            mode = 1;
        } else {
            enableButtonsCatch();
            mode = 2;
        }
        player.itemInUse = null;
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

    //  show moves
    public void showMoves() {
        hideMoves();
        move1Button.setVisible(true);
        move1Button.setText(player.teamLeader.learnedMoves.get(0).name);
        if (player.teamLeader.learnedMoves.size() > 1) {
            move2Button.setVisible(true);
            move2Button.setText(player.teamLeader.learnedMoves.get(1).name);
        }
        if (player.teamLeader.learnedMoves.size() > 2) {
            move3Button.setVisible(true);
            move3Button.setText(player.teamLeader.learnedMoves.get(2).name);
        }
        if (player.teamLeader.learnedMoves.size() > 3) {
            move4Button.setVisible(true);
            move4Button.setText(player.teamLeader.learnedMoves.get(3).name);
        }
    }
    //  hide moves
    public void hideMoves() {
        move1Button.setVisible(false);
        move2Button.setVisible(false);
        move3Button.setVisible(false);
        move4Button.setVisible(false);
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
        team.getStylesheets().add(Objects.requireNonNull(getClass().getResource("button.css")).toExternalForm());
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
        bag.getStylesheets().add(Objects.requireNonNull(getClass().getResource("button.css")).toExternalForm());
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
        main.getStylesheets().add(Objects.requireNonNull(getClass().getResource("button.css")).toExternalForm());
        return new Scene(main);
    }



    @FXML
    //  player uses move 1
    public void onMove1() {
        battle.setMove(player.teamLeader.learnedMoves.get(0));
        battle.checkSpeed(this);
        endBattleTurn();
    }
    @FXML
    //  player uses move 2
    public void onMove2() {
        battle.setMove(player.teamLeader.learnedMoves.get(1));
        battle.checkSpeed(this);
        endBattleTurn();
    }
    @FXML
    //  player uses move 3
    public void onMove3() {
        battle.setMove(player.teamLeader.learnedMoves.get(2));
        battle.checkSpeed(this);
        endBattleTurn();
    }
    @FXML
    //  player uses move 4
    public void onMove4() {
        battle.setMove(player.teamLeader.learnedMoves.get(3));
        battle.checkSpeed(this);
        endBattleTurn();
    }
    @FXML
    //  player chooses to attack enemy monster
    public void endBattleTurn() {
        int pcSize = player.pcSizeLimit;
        player.itemInUse = null;
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
                battleLogLabel.setText(battleLogLabel.getText() + "\n" + player.name + " won! Gained $" + ((battle.enemyMonster.xpYield + 20) / 2) + "!");
                player.trainer = null;
                if (player.pcSizeLimit > pcSize) {
                    battleLogLabel.setText(battleLogLabel.getText() + "\nTeam size expanded!");
                }
            }
            fightButton.setVisible(false);
            teamButton.setVisible(false);
            bagButton.setVisible(false);
            //  discard move if monster learns 5th move
            if (player.teamLeader.learnedMoves.size() >= 5) {
                runButton.setVisible(false);
                showDiscardMoves();
            } else {
                hideDiscardMoves();
                runButton.setVisible(true);
                runButton.setText("Back");
            }
        }
        player.setLeader();
    }

    @FXML
    //  player views moves, or catch monster
    public void onFightButton() {
        if (player.itemInUse == player.bag.get(1)) {
            catchMonster();
            player.itemInUse = null;
        }
        if (mode == 1 || mode == 2) {
            mode = 9;
            return;
        }
        showMoves();
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

    //  discard moves after learning 5th/6th move
    @FXML
    public void discardMove1() {
        if (Objects.equals(discard1Button.getText(), "Confirm")) {
            player.teamLeader.learnedMoves.remove(0);
            //  discard another move if 6th was learned
            if (player.teamLeader.learnedMoves.size() == 5) {
                showDiscardMoves();
                hideMoveInfo();
            } else {
                hideDiscardMoves();
                hideMoveInfo();
                runButton.setVisible(true);
                runButton.setText("Back");
            }
        } else {
            showDiscardMoves();
            discard1Button.setText("Confirm");
            showMoveInfo(0);
        }
    }
    @FXML
    public void discardMove2() {
        if (Objects.equals(discard2Button.getText(), "Confirm")) {
            player.teamLeader.learnedMoves.remove(1);
            //  discard another move if 6th was learned
            if (player.teamLeader.learnedMoves.size() == 5) {
                showDiscardMoves();
                hideMoveInfo();
            } else {
                hideDiscardMoves();
                hideMoveInfo();
                runButton.setVisible(true);
                runButton.setText("Back");
            }
        } else {
            showDiscardMoves();
            discard2Button.setText("Confirm");
            showMoveInfo(1);
        }
    }
    @FXML
    public void discardMove3() {
        if (Objects.equals(discard3Button.getText(), "Confirm")) {
            player.teamLeader.learnedMoves.remove(2);
            //  discard another move if 6th was learned
            if (player.teamLeader.learnedMoves.size() == 5) {
                showDiscardMoves();
                hideMoveInfo();
            } else {
                hideDiscardMoves();
                hideMoveInfo();
                runButton.setVisible(true);
                runButton.setText("Back");
            }
        } else {
            showDiscardMoves();
            discard3Button.setText("Confirm");
            showMoveInfo(2);
        }
    }
    @FXML
    public void discardMove4() {
        if (Objects.equals(discard4Button.getText(), "Confirm")) {
            player.teamLeader.learnedMoves.remove(3);
            //  discard another move if 6th was learned
            if (player.teamLeader.learnedMoves.size() == 5) {
                showDiscardMoves();
                hideMoveInfo();
            } else {
                hideDiscardMoves();
                hideMoveInfo();
                runButton.setVisible(true);
                runButton.setText("Back");
            }
        } else {
            showDiscardMoves();
            discard4Button.setText("Confirm");
            showMoveInfo(3);
        }
    }
    @FXML
    public void discardMove5() {
        if (Objects.equals(discard5Button.getText(), "Confirm")) {
            player.teamLeader.learnedMoves.remove(4);
            //  discard another move if 6th was learned
            if (player.teamLeader.learnedMoves.size() == 5) {
                showDiscardMoves();
                hideMoveInfo();
            } else {
                hideDiscardMoves();
                hideMoveInfo();
                runButton.setVisible(true);
                runButton.setText("Back");
            }
        } else {
            showDiscardMoves();
            discard5Button.setText("Confirm");
            showMoveInfo(4);
        }
    }
    @FXML
    public void discardMove6() {
        if (Objects.equals(discard6Button.getText(), "Confirm")) {
            player.teamLeader.learnedMoves.remove(5);
            //  discard another move if 6th was learned
            if (player.teamLeader.learnedMoves.size() == 5) {
                showDiscardMoves();
                hideMoveInfo();
            } else {
                hideDiscardMoves();
                hideMoveInfo();
                runButton.setVisible(true);
                runButton.setText("Back");
            }
        } else {
            showDiscardMoves();
            discard6Button.setText("Confirm");
            showMoveInfo(5);
        }
    }
}
