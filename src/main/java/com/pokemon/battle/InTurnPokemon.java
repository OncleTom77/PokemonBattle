package com.pokemon.battle;

import com.pokemon.moves.InsufficientPowerPointException;
import com.pokemon.moves.Move;
import com.pokemon.pokemon.GeneratedPokemon;

public class InTurnPokemon {

    private final GeneratedPokemon generatedPokemon;
    private final Move selectedMove;

    private InTurnPokemon(GeneratedPokemon generatedPokemon, Move selectedMove) {
        this.generatedPokemon = generatedPokemon;
        this.selectedMove = selectedMove;
    }

    public static InTurnPokemon from(GeneratedPokemon pokemon, Move selectedMove) {
        return new InTurnPokemon(pokemon, selectedMove);
    }

    void makeMoveOn(InTurnPokemon target) throws InsufficientPowerPointException {
        selectedMove.execute(generatedPokemon, target.generatedPokemon);
    }

    GeneratedPokemon getGeneratedPokemon() {
        return generatedPokemon;
    }

    boolean isFasterThan(InTurnPokemon secondPokemon) {
        return generatedPokemon.isFasterThan(secondPokemon.generatedPokemon);
    }

    boolean isKO() {
        return generatedPokemon.isKO();
    }
}
