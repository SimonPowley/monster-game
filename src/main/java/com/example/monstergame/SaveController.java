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

public class SaveController {
    Player player;
    Player playerSave1;
    Player playerSave2;
    Player playerSave3;
    Scene mainScene;
    @FXML
    private Label saveFile1Label;
    @FXML
    private Button overwrite1Button;
    @FXML
    private Label saveFile2Label;
    @FXML
    private Button overwrite2Button;
    @FXML
    private Label saveFile3Label;
    @FXML
    private Button overwrite3Button;
    @FXML
    private Button backButton;
    @FXML
    private Label gameLogLabel;
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

    //  create types for monster creation
    Type normal = new Type("Normal", 0);
    Type fire = new Type("Fire", 1);
    Type water = new Type("Water", 2);
    Type earth = new Type("Earth", 3);
    Type electric = new Type("Electric", 4);
    Type nature = new Type("Nature", 5);
    Type wind = new Type("Wind", 6);
    Type ice = new Type("Ice", 7);



    //  set player
    public void setPlayer (Player player) {
        this.player = player;
        this.player.setLeader();
        playerSave1 = null;
        playerSave2 = null;
        playerSave3 = null;
        setPlayerSaves();
        loadPlayerMonsterTypes();
        setSaveCircles();
        gameLogLabel.setText("Select save slot");
        disableOverwriteButtons();
    }

