package com.example.monstergame;

import java.util.Objects;
import java.util.Random;

public class Battle {
    //  create types for enemy creation
    Type normal = new Type("Normal", 0);
    Type fire = new Type("Fire", 1);
    Type water = new Type("Water", 2);
    Type earth = new Type("Earth", 3);
    Type electric = new Type("Electric", 4);
    Type nature = new Type("Nature", 5);
    Type wind = new Type("Wind", 6);
    Type ice = new Type("Ice", 7);

    //  battle participants
    boolean trainerBattle;
    boolean playerSwap;
    boolean trainerSwap;
    Player player;
    Trainer enemyTrainer;
    Monster enemyMonster;
    String winner = "";
    String questInfo;



    //  create a new battle
    public Battle(Player player, boolean trainerBattle, String questInfo) {
        this.player = player;
        this.player.setLeader();
        this.trainerBattle = trainerBattle;
        //  fighting trainer
        if (this.trainerBattle) {
            enemyTrainer = this.player.trainer;
            enemyMonster = enemyTrainer.pc.get(0);
            trainerSwap = false;
            this.questInfo = questInfo;
        }
        //  fighting wild monster
        else {
            setWildMonsterStats();
        }
    }

    //  check player and enemy monster health
    public void checkHealth(BattleController battleController) {
        player.setInGame();
        //  if player monster faints
        if (player.teamLeader.fainted) {
            //  if player has more monsters able to fight
            player.setInGame();
            if (player.inGame) {
                battleController.setBattleLog(battleController.getBattleLog().getText() +"\n" +  player.teamLeader.name + " fainted!");
                player.setLeader();
                playerSwap = true;
                battleController.setBattleLog(battleController.getBattleLog().getText() + "\n" + player.teamLeader.name + " was sent in!");
                battleController.setMonsterCircles();
            } else {
                winner = enemyMonster.name;
                player.setInBattle(false);
            }
        }
        //  if enemy monster faints
        else if (enemyMonster.fainted) {
            //  wild monster faints
            if (!trainerBattle) {
                winner = player.name;
                player.addMonstersBeaten();
                battleController.setBattleLog(battleController.getBattleLog().getText() + "\n" +  enemyMonster.name + " was defeated!" + "\n" + player.teamLeader.name + " gained " + enemyMonster.xpYield + " xp!");
                //  check if player made progress on any quests
                for (int i = 0; i < player.quests.size; i++) {
                    //  x wild battles quest
                    if (player.quests.get(i).type == 1) {
                        player.quests.get(i).decreaseRemaining();
                    }
                    //  x wild type battles quest
                    else if (player.quests.get(i).type == 2) {
                        if (Objects.equals(enemyMonster.type.name, player.quests.get(i).typeInfo)) {
                            player.quests.get(i).decreaseRemaining();
                        }
                    }
                }
                //  roll for new quest
                player.questRoll();
                player.setInBattle(false);
            }
            //  trainer monster faints
            else {
                //  remove fainted monster, send out next monster or end battle
                enemyTrainer.pc.remove(0);
                player.addMonstersBeaten();
                player.teamLeader.setXpCurr(enemyMonster.xpYield);
                battleController.setBattleLog(battleController.getBattleLog().getText() + "\n" + enemyMonster.name + " fainted!" + "\n" + player.teamLeader.name + " gained " + enemyMonster.xpYield + " xp!");
                //  enemy trainer out of monsters
                if (enemyTrainer.pc.size <= 0) {
                    winner = player.name;
                    //  complete battle quest
                    for (int i = 0; i < player.quests.size; i++) {
                        //  complete trainer battle quest
                        if (player.quests.get(i).type == 0 || player.quests.get(i).type == 3) {
                            if (Objects.equals(player.quests.get(i).trainer.name, enemyTrainer.name)) {
                                player.quests.get(i).decreaseRemaining();
                            }
                        }
                    }
                } else {
                    enemyMonster = enemyTrainer.pc.get(0);
                    battleController.setBattleLog(battleController.getBattleLog().getText() + "\n" + enemyTrainer.name + " sent out " + enemyMonster.name + "!");
                    trainerSwap = true;
                }
            }
        }
    }

