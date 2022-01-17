package com.example.monstergame;

import javafx.scene.control.Label;

import java.util.Random;

public class Item implements java.io.Serializable {
    //  Item attributes
    String name;
    boolean isPotion;
    boolean isBall;
    boolean isRevive;
    int itemId; // 0=weak,1=regular,2=strong item power
    int power;
    int buyPrice;
    int sellPrice;
    int amount;
    //  String info; // potential to view specific info for items someday



    //  create a new item
    public Item(String name, boolean isPotion, boolean isBall, boolean isRevive, int itemId, int buyPrice, int sellPrice, int amount) {
        this.name = name;
        this.isPotion = isPotion;
        this.isBall = isBall;
        this.isRevive = isRevive;
        this.itemId = itemId;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.amount = amount;
        setItemPower();
    }

    //  set potion and ball powers
    public void setItemPower() {
        //  if item is a potion
        if (isPotion) {
            //  set heal power
            if (itemId == 0) {
                power = 20;
            }
            if (itemId == 1) {
                power = 50;
            }
            if (itemId == 2) {
                power = 90;
            }
        }
        //  if item is a ball
        if (isBall) {
            //  set item stats
            if (itemId == 0) {
                power = 20;
            }
            if (itemId == 1) {
                power = 50;
            }
            if (itemId == 2) {
                power = 90;
            }
        }
    }

    //  use item on specified monster
    public void useItem(Player player, Monster monster, Label gameLogLabel) {
        //  if not enough
        if (amount <= 0) {
            gameLogLabel.setText("Not enough!");
            player.itemInUse = null;
            return;
        }
        //  if item is a potion
        if (isPotion) {
            //  if monster is fainted
            if (monster.fainted) {
                gameLogLabel.setText(monster.name + " is fainted!");
            }
            //  health already full
            else if (monster.hpCurr >= monster.hpMax) {
                gameLogLabel.setText(monster.name + "'s health is full!");
            }
            //  heal monster
            else {
                int healAmount = monster.hpMax - monster.hpCurr;
                if (healAmount > power) {
                    healAmount = power;
                }
                monster.setHpCurr(power);
                removeItem(1);
                player.potionsInBag--;
                if (amount <= 0) {
                    player.itemInUse = null;
                }
                gameLogLabel.setText(monster.name + " recovered " + healAmount + " Hp.");
            }
        }
        //  if item is a ball
        else if (isBall) {
            //  if monster storage full
            if (player.pc.size() >= player.pcSizeLimit) {
                gameLogLabel.setText("Monster storage full!");
            }
            //  use ball
            else {
                Random rand = new Random();
                //  calculate success chance
                int catchSuccess = rand.nextInt(100);
                int catchRate = monster.catchRate + power;
                //  add bonus catch power if monster hp <= 50%, again if hp <= 20%/hp <= 3
                if (monster.hpMax / monster.hpCurr >= 2) {
                    catchRate += power;
                }
                if ((monster.hpMax / monster.hpCurr >= 5) || monster.hpCurr <= 3) {
                    catchRate += power;
                }
                //  lower catch rate for bosses
                if (monster.bossMonster) {
                    catchRate -= 100;
                }
                //  limit catch rate to 100%
                if (catchRate > 100) {
                    catchRate = 100;
                }
                if (catchRate < 0) {
                    catchRate = 0;
                }
                removeItem(1);
                player.ballsInBag--;
                gameLogLabel.setText(player.name + " threw a " + name + "... (" + catchRate + "%)");
                //  if catch succeeds
                if (catchSuccess <= catchRate) {
                    gameLogLabel.setText(gameLogLabel.getText() + "\nCapture successful!");
                    monster.setHpCurr(monster.hpMax);
                    monster.playerOwned = true;
                    player.pc.add(monster.name, monster);
                    player.setScore(player.score + (monster.xpYield + (monster.xpYield / 2)));
                    player.addMonstersCaught(monster.type);
                    player.setInBattle(false);
                }
                //  if catch fails
                else {
                    gameLogLabel.setText(gameLogLabel.getText() + "\nCapture failed!");
                }
            }
        }
        //  if item is a revive
        else if (isRevive) {
            //  if monster is fainted
            if (monster.fainted) {
                monster.setHpCurr(monster.hpMax / 2);
                monster.fainted = false;
                removeItem(1);
                player.revivesInBag--;
                if (amount <= 0) {
                    player.itemInUse = null;
                }
                gameLogLabel.setText(monster.name + " was revived!");
            } else {
                gameLogLabel.setText(monster.name + " isn't fainted!");
            }
        }
    }

    //  add items
    public void addItem(int amount) {
        this.amount += amount;
    }
    //  remove items
    public void removeItem(int amount) {
        this.amount -= amount;
    }
}
