package com.example.monstergame;

import java.util.*;

public class Move implements java.io.Serializable {
    String name;
    transient Type type;
    int damage;
    int level;
    //  effect
    String info;
    //  move list for each type
    LinkedList<Move> normalMoves = new LinkedList<>();
    LinkedList<Move> fireMoves = new LinkedList<>();
    LinkedList<Move> waterMoves = new LinkedList<>();
    LinkedList<Move> earthMoves = new LinkedList<>();
    LinkedList<Move> electricMoves = new LinkedList<>();
    LinkedList<Move> natureMoves = new LinkedList<>();
    LinkedList<Move> windMoves = new LinkedList<>();
    LinkedList<Move> iceMoves = new LinkedList<>();



    public Move() {
        setNormalMoves();
        setFireMoves();
        setWaterMoves();
        setEarthMoves();
        setElectricMoves();
        setNatureMoves();
        setWindMoves();
        setIceMoves();
    }
    public Move(String name, Type type, int damage, int level, String info) {
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.level = level;
        this.info = info;
    }

    //  fill move lists with moves from each type
    public void setNormalMoves() {
        Type normal = new Type("Normal", 0);
        //  basic moves
        normalMoves.add(new Move("Tackle", normal, 20, 10, "User tackles the foe with a light force"));
        normalMoves.add(new Move("Scratch", normal, 20, 10, "User scratches the foe with sharp claws"));
        normalMoves.add(new Move("Bite", normal, 20, 10,"User bites the foe with sharp fangs"));
        normalMoves.add(new Move("Peck", normal, 20, 10, "User pecks the foe with a sharp beak"));
        //  intermediate moves
        normalMoves.add(new Move("Slam", normal, 40, 20, "User slams into the foe with a heavy force"));
        normalMoves.add(new Move("Stomp", normal, 40, 20, "User stomps on the foe with a heavy foot"));
        normalMoves.add(new Move("Smash", normal, 40, 20, "User smashes the foe with a heavy blow"));
        //  strong moves
        normalMoves.add(new Move("Pummel", normal, 60, 30, "User pummels the foe with powerful blows"));
        normalMoves.add(new Move("Slash", normal, 60, 30, "User slashes the foe with powerful claws"));
        normalMoves.add(new Move("Crunch", normal, 60, 30, "User crunches the foe with powerful fangs"));
        //  effect moves
    }
    public void setFireMoves() {
        Type fire = new Type("Fire", 1);
        //  basic moves
        fireMoves.add(new Move("Ember", fire, 20, 10, "User shoots the foe with a weak flame"));
        fireMoves.add(new Move("Burn", fire, 20, 10, "User burns the foe with a warm heat"));
        //  intermediate moves
        fireMoves.add(new Move("Blaze", fire, 40, 20, "User shoots the foe with a hot flame"));
        fireMoves.add(new Move("Boil", fire, 40, 20, "User burns the foe with high heat"));
        //  strong moves
        fireMoves.add(new Move("Flamethrower", fire, 60, 30, "User shoots the foe with a powerful flame"));
        fireMoves.add(new Move("Steamroller", fire, 60, 30, "User rolls into the foe with a powerful heat"));
        fireMoves.add(new Move("Eruption", fire, 60, 30, "User showers the foe with burning debris"));
        //  effect moves
        fireMoves.add(new Move("Kindle", fire, 0, 0, ""));
    }
    public void setWaterMoves() {
        Type water = new Type("Water", 2);
        //  basic moves
        waterMoves.add(new Move("Bubble", water, 20, 10, "User shoots the foe with weak bubbles"));
        waterMoves.add(new Move("Splash", water, 20, 10, "User soaks the foe with a weak splash of water"));
        //  intermediate moves
        waterMoves.add(new Move("Whirlpool", water, 40, 20, "User swirls the foe around with a small whirlpool"));
        waterMoves.add(new Move("Flood", water, 40, 20, "User soaks the foe with a small wave"));
        //  strong moves
        waterMoves.add(new Move("Surf", water, 60, 30, "User shoots the foe with a powerful stream of water"));
        waterMoves.add(new Move("Tsunami", water, 60, 30, "User drenches the foe with a powerful wave"));
        waterMoves.add(new Move("Downpour", water, 60, 30, "user showers the foe with powerful drops of water"));
        //  effect moves
    }
    public void setEarthMoves() {
        Type earth = new Type("Earth", 3);
        //  basic moves
        earthMoves.add(new Move("Dig", earth, 20, 10, "User attacks the foe from underground"));
        earthMoves.add(new Move("Rumble", earth, 20, 10, "User shakes the foe with a weak rumble"));
        //  intermediate moves
        earthMoves.add(new Move("Mudslide", earth, 40, 20, "User buries the foe with mud"));
        earthMoves.add(new Move("Tremor", earth, 40, 20, "User shakes the foe with a small tremor"));
        //  strong moves
        earthMoves.add(new Move("Landslide", earth, 60, 30, "User buries the foe with debris"));
        earthMoves.add(new Move("Earthquake", earth, 60, 30, "User rocks the foe with a powerful earthquake"));
        earthMoves.add(new Move("Sinkhole", earth, 60, 30, "User collapses the ground underneath the foe"));
        //  effect moves
    }
    public void setElectricMoves() {
        Type electric = new Type("Electric", 4);
        //  basic moves
        electricMoves.add(new Move("Zap", electric, 20, 10, "User zaps the foe with a weak charge"));
        electricMoves.add(new Move("Stun", electric, 20, 10, "User stuns the foe with a weak spark"));
        //  intermediate moves
        electricMoves.add(new Move("Shock", electric, 40, 20, "User shocks the foe with a small charge"));
        electricMoves.add(new Move("Spark", electric, 40, 20, "User shoots the foe with a small spark"));
        //  strong moves
        electricMoves.add(new Move("Electrocute", electric, 60, 30, "User electrocutes the foe with a powerful charge"));
        electricMoves.add(new Move("Thunderbolt", electric, 60, 30, "User shoots the foe with a powerful thunderbolt"));
        electricMoves.add(new Move("Discharge", electric, 60, 30, "User showers the foe with powerful bursts of electricity"));
        //  effect moves
        electricMoves.add(new Move("Charge", electric, 0, 0, ""));
    }
    public void setNatureMoves() {
        Type nature = new Type("Nature", 5);
        //  basic moves
        natureMoves.add(new Move("Thorns", nature, 20, 10, "User stabs the foe with sharp thorns"));
        natureMoves.add(new Move("Spores", nature, 20, 10, "User surrounds the foe with weak spores"));
        //  intermediate moves
        natureMoves.add(new Move("Vines", nature, 40, 20, "User whips the foe with small vines"));
        natureMoves.add(new Move("Leafage", nature, 40, 20, "User cuts the foe with small leaves"));
        //  strong moves
        natureMoves.add(new Move("Timber", nature, 60, 30, "User smashes the foe with a powerful log"));
        natureMoves.add(new Move("Overgrow", nature, 60, 30, "User overwhelms the foe with powerful plant growth"));
        natureMoves.add(new Move("Ingrain", nature, 60, 30, "User attaches to the foe with powerful roots"));
        //  effect moves
    }
    public void setWindMoves() {
        Type wind = new Type("Wind", 6);
        //  basic moves
        windMoves.add(new Move("Gust", wind, 20, 10, "User blows the foe with a weak gust of wind"));
        windMoves.add(new Move("Whirlwind", wind, 20, 10, "User whips the foe around with a weak whirlwind"));
        //  intermediate moves
        windMoves.add(new Move("Squall", wind, 40, 20, "User blows the foe with a small squall"));
        windMoves.add(new Move("Twister", wind, 40, 20, "User whips the foe around with a small twister"));
        //  strong moves
        windMoves.add(new Move("Hurricane", wind, 60, 30, "User blows the foe with a powerful hurricane"));
        windMoves.add(new Move("Tornado", wind, 60, 30, "User whips the foe around with a powerful tornado"));
        windMoves.add(new Move("Typhoon", wind, 60, 30, "User blows the foe with powerful winds"));
        //  effect moves
    }
    public void setIceMoves() {
        Type ice = new Type("Ice", 7);
        //  basic moves
        iceMoves.add(new Move("Flurry", ice, 20, 10, "User freezes the foe with a weak snow flurry"));
        iceMoves.add(new Move("Snowball", ice, 20, 10, "User pelts the foe with a weak snowball"));
        //  intermediate moves
        iceMoves.add(new Move("Frostbite", ice, 40, 20, "User bites the foe with frozen fangs"));
        iceMoves.add(new Move("Hail", ice, 40, 20, "User pelts the foe with small hailstones"));
        //  strong moves
        iceMoves.add(new Move("Blizzard", ice, 60, 30, "User freezes the foe with a powerful blizzard"));
        iceMoves.add(new Move("Avalanche", ice, 60, 30, "User buries the foe with a powerful avalanche"));
        iceMoves.add(new Move("Subzero", ice, 60, 30, "User freezes the foe with subzero temperatures"));
        //  effect moves
    }

