package com.pokemon.battle;

import com.pokemon.pokemon.GeneratedPokemon;
import com.pokemon.pokemon.PokemonFactory;

import java.util.Optional;

class Battle {

    private final TurnFactory turnFactory;
    private final PokemonFactory pokemonFactory;

    Battle() {
        turnFactory = new TurnFactory();
        pokemonFactory = new PokemonFactory();
    }

    Battle(TurnFactory turnFactory, PokemonFactory pokemonFactory) {
        this.turnFactory = turnFactory;
        this.pokemonFactory = pokemonFactory;
    }

    GeneratedPokemon fight(GeneratedPokemon a, GeneratedPokemon b) {
        while (true) {
            InTurnPokemon aInTurnPokemon = pokemonFactory.createFrom(a, a.getMove(0));
            InTurnPokemon bInTurnPokemon = pokemonFactory.createFrom(b, b.getMove(0));

            Optional<InTurnPokemon> potentialWinner = turnFactory.createFrom(aInTurnPokemon, bInTurnPokemon)
                    .resolveWithPotentialWinner();

            if (potentialWinner.isPresent()) {
                return potentialWinner.get().getGeneratedPokemon();
            }
        }
    }

}
