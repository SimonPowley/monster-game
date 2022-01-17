package com.example.monstergame;

import java.util.Random;

public class Quest implements java.io.Serializable {
    //  quest attributes
    int type; // 0=trainer battle, 1=x wild battles, 2=x wild type battles, 3=boss battle
    int skillCap; // 0=novice, 1=intermediate, 2=expert, 3=master
    int levelCap; // based on player's highest level in party
    int originalTotal;
    int remaining; // # remaining to complete quest
    String info; // quest's description
    String typeInfo; // wild type to battle
    Trainer trainer; // trainer for battle quests/all quest rewards
    BossMonster bossMonster; // boss monster for boss battle
    boolean completed;


    //  create a new quest
    public Quest(int levelCap) {
        setQuestType();
        this.levelCap = levelCap;
        this.remaining = 0;
        this.completed = false;
        setSkillCap();
        this.trainer = new Trainer(skillCap, levelCap);
        generateInfo();
    }

    //  set quest type
    public void setQuestType() {
        Random rand = new Random();
        type = rand.nextInt(5);
    }

    //  generate quest info/requirements
    public void generateInfo() {
        Random rand = new Random();
        //  trainer battle quest
        if (type == 0 || type == 4) {
            String[] challengeLines = {" wants to battle!", " challenged you to a battle!",
                    " asked you to battle!", " wants to battle you!", " is looking to battle!"};
            info = trainer.name + challengeLines[rand.nextInt(5)];
            originalTotal = 1;
            remaining = originalTotal;
        }
        //  x wild battles quest
        else if (this.type == 1) {
            originalTotal = rand.nextInt(20) + 1;
            remaining = originalTotal;
            info = trainer.name + " wants you to battle:\n" + remaining + " wild monsters";

        }
        //  x wild type battles quest
        else if (type == 2) {
            String[] type = {"Normal", "Fire", "Water", "Earth", "Electric", "Nature", "Wind", "Ice"};
            originalTotal = rand.nextInt(10) + 1;
            remaining = originalTotal;
            typeInfo = type[rand.nextInt(8)];
            info = trainer.name + " wants you to battle:\n" + remaining + " wild " + typeInfo + " monsters";
        }
        //  boss battle quest
        else if (type == 3) {
            bossMonster = new BossMonster(rand.nextInt(3), levelCap);
            originalTotal = 1;
            remaining = originalTotal;
            info = "A strong looking\n" + bossMonster.name + " is close by...";
            trainer.name = "BOSS";
        }
    }
    //  set quest info
    public void setInfo() {
        //  x wild battles quest
        if (type == 1) {
            info = trainer.name + " wants you to battle:\n" + remaining + " wild monsters";
        }
        //  x wild type battles quest
        else if (type == 2) {
            info = trainer.name + " wants you to battle:\n" + remaining + " wild " + typeInfo + " monsters";
        }
        //  quest complete
        if (remaining <= 0) {
            completed = true;
            info = "Quest completed!";
        }
    }

    //  set trainer's skill cap, determines team levels and team size
    public void setSkillCap() {
        //  novice, level 7 and under
        if (levelCap <= 7) {
            skillCap = 0;
        }
        //  intermediate, level 8-10
        else if (levelCap <= 10) {
            skillCap = 1;
        }
        //  expert, level 11-13
        else if (levelCap <= 13) {
            skillCap = 2;
        }
        //  master, level 14 and above
        else {
            skillCap = 3;
        }
    }

    //  decrease remaining quest progress
    public void decreaseRemaining() {
        remaining--;
        setInfo();
    }
}
