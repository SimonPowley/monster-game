package com.example.monstergame;

import java.util.LinkedList;
import java.util.Random;

public class Monster implements java.io.Serializable {
    //  monster attributes
    String name;
    transient Type type;
    String typeName;
    int catchRate;
    int xpYield;
    boolean playerOwned;
    boolean bossMonster;
    //  monster stats
    int level;
    int xpCurr;
    int xpToLevelUp;
    int hpCurr;
    int hpMax;
    int attack;
    int defense;
    int speed;
    boolean fainted;
    //  monster stat growth-rates
    double hpGrowth;
    double attGrowth;
    double defGrowth;
    double speGrowth;
    //  monster positive stat affinities
    String posStat1; // first stat raised
    String posStat2; // second stat raised (if applicable)
    int posStat1Amount; //  amount to raise first stat by (10=10%)
    int posStat2Amount; //  amount to raise second stat by (if applicable)
    int numPos; //  # of stats raised (1 or 2)
    //  monster negative stat affinities
    String negStat1; // first stat lowered
    String negStat2; // second stat lowered (if applicable)
    int negStat1Amount; // amount to lower first stat by (10=10%)
    int negStat2Amount; // amount to lower second stat by (if applicable)
    int numNeg; // # of stats lowered (1 or 2)
    //  monster stat affinities
    int hpStatBoost;
    int attackStatBoost;
    int defenseStatBoost;
    int speedStatBoost;
    //  monster move list <move, level learnt>
    LinkedList<Move> moveList = new LinkedList<>();
    LinkedList<Move> learnedMoves = new LinkedList<>();
    Move moves; // all moves, pull individual moves from here



    //  create a new monster
    public Monster(String name, Type type, int level, int hp, int att, int def, int spe, boolean playerOwned) {
        //  set monster attributes
        setName(name);
        setType(type);
        setMoveList();
        this.level = level;
        learnMove();
        this.xpCurr = 0;
        this.hpMax = hp;
        this.hpCurr = this.hpMax;
        this.attack = att;
        this.defense = def;
        this.speed = spe;
        setGrowthRates();
        setXpToLevelUp();
        setXpYield();
        setCatchRate();
        setStatAffinity();
        this.playerOwned = playerOwned;
        this.fainted = false;
        this.hpCurr = this.hpMax;
        this.bossMonster = false;
    }
    //  create a new boss monster
    public Monster() {
        this.xpCurr = 0;
        this.playerOwned = false;
        this.fainted = false;
        this.bossMonster = true;
    }

    //  set monster name
    public void setName(String name) {
        this.name = name;
    }
    //  set monster type
    public void setType(Type type) {
        this.type = type;
        typeName = this.type.name;
    }
    //  set xp needed to level up
    public void setXpToLevelUp() {
        this.xpToLevelUp = (int) (4 * Math.pow(level, 3)) / 5;
    }
    //  set monster xp yield
    public void setXpYield() {
        this.xpYield = 1 + (int) Math.pow(level + hpMax, 2) / 10;
    }
    //  set monster catch rate
    public void setCatchRate() {
        this.catchRate = 100 - level - (hpCurr + ((attack + defense + speed) / 3));
    }
    //  set monster stat affinities (1 stat +/-15%, or 2 stats +/-10%)
    public void setStatAffinity() {
        //  50% = 1 stat +/-5%, 10% = 2 stats +/-10%, 30% = no stat boost
        Random rand = new Random();
        int pos = rand.nextInt(10);
        int neg = rand.nextInt(10);
        //  raise stats
        if (pos <= 5) {
            //  one stat +10%
            boostStats(1, true, 1.1);
        } else if (pos == 6) {
            //  two stats +5%
            boostStats(2, true, 1.05);
        }
        //  lower stats
        if (neg <= 5) {
            //  one stat -10%
            boostStats(1, false, .9);
        } else if (neg == 6) {
            //  two stats -5%
            boostStats(2, false, .95);
        }
    }

    //  set monster level
    public void setLevel(int level) {
        this.level += level;
        learnMove();
        setHp();
        setAttack();
        setDefense();
        setSpeed();
        setXpToLevelUp();
        setXpYield();
        setCatchRate();
    }
    //  set monster current xp
    public void setXpCurr(int xp) {
        xpCurr += xp;
        if (xpCurr >= xpToLevelUp) {
            xpCurr -= xpToLevelUp;
            setLevel(1);
        }
    }
    //  set monster stat growth rates
    public void setGrowthRates() {
        Random rand = new Random();
        //  set hp stat growth
        int growth = rand.nextInt(3);
        if (growth == 0) {
            hpGrowth = 1.01;
        } else if (growth == 1) {
            hpGrowth = 1.015;
        } else {
            hpGrowth = 1.02;
        }
        //  set attack stat growth
        growth = rand.nextInt(3);
        if (growth == 0) {
            attGrowth = 1.01;
        } else if (growth == 1) {
            attGrowth = 1.015;
        } else {
            attGrowth = 1.02;
        }
        //  set defense stat growth
        growth = rand.nextInt(3);
        if (growth == 0) {
            defGrowth = 1.01;
        } else if (growth == 1) {
            defGrowth = 1.015;
        } else {
            defGrowth = 1.02;
        }
        //  set speed stat growth
        growth = rand.nextInt(3);
        if (growth == 0) {
            speGrowth = 1.01;
        } else if (growth == 1) {
            speGrowth = 1.015;
        } else {
            speGrowth = 1.02;
        }
    }