    //  set random wild monster stats, level scaled off player leader monster
    public void setWildMonsterStats() {
        Random rand = new Random();
        //  random type
        int typeChance = rand.nextInt(160);
        Type type;
        //  normal type, 10%
        if (typeChance >= 144) {
            type = normal;
        }
        //  fire type, 12%
        else if (typeChance >= 125) {
            type = fire;
        }
        //  water type, 15%
        else if (typeChance >= 101) {
            type = water;
        }
        //  earth type, 15%
        else if (typeChance >= 77) {
            type = earth;
        }
        //  electric type, 10%
        else if (typeChance >= 61) {
            type = electric;
        }
        //  nature type, 15%
        else if (typeChance >= 37) {
            type = nature;
        }
        //  wind type, 13%
        else if (typeChance >= 16){
            type = wind;
        }
        //  ice type, 10%
        else {
            type = ice;
        }
        //  random level
        int level = randomStats(2, player.teamLeader.level + 1);
        //  random stats
        int health = randomStats(10, 15);
        int attack = randomStats(10, 15);
        int defense = randomStats(10, 15);
        int speed = randomStats(10, 15);
        // create random wild monster
        enemyMonster = new Monster(type.name + " Monster", type, 1, health, attack, defense, speed, false);
        //  level up monster
        for (int i = 0; i < level; i++) {
            enemyMonster.setLevel(1);
        }
    }
    //  generate random stats within range for wild monster
    public int randomStats(int min, int max) {
        Random rand = new Random();
        int stat = rand.nextInt(max);
        if (stat < min) {
            stat = min + 1;
        }
        return stat;
    }

    //  set damage amount to be done
    public int checkDamage(Monster attacker, Monster defender) {
        return -((((2 * attacker.level / 5) + 2) * attacker.attack / defender.defense) + 2);
    }

    //  set move priority
    public void checkSpeed(BattleController battleController) {
        trainerSwap = false;
        winner = "";
        battleController.setBattleLog("");
        //  if player moves first
        if (player.teamLeader.speed > enemyMonster.speed) {
            playerAttacks(battleController);
            //  if player defeats enemy wild monster/trainer
            if (Objects.equals(winner, player.name)) {
                int prevLevel = player.teamLeader.level;
                player.teamLeader.setXpCurr(enemyMonster.xpYield);
                player.setHighestLevel();
                player.setMoney(player.money + (enemyMonster.xpYield + 20));
                this.player.setScore(player.score + (enemyMonster.xpYield / 2));
                if (player.teamLeader.level > prevLevel) {
                    battleController.getBattleLog().setText(battleController.getBattleLog().getText() + " " + player.teamLeader.name + " leveled up!");
                }
                return;
            }
            battleController.setBattleLog(battleController.getBattleLog().getText() + "\n");
            if (!trainerSwap) {
                enemyAttacks(battleController);
            }
            //  if enemy moves first
        }   else if (player.teamLeader.speed < enemyMonster.speed) {
            enemyAttacks(battleController);
            if (Objects.equals(winner, enemyMonster.name)) {
                return;
            }
            if (playerSwap) {
                playerSwap = false;
                return;
            }
            battleController.setBattleLog(battleController.getBattleLog().getText() + "\n");
            playerAttacks(battleController);
            if (Objects.equals(winner, player.name) || trainerSwap) {
                int prevLevel = player.teamLeader.level;
                player.teamLeader.setXpCurr(enemyMonster.xpYield);
                player.setHighestLevel();
                player.setMoney((player.money + (enemyMonster.xpYield + 20) / 2));
                this.player.setScore(player.score + (enemyMonster.xpYield / 2));
                if (player.teamLeader.level > prevLevel) {
                    battleController.getBattleLog().setText(battleController.getBattleLog().getText() + " " + player.teamLeader.name + " leveled up!");
                }
            }
        }   else {
            //  if speed tie
            Random rand = new Random();
            if (rand.nextInt(2) == 0) {
                playerAttacks(battleController);
                if (Objects.equals(winner, player.name) || trainerSwap) {
                    int prevLevel = player.teamLeader.level;
                    player.teamLeader.setXpCurr(enemyMonster.xpYield);
                    player.setHighestLevel();
                    player.setMoney((player.money + (enemyMonster.xpYield + 20) / 2));
                    player.setScore(player.score + (enemyMonster.xpYield / 2));
                    if (player.teamLeader.level > prevLevel) {
                        battleController.getBattleLog().setText(battleController.getBattleLog().getText() + " " + player.teamLeader.name + " leveled up!");
                    }
                    return;
                }
                battleController.setBattleLog(battleController.getBattleLog().getText() + "\n");
                enemyAttacks(battleController);
            } else {
                enemyAttacks(battleController);
                if (Objects.equals(winner, enemyMonster.name)) {
                    return;
                }
                battleController.setBattleLog(battleController.getBattleLog().getText() + "\n");
                playerAttacks(battleController);
                if (Objects.equals(winner, player.name) || trainerSwap) {
                    int prevLevel = player.teamLeader.level;
                    player.teamLeader.setXpCurr(enemyMonster.xpYield);
                    player.setHighestLevel();
                    player.setMoney((player.money + (enemyMonster.xpYield + 20) / 2));
                    player.setScore(player.score + (this.enemyMonster.xpYield / 2));
                    if (player.teamLeader.level > prevLevel) {
                        battleController.getBattleLog().setText(battleController.getBattleLog().getText() + " " + player.teamLeader.name + " leveled up!");
                    }
                }
            }
        }
    }

