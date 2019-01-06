package com.pokemon;

import com.pokemon.battle.Move;
import com.pokemon.stats.*;

public class Pokemon {

    private final String name;
    private final Type[] types;
    private final BaseStats baseStats;
    private final VariantStats variantStats;
    private final Move[] moves;

    private Pokemon(String name, Type[] types, BaseStats baseStats, VariantStats variantStats, Move[] moves) {
        this.name = name;
        this.types = types;
        this.baseStats = baseStats;
        this.variantStats = variantStats;
        this.moves = moves;
    }

    public static Pokemon from(String name, Type[] types, BaseStats baseStats, IndividualValues individualValues, EffortValues effortValues, Level level, Nature nature, Move[] moves) {
        return new Pokemon(name, types, baseStats, new VariantStats(individualValues, effortValues, level, nature), moves);
    }

    public boolean isKO() {
        throw new UnsupportedOperationException();
    }

    public int getSpeed() {
        throw new UnsupportedOperationException();
    }
}
