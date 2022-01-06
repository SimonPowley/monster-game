package com.example.monstergame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TitleController {
    Player player;
    Scene startupScene;
    Scene loadScene;
    @FXML
    private Button newGameButton;
    @FXML
    private Button loadGameButton;
    @FXML
    private Button exitButton;



    //  set player
    public void setPlayer (Player player) {
        this.player = player;
    }

    //  set startup selection scene
    public void setStartupScene() throws IOException {
        startupScene = loadStartupScene();
    }
    //  get startup selection scene
    public void getStartupScene() {
        player.setScene(startupScene);
        Stage stage = (Stage) newGameButton.getScene().getWindow();
        stage.setScene(player.scene);
    }
    //  load starter selection scene
    public Scene loadStartupScene() throws IOException {
        FXMLLoader startupLoader = new FXMLLoader(getClass().getResource("startup.fxml"));
        Parent startup = startupLoader.load();
        StartupController startupController = startupLoader.getController();
        startupController.setPlayer(player);
        return new Scene(startup);
    }

    //  set save selection scene
    public void setLoadScene() throws IOException {
        loadScene = loadLoadScene();
    }
    //  get save selection scene
    public void getLoadScene() {
        player.setScene(loadScene);
        Stage stage = (Stage) loadGameButton.getScene().getWindow();
        stage.setScene(player.scene);
    }
    //  load save selection scene
    public Scene loadLoadScene() throws IOException {
        FXMLLoader loadLoader = new FXMLLoader(getClass().getResource("load.fxml"));
        Parent load = loadLoader.load();
        LoadController loadController = loadLoader.getController();
        loadController.setPlayer(player);
        return new Scene(load);
    }



    @FXML
    //  change to player name and starter selection
    protected void onNewGame() throws IOException {
        setStartupScene();
        getStartupScene();
    }

    @FXML
    //  change to save file selection
    protected void onLoadGame() throws IOException {
        setLoadScene();
        getLoadScene();
    }

    @FXML
    //  player exits game
    protected void onExit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
