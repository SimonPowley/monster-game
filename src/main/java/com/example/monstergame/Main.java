package com.example.monstergame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    Player player;
    Scene titleScene;
    TitleController titleController;



    @Override
    public void start(Stage stage) throws IOException {
        //  create player
        this.player = new Player();
        //  create scenes
        this.titleScene = loadTitleScene();
        //  set player scene to title scene
        player.setScene(titleScene);
        stage.setTitle("Monster Game");
        stage.setScene(player.scene);
        stage.show();
    }

    //  load title scene and controllers
    public Scene loadTitleScene() throws IOException {
        FXMLLoader titleLoader = new FXMLLoader(getClass().getResource("title.fxml"));
        Parent title = titleLoader.load();
        this.titleController = titleLoader.getController();
        this.titleController.setPlayer(this.player);
        return new Scene(title);
    }

    //  start game
    public static void main(String[] args) {
        launch();
    }
}