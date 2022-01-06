package com.example.monstergame;

import javafx.scene.paint.Paint;

import java.util.Objects;

public class Type implements java.io.Serializable {
    //  type attributes
    String name;
    int id; // 0=normal,1=fire,2=water,3=earth,4=electric,5=nature,6=wind,7=ice
    transient Paint color;

    //  type match-ups while attacking
    StorageList<String> strongAgainst = new StorageList<>(); // super effective
    StorageList<String> weakAgainst = new StorageList<>(); // not very effective
    StorageList<String> neutralAgainst = new StorageList<>(); // neutral damage

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
            neutralAgainst.add(normal, normal);
            neutralAgainst.add(fire, fire);
            neutralAgainst.add(water, water);
            neutralAgainst.add(earth, earth);
            neutralAgainst.add(electric, electric);
            neutralAgainst.add(nature, nature);
            neutralAgainst.add(wind, wind);
            neutralAgainst.add(ice, ice);
        }
        //  if type is fire
        else if (Objects.equals(name, "Fire")) {
            //  super effective damage
            strongAgainst.add(nature, nature);
            strongAgainst.add(wind, wind);
            strongAgainst.add(ice, ice);
            //  not very effective damage
            weakAgainst.add(fire, fire);
            weakAgainst.add(water, water);
            //  neutral damage
            neutralAgainst.add(normal, normal);
            neutralAgainst.add(earth, earth);
            neutralAgainst.add(electric, electric);
        }
        //  if type is water
        else if (Objects.equals(name, "Water")) {
            //  super effective damage
            strongAgainst.add(fire, fire);
            strongAgainst.add(earth, earth);
            //  not very effective damage
            weakAgainst.add(nature, nature);
            weakAgainst.add(ice, ice);
            //  neutral damage
            neutralAgainst.add(normal, normal);
            neutralAgainst.add(water, water);
            neutralAgainst.add(electric, electric);
            neutralAgainst.add(wind, wind);
        }
        //  if type is earth
        else if (Objects.equals(name, "Earth")) {
            //  super effective damage
            strongAgainst.add(fire, fire);
            strongAgainst.add(electric, electric);
            //  not very effective damage
            weakAgainst.add(nature, nature);
            //  neutral damage
            neutralAgainst.add(normal, normal);
            neutralAgainst.add(water, water);
            neutralAgainst.add(wind, wind);
            neutralAgainst.add(ice, ice);
        }
        //  if type is electric
        else if (Objects.equals(name, "Electric")) {
            //  super effective damage
            strongAgainst.add(water, water);
            strongAgainst.add(wind, wind);
            //  not very effective against
            weakAgainst.add(earth, earth);
            weakAgainst.add(electric, electric);
            //  neutral against
            neutralAgainst.add(normal, normal);
            neutralAgainst.add(fire, fire);
            neutralAgainst.add(nature, nature);
            neutralAgainst.add(ice, ice);
        }
        //  if type is nature
        else if (Objects.equals(name, "Nature")) {
            //  super effective damage
            strongAgainst.add(water ,water);
            strongAgainst.add(earth, earth);
            //  not very effective damage
            weakAgainst.add(fire, fire);
            weakAgainst.add(wind, wind);
            //  neutral damage
            neutralAgainst.add(normal, normal);
            neutralAgainst.add(electric, electric);
            neutralAgainst.add(nature, nature);
            neutralAgainst.add(ice, ice);
        }
        //  if type is wind
        else if (Objects.equals(name, "Wind")) {
            //  super effective damage
            strongAgainst.add(nature, nature);
            //  not very effective damage
            weakAgainst.add(electric, electric);
            weakAgainst.add(ice, ice);
            //  neutral damage
            neutralAgainst.add(normal, normal);
            neutralAgainst.add(fire, fire);
            neutralAgainst.add(water ,water);
            neutralAgainst.add(earth, earth);
            neutralAgainst.add(wind, wind);
        }
        //  if type is ice
        else if (Objects.equals(name, "Ice")) {
            //  super effective damage
            strongAgainst.add(nature, nature);
            strongAgainst.add(wind, wind);
            //  not very effective damage
            weakAgainst.add(fire, fire);
            weakAgainst.add(ice, ice);
            //  neutral damage
            neutralAgainst.add(normal, normal);
            neutralAgainst.add(water, water);
            neutralAgainst.add(electric, electric);
            neutralAgainst.add(earth, earth);

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
