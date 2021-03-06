package com.example.monstergame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;

public class LoadController {
    Player player;
    Player playerSave1;
    Player playerSave2;
    Player playerSave3;
    Scene mainScene;
    Scene titleScene;
    @FXML
    protected Label saveFile1Label;
    @FXML
    protected Button loadSave1Button;
    @FXML
    protected Button deleteSave1Button;
    @FXML
    protected Label saveFile2Label;
    @FXML
    protected Button loadSave2Button;
    @FXML
    protected Button deleteSave2Button;
    @FXML
    protected Label saveFile3Label;
    @FXML
    protected Button loadSave3Button;
    @FXML
    protected Button deleteSave3Button;
    @FXML
    protected Circle save1Circle1;
    @FXML
    protected Circle save1Circle2;
    @FXML
    protected Circle save1Circle3;
    @FXML
    protected Circle save1Circle4;
    @FXML
    protected Circle save1Circle5;
    @FXML
    protected Circle save1Circle6;
    @FXML
    protected Circle save2Circle1;
    @FXML
    protected Circle save2Circle2;
    @FXML
    protected Circle save2Circle3;
    @FXML
    protected Circle save2Circle4;
    @FXML
    protected Circle save2Circle5;
    @FXML
    protected Circle save2Circle6;
    @FXML
    protected Circle save3Circle1;
    @FXML
    protected Circle save3Circle2;
    @FXML
    protected Circle save3Circle3;
    @FXML
    protected Circle save3Circle4;
    @FXML
    protected Circle save3Circle5;
    @FXML
    protected Circle save3Circle6;
    @FXML
    protected Button backButton;



    //  set player
    public void setPlayer (Player player) {
        this.player = player;
        playerSave1 = null;
        playerSave2 = null;
        playerSave3 = null;
        setPlayerSaves();
        loadPlayerMonsterTypes();
        setSaveCircles();
    }

