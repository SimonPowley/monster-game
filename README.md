# Monster Game
A Pokemon inspired game built with Java

# Intro

Started out as a simple text based monster fight with 2 random monsters.
I've been adding on to the game as I learn how to do stuff/get new ideas.
I don't really know how to make nice graphics yet, so graphics are done with javafx.
I can see it maybe becoming some kind of idle/rouguelike monster fighting/training mobile game. 
Intellij project for now, I'm not sure if there's a better/more convenient way to structure/upload projects here.


# Gameplay

Currently the game is basically:
- Start new game (or load saved game)
- Choose starting monster from 3 options (fire, water, or nature type)
- Battle wild monsters to gain money, xp and level up
- Check and use the items in your bag
- Buy items from the shop
- Capture wild monsters
- Check your monster team and their stats, change team leader, change monster names, release monsters
- Get quests randomly after defeating wild monsters, quests can currently be: fight x wild monsters(specific or unspecific type), battle a trainer, or battle a boss monster
- Player score goes up after defeating monsters and completing quests
- Save game, return to title screen

# Monsters

Monsters are randomly generated to make each one unique, and to give each one different utility in battle (hopefully)
- Random stats (within a range) that grow at different rates (slow, medium, fast)
- Potential affinities for certain stats (+/- x%)
- Random type, some are more common than others
- Random moves to be used in battle

Monsters currently have four stats: health, attack, defense, speed
- Health (HP): total health in battle, when health reaches 0 the monster faints and cannot battle until revived
- Attack (Att): power/damage potential, higher attack = higher damage dealt
- Defense (Def): sturdiness/damage reduction potential, higher defense = less damage taken
- Speed (Spe): how fast a monster is, higher speed moves before lower speed in battle (speed ties decided randomly each turn)

# Possible things to do/add/change

Honestly the whole thing is pretty messy and needs to be redone lol
- I'm thinking of making it so you can combine/breed monsters to control/customize their stats and types
- Add more types: poison (purple), dark (dark grey), ??, idk
- If there's an easy way to blend color shades in Javafx, I could make each mosnter have unique(ish) colors, maybe affected by breeding/merging? They'd probably all end up a muddy color eventually though lol, maybe just a set variance of shades for each type/type combo?
- Need to add some kind of boss/progression system, more item types (equipable?), more (convenient) monster storage space
- Need ways to add personality/uniqueness/utility to each monster, actual unique attack moves with different power/effects, etc
- More balancing for monster stat progression/battling, item stats
- Different areas? Some areas have more of a certain type, some are higher leveled, different bosses, etc
- Maybe change how speed stat affects battle flow? (instead of > speed moving first each turn, if speed is 2* opponent's you move twice, etc?)
- The "modes" I use in the scene controllers are kind of dumb and confusing lol (a lot of the scene switching stuff is tbh)
- Maybe a better way to save the game, because it seems "flimsy" the way it is now (just messy and kind of simple, idk maybe it's not)
