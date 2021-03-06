package com.example.monstergame;

import java.util.Objects;
import java.util.Random;

public class Battle {
    //  battle participants
    boolean trainerBattle;
    boolean playerSwap;
    boolean trainerSwap;
    Player player;
    Trainer enemyTrainer;
    Monster enemyMonster;
    String winner = "";
    String questInfo;
    Move playerMove;
    Move enemyMove;



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
            Random rand = new Random();
            int bossChance = rand.nextInt(100);
            //  1% chance for boss monster to appear in wild
            if (bossChance == 0) {
                enemyMonster = new BossMonster(rand.nextInt(3), player.highestLevel);
            } else {
                createWildMonster();
            }
        }
        this.player.addMonstersFought(enemyMonster.type);
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
                player.addMonstersBeaten(enemyMonster.type);
                battleController.setBattleLog(battleController.getBattleLog().getText() + "\n" +  enemyMonster.name + " was defeated!" + "\n" + player.teamLeader.name + " gained " + enemyMonster.xpYield + " xp!");
                //  check if player made progress on any quests
                for (int i = 0; i < player.quests.size(); i++) {
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
                    //  boss battle
                    else if (player.quests.get(i).type == 3 && player.quests.get(i).bossMonster.fainted) {
                        if (Objects.equals(player.quests.get(i).bossMonster.name, enemyMonster.name)) {
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
                player.addMonstersBeaten(enemyMonster.type);
                player.teamLeader.setXpCurr(enemyMonster.xpYield);
                battleController.setBattleLog(battleController.getBattleLog().getText() + "\n" + enemyMonster.name + " fainted!" + "\n" + player.teamLeader.name + " gained " + enemyMonster.xpYield + " xp!");
                //  enemy trainer out of monsters
                if (enemyTrainer.pc.size() <= 0) {
                    winner = player.name;
                    //  complete battle quest
                    for (int i = 0; i < player.quests.size(); i++) {
                        //  complete trainer battle quest
                        if (player.quests.get(i).type == 0 || player.quests.get(i).type == 4) {
                            if (Objects.equals(player.quests.get(i).trainer.name, enemyTrainer.name)) {
                                player.quests.get(i).decreaseRemaining();
                            }
                        }
                    }
                } else {
                    enemyMonster = enemyTrainer.pc.get(0);
                    battleController.setBattleLog(battleController.getBattleLog().getText() + "\n" + enemyTrainer.name + " sent out " + enemyMonster.name + "!");
                    player.addMonstersFought(enemyMonster.type);
                    trainerSwap = true;
                }
            }
        }
    }

    //  set random wild monster stats, level scaled off player leader monster
    public void createWildMonster() {
        enemyMonster = new Monster(false);
        //  level up monster
        int level = randomStats(2, player.teamLeader.level + 1);
        for (int i = 0; i < level; i++) {
            enemyMonster.setLevel(1);
        }
    }
    //  generate random stats within range for wild monster
    public int randomStats(int min, int max) {
        Random rand = new Random();
        int stat = rand.nextInt(max);
        //  return stat if in range
        if (stat >= min && stat <= max) {
            return stat;
        }
        return randomStats(min, max);
    }

    //  set damage amount to be done
    public int checkDamage(Move move, Monster attacker, Monster defender) {
        int attPower = move.damage;
        double levelRatio = (double) (2 * attacker.level + 10) / 100;
        double attDefRatio = (double) attacker.attack / defender.defense;
        int damage = (int) (attPower * levelRatio * attDefRatio);
        return -damage;
    }

    //  set player and enemy move for turn
    public void setMove(Move move) {
        Random rand = new Random();
        //  set player move for turn
        playerMove = move;
        //  set enemy move for turn
        enemyMove = enemyMonster.learnedMoves.get(rand.nextInt(enemyMonster.learnedMoves.size()));
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
                    battleController.getBattleLog().setText(battleController.getBattleLog().getText() + " Leveled up!");
                    int size = player.teamLeader.learnedMoves.size();
                    if (player.teamLeader.learnedMoves.get(size-1).level == player.teamLeader.level) {
                        battleController.getBattleLog().setText(battleController.getBattleLog().getText() + " Learned " + player.teamLeader.learnedMoves.get(size-1).name + "!");
                    }
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
        double damageMod = 1;
        //  if enemy dodges
        if (checkDodge() == 0) {
            battleController.setBattleLog(battleController.getBattleLog().getText() + player.teamLeader.name + " missed!");
            checkHealth(battleController);
            return;
        }
        //  set player leader type damage mod
        double typeDamage = checkTypeMatchUp(playerMove, enemyMonster);
        if (typeDamage == .5) {
            battleController.setBattleLog(battleController.getBattleLog().getText() + player.teamLeader.name + " used " + playerMove.name + "! Not very effective...");
        } else if (typeDamage == 1.5) {
            battleController.setBattleLog(battleController.getBattleLog().getText() + player.teamLeader.name + " used " + playerMove.name + "! Super effective!");
        } else {
            battleController.setBattleLog(battleController.getBattleLog().getText() + player.teamLeader.name + " used " + playerMove.name + "!");
        }
        //  set player stab damage mod
        double stabDamage = checkStab(player.teamLeader.type, playerMove.type);
        //  if player leader critical hits
        if (checkCritical() == 0) {
            battleController.setBattleLog(battleController.getBattleLog().getText() + " Critical hit!");
            damageMod = 2;
        }
        damageMod *= typeDamage * stabDamage;
        int damage = (int) (checkDamage(playerMove, player.teamLeader, enemyMonster) * damageMod);
        enemyMonster.setHpCurr(damage);
        checkHealth(battleController);
    }
    //  enemy monster attacks player monster
    public void enemyAttacks(BattleController battleController) {
        double damageMod = 1;
        //  if player leader dodges
        if (checkDodge() == 0) {
            battleController.setBattleLog(battleController.getBattleLog().getText() + enemyMonster.name + " missed!");
            checkHealth(battleController);
            return;
        }
        //  set enemy type damage mod
        double typeDamage = checkTypeMatchUp(enemyMove, player.teamLeader);
        if (typeDamage == .5) {
            battleController.setBattleLog(battleController.getBattleLog().getText() + enemyMonster.name + " used " + enemyMove.name + "! Not very effective...");
        } else if (typeDamage == 1.5) {
            battleController.setBattleLog(battleController.getBattleLog().getText() + enemyMonster.name + " used " + enemyMove.name + "! Super effective!");
        } else {
            battleController.setBattleLog(battleController.getBattleLog().getText() + enemyMonster.name + " used " + enemyMove.name + "!");
        }
        //  set enemy stab damage mod
        double stabDamage = checkStab(enemyMonster.type, enemyMove.type);
        //  if enemy critical hits
        if (checkCritical() == 0) {
            battleController.setBattleLog(battleController.getBattleLog().getText() + " Critical hit!");
            damageMod = 2;
        }
        damageMod *= typeDamage * stabDamage;
        int damage = (int) (checkDamage(enemyMove, enemyMonster, player.teamLeader) * damageMod);
        player.teamLeader.setHpCurr(damage);
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
    //  check type match up
    public double checkTypeMatchUp(Move attacker, Monster defender) {
        //  if neutral attack
        if (attacker.type.neutralAgainst.contains(defender.typeName)) {
            return 1;
        }
        //  if super effective attack
        if (attacker.type.strongAgainst.contains(defender.typeName)) {
            return 1.5;
        }
        //  if not very effective
        if (attacker.type.weakAgainst.contains(defender.typeName)) {
            return .5;
        }
        //  default
        return 1;
    }
    //  check same type attack bonus
    public double checkStab(Type monsterType, Type moveType) {
        if (Objects.equals(monsterType.name, moveType.name)) {
            return 1.5;
        } else {
            return 1;
        }
    }
}
