Legend: Monster And Heros
Jingyu Su U58115442

Compilation & running instructions:
compile: javac *.java
run: java Main

Program Specs:
The cells in the game is visually represented using a single character. C for common cell, M for market, I for inaccessible cells.
Upon entry of a common cell, there is a 40% chance to run into monsters of the same number as heroes. This chance is defined in CommonCell.MEET_MONSTER_PROB, it is scaled out of 10.
The program was compiled and executed on a machine running WIN10

Design patterns:
Factory pattern, used in HeroFactory and ItemFactory
Singleton Pattern, used in ItemFactory and MonsterFactory. Do not want more than 1, used only in Market & Common Cells.

Class Intro:
Main
- Main is the class that actually runs.

LMH
- LMH is the class for game entity.

Armory
- Class for Armors.

Cell
- Class for general cells in the map, inherited by CommonCell, InaccessibleCell, and Market

CellFactory
- A factory that generates difference kinds of cells

CommonCell
- A common cell with chances to create monsters

Dragons
- Monster Dragon, inherits Monster

Exoskeletons
- Monster Exoskeleton, inherits Monster

Fight
- Class that initializes and executes the fighting process

Figures
- Class for general figures, inherited by Monster and Hero

FireSpells
- Class for FireSpells, inherits Spell

Hero
-Class for general heroes, inherits Figure, inherited by Warrior, Paladin, and Sorcerer

HeroFactory
- Factory that creates heroes

HeroTeam
- Team of heroes, implements Iterable<String>

IceSpells
- Class for IceSpells, inherits Spell

InaccessibleCell
- Class for cells that are not accessible, inherits Cell

Inventory
- Inventory class that holds items for both hero and market, implements Iterable<String>

Item
- Class for general items, inherited by Armory, Spell, Potion, Weaponry

ItemFactory
- Factory class that creates all kinds of items

LightningSpells
- Class for lightning spells, inherits Spell

Market
- Class for market cells, inherits Cell

Monster
- Class for general monsters, inherits Figure, inherited by Dragon, Exoskeleton, and Spirit

MonsterFactory
- Factory class that creates monsters

Paladin
- Class for paladin, inherits Hero

Potions
- Class for potions, inherits Item

Sorcerer
- Class for sorcerer, inherits Hero

Spell
- Abstract class for spells, inherits Item, inherited by FireSpells, IceSpells, LightningSpells

Spirits
- Class for monster spirit, inherits Monster

Utils
- Utils class for all kinds of handy methods

Hero
- Class for warriors, inherits Hero

Weaponry
- Class for weapons, inherits Item

World
- Class for the world the game is played in