    //  create an individual monster move list, based on type
    public LinkedList<Move> createMoveList(Type type) {
        //  move list to return
        LinkedList<Move> moveList = new LinkedList<>();
        //  create normal type move list
        if (Objects.equals(type.name, "Normal")) {
            moveList = addBasicMoves(normalMoves, moveList);
            moveList = addIntermediateMoves(normalMoves, moveList);
            moveList = addStrongMoves(normalMoves, moveList);
        }
        //  create fire type move list
        if (Objects.equals(type.name, "Fire")) {
            moveList = addBasicMoves(fireMoves, moveList);
            moveList = addIntermediateMoves(fireMoves, moveList);
            moveList = addStrongMoves(fireMoves, moveList);
        }
        //  create water type move list
        if (Objects.equals(type.name, "Water")) {
            moveList = addBasicMoves(waterMoves, moveList);
            moveList = addIntermediateMoves(waterMoves, moveList);
            moveList = addStrongMoves(waterMoves, moveList);
        }
        //  create earth type move list
        if (Objects.equals(type.name, "Earth")) {
            moveList = addBasicMoves(earthMoves, moveList);
            moveList = addIntermediateMoves(earthMoves, moveList);
            moveList = addStrongMoves(earthMoves, moveList);
        }
        //  create electric type move list
        if (Objects.equals(type.name, "Electric")) {
            moveList = addBasicMoves(electricMoves, moveList);
            moveList = addIntermediateMoves(electricMoves, moveList);
            moveList = addStrongMoves(electricMoves, moveList);
        }
        //  create nature type move list
        if (Objects.equals(type.name, "Nature")) {
            moveList = addBasicMoves(natureMoves, moveList);
            moveList = addIntermediateMoves(natureMoves, moveList);
            moveList = addStrongMoves(natureMoves, moveList);
        }
        //  create wind type move list
        if (Objects.equals(type.name, "Wind")) {
            moveList = addBasicMoves(windMoves, moveList);
            moveList = addIntermediateMoves(windMoves, moveList);
            moveList = addStrongMoves(windMoves, moveList);
        }
        //  create ice type move list
        if (Objects.equals(type.name, "Ice")) {
            moveList = addBasicMoves(iceMoves, moveList);
            moveList = addIntermediateMoves(iceMoves, moveList);
            moveList = addStrongMoves(iceMoves, moveList);
        }
        moveList.sort(Comparator.comparingInt(o -> o.level));
        moveList = adjustLevels(moveList);
        return moveList;
    }

