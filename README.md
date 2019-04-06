# Pokemon battle kata

## Short description

The goal of this kata is to simulate a Pokémon battle reproducing most of the original battle mechanics of the game.
Indeed, some of the mechanics will not be representing to keep it relatively simple.

All the data game described here has been found on the [dragonflycave](https://www.dragonflycave.com/mechanics/stat) fan web site.

## What do you have to do ?

The goal is to determine the winner of a battle between two Pokémon.
A battle is divided in turns, where a Pokémon, as the attacker, used a move against the other Pokémon, defined as the target.

The main mechanics that come into play during a typical turn of a battle are :
* Turn Order
* Accuracy Checks
* Critical Hits
* Damage Calculation
* Secondary Effects

To do this, we will state that a Pokémon has :
* A name ("Bulbisaur")
* 1 or 2 types ("Grass", "Poison")
* 6 stat :
    * HP (Hit Points)
    * Attack
    * Defense
    * Special Attack
    * Special Defense
    * Speed
* 4 Abilities

### Stats calculation

The first step is to calculate the 6 stat for a Pokémon.
Pokémon stat are, in short, governed by a formula. For Attack, Defense, Special Attack, Special Defense and Speed, the formula is this:
> Stat = floor(floor((2 * B + I + E) * L / 100 + 5) * N)

Meanwhile, the HP formula is a little different:
> Stat = floor((2 * B + I + E) * L / 100 + L + 10)

So what are those letters in the formula? They're the five variables that can affect the stat of your Pokémon :
* Base stat (**B**)
    * Base stat are constants for each of the six stat of each Pokémon species, representing the general relative ability of that species in this regard.
* Individual Values (**I**)
    * Individual values (IVs) are the values responsible for the proclamation that no two Pokémon are alike: they vary between individuals even if they're of the same species, at the same level, have the same nature, and are both freshly caught.
* Effort Values (**E**)
    * Effort values (EVs) represent the advantage gained through training: this value is zero for all the stat in wild or freshly caught Pokémon but as your Pokémon battle and defeat other Pokémon, their stat will rise.
* Level (**L**)
    * L is simply the current level of the Pokémon.
* Nature (**N**)
    * A Pokémon's nature describes its personality. Some natures are neutral and have no effect on your Pokémon's stat, while others increase one stat by 10% at the cost of decreasing another by 10%.

### Turn Order

### Accuracy Checks

### Critical Hits

### Damage Calculation

### Secondary Effects