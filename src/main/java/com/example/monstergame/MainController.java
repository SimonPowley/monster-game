package com.example.monstergame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {
    Player player;
    int mode;   //  1 = lose, 2=battle quest
    private Scene battleScene;
    private Scene bagScene;
    private Scene teamScene;
    private Scene shopScene;
    private Scene saveGameScene;
    private Scene titleScene;
    private Scene infoScene;
    String questInfo; // for trainer battles
    @FXML
    private Button infoButton;
    @FXML
    private Button saveGameButton;
    @FXML
    private Button battleButton;
    @FXML
    private Button teamButton;
    @FXML
    private Button bagButton;
    @FXML
    private Button shopButton;
    @FXML
    private Button titleButton;
    @FXML
    private Label quest1Label;
    @FXML
    private Label quest2Label;
    @FXML
    private Label quest3Label;
    @FXML
    private Label questLabel;
    @FXML
    private Button quest1Button;
    @FXML
    private Button quest2Button;
    @FXML
    private Button quest3Button;
    @FXML
    private Label scoreLabel;



    //  set player
    public void setPlayer (Player player) {
        this.player = player;
        this.player.setLeader();
        this.player.itemInUse = null;
        int eligibleCount = 0;
        for (int i = 0; i < this.player.pc.size; i++) {
            if (!this.player.pc.get(i).fainted) {
                eligibleCount++;
            }
        }
        if (eligibleCount == 0) {
            setLoseButtons();
        }
        else {
            resetLoseButtons();
        }
        questLabel.setText("");
        scoreLabel.setText("Score: " + this.player.score);
        setQuestLog();
    }

    //  set quests
    public void setQuestLog() {
        //  quest 1
        if (player.quests.get(0) != null) {
            quest1Label.setText(player.quests.get(0).trainer.name);
            quest1Button.setVisible(true);
            //  show reward button
            if (player.quests.get(0).completed) {
                quest1Button.setText("Reward");
            }
            //  show battle button
            else if (player.quests.get(0).type == 0 || player.quests.get(0).type == 3) {
                quest1Button.setText("Battle");
            }
            //  show info button
            else {
                quest1Button.setText("Quest Info");
            }
        } else {
            quest1Label.setVisible(false);
            quest1Button.setVisible(false);
        }
        //  quest 2
        if (player.quests.get(1) != null) {
            quest2Label.setText(player.quests.get(1).trainer.name);
            quest2Button.setVisible(true);
            //  show reward button
            if (player.quests.get(1).completed) {
                quest2Button.setText("Reward");
            }
            //  show battle button
            else if (player.quests.get(1).type == 0 || player.quests.get(1).type == 3) {
                quest2Button.setText("Battle");
            }
            //  show info button
            else {
                quest2Button.setText("Quest Info");
            }
        } else {
            quest2Label.setVisible(false);
            quest2Button.setVisible(false);
        }
        //  quest 3
        if (player.quests.get(2) != null) {
            quest3Label.setText(player.quests.get(2).trainer.name);
            quest3Button.setVisible(true);
            //  show reward button
            if (player.quests.get(2).completed) {
                quest3Button.setText("Reward");
            }
            //  show battle button
            else if (player.quests.get(2).type == 0 || player.quests.get(2).type == 3) {
                quest3Button.setText("Battle");
            }
            //  show info button
            else {
                quest3Button.setText("Quest Info");
            }
        } else {
            quest3Label.setVisible(false);
            quest3Button.setVisible(false);
        }
    }

    //  restrict battle button after game over
    public void setLoseButtons() {
        mode = 1;
        battleButton.setVisible(false);
    }
    //  un-restrict battle button if player revives a monster after losing
    public void resetLoseButtons() {
        mode = 0;
        battleButton.setVisible(true);
        shopButton.setVisible(true);
    }

    //  set battle scene
    public void setBattleScene() throws IOException {
        battleScene = loadBattleScene();
    }
    //  get battle scene
    public void getBattleScene() {
        player.setScene(battleScene);
        Stage stage = (Stage) battleButton.getScene().getWindow();
        stage.setScene(player.scene);
    }
    //  load battle scene
    public Scene loadBattleScene() throws IOException {
        FXMLLoader battleLoader = new FXMLLoader(getClass().getResource("battle.fxml"));
        Parent battle = battleLoader.load();
        BattleController battleController = battleLoader.getController();
        //  battle trainer for quest
        if (mode == 2) {
            battleController.setPlayer(player, new Battle(player, true, questInfo));
        }
        //  wild battle
        else {
            battleController.setPlayer(player, new Battle(player, false, ""));
        }
        return new Scene(battle);
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
        bagController.setPlayer(player, 0);
        if (mode == 1) {
            bagController.mode = 2;
        }
        return new Scene(bag);
    }

    //  set shop scene
    public void setShopScene() throws IOException {
        shopScene = loadShopScene();
    }
    //  get shop scene
    public void getShopScene() {
        player.setScene(shopScene);
        Stage stage = (Stage) shopButton.getScene().getWindow();
        stage.setScene(player.scene);
    }
    //  load shop scene
    public Scene loadShopScene() throws IOException {
        FXMLLoader shopLoader = new FXMLLoader(getClass().getResource("shop.fxml"));
        Parent shop = shopLoader.load();
        ShopController shopController = shopLoader.getController();
        shopController.setPlayer(player);
        return new Scene(shop);
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
        teamController.setPlayer(player, 0);
        if (mode == 1) {
            teamController.mode = 5;
        }
        return new Scene(team);
    }

    //  set save game scene
    public void setSaveScene() throws IOException {
        saveGameScene = loadSaveScene();
    }
    //  get save game scene
    public void getSaveScene() {
        player.setScene(saveGameScene);
        Stage stage = (Stage) saveGameButton.getScene().getWindow();
        stage.setScene(player.scene);
    }
    //  load save game scene
    public Scene loadSaveScene() throws IOException {
        FXMLLoader saveLoader = new FXMLLoader(getClass().getResource("save.fxml"));
        Parent save = saveLoader.load();
        SaveController saveController = saveLoader.getController();
        saveController.setPlayer(player);
        return new Scene(save);
    }

    //  set title scene
    public void setTitleScene() throws IOException {
        titleScene = loadTitleScene();
    }
    //  get title scene
    public void getTitleScene() {
        player.setScene(titleScene);
        Stage stage = (Stage) saveGameButton.getScene().getWindow();
        stage.setScene(player.scene);
    }
    //  load title scene
    public Scene loadTitleScene() throws IOException {
        FXMLLoader titleLoader = new FXMLLoader(getClass().getResource("title.fxml"));
        Parent title = titleLoader.load();
        TitleController titleController = titleLoader.getController();
        titleController.setPlayer(player);
        return new Scene(title);
    }

    //  set type info scene
    public void setInfoScene() throws IOException {
        infoScene = loadInfoScene();
    }
    //  get type info scene
    public void getInfoScene() {
        player.setScene(infoScene);
        Stage stage = (Stage) infoButton.getScene().getWindow();
        stage.setScene(player.scene);
    }
    //  load type info scene
    public Scene loadInfoScene() throws IOException {
        FXMLLoader infoLoader = new FXMLLoader(getClass().getResource("info.fxml"));
        Parent info = infoLoader.load();
        InfoController infoController = infoLoader.getController();
        infoController.setPlayer(player);
        return new Scene(info);
    }



    @FXML
    //  change to save game screen
    protected void onSaveButtonClick() throws IOException {
        setSaveScene();
        getSaveScene();
    }

    @FXML
    protected void onInfoButton() throws IOException {
        setInfoScene();
        getInfoScene();
    }

    @FXML
    //  quest complete, reward item
    protected void onQuest1Click() throws IOException {
        player.setLeader();
        if (player.quests.get(0).completed) {
            int reward = (player.quests.get(0).trainer.levelCap + player.quests.get(0).trainer.pc.size + player.quests.get(0).originalTotal) * 8;
            //  bonus reward for trainer battles
            if (player.quests.get(0).type == 0) {
                reward *= 3;
            }
            player.setMoney(player.money + reward);
            player.setScore(player.score + reward);
            setPlayer(player);
            questLabel.setText(player.quests.get(0).trainer.name + " rewarded " + player.name + " $" + reward + "!");
            player.quests.remove(0);
            setQuestLog();
        }
        //  battle quest, start battle
        else if (player.quests.get(0).type == 0 || player.quests.get(0).type == 3) {
            if (!player.teamLeader.fainted) {
                player.quests.get(0).trainer.resetTeam();
                player.trainer = player.quests.get(0).trainer;
                questInfo = player.quests.get(0).info;
                mode = 2;
                setBattleScene();
                getBattleScene();
            }
        }
        //  quest incomplete, show info
        else {
            questLabel.setText(player.quests.get(0).info);
        }
    }

    @FXML
    //  quest complete, reward item
    protected void onQuest2Click() throws IOException {
        player.setLeader();
        if (player.quests.get(1).completed) {
            int reward = (player.quests.get(1).trainer.levelCap + player.quests.get(0).trainer.pc.size + player.quests.get(1).originalTotal) * 8;
            //  bonus reward for trainer battles
            if (player.quests.get(1).type == 0) {
                reward *= 3;
            }
            player.setMoney(player.money + reward);
            player.setScore(player.score + reward);
            setPlayer(player);
            questLabel.setVisible(true);
            questLabel.setText(player.quests.get(1).trainer.name + " rewarded " + player.name + " $" + reward + "!");
            player.quests.remove(1);
            setQuestLog();
        }
        //  battle quest, start battle
        else if (player.quests.get(1).type == 0 || player.quests.get(1).type == 3) {
            if (!player.teamLeader.fainted) {
                player.quests.get(1).trainer.resetTeam();
                player.trainer = player.quests.get(1).trainer;
                questInfo = player.quests.get(1).info;
                mode = 2;
                setBattleScene();
                getBattleScene();
            }
        }
        //  quest incomplete, show info
        else {
            questLabel.setText(player.quests.get(1).info);
        }
    }

    @FXML
    //  quest complete, reward item
    protected void onQuest3Click() throws IOException {
        player.setLeader();
        if (player.quests.get(2).completed) {
            int reward = (player.quests.get(2).trainer.levelCap + player.quests.get(0).trainer.pc.size + player.quests.get(2).originalTotal) * 8;
            //  bonus reward for trainer battles
            if (player.quests.get(2).type == 0) {
                reward *= 3;
            }
            player.setMoney(player.money + reward);
            player.setScore(player.score + reward);
            setPlayer(player);
            questLabel.setVisible(true);
            questLabel.setText(player.quests.get(2).trainer.name + " rewarded " + player.name + " $" + reward + "!");
            player.quests.remove(2);
            setQuestLog();
        }
        //  battle quest, start battle
        else if (player.quests.get(2).type == 0 || player.quests.get(2).type == 3) {
            if (!player.teamLeader.fainted) {
                player.quests.get(2).trainer.resetTeam();
                player.trainer = player.quests.get(2).trainer;
                questInfo = player.quests.get(2).info;
                mode = 2;
                setBattleScene();
                getBattleScene();
            }
        }
        //  quest incomplete, show info
        else {
            questLabel.setText(player.quests.get(2).info);
        }
    }

    @FXML
    //  player starts wild battle
    protected void onBattleButtonClick() throws IOException {
        if (!player.teamLeader.fainted) {
            setBattleScene();
            getBattleScene();
        }
    }

    @FXML
    //  player enters team organization
    protected void onTeamButtonClick() throws IOException {
        setTeamScene();
        getTeamScene();
    }

    @FXML
    //  player enters bag
    public void onBagButtonClick() throws IOException {
        setBagScene();
        getBagScene();
    }

    @FXML
    //  player enters shop
    public void onShopButtonClick() throws IOException {
        setShopScene();
        getShopScene();
    }

    @FXML
    //  player goes back to title screen
    protected void onTitleButton() throws IOException {
        if (Objects.equals(titleButton.getText(), "Title Screen")) {
            titleButton.setText("Confirm");
        } else {
            player = new Player();
            setTitleScene();
            getTitleScene();
        }
    }
}