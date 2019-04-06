package com.pokemon.battle;

class TurnFactory {
    Turn createFrom(ComputedStatsPokemon attacker, ComputedStatsPokemon target) {
        return Turn.of(attacker, target);
    }
}
