package com.pokemon.battle;

import java.util.Optional;

class Turn {
    private final InTurnPokemon a;
    private final InTurnPokemon b;

    private Turn(InTurnPokemon a, InTurnPokemon b) {
        this.a = a;
        this.b = b;
    }

    static Turn of(InTurnPokemon a, InTurnPokemon b) {
        return new Turn(a, b);
    }

    Optional<InTurnPokemon> resolveWithPotentialWinner() {
        InTurnPokemon first;
        InTurnPokemon second;
        if (a.isFasterThan(b)) {
            first = a;
            second = b;
        } else {
            first = b;
            second = a;
        }

        first.makeMoveOn(second);
        Optional<InTurnPokemon> potentialWinner = getPotentialWinner();
        if (potentialWinner.isPresent())
            return potentialWinner;

        second.makeMoveOn(first);
        return getPotentialWinner();
    }

    private Optional<InTurnPokemon> getPotentialWinner() {
        if (a.isKO()) {
            return Optional.of(b);
        } else if (b.isKO()) {
            return Optional.of(a);
        }
        return Optional.empty();
    }
}
