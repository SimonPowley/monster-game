package com.example.monstergame;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

public class Trainer implements java.io.Serializable {
    //  trainer attributes
    String name;
    int skill; // 0=novice, 1=intermediate, 2=expert, 3=master
    int teamSize;
    int levelCap;
    boolean beaten;
    //  trainer monster storage
    LinkedList<Monster> pc = new LinkedList<>();
    LinkedList<Monster> backupPc = new LinkedList<>(); // used to reset team health after battle



    //  create a new trainer
    public Trainer(int skill, int levelCap) {
        setRandomName();
        this.skill = skill;
        this.levelCap = levelCap + skill;
        setRandomTeamSize();
        setRandomTeam();
        beaten = false;
    }

    //  set random name for trainer
    public void setRandomName() {
        Random rand = new Random();
        String[] nameList = {"John", "Paul", "George", "Ringo", "Kurt", "Dave", "Chris", "Derek", "Ryan", "Ethan",
                "Abby", "Katie", "Emma", "Carly", "Terry", "Gabby", "Naomi", "Ron", "Walter", "Jesse",
                "Riley", "Stewart", "Billy", "Tashrif", "Debby", "Mark", "Jeff", "Andrew", "Michael", "Aaron",
                "Sarah", "Tony", "Monty", "Steve", "Daniel", "Susan", "Leah", "Maddie", "James", "Owen",
                "Luke", "Jordan", "Seth", "Olivia", "Megan", "Blake", "Sheldon", "Larry", "Henry", "Albert",
                "Edward", "Sean", "Patrick", "Lucas", "Simon", "Justin", "Jason", "Julie", "Dominic", "Trevor",
                "Peyton", "Bob", "Jack", "Felix", "Keith", "Kenny", "Johnny", "Carson", "Scott", "Sally",
                "Hank", "Skylar", "Flynn", "Marie", "Gustavo", "Tina", "Amy", "Chloe", "Kayla", "Taylor",
                "Tyler", "Carl", "Adam", "Connor", "Casey", "Cody", "Lindsey", "Daryl", "Brandon", "Jeff",
                "Sawyer", "William", "Erin", "Gabe", "Amelia", "Mary", "Morgan", "Larry", "Karen", "Stodd",
                "Nick", "Donny", "Brian", "Miles", "Peter", "Julius", "Chuck", "Jeremy", "Howard", "Franklin",
                "Diane", "Tommy", "Isaac", "Edward", "Alphonse", "Hector", "Percy", "Philip", "Tim", "Murphy"};
        name = nameList[rand.nextInt(120)];
    }

    //  set random team size for trainer based on skill level
    public void setRandomTeamSize() {
        Random rand = new Random();
        if (skill == 0) {
            //  novice: 1-2 monsters
            teamSize = rand.nextInt(2) + 1;
        } else if (skill == 1) {
            //  intermediate: 1-3 monsters
            teamSize = rand.nextInt(3) + 1;
        } else if (skill == 2) {
            //  expert: 3-5 monsters
            teamSize = rand.nextInt(5) + 1;
            if (teamSize < 3) {
                teamSize = 3;
            }
        } else if (skill == 3) {
            //  master: 5-6 monsters
            teamSize = rand.nextInt(6) + 1;
            if (teamSize < 5) {
                teamSize = 5;
            }
        }
    }
    //  set random team for trainer
    public void setRandomTeam() {
        Monster monster;
        for (int i = 0; i < teamSize; i++) {
            monster = setRandomMonster();
            pc.add(monster);
        }
        //  create backup pc to restore team to after running from battle
        for (int i = 0; i < teamSize; i++) {
            backupPc.add(pc.get(i));
        }
    }

    //  set random monster for trainer team, level scaled off player's highest level monster
    public Monster setRandomMonster() {
        // create random wild monster
        Monster monster = new Monster(false);
        //  level up monster
        int level = randomStats(2, levelCap+1);
        for (int i = 0; i < level; i++) {
            monster.setLevel(1);
        }
        return monster;
    }
    //  set random stat between specified range
    public int randomStats(int min, int max) {
        Random rand = new Random();
        int stat = rand.nextInt(max);
        //  return stat if in range
        if (stat >= min && stat <= max) {
            return stat;
        }
        return randomStats(min, max);
    }

    //  reset trainer team to pc backup after battle
    public void resetTeam() {
        pc = new LinkedList<>();
        for (int i = 0; i < teamSize; i++) {
            pc.add(backupPc.get(i));
        }
        for (Monster monster : pc) {
            monster.setHpCurr(monster.hpMax);
        }
        teamSize = pc.size();
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
}
