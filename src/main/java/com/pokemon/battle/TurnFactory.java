package com.pokemon.battle;

class TurnFactory {
    Turn createFrom(InTurnPokemon a, InTurnPokemon b) {
        return Turn.of(a, b);
    }
}
