package com.pokemon.battle;

import com.pokemon.Pokemon;

import java.util.Optional;

class Battle {

    private final TurnFactory turnFactory;

    Battle() {
        turnFactory = new TurnFactory();
    }

    Battle(TurnFactory turnFactory) {
        this.turnFactory = turnFactory;
    }

    Pokemon fight(Pokemon a, Pokemon b) {

        while (true) {
            Pokemon first = a.getSpeed() > b.getSpeed() ? a : b;
            Pokemon second = first.equals(a) ? b : a;

            Optional<Pokemon> optionalWinner = resolveTurnWithOptionalWinner(first, second);
            if (optionalWinner.isPresent()) return optionalWinner.get();

            optionalWinner = resolveTurnWithOptionalWinner(second, first);
            if (optionalWinner.isPresent()) return optionalWinner.get();
        }
    }

    private Optional<Pokemon> resolveTurnWithOptionalWinner(Pokemon attacker, Pokemon target) {
        turnFactory.createFrom(attacker, target)
                .compute();

        return Optional.ofNullable(target.isKO() ? attacker : null);
    }
}