    //  add moves and set learn level for individual move list
    public LinkedList<Move> addBasicMoves(LinkedList<Move> typeMoveList, LinkedList<Move> moveList) {
        Random rand = new Random();
        Move move;
        //  add a default basic move (normal type)
        while (moveList.size() < 1) {
            move = normalMoves.get(rand.nextInt(normalMoves.size()));
            if (move.damage == 20 && !moveList.contains(move)) {
                move.level = 1;
                moveList.add(move);
            }
        }
        //  add 2 basic moves (either 2x type, or 1x type and 1x normal type)
        int numMoves = rand.nextInt(2);
        //  add 2 type moves
        if (numMoves == 0) {
            while (moveList.size() < 3) {
                move = typeMoveList.get(rand.nextInt(typeMoveList.size()));
                if (move.damage == 20 && !moveList.contains(move)) {
                    move.level = rand.nextInt(10) + 1;
                    moveList.add(move);
                }
            }
        }
        //  add 1 type move, 1 normal move
        else {
            //  add type move
            while (moveList.size() < 2) {
                move = typeMoveList.get(rand.nextInt(typeMoveList.size()));
                if (move.damage == 20 && !moveList.contains(move)) {
                    move.level = rand.nextInt(10) + 1;
                    moveList.add(move);
                }
            }
            //  add normal move
            while (moveList.size() < 3) {
                move = normalMoves.get(rand.nextInt(normalMoves.size()));
                if (move.damage == 20 && !moveList.contains(move)) {
                    move.level = rand.nextInt(10) + 1;
                    moveList.add(move);
                }
            }
        }
        return moveList;
    }
    public LinkedList<Move> addIntermediateMoves(LinkedList<Move> typeMoveList, LinkedList<Move> moveList) {
        Random rand = new Random();
        Move move;
        //  add a default intermediate move (normal type)
        while (moveList.size() < 4) {
            move = normalMoves.get(rand.nextInt(normalMoves.size()));
            if (move.damage == 40 && !moveList.contains(move)) {
                move.level = 11;
                moveList.add(move);
            }
        }
        //  add 2 intermediate moves (either 2x type, or 1x type and 1x normal type)
        int numMoves = rand.nextInt(2);
        //  add 2 type moves
        if (numMoves == 0) {
            while (moveList.size() < 6) {
                move = typeMoveList.get(rand.nextInt(typeMoveList.size()));
                if (move.damage == 40 && !moveList.contains(move)) {
                    move.level = rand.nextInt(10) + 10;
                    moveList.add(move);
                }
            }
        }
        //  add 1 type move, 1 normal move
        else {
            //  add type move
            while (moveList.size() < 5) {
                move = typeMoveList.get(rand.nextInt(typeMoveList.size()));
                if (move.damage == 40 && !moveList.contains(move)) {
                    move.level = rand.nextInt(10) + 10;
                    moveList.add(move);
                }
            }
            //  add normal move
            while (moveList.size() < 6) {
                move = normalMoves.get(rand.nextInt(normalMoves.size()));
                if (move.damage == 40 && !moveList.contains(move)) {
                    move.level = rand.nextInt(10) + 10;
                    moveList.add(move);
                }
            }
        }
        return moveList;
    }
    public LinkedList<Move> addStrongMoves(LinkedList<Move> typeMoveList, LinkedList<Move> moveList) {
        Random rand = new Random();
        Move move;
        //  add a default strong move (normal type)
        while (moveList.size() < 7) {
            move = normalMoves.get(rand.nextInt(normalMoves.size()));
            if (move.damage == 60 && !moveList.contains(move)) {
                move.level = 21;
                moveList.add(move);
            }
        }
        //  add 2 strong moves (either 2x type, or 1x type and 1x normal type)
        int numMoves = rand.nextInt(2);
        //  add 2 type moves
        if (numMoves == 0) {
            while (moveList.size() < 9) {
                move = typeMoveList.get(rand.nextInt(typeMoveList.size()));
                if (move.damage == 60 && !moveList.contains(move)) {
                    move.level = rand.nextInt(10) + 20;
                    moveList.add(move);
                }
            }
        }
        //  add 1 type move, 1 normal move
        else {
            //  add type move
            while (moveList.size() < 8) {
                move = typeMoveList.get(rand.nextInt(typeMoveList.size()));
                if (move.damage == 60 && !moveList.contains(move)) {
                    move.level = rand.nextInt(10) + 20;
                    moveList.add(move);
                }
            }
            //  add normal move
            while (moveList.size() < 9) {
                move = normalMoves.get(rand.nextInt(normalMoves.size()));
                if (move.damage == 60 && !moveList.contains(move)) {
                    move.level = rand.nextInt(10) + 20;
                    moveList.add(move);
                }
            }
        }
        return moveList;
    }

