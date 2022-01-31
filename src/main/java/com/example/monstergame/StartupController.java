package com.example.monstergame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StartupController {
    Player player;
    private Scene starterScene;
    @FXML
    private TextField playerNameTextField;


    //  set player
    public void setPlayer (Player player) {
        this.player = player;
    }

    //  set starter selection scene
    public void setStarterScene() throws IOException {
        starterScene = loadStarterScene();
    }
    //  get starter selection scene
    public void getStarterScene() {
        player.setScene(starterScene);
        Stage stage = (Stage) playerNameTextField.getScene().getWindow();
        stage.setScene(player.scene);
    }
    //  load starter selection scene
    public Scene loadStarterScene() throws IOException {
        FXMLLoader starterLoader = new FXMLLoader(getClass().getResource("starter.fxml"));
        Parent starter = starterLoader.load();
        StarterController starterController = starterLoader.getController();
        starterController.setPlayer(player);
        starter.getStylesheets().add(Objects.requireNonNull(getClass().getResource("button.css")).toExternalForm());
        return new Scene(starter);
    }



    @FXML
    //  change to starter selection scene once player enters name
    public void onPlayerNameEntered() throws IOException {
        setStarterScene();
        player.setName(playerNameTextField.getText());
        getStarterScene();
    }
}
