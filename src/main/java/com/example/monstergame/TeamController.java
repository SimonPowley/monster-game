package com.example.monstergame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TeamController {
    Player player;
    Monster swapTemp;
    BattleController battleController;
    int swapIndex;
    int statIndex;
    private Scene mainScene;
    private Scene bagScene;
    private Scene battleScene;
    int mode; // 0=from main, 1=from bag, 2=from battle, 3=from battle bag, 4=swap, 5=lose
    //  honestly not quite sure what all these modes are for at this point lol
    //  could probably reduce it to 2, maybe 3
    int prevMode;
    @FXML
    private Circle team1Circle;
    @FXML
    private Circle team2Circle;
    @FXML
    private Circle team3Circle;
    @FXML
    private Circle team4Circle;
    @FXML
    private Circle team5Circle;
    @FXML
    private Circle team6Circle;
    @FXML
    private Label team1LevelLabel;
    @FXML
    private Label team2LevelLabel;
    @FXML
    private Label team3LevelLabel;
    @FXML
    private Label team4LevelLabel;
    @FXML
    private Label team5LevelLabel;
    @FXML
    private Label team6LevelLabel;
    @FXML
    private Label gameLogLabel;
    @FXML
    private Button backButton;
    @FXML
    private Label team1NameLabel;
    @FXML
    private ProgressBar team1HpBar;
    @FXML
    private ProgressBar team1XpBar;
    @FXML
    private Label team2NameLabel;
    @FXML
    private ProgressBar team2HpBar;
    @FXML
    private ProgressBar team2XpBar;
    @FXML
    private Label team3NameLabel;
    @FXML
    private ProgressBar team3HpBar;
    @FXML
    private ProgressBar team3XpBar;
    @FXML
    private Label team4NameLabel;
    @FXML
    private ProgressBar team4HpBar;
    @FXML
    private ProgressBar team4XpBar;
    @FXML
    private Label team5NameLabel;
    @FXML
    private ProgressBar team5HpBar;
    @FXML
    private ProgressBar team5XpBar;
    @FXML
    private Label team6NameLabel;
    @FXML
    private ProgressBar team6HpBar;
    @FXML
    private ProgressBar team6XpBar;

    @FXML
    private Circle statCircle;
    @FXML
    private Label statNameLabel;
    @FXML
    private Label statLevelLabel;
    @FXML
    private Label statHpLabel;
    @FXML
    private Label statXpLabel;
    @FXML
    private Label statTypeLabel;
    @FXML
    private Label statAttackLabel;
    @FXML
    private Label statDefenseLabel;
    @FXML
    private Label statSpeedLabel;
    @FXML
    private ProgressBar statHpBar;
    @FXML
    private ProgressBar statXpBar;
    @FXML
    private Button makeLeaderButton;
    @FXML
    private Button changeNameButton;
    @FXML
    private Button releaseButton;
    @FXML
    private TextField changeNameField;



    //  set player
    public void setPlayer(Player player, int mode) {
        this.player = player;
        this.mode = mode;
        this.player.setLeader();
        setTeamCircles();
        setGameLogLabel();
        hideStatView();
    }
    //  set battle controller to return to
    public void setBattleController(BattleController battleController) {
        this.battleController = battleController;
    }

    //  set monster health and xp bars
    public void setBars(int index) {
        //  set stat bars
        if (statCircle.isVisible()) {
            setStatBars(statIndex);
        }
        //  team 1 hp and xp
        if (index == 0) {
            double team1Hp = (double) player.pc.get(0).hpCurr / player.pc.get(0).hpMax;
            double team1Xp = (double) player.pc.get(0).xpCurr / player.pc.get(0).xpToLevelUp;
            team1HpBar.setProgress(team1Hp);
            team1XpBar.setProgress(team1Xp);
            if (team1Hp > .5) {
                team1HpBar.setStyle("-fx-accent: #24ff24;");
            } else if (team1Hp <= .5 && team1Hp > .2) {
                team1HpBar.setStyle("-fx-accent: #ffde24");
            } else if (team1Hp <= .2 || player.pc.get(0).hpCurr <= 3) {
                team1HpBar.setStyle("-fx-accent: #ff2424");
            }
        }
        //  team 2 hp and xp
        if (index == 1) {
            double team2Hp = (double) player.pc.get(1).hpCurr / player.pc.get(1).hpMax;
            double team2Xp = (double) player.pc.get(1).xpCurr / player.pc.get(1).xpToLevelUp;
            team2HpBar.setProgress(team2Hp);
            team2XpBar.setProgress(team2Xp);
            if (team2Hp > .5) {
                team2HpBar.setStyle("-fx-accent: #24ff24;");
            } else if (team2Hp <= .5 && team2Hp > .2) {
                team2HpBar.setStyle("-fx-accent: #ffde24");
            } else if (team2Hp <= .2 || player.pc.get(1).hpCurr <= 3) {
                team2HpBar.setStyle("-fx-accent: #ff2424");
            }
        }
        //  team 3 hp and xp
        if (index == 2) {
            double team3Hp = (double) player.pc.get(2).hpCurr / player.pc.get(2).hpMax;
            double team3Xp = (double) player.pc.get(2).xpCurr / player.pc.get(2).xpToLevelUp;
            team3HpBar.setProgress(team3Hp);
            team3XpBar.setProgress(team3Xp);
            if (team3Hp > .5) {
                team3HpBar.setStyle("-fx-accent: #24ff24;");
            } else if (team3Hp <= .5 && team3Hp > .2) {
                team3HpBar.setStyle("-fx-accent: #ffde24");
            } else if (team3Hp <= .2 || player.pc.get(2).hpCurr <= 3) {
                team3HpBar.setStyle("-fx-accent: #ff2424");
            }
        }
        //  team 4 hp and xp
        if (index == 3) {
            double team4Hp = (double) player.pc.get(3).hpCurr / player.pc.get(3).hpMax;
            double team4Xp = (double) player.pc.get(3).xpCurr / player.pc.get(3).xpToLevelUp;
            team4HpBar.setProgress(team4Hp);
            team4XpBar.setProgress(team4Xp);
            if (team4Hp > .5) {
                team4HpBar.setStyle("-fx-accent: #24ff24;");
            } else if (team4Hp <= .5 && team4Hp > .2) {
                team4HpBar.setStyle("-fx-accent: #ffde24");
            } else if (team4Hp <= .2 || player.pc.get(3).hpCurr <= 3) {
                team4HpBar.setStyle("-fx-accent: #ff2424");
            }
        }
        //  team 5 hp and xp
        if (index == 4) {
            double team5Hp = (double) player.pc.get(4).hpCurr / player.pc.get(4).hpMax;
            double team5Xp = (double) player.pc.get(4).xpCurr / player.pc.get(4).xpToLevelUp;
            team5HpBar.setProgress(team5Hp);
            team5XpBar.setProgress(team5Xp);
            if (team5Hp > .5) {
                team5HpBar.setStyle("-fx-accent: #24ff24;");
            } else if (team5Hp <= .5 && team5Hp > .2) {
                team5HpBar.setStyle("-fx-accent: #ffde24");
            } else if (team5Hp <= .2 || player.pc.get(4).hpCurr <= 3) {
                team5HpBar.setStyle("-fx-accent: #ff2424");
            }
        }
        //  team 6 hp and xp
        if (index == 5) {
            double team6Hp = (double) player.pc.get(5).hpCurr / player.pc.get(5).hpMax;
            double team6Xp = (double) player.pc.get(5).xpCurr / player.pc.get(5).xpToLevelUp;
            team6HpBar.setProgress(team6Hp);
            team6XpBar.setProgress(team6Xp);
            if (team6Hp > .5) {
                team6HpBar.setStyle("-fx-accent: #24ff24;");
            } else if (team6Hp <= .5 && team6Hp > .2) {
                team6HpBar.setStyle("-fx-accent: #ffde24");
            } else if (team6Hp <= .2 || player.pc.get(5).hpCurr <= 3) {
                team6HpBar.setStyle("-fx-accent: #ff2424");
            }
        }
    }
    //  set empty team circles
    public void setEmptyTeamCircles() {
        //  if slot is unlocked and unoccupied
        for (int i = player.pc.size(); i < 6; i++) {
            //  slot 1
            if (i == 0 && player.pc.get(0) == null) {
                team1Circle.setFill(Paint.valueOf("#ffffff"));
                if (player.pcSizeLimit < i) {
                    team1Circle.setVisible(false);
                    team1NameLabel.setVisible(false);
                } else {
                    team1NameLabel.setText("Slot empty");
                }
                team1LevelLabel.setVisible(false);
                team1HpBar.setVisible(false);
                team1XpBar.setVisible(false);
            }
            //  slot 2
            if (i == 1 && player.pc.get(1) == null) {
                team2Circle.setFill(Paint.valueOf("#ffffff"));
                if (player.pcSizeLimit <= i) {
                    team2Circle.setVisible(false);
                    team2NameLabel.setVisible(false);
                } else {
                    team2NameLabel.setText("Slot empty!");
                }
                team2LevelLabel.setVisible(false);
                team2HpBar.setVisible(false);
                team2XpBar.setVisible(false);
            }
            //  slot 3
            if (i == 2 && player.pc.get(2) == null) {
                team3Circle.setFill(Paint.valueOf("#ffffff"));
                if (player.pcSizeLimit <= i) {
                    team3Circle.setVisible(false);
                    team3NameLabel.setVisible(false);
                } else {
                    team3NameLabel.setText("Slot empty!");
                }
                team3LevelLabel.setVisible(false);
                team3HpBar.setVisible(false);
                team3XpBar.setVisible(false);
            }
            //  slot 4
            if (i == 3 && player.pc.get(3) == null) {
                team4Circle.setFill(Paint.valueOf("#ffffff"));
                if (player.pcSizeLimit <= i) {
                    team4Circle.setVisible(false);
                    team4NameLabel.setVisible(false);
                } else {
                    team4NameLabel.setText("Slot empty!");
                }
                team4LevelLabel.setVisible(false);
                team4HpBar.setVisible(false);
                team4XpBar.setVisible(false);
            }
            //  slot 5
            if (i == 4 && player.pc.get(4) == null) {
                team5Circle.setFill(Paint.valueOf("#ffffff"));
                if (player.pcSizeLimit <= i) {
                    team5Circle.setVisible(false);
                    team5NameLabel.setVisible(false);
                } else {
                    team5NameLabel.setText("Slot empty!");
                }
                team5LevelLabel.setVisible(false);
                team5HpBar.setVisible(false);
                team5XpBar.setVisible(false);
            }
            //  slot 6
            if (i == 5 && player.pc.get(5) == null) {
                team6Circle.setFill(Paint.valueOf("#ffffff"));
                if (player.pcSizeLimit <= i) {
                    team6Circle.setVisible(false);
                    team6NameLabel.setVisible(false);
                } else {
                    team6NameLabel.setText("Slot empty!");
                }
                team6LevelLabel.setVisible(false);
                team6HpBar.setVisible(false);
                team6XpBar.setVisible(false);
            }
        }
    }
    //  set each team circle color and stats
    public void setTeamCircles() {
        for (int i = 0; i < player.pc.size(); i++) {
            //  team 1
            if (i == 0) {
                team1Circle.setFill(player.pc.get(i).type.color);
                team1LevelLabel.setText("Lvl: " + player.pc.get(i).level);
                if (player.pc.get(i).hpCurr <= 0) {
                    team1LevelLabel.setText(team1LevelLabel.getText() + " Fainted!");
                } else {
                    team1LevelLabel.setText(team1LevelLabel.getText() + " Hp: " + player.pc.get(i).hpCurr + "/" + player.pc.get(i).hpMax);
                }
                team1NameLabel.setText(player.pc.get(0).name);
                setBars(0);
            }
            //  team 2
            else if (i == 1) {
                team2Circle.setFill(player.pc.get(i).type.color);
                team2LevelLabel.setText("Lvl: " + player.pc.get(i).level);
                if (player.pc.get(i).hpCurr <= 0) {
                    team2LevelLabel.setText(team2LevelLabel.getText() + " Fainted!");
                } else {
                    team2LevelLabel.setText(team2LevelLabel.getText() + " Hp: " + player.pc.get(i).hpCurr + "/" + player.pc.get(i).hpMax);
                }
                team2NameLabel.setText(player.pc.get(1).name);
                setBars(1);
            }
            //  team 3
            else if (i == 2) {
                team3Circle.setFill(player.pc.get(i).type.color);
                team3LevelLabel.setText("Lvl: " + player.pc.get(i).level);
                if (player.pc.get(i).hpCurr <= 0) {
                    team3LevelLabel.setText(team3LevelLabel.getText() + " Fainted!");
                } else {
                    team3LevelLabel.setText(team3LevelLabel.getText() + " Hp: " + player.pc.get(i).hpCurr + "/" + player.pc.get(i).hpMax);
                }
                team3NameLabel.setText(player.pc.get(2).name);
                setBars(2);
            }
            //  team 4
            else if (i == 3) {
                team4Circle.setFill(player.pc.get(i).type.color);
                team4LevelLabel.setText("Lvl: " + player.pc.get(i).level);
                if (player.pc.get(i).hpCurr <= 0) {
                    team4LevelLabel.setText(team4LevelLabel.getText() + " Fainted!");
                } else {
                    team4LevelLabel.setText(team4LevelLabel.getText() + " Hp: " + player.pc.get(i).hpCurr + "/" + player.pc.get(i).hpMax);
                }
                team4NameLabel.setText(player.pc.get(3).name);
                setBars(3);
            }
            //  team 5
            else if (i == 4) {
                team5Circle.setFill(player.pc.get(i).type.color);
                team5LevelLabel.setText("Lvl: " + player.pc.get(i).level);
                if (player.pc.get(i).hpCurr <= 0) {
                    team5LevelLabel.setText(team5LevelLabel.getText() + " Fainted!");
                } else {
                    team5LevelLabel.setText(team5LevelLabel.getText() + " Hp: " + player.pc.get(i).hpCurr + "/" + player.pc.get(i).hpMax);
                }
                team5NameLabel.setText(player.pc.get(4).name);
                setBars(4);
            }
            //  team6
            else if (i == 5) {
                team6Circle.setFill(player.pc.get(i).type.color);
                team6LevelLabel.setText("Lvl: " + player.pc.get(i).level);
                if (player.pc.get(i).hpCurr <= 0) {
                    team6LevelLabel.setText(team6LevelLabel.getText() + " Fainted!");
                } else {
                    team6LevelLabel.setText(team6LevelLabel.getText() + " Hp: " + player.pc.get(i).hpCurr + "/" + player.pc.get(i).hpMax);
                }
                team6NameLabel.setText(player.pc.get(5).name);
                setBars(5);
            }
        }
        setEmptyTeamCircles();
    }

    //  set team organization message log
    public void setGameLogLabel() {
        if (mode == 0 || mode == 2) {
            gameLogLabel.setText("Select a monster to view stats");
        }
        else if (mode == 1 || mode == 3) {
            gameLogLabel.setText("Select a monster to heal");
        }
    }

    //  hide individual stat view
    public void hideStatView() {
        statCircle.setVisible(false);
        statNameLabel.setVisible(false);
        statLevelLabel.setVisible(false);
        statHpLabel.setVisible(false);
        statXpLabel.setVisible(false);
        statTypeLabel.setVisible(false);
        statAttackLabel.setVisible(false);
        statDefenseLabel.setVisible(false);
        statSpeedLabel.setVisible(false);
        statHpBar.setVisible(false);
        statXpBar.setVisible(false);
        makeLeaderButton.setVisible(false);
        changeNameButton.setVisible(false);
        releaseButton.setVisible(false);
        changeNameField.setVisible(false);
        swapTemp = null;
        swapIndex = -1;
    }
    //  show individual stat view
    public void showStatView(int index) {
        Monster monster = player.pc.get(index);
        statCircle.setVisible(true);
        statCircle.setFill(monster.type.color);
        statNameLabel.setVisible(true);
        statNameLabel.setText(monster.name);
        statLevelLabel.setVisible(true);
        statLevelLabel.setText("Lvl: " + monster.level);
        statHpLabel.setVisible(true);
        statHpLabel.setText("Hp: " + monster.hpCurr + "/" + monster.hpMax);
        statXpLabel.setVisible(true);
        statXpLabel.setText("Xp: " + monster.xpCurr + "/" + monster.xpToLevelUp);
        statTypeLabel.setVisible(true);
        statAttackLabel.setVisible(true);
        statDefenseLabel.setVisible(true);
        statSpeedLabel.setVisible(true);
        statTypeLabel.setText("Type: " + monster.type.name);
        statAttackLabel.setText("Attack: " + monster.attack);
        statDefenseLabel.setText("Defense: " + monster.defense);
        statSpeedLabel.setText("Speed: " + monster.speed);
        statHpBar.setVisible(true);
        statXpBar.setVisible(true);
        makeLeaderButton.setVisible(true);
        changeNameButton.setVisible(true);
        releaseButton.setVisible(true);
        releaseButton.setText("Release");
        setStatBars(index);
        swapIndex = index;
        statIndex = index;
        gameLogLabel.setText("Viewing " + player.pc.get(statIndex).name + "'s stats");
        showStatAffinities(monster);
    }
    //  show stat affinities
    public void showStatAffinities(Monster monster) {
        //  show hp affinity
        if (monster.hpStatBoost > 0) {
            statHpLabel.setText(statHpLabel.getText() + "   +" + monster.hpStatBoost + "%");
        } else if (monster.hpStatBoost < 0) {
            statHpLabel.setText(statHpLabel.getText() + "   " + monster.hpStatBoost + "%");
        }
        //  show attack affinity
        if (monster.attackStatBoost > 0) {
            statAttackLabel.setText(statAttackLabel.getText() + "   +" + monster.attackStatBoost + "%");
        } else if (monster.attackStatBoost < 0) {
            statAttackLabel.setText(statAttackLabel.getText() + "   " + monster.attackStatBoost + "%");
        }
        //  show defense affinity
        if (monster.defenseStatBoost > 0) {
            statDefenseLabel.setText(statDefenseLabel.getText() + "   +" + monster.defenseStatBoost + "%");
        } else if (monster.defenseStatBoost < 0) {
            statDefenseLabel.setText(statDefenseLabel.getText() + "   " + monster.defenseStatBoost + "%");
        }
        //  show speed affinity
        if (monster.speedStatBoost > 0) {
            statSpeedLabel.setText(statSpeedLabel.getText() + "   +" + monster.speedStatBoost + "%");
        } else if (monster.speedStatBoost < 0) {
            statSpeedLabel.setText(statSpeedLabel.getText() + "   " + monster.speedStatBoost + "%");
        }
    }

    //  stat hp and xp if viewing
    public void setStatBars(int index) {
        Monster monster = player.pc.get(index);
        double statHp = (double) monster.hpCurr / monster.hpMax;
        double statXp = (double) monster.xpCurr / monster.xpToLevelUp;
        statHpBar.setProgress(statHp);
        statXpBar.setProgress(statXp);
        if (statHp > .5) {
            statHpBar.setStyle("-fx-accent: #24ff24;");
        } else if (statHp <= .5 && statHp > .2) {
            statHpBar.setStyle("-fx-accent: #ffde24");
        } else if (statHp <= .2 || player.pc.get(0).hpCurr <= 3) {
            statHpBar.setStyle("-fx-accent: #ff2424");
        }
    }

    //  show stats or use item on selected circle
    public void onTeamCircle1Click() {
        //  if in heal mode
        if ((mode == 1 || mode == 3) && player.pc.get(0) != null) {
            //  using potion
            if (player.itemInUse == player.bag.get(0)) {
                player.usePotion(player.pc.get(0), gameLogLabel);
            }
            //  using revive
            else if (player.itemInUse == player.bag.get(2)) {
                player.useRevive(player.pc.get(0), gameLogLabel);
            }
            setTeamCircles();
        }
        //  if in team mode
        else if ((mode == 0 || mode == 2 || mode == 5) && player.pc.get(0) != null) {
            gameLogLabel.setText("Viewing " + player.pc.get(0).name + "'s stats");
            showStatView(0);
            changeNameField.setVisible(false);
            changeNameButton.setText("Change Name");
        }
        //  if in swap mode
        else if (mode == 4 && player.pc.get(0) != null) {
            if (swapIndex != 0) {
                gameLogLabel.setText("Swapped " + player.pc.get(swapIndex).name + " with " + player.pc.get(0).name);
                if (prevMode == 2) {
                    battleController.setBattleLog(gameLogLabel.getText());
                }
                Monster temp = player.pc.get(0);
                player.pc.set(0, swapTemp);
                player.pc.set(swapIndex, temp);
            } else {
                gameLogLabel.setText("Select monster to view stats");
            }
            player.setLeader();
            mode = prevMode;
            setTeamCircles();
        }
    }
    public void onTeamCircle2Click() {
        //  if in heal mode
        if ((mode == 1 || mode == 3) && player.pc.get(1) != null) {
            //  using potion
            if (player.itemInUse == player.bag.get(0)) {
                player.usePotion(player.pc.get(1), gameLogLabel);
            }
            //  using revive
            else if (player.itemInUse == player.bag.get(2)) {
                player.useRevive(player.pc.get(1), gameLogLabel);
            }
            setTeamCircles();
        }
        //  if in team mode
        else if ((mode == 0 || mode == 2 || mode == 5) && player.pc.get(1) != null) {
            gameLogLabel.setText("Viewing " + player.pc.get(1).name + "'s stats");
            showStatView(1);
            changeNameField.setVisible(false);
            changeNameButton.setText("Change Name");
        }
        //  if in swap mode
        else if (mode == 4 && player.pc.get(1) != null) {
            if (swapIndex != 1) {
                gameLogLabel.setText("Swapped " + player.pc.get(swapIndex).name + " with " + this.player.pc.get(1).name);
                if (prevMode == 2) {
                    battleController.setBattleLog(gameLogLabel.getText());
                }
                Monster temp = player.pc.get(1);
                player.pc.set(1, swapTemp);
                player.pc.set(swapIndex, temp);
            } else {
                gameLogLabel.setText("Swapped " + player.pc.get(swapIndex).name + " with " + player.pc.get(0).name);
                if (prevMode == 2) {
                    battleController.setBattleLog(gameLogLabel.getText());
                }
                Monster temp = player.pc.get(0);
                player.pc.set(0, swapTemp);
                player.pc.set(swapIndex, temp);
            }
            player.setLeader();
            mode = prevMode;
            setTeamCircles();
        }
    }
    public void onTeamCircle3Click() {
        //  if in heal mode
        if ((mode == 1 || mode == 3) && player.pc.get(2) != null) {
            //  using potion
            if (player.itemInUse == player.bag.get(0)) {
                player.usePotion(player.pc.get(2), gameLogLabel);
            }
            //  using revive
            else if (player.itemInUse == player.bag.get(2)) {
                player.useRevive(player.pc.get(2), gameLogLabel);
            }
            setTeamCircles();
        }
        //  if in team mode
        else if ((mode == 0 || mode == 2 || mode == 5) && player.pc.get(2) != null) {
            gameLogLabel.setText("Viewing " + player.pc.get(2).name + "'s stats");
            showStatView(2);
            changeNameField.setVisible(false);
            changeNameButton.setText("Change Name");
        }
        //  if in swap mode
        else if (mode == 4 && player.pc.get(2) != null) {
            if (swapIndex != 2) {
                gameLogLabel.setText("Swapped " + player.pc.get(swapIndex).name + " with " + player.pc.get(2).name);
                if (prevMode == 2) {
                    battleController.setBattleLog(gameLogLabel.getText());
                }
                Monster temp = player.pc.get(2);
                player.pc.set(2, swapTemp);
                player.pc.set(swapIndex, temp);
            } else {
                gameLogLabel.setText("Swapped " + player.pc.get(swapIndex).name + " with " + player.pc.get(0).name);
                if (prevMode == 2) {
                    battleController.setBattleLog(gameLogLabel.getText());
                }
                Monster temp = player.pc.get(0);
                player.pc.set(0, swapTemp);
                player.pc.set(swapIndex, temp);
            }
            player.setLeader();
            mode = prevMode;
            setTeamCircles();
        }
    }
    public void onTeamCircle4Click() {
        //  if in heal mode
        if ((mode == 1 || mode == 3) && player.pc.get(3) != null) {
            //  using potion
            if (player.itemInUse == player.bag.get(0)) {
                player.usePotion(player.pc.get(3), gameLogLabel);
            }
            //  using revive
            else if (player.itemInUse == player.bag.get(2)) {
                player.useRevive(player.pc.get(3), gameLogLabel);
            }
            setTeamCircles();
        }
        //  if in team mode
        else if ((mode == 0 || mode == 2 || mode == 5) &&player.pc.get(3) != null) {
            gameLogLabel.setText("Viewing " + player.pc.get(3).name + "'s stats");
            showStatView(3);
            changeNameField.setVisible(false);
            changeNameButton.setText("Change Name");
        }
        //  if in swap mode
        else if (mode == 4 && player.pc.get(3) != null) {
            if (swapIndex != 3) {
                gameLogLabel.setText("Swapped " + player.pc.get(swapIndex).name + " with " + player.pc.get(3).name);
                if (prevMode == 2) {
                    battleController.setBattleLog(gameLogLabel.getText());
                }
                Monster temp = player.pc.get(3);
                player.pc.set(3, swapTemp);
                player.pc.set(swapIndex, temp);
            } else {
                gameLogLabel.setText("Swapped " + player.pc.get(swapIndex).name + " with " + player.pc.get(0).name);
                if (prevMode == 2) {
                    battleController.setBattleLog(gameLogLabel.getText());
                }
                Monster temp = player.pc.get(0);
                player.pc.set(0, swapTemp);
                player.pc.set(swapIndex, temp);
            }
            player.setLeader();
            mode = prevMode;
            setTeamCircles();
        }
    }
    public void onTeamCircle5Click() {
        //  if in heal mode
        if ((mode == 1 || mode == 3) && player.pc.get(4) != null) {
            //  using potion
            if (player.itemInUse == player.bag.get(0)) {
                player.usePotion(player.pc.get(4), gameLogLabel);
            }
            //  using revive
            else if (player.itemInUse == player.bag.get(2)) {
                player.useRevive(player.pc.get(4), gameLogLabel);
            }
            setTeamCircles();
        }
        //  if in team mode
        else if ((mode == 0 || mode == 2 || mode == 5) && player.pc.get(4) != null) {
            gameLogLabel.setText("Viewing " + player.pc.get(4).name + "'s stats");
            showStatView(4);
            changeNameField.setVisible(false);
            changeNameButton.setText("Change Name");
        }
        //  if in swap mode
        else if (mode == 4 && player.pc.get(4) != null) {
            if (swapIndex != 4) {
                gameLogLabel.setText("Swapped " + player.pc.get(swapIndex).name + " with " + player.pc.get(4).name);
                if (prevMode == 2) {
                    battleController.setBattleLog(gameLogLabel.getText());
                }
                Monster temp = player.pc.get(4);
                player.pc.set(4, swapTemp);
                player.pc.set(swapIndex, temp);
            } else {
                gameLogLabel.setText("Swapped " + player.pc.get(swapIndex).name + " with " + player.pc.get(0).name);
                if (prevMode == 2) {
                    battleController.setBattleLog(gameLogLabel.getText());
                }
                Monster temp = player.pc.get(0);
                player.pc.set(0, swapTemp);
                player.pc.set(swapIndex, temp);
            }
            player.setLeader();
            mode = prevMode;
            setTeamCircles();
        }
    }
    public void onTeamCircle6Click() {
        //  if in heal mode
        if ((mode == 1 || mode == 3) && player.pc.get(5) != null) {
            //  using potion
            if (player.itemInUse == player.bag.get(0)) {
                player.usePotion(player.pc.get(5), gameLogLabel);
            }
            //  using revive
            else if (player.itemInUse == player.bag.get(2)) {
                player.useRevive(player.pc.get(5), gameLogLabel);
            }
            setTeamCircles();
        }
        //  if in team mode
        else if ((mode == 0 || mode == 2 || mode == 5) && player.pc.get(5) != null) {
            gameLogLabel.setText("Viewing " + player.pc.get(5).name + "'s stats");
            showStatView(5);
            changeNameField.setVisible(false);
            changeNameButton.setText("Change Name");
        }
        //  if in swap mode
        else if (mode == 4 && player.pc.get(5) != null) {
            if (swapIndex != 5) {
                gameLogLabel.setText("Swapped " + player.pc.get(swapIndex).name + " with " + player.pc.get(5).name);
                if (prevMode == 2) {
                    battleController.setBattleLog(gameLogLabel.getText());
                }
                Monster temp = player.pc.get(5);
                player.pc.set(5, swapTemp);
                player.pc.set(swapIndex, temp);
            } else {
                gameLogLabel.setText("Swapped " + player.pc.get(swapIndex).name + " with " + player.pc.get(0).name);
                if (prevMode == 2) {
                    battleController.setBattleLog(gameLogLabel.getText());
                }
                Monster temp = player.pc.get(0);
                player.pc.set(0, swapTemp);
                player.pc.set(swapIndex, temp);
            }
            player.setLeader();
            mode = prevMode;
            setTeamCircles();
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

    //  set bag scene
    public void setBagScene() throws IOException {
        bagScene = loadBagScene();
    }
    //  get bag scene
    public void getBagScene() {
        player.setScene(bagScene);
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(player.scene);
    }
    //  load bag scene
    public Scene loadBagScene() throws IOException {
        FXMLLoader bagLoader = new FXMLLoader(getClass().getResource("bag.fxml"));
        Parent bag = bagLoader.load();
        BagController bagController = bagLoader.getController();
        if (mode == 1) {
            bagController.setPlayer(player, 0);
        } else if (mode == 3) {
            bagController.setPlayer(player, 1);
            bagController.setBattleController(battleController);
        } else if (mode == 5) {
            bagController.setPlayer(player, 2);
        }
        return new Scene(bag);
    }

    //  set battle scene
    public void setBattleScene() throws IOException {
        battleScene = loadBattleScene();
    }
    //  get battle scene
    public void getBattleScene() {
        player.setScene(battleScene);
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(player.scene);
    }
    //  load battle scene
    public Scene loadBattleScene() throws IOException {
        FXMLLoader battleLoader = new FXMLLoader(getClass().getResource("battle.fxml"));
        Parent battle = battleLoader.load();
        BattleController battleController = battleLoader.getController();
        battleController.setPlayer(player, this.battleController.battle);
        if (!Objects.equals(gameLogLabel.getText(), "Select a monster to swap")) {
            battleController.setBattleLog(this.battleController.getBattleLog().getText());
            battleController.mode = 3;
        } else {
            battleController.setBattleLog(this.battleController.getBattleLog().getText());
        }
        return new Scene(battle);
    }



    @FXML
    //  player exits team organization scene, return to main menu, bag, or battle scene
    public void onBackButtonClick() throws IOException {
        if (mode == 0) {
            setMainScene();
            getMainScene();
        } else if (mode == 1 || mode == 3) {
            setBagScene();
            getBagScene();
        } else if (mode == 2) {
            setBattleScene();
            getBattleScene();
        } else if (mode == 4) {
            mode = prevMode;
            setGameLogLabel();
        } else if (mode == 5) {
            setMainScene();
            getMainScene();
        }
    }

    @FXML
    //  swap stat view monster with monster leader
    protected void onChangeLeaderButton() {
        if (swapIndex != 0 && !player.pc.get(swapIndex).fainted) {
            swapTemp = player.pc.get(0);
            Monster monster = player.pc.get(swapIndex);
            player.pc.set(0, monster);
            player.pc.set(swapIndex, swapTemp);
            player.setLeader();
            swapTemp = null;
            swapIndex = 0;
            setTeamCircles();
            showStatView(0);
        }
    }

    @FXML
    //  show change name text field
    protected void onChangeNameButton() {
        if (!changeNameField.isVisible()) {
            changeNameField.setVisible(true);
            changeNameButton.setText("Cancel");
        }
        else {
            changeNameField.setText("");
            changeNameField.setVisible(false);
            changeNameButton.setText("Change Name");
        }
    }
    @FXML
    //  change monster's name
    protected void onNameEntered() {
        player.pc.get(statIndex).setName(changeNameField.getText());
        onChangeNameButton();
        setTeamCircles();
        showStatView(statIndex);
    }

    @FXML
    //  release a monster
    protected void onReleaseButton() {
        if (player.pc.size() == 1) {
            gameLogLabel.setText("Cannot release your last monster!");
            return;
        } else if (mode == 2) {
            gameLogLabel.setText("Cannot release while in battle!");
            return;
        }
        if (Objects.equals(releaseButton.getText(), "Confirm")) {
            releaseButton.setText("Release");
            gameLogLabel.setText(player.pc.get(statIndex).name + " was released!");
            player.pc.remove(statIndex);
            setTeamCircles();
            hideStatView();
            player.setLeader();
        } else {
            releaseButton.setText("Confirm");
        }
    }
}
