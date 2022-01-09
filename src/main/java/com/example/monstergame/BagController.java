package com.example.monstergame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class BagController {
    Player player;
    BattleController battleController;
    private Scene mainScene;
    private Scene teamScene;
    private Scene battleScene;
    int mode;   // 0=main, 1=battle, 2=lose
    @FXML
    private Label potionLabel;
    @FXML
    private Label ballLabel;
    @FXML
    private Label reviveLabel;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label shopLogLabel;
    @FXML
    private Button backButton;



    //  set player
    public void setPlayer (Player player, int mode) {
        this.player = player;
        this.mode = mode;
        this.player.setLeader();
        setItemLabels();
    }
    //  save battle controller to return to after finished with bag
    public void setBattleController(BattleController battleController) {
        this.battleController = battleController;
    }

    //  set main menu scene
    public void setMainScene() throws IOException {
        mainScene = loadMainScene();
    }
    //  get main menu scene
    public void getMainScene() {
        player.setScene(mainScene);
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(player.scene);
    }
    //  load main menu scene
    public Scene loadMainScene() throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent main = mainLoader.load();
        MainController mainController = mainLoader.getController();
        mainController.setPlayer(player);
        //  check for fainted monsters
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

    //  set team scene
    public void setTeamScene() throws IOException {
        teamScene = loadTeamScene();
    }
    //  get team scene
    public void getTeamScene() {
        player.setScene(teamScene);
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(player.scene);
    }
    //  load team scene
    public Scene loadTeamScene() throws IOException {
        FXMLLoader teamLoader = new FXMLLoader(getClass().getResource("team.fxml"));
        Parent team = teamLoader.load();
        TeamController teamController = teamLoader.getController();
        if (mode == 0 || mode == 2) {
            teamController.setPlayer(player, 1);
        } else if (mode == 1) {
            teamController.setPlayer(player, 3);
            teamController.setBattleController(battleController);
        }
        return new Scene(team);
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
        if (player.itemInUse == player.bag.get(1)) {
            battleController.setBattleLog("Select enemy to throw ball!");
            battleController.disableButtonsCatch();
        } else {
            battleController.setBattleLog(this.battleController.getBattleLog().getText());
            battleController.enableButtonsCatch();
        }
        battleController.mode = 4;
        return new Scene(battle);
    }



    @FXML
    //  update player money and item counts
    public void setItemLabels() {
        moneyLabel.setText("$" + player.money);
        potionLabel.setText("" + player.potionsInBag);
        ballLabel.setText("" + player.ballsInBag);
        reviveLabel.setText("" + player.revivesInBag);
    }

    @FXML
    //  player clicks on potion, enter team scene to heal monsters
    public void onPotionClick() throws IOException {
        if (player.potionsInBag == 0) {
            shopLogLabel.setText("No potions to use!");
        }
        if (player.potionsInBag > 0) {
            player.itemInUse = player.bag.get(0);
            setTeamScene();
            getTeamScene();
        }
    }

    @FXML
    //  if in battle mode: enter battle scene in catch mode
    public void onBallClick() throws IOException {

        if (!player.inBattle) {
            shopLogLabel.setText("Can only use balls in battle!");
        }
        else if (player.ballsInBag == 0) {
            shopLogLabel.setText("No balls to use!");
            return;
        }
        else if (player.pc.size >= player.pcSizeLimit) {
            shopLogLabel.setText("Monster storage is full!");
            return;
        }
        if (mode == 1) {
            if (battleController.battle.trainerBattle) {
                shopLogLabel.setText("Cannot steal " + player.trainer.name + "'s monster!");
            } else if (player.ballsInBag > 0) {
                player.itemInUse = player.bag.get(1);
                setBattleScene();
                getBattleScene();
            }
        }
    }

    @FXML
    //  player clicks ono revive, enter team scene to revive fainted monsters
    public void onReviveClick() throws IOException {
        if (player.revivesInBag == 0) {
            shopLogLabel.setText("No revives to use!");
        }
        if (player.revivesInBag > 0) {
            player.itemInUse = player.bag.get(2);
            setTeamScene();
            getTeamScene();
        }
    }

    @FXML
    //  player exits to scene they came from: main menu scene or battle scene
    public void onBackButtonClick() throws IOException {
        if (mode == 0 || mode == 2) {
            setMainScene();
            getMainScene();
        }
        else if (mode == 1) {
            setBattleScene();
            getBattleScene();
        }
    }
}
