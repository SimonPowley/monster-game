# MonsterGame
Pokemon clone game Java project

Intro
* Started out as a simple text based monster fight with 2 random monsters,
* I've been adding on to the game as I learn how to do stuff/get new ideas,
* I don't really know how to make nice graphics yet, so graphics are done with javafx
* I can see it maybe becoming some kind of idle/rouguelike monster fighting/training mobile game idk lol
* Intellij project for now, idk if there's a better/more convenient way to structure/upload projects here


* Gameplay

Currently the game is basically:
start new game (or load saved game),
enter player name,
choose starting monster from 3 options,
battle wild monsters to gain xp and level up,
buy items from the shop, catch wild monsters,
check and use the items in your bag,
check your monster team and their stats/change team leader/change monster name/release monster,
get quests randomly after defeating wild monsters, quests can be: fight x wild monsters(specific or unspecific type) or battle a trainer,
player score goes up after defeating monsters and completing quests,
save game, return to title screen


* Things to do/add/change

Honestly the whole thing is pretty messy and needs to be redone lol
the "StorageList" and "StorageNode" classes are from a comp sci class, and I just wanted an excuse to incorporate them, 
could probably just use a built in Java structure to cut those files out
I'm thinking of making it so you can combine/breed monsters to control/customize their stats and types
adding more types: poison (purple), ??, idk
if there's an easy way to blend color shades, I could make each mosnter have unique(ish) colors, maybe affected by breeding/merging?
they'd probably all end up a muddy color eventually though lol, maybe just a set variance of shades for each type/type combo
also need to add some kind of boss/progression system, more item types (equipable?), more (convenient) monster storage space
need ways to add personality/uniqueness/utility to each monster, actual unique attack moves with different power/effects
more balancing for monster stat progression/battling, item stats
different areas? some areas have more of a certain type, some are higher leveled, different bosses, etc
the "modes" I use in the scene controllers are kind of dumb and confusing lol (a lot of the scene switching stuff is tbh)
