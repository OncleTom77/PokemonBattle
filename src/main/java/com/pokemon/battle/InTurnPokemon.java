package com.pokemon.battle;

import com.pokemon.Pokemon;
import com.pokemon.moves.InsufficientPowerPointException;
import com.pokemon.moves.Move;

class InTurnPokemon {

    private final ComputedStatsPokemon computedStatsPokemon;
    private final Move selectedMove;

    private InTurnPokemon(ComputedStatsPokemon computedStatsPokemon, Move selectedMove) {
        this.computedStatsPokemon = computedStatsPokemon;
        this.selectedMove = selectedMove;
    }

    static InTurnPokemon from(ComputedStatsPokemon computedStatsPokemon, Move selectedMove) {
        return new InTurnPokemon(computedStatsPokemon, selectedMove);
    }

    void makeMoveOn(InTurnPokemon target) throws InsufficientPowerPointException {
        selectedMove.execute(computedStatsPokemon, target.computedStatsPokemon);
    }

    boolean isFasterThan(InTurnPokemon secondPokemon) {
        return computedStatsPokemon.isFasterThan(secondPokemon.computedStatsPokemon);
    }

    boolean isKO() {
        return computedStatsPokemon.isKO();
    }

    public Pokemon getPokemon() {
        return computedStatsPokemon.getPokemon();
    }
}
