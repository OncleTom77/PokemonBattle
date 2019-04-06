package com.pokemon.battle;

import com.pokemon.Pokemon;

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

    Pokemon fight(Pokemon a, Pokemon b) {
        ComputedStatsPokemon aComputedStatsPokemon = a.getComputedStatsPokemon();
        ComputedStatsPokemon bComputedStatsPokemon = b.getComputedStatsPokemon();

        while (true) {
            InTurnPokemon aInTurnPokemon = pokemonFactory.createFrom(
                    aComputedStatsPokemon,
                    aComputedStatsPokemon.getPokemon().getMoves()[0]
            );

            InTurnPokemon bInTurnPokemon = pokemonFactory.createFrom(
                    bComputedStatsPokemon,
                    bComputedStatsPokemon.getPokemon().getMoves()[0]
            );

            Optional<InTurnPokemon> potentialWinner = turnFactory.createFrom(aInTurnPokemon, bInTurnPokemon)
                    .resolveWithPotentialWinner();

            if (potentialWinner.isPresent()) {
                return potentialWinner.get().getPokemon();
            }
        }
    }

}
