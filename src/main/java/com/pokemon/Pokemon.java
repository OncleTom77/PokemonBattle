package com.pokemon;

import com.pokemon.stats.*;

public class Pokemon {

    private final String name;
    private final Type[] types;
    private final VariantStats variantStats;

    private Pokemon(String name, Type[] types, VariantStats variantStats) {
        this.name = name;
        this.types = types;
        this.variantStats = variantStats;
    }

    public static Pokemon from(String name, Type[] types, BaseStats baseStats, IndividualValues individualValues, EffortValues effortValues, Level level, Nature nature) {
        return new Pokemon(name, types, new VariantStats(baseStats, individualValues, effortValues, level, nature));
    }
}
