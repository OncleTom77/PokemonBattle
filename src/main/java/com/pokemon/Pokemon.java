package com.pokemon;

import com.pokemon.battle.ComputedStatsPokemon;
import com.pokemon.battle.ComputedStatsPokemonFactory;
import com.pokemon.battle.Move;
import com.pokemon.stats.*;

public class Pokemon {

    private final String name;
    private final Type[] types;
    private final Nature nature;
    private final Stats baseStats;
    private final VariantStats variantStats;
    private final Move[] moves;
    private ComputedStatsPokemonFactory computedStatsPokemonFactory;

    private Pokemon(String name, Type[] types, Nature nature, Stats baseStats,
                    VariantStats variantStats,
                    Move[] moves,
                    ComputedStatsPokemonFactory computedStatsPokemonFactory) {
        this.name = name;
        this.types = types;
        this.nature = nature;
        this.baseStats = baseStats;
        this.variantStats = variantStats;
        this.moves = moves;
        this.computedStatsPokemonFactory = computedStatsPokemonFactory;
    }

    public static Pokemon from(String name, Type[] types, Nature nature, Stats baseStats,
                               Stats individualValues, Stats effortValues, Level level,
                               Move[] moves,
                               ComputedStatsPokemonFactory computedStatsPokemonFactory) {
        return new Pokemon(
                name, types, nature, baseStats,
                VariantStats.from(individualValues, effortValues, level),
                moves,
                computedStatsPokemonFactory);
    }

    public ComputedStatsPokemon getComputedStatsPokemon() {
        return computedStatsPokemonFactory.createFrom(this);
    }

    public Stats getBaseStats() {
        return baseStats;
    }

    public VariantStats getVariantStats() {
        return variantStats;
    }

    public Nature getNature() {
        return nature;
    }
}
