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
        ComputedStatsPokemon firstComputedStatsPokemon = a.getComputedStatsPokemon();
        ComputedStatsPokemon secondComputedStatsPokemon = b.getComputedStatsPokemon();

        while (true) {
            ComputedStatsPokemon first;
            ComputedStatsPokemon second;

            if (firstComputedStatsPokemon.isFasterThan(secondComputedStatsPokemon)) {
                first = firstComputedStatsPokemon;
                second = secondComputedStatsPokemon;
            } else {
                first = secondComputedStatsPokemon;
                second = firstComputedStatsPokemon;
            }

            turnFactory.createFrom(first, second)
                    .compute();

            if (second.isKO()) {
                return a;
            }

            turnFactory.createFrom(second, first)
                    .compute();

            if (first.isKO()) {
                return b;
            }
        }
    }

}