    //  show info for save slots
    public void setPlayerSaves() {
        loadPlayerSave1();
        loadPlayerSave2();
        loadPlayerSave3();
        if (playerSave1 != null) {
            saveFile1Label.setText(playerSave1.name + "\tScore: " + playerSave1.score);
        } else {
            saveFile1Label.setText("Save slot empty");
        }
        if (playerSave2 != null) {
            saveFile2Label.setText(playerSave2.name + "\tScore: " + playerSave2.score);
        } else {
            saveFile2Label.setText("Save slot empty");
        }
        if (playerSave3 != null) {
            saveFile3Label.setText(playerSave3.name + "\tScore: " + playerSave3.score);
        } else {
            saveFile3Label.setText("Save slot empty");
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

    //  load player's monster types for each save file
    public void loadPlayerMonsterTypes() {
        if (playerSave1 != null) {
            //  set player save 1 monster types
            for (int i = 0; i < playerSave1.pc.size; i++) {
                //  set normal types
                if (Objects.equals(playerSave1.pc.get(i).typeName, "Normal")) {
                    playerSave1.pc.get(i).setType(normal);
                }
                //  set fire types
                if (Objects.equals(playerSave1.pc.get(i).typeName, "Fire")) {
                    playerSave1.pc.get(i).setType(fire);
                }
                //  set water types
                if (Objects.equals(playerSave1.pc.get(i).typeName, "Water")) {
                    playerSave1.pc.get(i).setType(water);
                }
                //  set earth types
                if (Objects.equals(playerSave1.pc.get(i).typeName, "Earth")) {
                    playerSave1.pc.get(i).setType(earth);
                }
                //  set electric types
                if (Objects.equals(playerSave1.pc.get(i).typeName, "Electric")) {
                    playerSave1.pc.get(i).setType(electric);
                }
                //  set nature types
                if (Objects.equals(playerSave1.pc.get(i).typeName, "Nature")) {
                    playerSave1.pc.get(i).setType(nature);
                }
                //  set wind types
                if (Objects.equals(playerSave1.pc.get(i).typeName, "Wind")) {
                    playerSave1.pc.get(i).setType(wind);
                }
                //  set ice types
                if (Objects.equals(playerSave1.pc.get(i).typeName, "Ice")) {
                    playerSave1.pc.get(i).setType(ice);
                }
            }
        }
        if (playerSave2 != null) {
            //  set player save 2 monster types
            for (int i = 0; i < playerSave2.pc.size; i++) {
                //  set normal types
                if (Objects.equals(playerSave2.pc.get(i).typeName, "Normal")) {
                    playerSave2.pc.get(i).setType(normal);
                }
                //  set fire types
                if (Objects.equals(playerSave2.pc.get(i).typeName, "Fire")) {
                    playerSave2.pc.get(i).setType(fire);
                }
                //  set water types
                if (Objects.equals(playerSave2.pc.get(i).typeName, "Water")) {
                    playerSave2.pc.get(i).setType(water);
                }
                //  set earth types
                if (Objects.equals(playerSave2.pc.get(i).typeName, "Earth")) {
                    playerSave2.pc.get(i).setType(earth);
                }
                //  set electric types
                if (Objects.equals(playerSave2.pc.get(i).typeName, "Electric")) {
                    playerSave2.pc.get(i).setType(electric);
                }
                //  set nature types
                if (Objects.equals(playerSave2.pc.get(i).typeName, "Nature")) {
                    playerSave2.pc.get(i).setType(nature);
                }
                //  set wind types
                if (Objects.equals(playerSave2.pc.get(i).typeName, "Wind")) {
                    playerSave2.pc.get(i).setType(wind);
                }
                //  set ice types
                if (Objects.equals(playerSave2.pc.get(i).typeName, "Ice")) {
                    playerSave2.pc.get(i).setType(ice);
                }
            }
        }
        if (playerSave3 != null) {
            //  set player save 3 monster types
            for (int i = 0; i < playerSave3.pc.size; i++) {
                //  set normal types
                if (Objects.equals(playerSave3.pc.get(i).typeName, "Normal")) {
                    playerSave3.pc.get(i).setType(normal);
                }
                //  set fire types
                if (Objects.equals(playerSave3.pc.get(i).typeName, "Fire")) {
                    playerSave3.pc.get(i).setType(fire);
                }
                //  set water types
                if (Objects.equals(playerSave3.pc.get(i).typeName, "Water")) {
                    playerSave3.pc.get(i).setType(water);
                }
                //  set earth types
                if (Objects.equals(playerSave3.pc.get(i).typeName, "Earth")) {
                    playerSave3.pc.get(i).setType(earth);
                }
                //  set electric types
                if (Objects.equals(playerSave3.pc.get(i).typeName, "Electric")) {
                    playerSave3.pc.get(i).setType(electric);
                }
                //  set nature types
                if (Objects.equals(playerSave3.pc.get(i).typeName, "Nature")) {
                    playerSave3.pc.get(i).setType(nature);
                }
                //  set wind types
                if (Objects.equals(playerSave3.pc.get(i).typeName, "Wind")) {
                    playerSave3.pc.get(i).setType(wind);
                }
                //  set ice types
                if (Objects.equals(playerSave3.pc.get(i).typeName, "Ice")) {
                    playerSave3.pc.get(i).setType(ice);
                }
            }
        }
    }
    //  set circles for save slot teams
    public void setSaveCircles() {
        //  show save 1's team
        if (playerSave1 != null) {
            //  monster in slot 1
            if (playerSave1.pc.get(0) != null) {
                save1Circle1.setVisible(true);
                save1Circle1.setFill(playerSave1.pc.get(0).type.color);
            } else {
                save1Circle1.setVisible(false);
            }
            //  monster in slot 2
            if (playerSave1.pc.get(1) != null) {
                save1Circle2.setVisible(true);
                save1Circle2.setFill(playerSave1.pc.get(1).type.color);
            } else {
                save1Circle2.setVisible(false);
            }
            //  monster in slot 3
            if (playerSave1.pc.get(2) != null) {
                save1Circle3.setVisible(true);
                save1Circle3.setFill(playerSave1.pc.get(2).type.color);
            } else {
                save1Circle3.setVisible(false);
            }
            //  monster in slot 4
            if (playerSave1.pc.get(3) != null) {
                save1Circle4.setVisible(true);
                save1Circle4.setFill(playerSave1.pc.get(3).type.color);
            } else {
                save1Circle4.setVisible(false);
            }
            //  monster in slot 5
            if (playerSave1.pc.get(4) != null) {
                save1Circle5.setVisible(true);
                save1Circle5.setFill(playerSave1.pc.get(4).type.color);
            } else {
                save1Circle5.setVisible(false);
            }
            //  monster in slot 6
            if (playerSave1.pc.get(5) != null) {
                save1Circle6.setVisible(true);
                save1Circle6.setFill(playerSave1.pc.get(5).type.color);
            } else {
                save1Circle6.setVisible(false);
            }
        } else {
            save1Circle1.setVisible(false);
            save1Circle2.setVisible(false);
            save1Circle3.setVisible(false);
            save1Circle4.setVisible(false);
            save1Circle5.setVisible(false);
            save1Circle6.setVisible(false);
        }
        //  show save 2's team
        if (playerSave2 != null) {
            //  monster in slot 1
            if (playerSave2.pc.get(0) != null) {
                save2Circle1.setVisible(true);
                save2Circle1.setFill(playerSave2.pc.get(0).type.color);
            } else {
                save2Circle1.setVisible(false);
            }
            //  monster in slot 2
            if (playerSave2.pc.get(1) != null) {
                save2Circle2.setVisible(true);
                save2Circle2.setFill(playerSave2.pc.get(1).type.color);
            } else {
                save2Circle2.setVisible(false);
            }
            //  monster in slot 3
            if (playerSave2.pc.get(2) != null) {
                save2Circle3.setVisible(true);
                save2Circle3.setFill(playerSave2.pc.get(2).type.color);
            } else {
                save2Circle3.setVisible(false);
            }
            //  monster in slot 4
            if (playerSave2.pc.get(3) != null) {
                save2Circle4.setVisible(true);
                save2Circle4.setFill(playerSave2.pc.get(3).type.color);
            } else {
                save2Circle4.setVisible(false);
            }
            //  monster in slot 5
            if (playerSave2.pc.get(4) != null) {
                save2Circle5.setVisible(true);
                save2Circle5.setFill(playerSave2.pc.get(4).type.color);
            } else {
                save2Circle5.setVisible(false);
            }
            //  monster in slot 6
            if (playerSave2.pc.get(5) != null) {
                save2Circle6.setVisible(true);
                save2Circle6.setFill(playerSave2.pc.get(5).type.color);
            } else {
                save2Circle6.setVisible(false);
            }
        } else {
            save2Circle1.setVisible(false);
            save2Circle2.setVisible(false);
            save2Circle3.setVisible(false);
            save2Circle4.setVisible(false);
            save2Circle5.setVisible(false);
            save2Circle6.setVisible(false);
        }
        //  show save 3's team
        if (playerSave3 != null) {
            //  monster in slot 1
            if (playerSave3.pc.get(0) != null) {
                save3Circle1.setVisible(true);
                save3Circle1.setFill(playerSave3.pc.get(0).type.color);
            } else {
                save3Circle1.setVisible(false);
            }
            //  monster in slot 2
            if (playerSave3.pc.get(1) != null) {
                save3Circle2.setVisible(true);
                save3Circle2.setFill(playerSave3.pc.get(1).type.color);
            } else {
                save3Circle2.setVisible(false);
            }
            //  monster in slot 3
            if (playerSave3.pc.get(2) != null) {
                save3Circle3.setVisible(true);
                save3Circle3.setFill(playerSave3.pc.get(2).type.color);
            } else {
                save3Circle3.setVisible(false);
            }
            //  monster in slot 4
            if (playerSave3.pc.get(3) != null) {
                save3Circle4.setVisible(true);
                save3Circle4.setFill(playerSave3.pc.get(3).type.color);
            } else {
                save3Circle4.setVisible(false);
            }
            //  monster in slot 5
            if (playerSave3.pc.get(4) != null) {
                save3Circle5.setVisible(true);
                save3Circle5.setFill(playerSave3.pc.get(4).type.color);
            } else {
                save3Circle5.setVisible(false);
            }
            //  monster in slot 6
            if (playerSave3.pc.get(5) != null) {
                save3Circle6.setVisible(true);
                save3Circle6.setFill(playerSave3.pc.get(5).type.color);
            } else {
                save3Circle6.setVisible(false);
            }
        } else {
            save3Circle1.setVisible(false);
            save3Circle2.setVisible(false);
            save3Circle3.setVisible(false);
            save3Circle4.setVisible(false);
            save3Circle5.setVisible(false);
            save3Circle6.setVisible(false);
        }
    }

    //  hide confirmation buttons for save overwrite
    public void disableOverwriteButtons() {
        overwrite1Button.setVisible(false);
        overwrite2Button.setVisible(false);
        overwrite3Button.setVisible(false);
    }

    //  show specific overwrite confirmation button
    public void showOverwrite1Button() {
        //  show 1, hide 2 and 3
        overwrite2Button.setVisible(false);
        overwrite3Button.setVisible(false);
        overwrite1Button.setVisible(true);
    }
    public void showOverwrite2Button() {
        //  show 2, hide 1 and 3
        overwrite1Button.setVisible(false);
        overwrite3Button.setVisible(false);
        overwrite2Button.setVisible(true);
    }
    public void showOverwrite3Button() {
        //  show 3, hide 1 and 2
        overwrite1Button.setVisible(false);
        overwrite2Button.setVisible(false);
        overwrite3Button.setVisible(true);
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
        return new Scene(main);
    }



    @FXML
    //  change to main scene
    protected void onBackButton() throws IOException {
        setMainScene();
        getMainScene();
    }

    @FXML
    //  show confirmation button for save in slot 1
    protected void onSave1Button() {
        showOverwrite1Button();
    }
    @FXML
    //  save current player's data to save slot 1
    protected void onOverwrite1Button() {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/main/saves/player1.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(player);
            out.close();
            fileOut.close();
            System.out.print("Player save data stored in src/main/saves/player1.ser\n");
        } catch (IOException i) {
            i.printStackTrace();
        }
        setPlayer(player);
        gameLogLabel.setText("Saved game!");
    }

    @FXML
    //  show confirmation button for save in slot 2
    protected void onSave2Button() {
        showOverwrite2Button();
    }
    @FXML
    //  save current player's data to save slot 2
    protected void onOverwrite2Button() {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/main/saves/player2.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(player);
            out.close();
            fileOut.close();
            System.out.print("Player save data stored in src/main/saves/player2.ser\n");
        } catch (IOException i) {
            i.printStackTrace();
        }
        setPlayer(player);
        gameLogLabel.setText("Saved game!");
    }

    @FXML
    //  show confirmation button for save in slot 3
    protected void onSave3Button() {
        showOverwrite3Button();
    }
    @FXML
    //  save current player's data to save slot 3
    protected void onOverwrite3Button() {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/main/saves/player3.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(player);
            out.close();
            fileOut.close();
            System.out.print("Player save data stored in src/main/saves/player3.ser\n");
        } catch (IOException i) {
            i.printStackTrace();
        }
        setPlayer(player);
        gameLogLabel.setText("Saved game!");
    }
}
