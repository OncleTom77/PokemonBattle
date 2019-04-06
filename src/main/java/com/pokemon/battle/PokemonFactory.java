package com.pokemon.battle;

import com.pokemon.Pokemon;

public class PokemonFactory {

    public ComputedStatsPokemon createFrom(Pokemon pokemon) {
        return ComputedStatsPokemon.from(pokemon);
    }

    public InTurnPokemon createFrom(ComputedStatsPokemon computedStatsPokemon, Move selectedMove) {
        return InTurnPokemon.from(computedStatsPokemon, selectedMove);
    }
}