    //  adjust move learn levels to avoid multiple same level moves
    public LinkedList<Move> adjustLevels(LinkedList<Move> moveList) {
        Move nextMove = moveList.get(1);
        for (int i = 0; i < moveList.size()-1; i++) {
            //  if next level in move list is <=, raise it's level
            if (moveList.get(i).level >= nextMove.level) {
                moveList.get(i+1).level++;
            }
            nextMove = moveList.get(i+1);
        }
        return moveList;
    }

    //  reload types for all moves
    public void reloadTypes() {
        //  reload normal types
        Type normal = new Type("Normal", 0);
        for (Move normalMove : normalMoves) {
            normalMove.type = normal;
        }
        //  reload fire types
        Type fire = new Type("Fire", 1);
        for (Move fireMove : fireMoves) {
            fireMove.type = fire;
        }
        //  reload water types
        Type water = new Type("Water", 2);
        for (Move waterMove : waterMoves) {
            waterMove.type = water;
        }
        //  reload earth types
        Type earth = new Type("Earth", 3);
        for (Move earthMove : earthMoves) {
            earthMove.type = earth;
        }
        //  reload electric types
        Type electric = new Type("Electric", 4);
        for (Move electricMove : electricMoves) {
            electricMove.type = electric;
        }
        //  reload nature types
        Type nature = new Type("Nature", 5);
        for (Move natureMove : natureMoves) {
            natureMove.type = nature;
        }
        //  reload wind types
        Type wind = new Type("Wind", 6);
        for (Move windMove : windMoves) {
            windMove.type = wind;
        }
        //  reload ice types
        Type ice = new Type("Ice", 7);
        for (Move iceMove : iceMoves) {
            iceMove.type = ice;
        }
    }
    //  reload type for individual move
    public LinkedList<Move> reloadMove(LinkedList<Move> moveList) {
        reloadTypes();
        for (Move move : moveList) {
            //  normal type
            for (Move normalMove : normalMoves) {
                if (Objects.equals(move.name, normalMove.name)) {
                    move.type = normalMove.type;
                }
            }
            //  fire type
            for (Move fireMove : fireMoves) {
                if (Objects.equals(move.name, fireMove.name)) {
                    type = fireMove.type;
                }
            }
            //  water type
            for (Move waterMove : waterMoves) {
                if (Objects.equals(move.name, waterMove.name)) {
                    type = waterMove.type;
                }
            }
            //  earth type
            for (Move earthMove : earthMoves) {
                if (Objects.equals(move.name, earthMove.name)) {
                    type = earthMove.type;
                }
            }
            //  electric type
            for (Move electricMove : electricMoves) {
                if (Objects.equals(move.name, electricMove.name)) {
                    type = electricMove.type;
                }
            }
            //  nature type
            for (Move natureMove : natureMoves) {
                if (Objects.equals(move.name, natureMove.name)) {
                    type = natureMove.type;
                }
            }
            //  wind type
            for (Move windMove : windMoves) {
                if (Objects.equals(move.name, windMove.name)) {
                    type = windMove.type;
                }
            }
            //  ice type
            for (Move iceMove : iceMoves) {
                if (Objects.equals(move.name, iceMove.name)) {
                    type = iceMove.type;
                }
            }
        }
        return moveList;
    }
}
