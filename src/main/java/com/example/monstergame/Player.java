package com.example.monstergame;

import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.util.Random;

public class Player implements java.io.Serializable {
    //  gui stuff
    transient Scene scene;
    //  player attributes
    String name;
    int score;
    int money;
    boolean inGame;
    boolean inBattle;
    //  player monster storage
    StorageList<Monster> pc = new StorageList<>();
    Monster teamLeader; // first monster sent into battle
    int pcSizeLimit;
    int highestLevel;
    //  player item storage
    StorageList<Item> bag = new StorageList<>();
    Item itemInUse;
    int potionsInBag;
    int ballsInBag;
    int revivesInBag;
    //  player quest storage
    StorageList<Quest> quests = new StorageList<>();
    Trainer trainer;
    int questLimit;
    int monstersBeaten;
    int monstersCaught;



    //  create a new player
    public Player() {
        //  add starting items to bag
        Item potion = new Item("Potion", true, false, false, 0, 300, 150, 5);
        Item ball = new Item("Ball", false, true, false, 0, 200, 100, 0);
        Item revive = new Item("Revive", false, false, true, 0, 1000, 500, 0);
        bag.add(potion.name, potion);
        bag.add(ball.name, ball);
        bag.add(revive.name, revive);
        potionsInBag = potion.amount;
        ballsInBag = ball.amount;
        revivesInBag = revive.amount;
        //  initialize other stuff
        questLimit = 3;
        monstersBeaten = 0;
        monstersCaught = 0;
        setMoney(1000);
        setScore(0);
        setPcSizeLimit(1);
        setHighestLevel();
        inGame = true;
        inBattle = false;
        trainer = null;
    }

    //  set player name
    public void setName(String name) {
        this.name = name;
    }
    //  set player money
    public void setMoney(int money) {
        this.money = money;
    }
    //  set player pc size limit
    public void setPcSizeLimit(int size) {
        this.pcSizeLimit = size;
    }
    //  set player score
    public void setScore(int score) {
        this.score = score;
        addPcStorage();
    }
    //  set player's highest level
    public void setHighestLevel() {
        highestLevel = 0;
        for (int i = 0; i < pc.size(); i++) {
            if (pc.get(i).level > highestLevel) {
                highestLevel = pc.get(i).level;
            }
        }
        addPcStorage();
    }

    //  add to monsters beaten total
    public void addMonstersBeaten() {
        monstersBeaten++;
    }
    //  add to monsters caught total
    public void addMonstersCaught() {
        monstersCaught++;
    }

    //  set player scene
    public void setScene(Scene scene) {
        this.scene = scene;
        setLeader();
    }

    //  roll for chance to add a new quest to quest list after defeating wild monster
    public void questRoll() {
        Random rand = new Random();
        if (rand.nextInt(10) >= 7) {
            addQuest();
        }
    }
    //  add quest to quest list if there is space
    public void addQuest() {
        if (!(quests.size >= questLimit)) {
            quests.add("Quest", new Quest(highestLevel));
        }
    }

    //  set if player is in game or not (game over)
    public void setInGame() {
        int healthyCount = 0;
        for (int i = 0; i < pc.size(); i++) {
            if (!pc.get(i).fainted) {
                healthyCount++;
            }
        }
        if (healthyCount > 0) {
            inGame = true;
        } else if (healthyCount == 0) {
            inGame = false;
        }
    }
    //  set if player is in a battle or not
    public void setInBattle(boolean inBattle) {
        this.inBattle = inBattle;
    }
    //  set monster team leader
    public void setLeader() {
        for (int i = 0; i < pc.size(); i++) {
            if (!pc.get(i).fainted) {
                teamLeader = pc.get(i);
                return;
            }
        }
    }

    //  expand pc storage limit
    public void addPcStorage() {
        //  add second pc slot, if score >= 100
        if (pcSizeLimit == 1) {
            if (score >= 100) {
                pcSizeLimit++;
            }
        }
        //  add third pc slot, if highest level >= 7
        if (pcSizeLimit == 2) {
            if (highestLevel >= 7) {
                pcSizeLimit++;
            }
        }
        //  add fourth pc slot, if score >= 900
        if (pcSizeLimit == 3) {
            if (score >= 900) {
                pcSizeLimit++;
            }
        }
        //  add fifth pc slot, if highest level >= 10
        if (pcSizeLimit == 4) {
            if (highestLevel >= 10) {
                pcSizeLimit++;
            }
        }
        //  add sixth pc slot, if highest level >= 12
        if (pcSizeLimit == 5) {
            if (highestLevel >= 12) {
                pcSizeLimit++;
            }
        }
    }

    //  use potion item on monster
    public void usePotion(Monster monster, Label gameLogLabel) {
        if (bag.get(0).amount > 0) {
            bag.get(0).useItem(this, monster, gameLogLabel);
        }
    }
    //  use ball item on wild monster
    public boolean useBall(Monster monster, Label gameLogLabel) {
        int size = pc.size();
        if (bag.get(1).amount > 0) {
            bag.get(1).useItem(this, monster, gameLogLabel);
        }
        //  return whether capture succeeded
        return size < pc.size();
    }
    //  use revive item on monster
    public void useRevive(Monster monster, Label gameLogLabel) {
        if (bag.get(2).amount > 0) {
            bag.get(2).useItem(this, monster, gameLogLabel);
        }
    }
}
