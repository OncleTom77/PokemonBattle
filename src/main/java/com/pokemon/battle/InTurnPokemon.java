package com.pokemon.battle;

import com.pokemon.moves.InsufficientPowerPointException;
import com.pokemon.moves.Move;

class InTurnPokemon extends ComputedStatsPokemon {

    private final Move selectedMove;

    private InTurnPokemon(ComputedStatsPokemon computedStatsPokemon, Move selectedMove) {
        super(computedStatsPokemon);
        this.selectedMove = selectedMove;
    }

    static InTurnPokemon from(ComputedStatsPokemon computedStatsPokemon, Move selectedMove) {
        return new InTurnPokemon(computedStatsPokemon, selectedMove);
    }

    void makeMoveOn(InTurnPokemon target) throws InsufficientPowerPointException {
        selectedMove.execute(this, target);
    }
}