    //  player monster attacks enemy monster
    public void playerAttacks(BattleController battleController) {
        double damage;
        //  if enemy dodges
        if (checkDodge() == 0) {
            battleController.setBattleLog(battleController.getBattleLog().getText() + player.teamLeader.name + " missed!");
            checkHealth(battleController);
            return;
        }
        //  set player leader type damage mod
        damage = checkTypeMatchUp(player.teamLeader, enemyMonster);
        if (damage == .5) {
            battleController.setBattleLog(battleController.getBattleLog().getText() + player.teamLeader.name + " attacked! Not very effective...");
        } else if (damage == 2) {
            battleController.setBattleLog(battleController.getBattleLog().getText() + player.teamLeader.name + " attacked! Super effective!");
        } else {
            battleController.setBattleLog(battleController.getBattleLog().getText() + player.teamLeader.name + " attacked!");
        }
        //  if player leader critical hits
        if (checkCritical() == 0) {
            battleController.setBattleLog(battleController.getBattleLog().getText() + " Critical hit!");
            damage *= 2;
        }
        enemyMonster.setHpCurr((int) ((checkDamage(player.teamLeader, enemyMonster)) * damage));
        checkHealth(battleController);
    }
    //  enemy monster attacks player monster
    public void enemyAttacks(BattleController battleController) {
        double damage;
        //  if player leader dodges
        if (checkDodge() == 0) {
            battleController.setBattleLog(battleController.getBattleLog().getText() + enemyMonster.name + " missed!");
            checkHealth(battleController);
            return;
        }
        //  set enemy type damage mod
        damage = checkTypeMatchUp(enemyMonster, player.teamLeader);
        if (damage == .5) {
            battleController.setBattleLog(battleController.getBattleLog().getText() + enemyMonster.name + " attacked! Not very effective...");
        } else if (damage == 2) {
            battleController.setBattleLog(battleController.getBattleLog().getText() + enemyMonster.name + " attacked! Super effective!");
        } else {
            battleController.setBattleLog(battleController.getBattleLog().getText() + enemyMonster.name + " attacked!");
        }
        //  if enemy critical hits
        if (checkCritical() == 0) {
            battleController.setBattleLog(battleController.getBattleLog().getText() + " Critical hit!");
            damage *= 2;
        }
        player.teamLeader.setHpCurr((int) ((checkDamage(enemyMonster, player.teamLeader)) * damage));
        checkHealth(battleController);
    }

    //  check if player or enemy should dodge an attack
    public int checkDodge() {
        Random rand = new Random();
        return rand.nextInt(95);
    }
    //  check if player or enemy should land a critical hit
    public int checkCritical() {
        Random rand = new Random();
        return rand.nextInt(95);
    }
    //  check type match up,
    public double checkTypeMatchUp(Monster attacker, Monster defender) {
        //  if neutral attack
        for (int i = 0; i < attacker.type.neutralAgainst.size(); i++) {
            if (Objects.equals(attacker.type.neutralAgainst.get(i), defender.type.name)) {
                return 1;
            }
        }
        //  if super effective attack
        for (int i = 0; i < attacker.type.strongAgainst.size(); i++) {
            if (Objects.equals(attacker.type.strongAgainst.get(i), defender.type.name)) {
                return 2;
            }
        }
        //  if not very effective
        for (int i = 0; i < attacker.type.weakAgainst.size(); i++) {
            if (Objects.equals(attacker.type.weakAgainst.get(i), defender.type.name)) {
                return .5;
            }
        }
        //  default
        return 1;
    }
}
