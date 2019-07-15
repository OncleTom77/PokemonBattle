package com.pokemon.pokemon;

import com.pokemon.battle.InTurnPokemon;
import com.pokemon.moves.Move;

public class PokemonFactory {

    public InTurnPokemon createFrom(GeneratedPokemon pokemon, Move selectedMove) {
        return InTurnPokemon.from(pokemon, selectedMove);
    }

//    GeneratedPokemon generatePokemon(Pokemon pokemon) {
//        return GeneratedPokemon.of(pokemon, );
//    }
}