    //  set monster hp
    public void setHp() {
        int hpBoost = (int) (1 + (Math.pow(hpMax, hpGrowth)) / 20);
        if (hpStatBoost != 0) {
            hpBoost = hpBoost * (1 + (hpStatBoost / 10));
        }
        if (hpBoost <= 0) {
            hpBoost = 1;
        }
        hpCurr += hpBoost;
        hpMax += hpBoost;
    }
    //  set monster current health
    public void setHpCurr(int hp) {
        hpCurr += hp;
        if (hpCurr > hpMax) {
            hpCurr = hpMax;
        }
        if (hpCurr <= 0) {
            hpCurr = 0;
            fainted = true;
        } else {
            fainted = false;
        }
    }
    //  set monster attack
    public void setAttack() {
        int attBoost = (int) (1 + (Math.pow(attack, attGrowth)) / 20);
        if (attackStatBoost != 0) {
            attBoost = attBoost * (1 + (attackStatBoost / 10));
        }
        if (attBoost <= 0) {
            attBoost = 1;
        }
        attack += attBoost;
    }
    //  set monster defense
    public void setDefense() {
        int defBoost = (int) (1 + (Math.pow(defense, defGrowth)) / 20);
        if (defenseStatBoost != 0) {
            defBoost = defBoost * (1 + (defenseStatBoost / 10));
        }
        if (defBoost <= 0) {
            defBoost = 1;
        }
        defense += defBoost;
    }
    //  set monster speed
    public void setSpeed() {
        int speBoost = (int) (1 + (Math.pow(speed, speGrowth)) / 20);
        if (speedStatBoost != 0) {
            speBoost = speBoost * (1 + (speedStatBoost / 10));
        }
        if (speBoost <= 0) {
            speBoost = 1;
        }
        speed += speBoost;
    }

    //  increase or decrease monster stats based on stats affinities
    public void boostStats(int numAffinities, boolean positive, double amount) {
        //  number of stats affected: 1 or 2, type: positive(0) or negative(1), amount: rate of change
        Random rand = new Random();
        String[] stats = {"HP", "Att", "Def", "Spe"};
        //  boost first stat
        String stat1 = stats[rand.nextInt(4)];
        switch (stat1) {
            case "HP" -> {
                hpMax = (int) (hpMax * amount);
                if (positive) {
                    posStat1 = "HP";
                    if (numAffinities == 1) {
                        hpStatBoost += 10;
                    } else if (numAffinities == 2) {
                        hpStatBoost += 5;
                    }
                } else {
                    negStat1 = "HP";
                    if (numAffinities == 1) {
                        hpStatBoost -= 10;
                    } else if (numAffinities == 2) {
                        hpStatBoost -= 5;
                    }
                }
            }
            case "Att" -> {
                attack = (int) (attack * amount);
                if (positive) {
                    posStat1 = "Att";
                    if (numAffinities == 1) {
                        attackStatBoost += 10;
                    } else if (numAffinities == 2) {
                        attackStatBoost += 5;
                    }
                } else {
                    negStat1 = "Att";
                    if (numAffinities == 1) {
                        attackStatBoost -= 10;
                    } else if (numAffinities == 2) {
                        attackStatBoost -= 5;
                    }
                }
            }
            case "Def" -> {
                defense = (int) (defense * amount);
                if (positive) {
                    posStat1 = "Def";
                    if (numAffinities == 1) {
                        defenseStatBoost += 10;
                    } else if (numAffinities == 2) {
                        defenseStatBoost += 5;
                    }
                } else {
                    negStat1 = "Def";
                    if (numAffinities == 1) {
                        defenseStatBoost -= 10;
                    } else if (numAffinities == 2) {
                        defenseStatBoost -= 5;
                    }
                }
            }
            case "Spe" -> {
                speed = (int) (speed * amount);
                if (positive) {
                    posStat1 = "Spe";
                    if (numAffinities == 1) {
                        speedStatBoost += 10;
                    } else if (numAffinities == 2) {
                        speedStatBoost += 5;
                    }
                } else {
                    negStat1 = "Spe";
                    if (numAffinities == 1) {
                        speedStatBoost -= 10;
                    } else if (numAffinities == 2) {
                        speedStatBoost -= 5;
                    }
                }
            }
        }
        //  finish if only one stat affected
        if (numAffinities == 1) {
            return;
        }
        //  check if same stat boosted twice
        String stat2 = stats[rand.nextInt(4)];
        switch (stat2) {
            case "HP" -> {
                hpMax = (int) (hpMax * amount);
                if (positive) {
                    posStat2 = "HP";
                    hpStatBoost += 5;
                } else {
                    negStat2 = "HP";
                    hpStatBoost -= 5;
                }
            }
            case "Att" -> {
                attack = (int) (attack * amount);
                if (positive) {
                    posStat2 = "Att";
                    attackStatBoost += 5;
                } else {
                    negStat2 = "Att";
                    attackStatBoost -= 5;
                }
            }
            case "Def" -> {
                defense = (int) (defense * amount);
                if (positive) {
                    posStat2 = "Def";
                    defenseStatBoost += 5;
                } else {
                    negStat2 = "Def";
                    defenseStatBoost -= 5;
                }
            }
            case "Spe" -> {
                speed = (int) (speed * amount);
                if (positive) {
                    posStat2 = "Spe";
                    speedStatBoost += 5;
                } else {
                    negStat2 = "Spe";
                    speedStatBoost -= 5;
                }
            }
        }
        //  finish if two stats affected
        if (positive) {
            posStat1Amount = 5;
            posStat2Amount = 5;
            numPos = 2;
        } else {
            negStat1Amount = 5;
            negStat2Amount = 5;
            numNeg = 2;
        }
    }

    //  set monster move list
    public void setMoveList() {
        moves = new Move();
        moveList = moves.createMoveList(type);
    }
    //  learn new move after needed level reached
    public void learnMove() {
        for (Move move : moveList) {
            if (level >= move.level && !learnedMoves.contains(move)) {
                learnedMoves.add(move);
            }
        }
    }
}