    //  set info for save slots
    public void setPlayerSaves() {
        loadPlayerSave1();
        loadPlayerSave2();
        loadPlayerSave3();
        if (playerSave1 != null) {
            saveFile1Label.setText(playerSave1.name + "\tScore: " + playerSave1.score);
        } else {
            saveFile1Label.setText("Save slot empty");
            hideSaveSlot(1);
        }
        if (playerSave2 != null) {
            saveFile2Label.setText(playerSave2.name + "\tScore: " + playerSave2.score);
        } else {
            saveFile2Label.setText("Save slot empty");
            hideSaveSlot(2);
        }
        if (playerSave3 != null) {
            saveFile3Label.setText(playerSave3.name + "\tScore: " + playerSave3.score);
        } else {
            saveFile3Label.setText("Save slot empty");
            hideSaveSlot(3);
        }
    }
    //  set circles for save slot teams
    public void setSaveCircles() {
        setSave1Circles();
        setSave2Circles();
        setSave3Circles();
    }
    public void setSave1Circles() {
        //  show save 1's team
        if (playerSave1 == null) {
            save1Circle1.setVisible(false);
            save1Circle2.setVisible(false);
            save1Circle3.setVisible(false);
            save1Circle4.setVisible(false);
            save1Circle5.setVisible(false);
            save1Circle6.setVisible(false);
        } else {
            //  monster in slot 1
            save1Circle1.setVisible(true);
            save1Circle1.setFill(playerSave1.pc.get(0).type.color);
            playerSave1.pc.get(0).moves.reloadTypes();
            //  monster in slot 2
            if (playerSave1.pc.size() > 1) {
                save1Circle2.setVisible(true);
                save1Circle2.setFill(playerSave1.pc.get(1).type.color);
                playerSave1.pc.get(1).moves.reloadTypes();
            } else {
                save1Circle2.setVisible(false);
                save1Circle3.setVisible(false);
                save1Circle4.setVisible(false);
                save1Circle5.setVisible(false);
                save1Circle6.setVisible(false);
            }
            //  monster in slot 3
            if (playerSave1.pc.size() > 2) {
                save1Circle3.setVisible(true);
                save1Circle3.setFill(playerSave1.pc.get(2).type.color);
                playerSave1.pc.get(2).moves.reloadTypes();
            } else {
                save1Circle3.setVisible(false);
                save1Circle4.setVisible(false);
                save1Circle5.setVisible(false);
                save1Circle6.setVisible(false);
            }
            //  monster in slot 4
            if (playerSave1.pc.size() > 3) {
                save1Circle4.setVisible(true);
                save1Circle4.setFill(playerSave1.pc.get(3).type.color);
                playerSave1.pc.get(3).moves.reloadTypes();
            } else {
                save1Circle4.setVisible(false);
                save1Circle5.setVisible(false);
                save1Circle6.setVisible(false);
            }
            //  monster in slot 5
            if (playerSave1.pc.size() > 4) {
                save1Circle5.setVisible(true);
                save1Circle5.setFill(playerSave1.pc.get(4).type.color);
                playerSave1.pc.get(4).moves.reloadTypes();
            } else {
                save1Circle5.setVisible(false);
                save1Circle6.setVisible(false);
            }
            //  monster in slot 6
            if (playerSave1.pc.size() > 5) {
                save1Circle6.setVisible(true);
                save1Circle6.setFill(playerSave1.pc.get(5).type.color);
                playerSave1.pc.get(5).moves.reloadTypes();
            } else {
                save1Circle6.setVisible(false);
            }
        }
    }
    public void setSave2Circles() {
        //  show save 2's team
        if (playerSave2 == null) {
            save2Circle1.setVisible(false);
            save2Circle2.setVisible(false);
            save2Circle3.setVisible(false);
            save2Circle4.setVisible(false);
            save2Circle5.setVisible(false);
            save2Circle6.setVisible(false);
        } else {
            //  monster in slot 1
            save2Circle1.setVisible(true);
            save2Circle1.setFill(playerSave2.pc.get(0).type.color);
            playerSave1.pc.get(0).moves.reloadTypes();
            //  monster in slot 2
            if (playerSave2.pc.size() > 1) {
                save2Circle2.setVisible(true);
                save2Circle2.setFill(playerSave2.pc.get(1).type.color);
                playerSave1.pc.get(1).moves.reloadTypes();
            } else {
                save2Circle2.setVisible(false);
                save2Circle3.setVisible(false);
                save2Circle4.setVisible(false);
                save2Circle5.setVisible(false);
                save2Circle6.setVisible(false);
            }
            //  monster in slot 3
            if (playerSave2.pc.size() > 2) {
                save2Circle3.setVisible(true);
                save2Circle3.setFill(playerSave2.pc.get(2).type.color);
                playerSave1.pc.get(2).moves.reloadTypes();
            } else {
                save2Circle3.setVisible(false);
                save2Circle4.setVisible(false);
                save2Circle5.setVisible(false);
                save2Circle6.setVisible(false);
            }
            //  monster in slot 4
            if (playerSave2.pc.size() > 3) {
                save2Circle4.setVisible(true);
                save2Circle4.setFill(playerSave2.pc.get(3).type.color);
                playerSave1.pc.get(3).moves.reloadTypes();
            } else {
                save2Circle4.setVisible(false);
                save2Circle5.setVisible(false);
                save2Circle6.setVisible(false);
            }
            //  monster in slot 5
            if (playerSave2.pc.size() > 4) {
                save2Circle5.setVisible(true);
                save2Circle5.setFill(playerSave2.pc.get(4).type.color);
                playerSave1.pc.get(4).moves.reloadTypes();
            } else {
                save2Circle5.setVisible(false);
                save2Circle6.setVisible(false);
            }
            //  monster in slot 6
            if (playerSave2.pc.size() > 5) {
                save2Circle6.setVisible(true);
                save2Circle6.setFill(playerSave2.pc.get(5).type.color);
                playerSave1.pc.get(5).moves.reloadTypes();
            } else {
                save2Circle6.setVisible(false);
            }
        }
    }
    public void setSave3Circles() {
        //  show save 3's team
        if (playerSave3 == null) {
            save3Circle1.setVisible(false);
            save3Circle2.setVisible(false);
            save3Circle3.setVisible(false);
            save3Circle4.setVisible(false);
            save3Circle5.setVisible(false);
            save3Circle6.setVisible(false);
        } else {
            //  monster in slot 1
            save3Circle1.setVisible(true);
            save3Circle1.setFill(playerSave3.pc.get(0).type.color);
            playerSave1.pc.get(0).moves.reloadTypes();
            //  monster in slot 2
            if (playerSave3.pc.size() > 1) {
                save3Circle2.setVisible(true);
                save3Circle2.setFill(playerSave3.pc.get(1).type.color);
                playerSave1.pc.get(1).moves.reloadTypes();
            } else {
                save3Circle2.setVisible(false);
                save3Circle3.setVisible(false);
                save3Circle4.setVisible(false);
                save3Circle5.setVisible(false);
                save3Circle6.setVisible(false);
            }
            //  monster in slot 3
            if (playerSave3.pc.size() > 2) {
                save3Circle3.setVisible(true);
                save3Circle3.setFill(playerSave3.pc.get(2).type.color);
                playerSave1.pc.get(2).moves.reloadTypes();
            } else {
                save3Circle3.setVisible(false);
                save3Circle4.setVisible(false);
                save3Circle5.setVisible(false);
                save3Circle6.setVisible(false);
            }
            //  monster in slot 4
            if (playerSave3.pc.size() > 3) {
                save3Circle4.setVisible(true);
                save3Circle4.setFill(playerSave3.pc.get(3).type.color);
                playerSave1.pc.get(3).moves.reloadTypes();
            } else {
                save3Circle4.setVisible(false);
                save3Circle5.setVisible(false);
                save3Circle6.setVisible(false);
            }
            //  monster in slot 5
            if (playerSave3.pc.size() > 4) {
                save3Circle5.setVisible(true);
                save3Circle5.setFill(playerSave3.pc.get(4).type.color);
                playerSave1.pc.get(4).moves.reloadTypes();
            } else {
                save3Circle5.setVisible(false);
                save3Circle6.setVisible(false);
            }
            //  monster in slot 6
            if (playerSave3.pc.size() > 5) {
                save3Circle6.setVisible(true);
                save3Circle6.setFill(playerSave3.pc.get(5).type.color);
                playerSave1.pc.get(5).moves.reloadTypes();
            } else {
                save3Circle6.setVisible(false);
            }
        }
    }
    //  hide save slot if empty
    public void hideSaveSlot(int slot) {
        //  hide save slot 1
        if (slot == 1) {
            save1Circle1.setVisible(false);
            save1Circle2.setVisible(false);
            save1Circle3.setVisible(false);
            save1Circle4.setVisible(false);
            save1Circle5.setVisible(false);
            save1Circle6.setVisible(false);
            loadSave1Button.setVisible(false);
            deleteSave1Button.setVisible(false);
        } else if (slot == 2) {
            save2Circle1.setVisible(false);
            save2Circle2.setVisible(false);
            save2Circle3.setVisible(false);
            save2Circle4.setVisible(false);
            save2Circle5.setVisible(false);
            save2Circle6.setVisible(false);
            loadSave2Button.setVisible(false);
            deleteSave2Button.setVisible(false);
        } else if (slot == 3) {
            save3Circle1.setVisible(false);
            save3Circle2.setVisible(false);
            save3Circle3.setVisible(false);
            save3Circle4.setVisible(false);
            save3Circle5.setVisible(false);
            save3Circle6.setVisible(false);
            loadSave3Button.setVisible(false);
            deleteSave3Button.setVisible(false);
        }
    }

