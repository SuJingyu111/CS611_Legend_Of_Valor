Legend: Monster And Heros
Jingyu Su U58115442
Shen Yan U92118991

Compilation & running instructions:
compile: javac *.java
run: java Main

Program Specs:
The cells in the game is visually represented using characters surrounding the grid. P for plain cell, C for cave cell, B for bush, K for koulou, N for nexus, I for inaccessible cells. Within a cell, there could possibly be characters H standing for Hero, and M standing for Monster.

The program was compiled and executed on a machine running MACOS.

Colorful printing implemented.

Design patterns:
Factory pattern, used in HeroFactory and ItemFactory
Singleton Pattern, used in ItemFactory and MonsterFactory. Do not want more than 1, used only in Market & Common Cells.

Class Intro:
Main
- Main is the class that actually runs.

LOV
- LOV is the class for game entity.

Armory
- Class for Armors.

Bush
- Cell type Bush, inherits Cell. Also implements HeroBoostable.

Cave
- Cell type Cave, inherits Cell. Also implements HeroBoostable.

Cell
- Class for general cells in the map, inherited by Bush, Cave, Koulou, PlainCell.

CellFactory
- A factory that generates difference kinds of cells.

Dragons
- Monster Dragon, inherits Monster

Exoskeletons
- Monster Exoskeleton, inherits Monster

Fight
- Class that initializes and executes the fighting process

Figure
- Class for general figures, inherited by Monster and Hero

FireSpells
- Class for FireSpells, inherits Spell

Game
- Class of general games, inherited by RPG.

Hero
- Class for general heroes, inherits Figure, inherited by Warrior, Paladin, and Sorcerer

HeroBoostable
- Interface for cells that boost hero stats. Implemented by Bush, Cave and Koulou.

HeroFactory
- Factory that creates heroes.

HeroNexus
- Nexus for Heroes. Implements Nexus.

HeroTeam
- Team of heroes, implements Iterable<String> and inherits Team.

IceSpells
- Class for IceSpells, inherits Spell.

InaccessibleCell
- Class for cells that are not accessible, inherits Cell.

Inventory
- Inventory class that holds items for both hero and market, implements Iterable<String>

Item
- Class for general items, inherited by Armory, Spell, Potion, Weaponry

ItemFactory
- Factory class that creates all kinds of items

Koulou
- Cell type Koulou, inherits Cell. Also implements HeroBoostable.

Lane
- Class for lanes in World. Each lane is composed of cells.

LaneFactory
- Factory that creates lanes.

LightningSpells
- Class for lightning spells, inherits Spell

Market
- Class for market cells, inherits Cell and inherited by HeroNexus.

Monster
- Class for general monsters, inherits Figure, inherited by Dragon, Exoskeleton, and Spirit

MonsterFactory
- Factory class that creates monsters

Monsternexus
- Nexus for Monsters. Implements Nexus.

MonsterTeam
- Team of monsters, implements Iterable<String> and inherits Team.

Nexus
- Interface for all types of nexus in World. Implemented by HeroNexus and MonsterNexus.

Paladin
- Class for paladin, inherits Hero

PlainCell
- Cell type PlainCell, inherits Cell. 

Potions
- Class for potions, inherits Item

RPG
- Class for all RPG games. Inherits Game and inherited by LOV. 

Sorcerer
- Class for sorcerer, inherits Hero

Spell
- Abstract class for spells, inherits Item, inherited by FireSpells, IceSpells, LightningSpells

Spirits
- Class for monster spirit, inherits Monster

Team
- Class for general teams in game. Inherited by HeroTeam and MonsterTeam.

Utils
- Utils class for all kinds of handy methods

Warrior
- Class for warriors, inherits Hero

Weaponry
- Class for weapons, inherits Item

World
- Class for the world the game is played in