package com.example.monstergame;

import java.util.Objects;

public class BossMonster extends Monster {
    //  boss monster tier, tier 0=+2 levels, 1=+3, 2=+5
    //  could base boss level on highest team level, or average ?
    int tier;



    public BossMonster(int tier, int baseLevel) {
        super(true);
        this.tier = tier;
        setBossLevel(baseLevel);
    }

    public void setBossLevel(int baseLevel) {
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

    //  reload boss type
    public void reloadTypes() {
        //  reload move types
        moveList = moves.reloadMove(moveList);
        learnedMoves = moves.reloadMove(learnedMoves);
        //  set normal types
        if (Objects.equals(typeName, "Normal")) {
            setType(new Type("Normal", 0));
        }
        //  set fire types
        if (Objects.equals(typeName, "Fire")) {
            setType(new Type("Fire", 1));
        }
        //  set water types
        if (Objects.equals(typeName, "Water")) {
            setType(new Type("Water", 2));
        }
        //  set earth types
        if (Objects.equals(typeName, "Earth")) {
            setType(new Type("Earth", 3));
        }
        //  set electric types
        if (Objects.equals(typeName, "Electric")) {
            setType(new Type("Electric", 4));
        }
        //  set nature types
        if (Objects.equals(typeName, "Nature")) {
            setType(new Type("Nature", 5));
        }
        //  set wind types
        if (Objects.equals(typeName, "Wind")) {
            setType(new Type("Wind", 6));
        }
        //  set ice types
        if (Objects.equals(typeName, "Ice")) {
            setType(new Type("Ice", 7));
        }
    }
}
