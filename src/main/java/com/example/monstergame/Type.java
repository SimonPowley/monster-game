package com.example.monstergame;

import javafx.scene.paint.Paint;

import java.util.LinkedList;
import java.util.Objects;

public class Type implements java.io.Serializable {
    //  type attributes
    String name;
    int id; // 0=normal,1=fire,2=water,3=earth,4=electric,5=nature,6=wind,7=ice
    transient Paint color;

    //  type match-ups while attacking
    LinkedList<String> strongAgainst = new LinkedList<>(); // super effective
    LinkedList<String> weakAgainst = new LinkedList<>(); // not very effective
    LinkedList<String> neutralAgainst = new LinkedList<>(); // neutral damage

    //  type names
    String normal = "Normal";
    String fire = "Fire";
    String water = "Water";
    String earth = "Earth";
    String electric = "Electric";
    String nature = "Nature";
    String wind = "Wind";
    String ice = "Ice";
    //  type colors
    Paint normalColor = Paint.valueOf("#a8a8a8");
    Paint fireColor = Paint.valueOf("#ff2424");
    Paint waterColor = Paint.valueOf("#5252ff");
    Paint earthColor = Paint.valueOf("#ad7100");
    Paint electricColor = Paint.valueOf("#ffff24");
    Paint natureColor = Paint.valueOf("#00a800");
    Paint windColor = Paint.valueOf("#eadbc8");
    Paint iceColor = Paint.valueOf("#cff2ff");



    //  create a new type
    public Type(String name, int id) {
        this.id = id;
        setName(name);
        setTypeMatchUp();
        setColor();
    }

    //  set type name
    public void setName(String name) {
        this.name = name;
    }

    //  set type match ups
    public void setTypeMatchUp() {
        //  if type is normal
        if (Objects.equals(name, "Normal")) {
            //  neutral damage
            neutralAgainst.add(normal);
            neutralAgainst.add(fire);
            neutralAgainst.add(water);
            neutralAgainst.add(earth);
            neutralAgainst.add(electric);
            neutralAgainst.add(nature);
            neutralAgainst.add(wind);
            neutralAgainst.add(ice);
        }
        //  if type is fire
        else if (Objects.equals(name, "Fire")) {
            //  super effective damage
            strongAgainst.add(nature);
            strongAgainst.add(wind);
            strongAgainst.add(ice);
            //  not very effective damage
            weakAgainst.add(fire);
            weakAgainst.add(water);
            //  neutral damage
            neutralAgainst.add(normal);
            neutralAgainst.add(earth);
            neutralAgainst.add(electric);
        }
        //  if type is water
        else if (Objects.equals(name, "Water")) {
            //  super effective damage
            strongAgainst.add(fire);
            strongAgainst.add(earth);
            //  not very effective damage
            weakAgainst.add(nature);
            weakAgainst.add(ice);
            //  neutral damage
            neutralAgainst.add(normal);
            neutralAgainst.add(water);
            neutralAgainst.add(electric);
            neutralAgainst.add(wind);
        }
        //  if type is earth
        else if (Objects.equals(name, "Earth")) {
            //  super effective damage
            strongAgainst.add(fire);
            strongAgainst.add(electric);
            //  not very effective damage
            weakAgainst.add(nature);
            //  neutral damage
            neutralAgainst.add(normal);
            neutralAgainst.add(water);
            neutralAgainst.add(wind);
            neutralAgainst.add(ice);
        }
        //  if type is electric
        else if (Objects.equals(name, "Electric")) {
            //  super effective damage
            strongAgainst.add(water);
            strongAgainst.add(wind);
            //  not very effective against
            weakAgainst.add(earth);
            weakAgainst.add(electric);
            //  neutral against
            neutralAgainst.add(normal);
            neutralAgainst.add(fire);
            neutralAgainst.add(nature);
            neutralAgainst.add(ice);
        }
        //  if type is nature
        else if (Objects.equals(name, "Nature")) {
            //  super effective damage
            strongAgainst.add(water);
            strongAgainst.add(earth);
            //  not very effective damage
            weakAgainst.add(fire);
            weakAgainst.add(wind);
            //  neutral damage
            neutralAgainst.add(normal);
            neutralAgainst.add(electric);
            neutralAgainst.add(nature);
            neutralAgainst.add(ice);
        }
        //  if type is wind
        else if (Objects.equals(name, "Wind")) {
            //  super effective damage
            strongAgainst.add(nature);
            //  not very effective damage
            weakAgainst.add(electric);
            weakAgainst.add(ice);
            //  neutral damage
            neutralAgainst.add(normal);
            neutralAgainst.add(fire);
            neutralAgainst.add(water);
            neutralAgainst.add(earth);
            neutralAgainst.add(wind);
        }
        //  if type is ice
        else if (Objects.equals(name, "Ice")) {
            //  super effective damage
            strongAgainst.add(nature);
            strongAgainst.add(wind);
            //  not very effective damage
            weakAgainst.add(fire);
            weakAgainst.add(ice);
            //  neutral damage
            neutralAgainst.add(normal);
            neutralAgainst.add(water);
            neutralAgainst.add(electric);
            neutralAgainst.add(earth);

        }
    }

    //  set type color
    public void setColor() {
        if (id == 0) {
            color = normalColor;
        }
        else if (id == 1) {
            color = fireColor;
        }
        else if (id == 2) {
            color = waterColor;
        }
        else if (id == 3) {
            color = earthColor;
        }
        else if (id == 4) {
            color = electricColor;
        }
        else if (id == 5) {
            color = natureColor;
        }
        else if (id == 6){
            color = windColor;
        }
        else {
            color = iceColor;
        }
    }

}
