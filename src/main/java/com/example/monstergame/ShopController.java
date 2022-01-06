package com.example.monstergame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ShopController {
    Player player;
    Scene mainScene;
    @FXML
    private Button exitButton;
    @FXML
    private Label playerMoneyLabel;
    @FXML
    private Label shopLogLabel;
    @FXML
    private Label potionPriceLabel;
    @FXML
    private Label potionBagLabel;
    @FXML
    private Label ballPriceLabel;
    @FXML
    private Label ballBagLabel;
    @FXML
    private Label revivePriceLabel;
    @FXML
    private Label reviveBagLabel;



    //  set player
    public void setPlayer(Player player) {
        this.player = player;
        this.player.setLeader();
        setBuyPrices();
        setInBag();
    }

    //  update buy prices for shop items
    public void setBuyPrices() {
        potionPriceLabel.setText("$" + player.bag.get(0).buyPrice);
        ballPriceLabel.setText("$" + player.bag.get(1).buyPrice);
        revivePriceLabel.setText("$" + player.bag.get(2).buyPrice);
    }
    
    //  update player money and items in bag
    public void setInBag() {
        playerMoneyLabel.setText("$" + player.money);
        potionBagLabel.setText(player.potionsInBag + " in bag");
        ballBagLabel.setText(player.ballsInBag + " in bag");
        reviveBagLabel.setText(player.revivesInBag + " in bag");
    }

    //  set main menu scene
    public void setMainScene() throws IOException {
        mainScene = loadMainScene();
    }
    //  get main menu scene
    public void getMainScene() {
        player.setScene(mainScene);
        Stage stage = (Stage) exitButton.getScene().getWindow();
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
    //  player purchases a potion
    public void onPotionRectangleClick() {
        if (player.money < player.bag.get(0).buyPrice) {
            shopLogLabel.setText("Not enough money!");
        } else {
            shopLogLabel.setText("Bought a " + player.bag.get(0).name + " for $" + player.bag.get(0).buyPrice + "!");
            player.money -= player.bag.get(0).buyPrice;
            player.bag.get(0).addItem(1);
            player.potionsInBag++;
            setInBag();
        }
    }

    @FXML
    //  player purchases a ball
    public void onBallRectangleClick() {
        if (player.money < player.bag.get(1).buyPrice) {
            shopLogLabel.setText("Not enough money!");
        } else {
            shopLogLabel.setText("Bought a " + player.bag.get(1).name + " for $" + player.bag.get(1).buyPrice + "!");
            player.money -= player.bag.get(1).buyPrice;
            player.bag.get(1).addItem(1);
            player.ballsInBag++;
        }
        setInBag();
    }

    @FXML
    //  player purchases a revive
    public void onReviveRectangleClick() {
        if (player.money < player.bag.get(2).buyPrice) {
            shopLogLabel.setText("Not enough money!");
        }
        else {
            shopLogLabel.setText("Bought a " + player.bag.get(2).name + " for $" + player.bag.get(2).buyPrice + "!");
            player.money -= player.bag.get(2).buyPrice;
            player.bag.get(2).addItem(1);
            player.revivesInBag++;
            setInBag();
        }
    }

    @FXML
    //  player exits shop
    public void onExitButtonClicked() throws IOException {
        setMainScene();
        getMainScene();
    }
}
