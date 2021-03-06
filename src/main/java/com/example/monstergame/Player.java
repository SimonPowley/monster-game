package com.example.monstergame;

import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.util.LinkedList;
import java.util.Objects;
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
    LinkedList<Monster> pc = new LinkedList<>();
    Monster teamLeader; // first monster sent into battle
    int pcSizeLimit;
    int highestLevel;
    //  player item storage
    LinkedList<Item> bag = new LinkedList<>();
    Item itemInUse;
    int potionsInBag;
    int ballsInBag;
    int revivesInBag;
    //  player quest storage
    LinkedList<Quest> quests = new LinkedList<>();
    Trainer trainer;
    int questLimit;
    int monstersBeaten;
    int monstersCaught;
    //  individual type fought, beaten, caught
    int normalFought;
    int normalBeaten;
    int normalCaught;
    int fireFought;
    int fireBeaten;
    int fireCaught;
    int waterFought;
    int waterBeaten;
    int waterCaught;
    int earthFought;
    int earthBeaten;
    int earthCaught;
    int electricFought;
    int electricBeaten;
    int electricCaught;
    int natureFought;
    int natureBeaten;
    int natureCaught;
    int windFought;
    int windBeaten;
    int windCaught;
    int iceFought;
    int iceBeaten;
    int iceCaught;



    //  create a new player
    public Player() {
        //  add starting items to bag
        Item potion = new Item("Potion", true, false, false, 0, 300, 150, 5);
        Item ball = new Item("Ball", false, true, false, 0, 200, 100, 0);
        Item revive = new Item("Revive", false, false, true, 0, 1000, 500, 0);
        bag.add(potion);
        bag.add(ball);
        bag.add(revive);
        potionsInBag = potion.amount;
        ballsInBag = ball.amount;
        revivesInBag = revive.amount;
        //  initialize other stuff
        questLimit = 3;
        monstersBeaten = 0;
        monstersCaught = 0;
        normalFought = 0; normalBeaten = 0; normalCaught = 0;
        fireFought = 0; fireBeaten = 0; fireCaught = 0;
        waterFought = 0; waterBeaten = 0; waterCaught = 0;
        earthFought = 0; earthBeaten = 0; earthCaught = 0;
        electricFought = 0; electricBeaten = 0; electricCaught = 0;
        natureFought = 0; natureBeaten = 0; natureCaught = 0;
        windFought = 0; windBeaten = 0; windCaught = 0;
        iceFought = 0; iceBeaten = 0; iceCaught = 0;
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
        for (Monster monster : pc) {
            if (monster.level > highestLevel) {
                highestLevel = monster.level;
            }
        }
        addPcStorage();
    }
    //  reload monster types
    public void reloadTypes() {
        for (Monster monster : pc) {
            //  reload move types
            monster.moveList = monster.moves.reloadMove(monster.moveList);
            monster.learnedMoves = monster.moves.reloadMove(monster.learnedMoves);
            //  set normal types
            if (Objects.equals(monster.typeName, "Normal")) {
                monster.setType(new Type("Normal", 0));
            }
            //  set fire types
            if (Objects.equals(monster.typeName, "Fire")) {
                monster.setType(new Type("Fire", 1));
            }
            //  set water types
            if (Objects.equals(monster.typeName, "Water")) {
                monster.setType(new Type("Water", 2));
            }
            //  set earth types
            if (Objects.equals(monster.typeName, "Earth")) {
                monster.setType(new Type("Earth", 3));
            }
            //  set electric types
            if (Objects.equals(monster.typeName, "Electric")) {
                monster.setType(new Type("Electric", 4));
            }
            //  set nature types
            if (Objects.equals(monster.typeName, "Nature")) {
                monster.setType(new Type("Nature", 5));
            }
            //  set wind types
            if (Objects.equals(monster.typeName, "Wind")) {
                monster.setType(new Type("Wind", 6));
            }
            //  set ice types
            if (Objects.equals(monster.typeName, "Ice")) {
                monster.setType(new Type("Ice", 7));
            }
        }
    }

    //  add monsters fought total
    public void addMonstersFought(Type type) {
        if (Objects.equals(type.name, "Normal")) {
            normalFought++;
        } else if (Objects.equals(type.name, "Fire")) {
            fireFought++;
        } else if (Objects.equals(type.name, "Water")) {
            waterFought++;
        } else if (Objects.equals(type.name, "Earth")) {
            earthFought++;
        } else if (Objects.equals(type.name, "Electric")) {
            electricFought++;
        } else if (Objects.equals(type.name, "Nature")) {
            natureFought++;
        } else if (Objects.equals(type.name, "Wind")) {
            windFought++;
        } else if (Objects.equals(type.name, "Ice")) {
            iceFought++;
        }
    }
    //  add to monsters beaten total
    public void addMonstersBeaten(Type type) {
        monstersBeaten++;
        if (Objects.equals(type.name, "Normal")) {
            normalBeaten++;
        } else if (Objects.equals(type.name, "Fire")) {
            fireBeaten++;
        } else if (Objects.equals(type.name, "Water")) {
            waterBeaten++;
        } else if (Objects.equals(type.name, "Earth")) {
            earthBeaten++;
        } else if (Objects.equals(type.name, "Electric")) {
            electricBeaten++;
        } else if (Objects.equals(type.name, "Nature")) {
            natureBeaten++;
        } else if (Objects.equals(type.name, "Wind")) {
            windBeaten++;
        } else if (Objects.equals(type.name, "Ice")) {
            iceBeaten++;
        }
    }
    //  add to monsters caught total
    public void addMonstersCaught(Type type) {
        monstersCaught++;
        if (Objects.equals(type.name, "Normal")) {
            normalCaught++;
        } else if (Objects.equals(type.name, "Fire")) {
            fireCaught++;
        } else if (Objects.equals(type.name, "Water")) {
            waterCaught++;
        } else if (Objects.equals(type.name, "Earth")) {
            earthCaught++;
        } else if (Objects.equals(type.name, "Electric")) {
            electricCaught++;
        } else if (Objects.equals(type.name, "Nature")) {
            natureCaught++;
        } else if (Objects.equals(type.name, "Wind")) {
            windCaught++;
        } else if (Objects.equals(type.name, "Ice")) {
            iceCaught++;
        }
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
        if (!(quests.size() >= questLimit)) {
            quests.add(new Quest(highestLevel));
        }
    }

    //  set if player is in game or not (game over)
    public void setInGame() {
        int healthyCount = 0;
        for (Monster monster : pc) {
            if (!monster.fainted) {
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
        for (Monster monster : pc) {
            if (!monster.fainted) {
                teamLeader = monster;
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
