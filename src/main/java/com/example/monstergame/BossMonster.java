package com.example.monstergame;

import java.util.Random;

public class BossMonster extends Monster {
    //  boss monster tier, tier 0=+5 levels, 1=+10, 2=+15
    //  could base boss level on highest team level, or average ?
    int tier;
    //  types
    transient Type normal = new Type("Normal", 0);
    transient Type fire = new Type("Fire", 1);
    transient Type water = new Type("Water", 2);
    transient Type earth = new Type("Earth", 3);
    transient Type electric = new Type("Electric", 4);
    transient Type nature = new Type("Nature", 5);
    transient Type wind = new Type("Wind", 6);
    transient Type ice = new Type("Ice", 7);

    public BossMonster(int tier, int baseLevel) {
        super();
        this.tier = tier;
        setBossType();
        setName(typeName + " Monster");
        setMoveList();
        setBossStats();
        setBossGrowthRates();
        setBossLevel(baseLevel);
    }

    public void setBossType() {
        Random rand = new Random();
        //  random type
        int typeChance = rand.nextInt(160);
        //  normal type, 10%
        if (typeChance >= 144) {
            setType(normal);
        }
        //  fire type, 12%
        else if (typeChance >= 125) {
            setType(fire);
        }
        //  water type, 15%
        else if (typeChance >= 101) {
            setType(water);
        }
        //  earth type, 15%
        else if (typeChance >= 77) {
            setType(earth);
        }
        //  electric type, 10%
        else if (typeChance >= 61) {
            setType(electric);
        }
        //  nature type, 15%
        else if (typeChance >= 37) {
            setType(nature);
        }
        //  wind type, 13%
        else if (typeChance >= 16){
            setType(wind);
        }
        //  ice type, 10%
        else {
            setType(ice);
        }
    }
    public void setBossLevel(int baseLevel) {
        level = 1;
        learnMove();
        //  + 2 levels
        if (tier == 0) {
            for (int i = 0; i < baseLevel+2; i++) {
                setLevel(1);
            }
        }
        //  + 3 levels
        else if (tier == 1) {
            for (int i = 0; i < baseLevel+3; i++) {
                setLevel(1);
            }
        }
        //  + 5 levels
        else if (tier == 2) {
            for (int i = 0; i < baseLevel+5; i++) {
                setLevel(1);
            }
        }
    }
    public void setBossStats() {
        hpMax = randomStats(20, 30);
        hpCurr = hpMax;
        attack = randomStats(15, 25);
        defense = randomStats(15, 25);
        speed = randomStats(15, 25);
    }
    public int randomStats(int min, int max) {
        Random rand = new Random();
        int stat = rand.nextInt(max);
        if (stat < min) {
            stat = min + 1;
        }
        return stat;
    }
    public void setBossGrowthRates() {
        Random rand = new Random();
        //  set hp stat growth
        int growth = rand.nextInt(3);
        if (growth == 0) {
            hpGrowth = 1.02;
        } else if (growth == 1) {
            hpGrowth = 1.03;
        } else {
            hpGrowth = 1.04;
        }
        //  set attack stat growth
        growth = rand.nextInt(3);
        if (growth == 0) {
            attGrowth = 1.02;
        } else if (growth == 1) {
            attGrowth = 1.03;
        } else {
            attGrowth = 1.04;
        }
        //  set defense stat growth
        growth = rand.nextInt(3);
        if (growth == 0) {
            defGrowth = 1.02;
        } else if (growth == 1) {
            defGrowth = 1.03;
        } else {
            defGrowth = 1.04;
        }
        //  set speed stat growth
        growth = rand.nextInt(3);
        if (growth == 0) {
            speGrowth = 1.02;
        } else if (growth == 1) {
            speGrowth = 1.03;
        } else {
            speGrowth = 1.04;
        }
    }

}
