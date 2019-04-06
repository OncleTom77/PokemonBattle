package com.pokemon.battle;

import com.pokemon.Pokemon;

public class ComputedStatsPokemonFactory {
    public ComputedStatsPokemon createFrom(Pokemon pokemon) {
        return ComputedStatsPokemon.of(pokemon);
    }
}
