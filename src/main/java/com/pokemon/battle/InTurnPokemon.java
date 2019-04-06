package com.pokemon.battle;

public class InTurnPokemon extends ComputedStatsPokemon {

    private final Move selectedMove;

    public InTurnPokemon(ComputedStatsPokemon computedStatsPokemon, Move selectedMove) {
        super(computedStatsPokemon);
        this.selectedMove = selectedMove;
    }

    public static InTurnPokemon from(ComputedStatsPokemon computedStatsPokemon, Move selectedMove) {
        return new InTurnPokemon(computedStatsPokemon, selectedMove);
    }

    public void makeMoveOn(InTurnPokemon target) {
        throw new UnsupportedOperationException();
    }
}