    //  load player data from save slot 1 serialization
    public void loadPlayerSave1() {
        try {
            FileInputStream fileIn = new FileInputStream("src/main/saves/player1.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            playerSave1 = (Player) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i){
            i.printStackTrace();
            playerSave1 = null;
        } catch (ClassNotFoundException c) {
            System.out.println("Player 1 save data not found!\n");
            c.printStackTrace();
            playerSave1 = null;
        }
    }
    //  load player data from save slot 2 serialization
    public void loadPlayerSave2() {
        try {
            FileInputStream fileIn = new FileInputStream("src/main/saves/player2.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            playerSave2 = (Player) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i){
            i.printStackTrace();
            playerSave2 = null;
        } catch (ClassNotFoundException c) {
            System.out.println("Player 2 save data not found!\n");
            c.printStackTrace();
            playerSave2 = null;
        }
    }
    //  load player data from save slot 3 serialization
    public void loadPlayerSave3() {
        try {
            FileInputStream fileIn = new FileInputStream("src/main/saves/player3.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            playerSave3 = (Player) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i){
            i.printStackTrace();
            playerSave3 = null;
        } catch (ClassNotFoundException c) {
            System.out.println("Player 2 save data not found!\n");
            c.printStackTrace();
            playerSave3 = null;
        }
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
        main.getStylesheets().add(Objects.requireNonNull(getClass().getResource("button.css")).toExternalForm());
        return new Scene(main);
    }

    //  set title scene
    public void setTitleScene() throws IOException {
        titleScene = loadTitleScene();
    }
    //  get title scene
    public void getTitleScene() {
        player.setScene(titleScene);
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(player.scene);
    }
    //  load main menu scene
    public Scene loadTitleScene() throws IOException {
        FXMLLoader titleLoader = new FXMLLoader(getClass().getResource("title.fxml"));
        Parent title = titleLoader.load();
        TitleController titleController = titleLoader.getController();
        titleController.setPlayer(player);
        title.getStylesheets().add(Objects.requireNonNull(getClass().getResource("button.css")).toExternalForm());
        return new Scene(title);
    }

    //  load player's monster types for each save file
    public void loadPlayerMonsterTypes() {
        //  set player save 1 monster types
        if (playerSave1 != null) {
            playerSave1.reloadTypes();
        }
        //  set player save 2 monster types
        if (playerSave2 != null) {
            playerSave2.reloadTypes();
        }
        //  set player save 3 monster types
        if (playerSave3 != null) {
            playerSave3.reloadTypes();
        }
    }
    //  load trainer's monster types for quests
    public void loadTrainerMonsterTypes() {
        for (Quest quest : player.quests) {
            if (quest.trainer != null) {
                quest.trainer.reloadTypes();
            }
        }
    }
    //  load boss monster types for quests
    public void loadBossMonsterTypes() {
        for (Quest quest : player.quests) {
            if (quest.bossMonster != null) {
                quest.bossMonster.reloadTypes();
            }
        }
    }
    //  load enemy trainer teams for battle quests
    public void loadQuestMonsterTypes() {
        for (Quest quest : player.quests) {
            //  if quest is a trainer battle
            if (quest.type == 0 || quest.type == 4) {
                loadTrainerMonsterTypes();
            }
            //  if quest is a boss battle
            if (quest.type == 3) {
                loadBossMonsterTypes();
            }
        }
    }

    //  show confirm button when deleting a save file
    public void deleteConfirmation(int saveNum) {
        if (saveNum == 1) {
            deleteSave1Button.setText("Confirm");
            deleteSave2Button.setText("Delete");
            deleteSave3Button.setText("Delete");
        } else if (saveNum == 2) {
            deleteSave1Button.setText("Delete");
            deleteSave2Button.setText("Confirm");
            deleteSave3Button.setText("Delete");
        } else if (saveNum == 3) {
            deleteSave1Button.setText("Delete");
            deleteSave2Button.setText("Delete");
            deleteSave3Button.setText("Confirm");
        }
    }



    @FXML
    //  load player data from save slot 1 serialization
    public void loadSave1() throws IOException {
        if (playerSave1 != null) {
            player = playerSave1;
            loadQuestMonsterTypes();
            setMainScene();
            getMainScene();
        }
    }
    @FXML
    //  delete data from save slot 1 (save null player in slot 1)
    public void deleteSave1() {
        if (Objects.equals(deleteSave1Button.getText(), "Confirm")) {
            if (playerSave1 != null) {
                try {
                    FileOutputStream fileOut = new FileOutputStream("src/main/saves/player1.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(null);
                    out.close();
                    fileOut.close();
                    System.out.print("Player save data deleted in src/main/saves/player1.ser\n");
                } catch (IOException i) {
                    i.printStackTrace();
                }
                setPlayer(player);
                deleteSave1Button.setText("Delete");
            }
        } else {
            if (playerSave1 != null) {
                deleteConfirmation(1);
            }
        }
    }

    @FXML
    //  load player data from save slot 2 serialization
    public void loadSave2() throws IOException {
        if (playerSave2 != null) {
            player = playerSave2;
            loadQuestMonsterTypes();
            setMainScene();
            getMainScene();
        }
    }
    @FXML
    //  delete data from save slot 2 (save null player in slot 2)
    public void deleteSave2() {
        if (Objects.equals(deleteSave2Button.getText(), "Confirm")) {
            if (playerSave2 != null) {
                try {
                    FileOutputStream fileOut = new FileOutputStream("src/main/saves/player2.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(null);
                    out.close();
                    fileOut.close();
                    System.out.print("Player save data deleted in src/main/saves/player2.ser\n");
                } catch (IOException i) {
                    i.printStackTrace();
                }
                setPlayer(player);
                deleteSave2Button.setText("Delete");
            }
        } else {
            if (playerSave2 != null) {
                deleteConfirmation(2);
            }
        }
    }

    @FXML
    //  load player data from save slot 3 serialization
    public void loadSave3() throws IOException {
        if (playerSave3 != null) {
            player = playerSave3;
            loadQuestMonsterTypes();
            setMainScene();
            getMainScene();
        }
    }
    @FXML
    //  delete data from save slot 3 (save null player in slot 3)
    public void deleteSave3() {
        if (Objects.equals(deleteSave3Button.getText(), "Confirm")) {
            if (playerSave3 != null) {
                try {
                    FileOutputStream fileOut = new FileOutputStream("src/main/saves/player3.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(null);
                    out.close();
                    fileOut.close();
                    System.out.print("Player save data deleted in src/main/saves/player3.ser\n");
                } catch (IOException i) {
                    i.printStackTrace();
                }
                setPlayer(player);
                deleteSave3Button.setText("Delete");
            }
        } else {
            if (playerSave3 != null) {
                deleteConfirmation(3);
            }
        }
    }

    @FXML
    //  change scene to title scene
    public void onBackButton() throws IOException {
        setTitleScene();
        getTitleScene();
    }
}
