package com.pokemon.battle;

class Turn {
    private final ComputedStatsPokemon attacker;
    private final ComputedStatsPokemon target;

    private Turn(ComputedStatsPokemon attacker, ComputedStatsPokemon target) {
        this.attacker = attacker;
        this.target = target;
    }

    static Turn of(ComputedStatsPokemon attacker, ComputedStatsPokemon target) {
        return new Turn(attacker, target);
    }

    void compute() {
        throw new UnsupportedOperationException();
    }
}
