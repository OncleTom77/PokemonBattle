package com.pokemon;

import com.pokemon.battle.ComputedStatsPokemon;
import com.pokemon.battle.PokemonFactory;
import com.pokemon.moves.Move;
import com.pokemon.stats.Level;
import com.pokemon.stats.Nature;
import com.pokemon.stats.Stats;
import com.pokemon.stats.Type;

import java.util.stream.Stream;

public class Pokemon {

    private final String name;
    private final Type[] types;
    private final Nature nature;
    private final Stats baseStats;
    private final VariantStats variantStats;
    private final Move[] moves;
    private PokemonFactory pokemonFactory;

    private Pokemon(String name, Type[] types, Nature nature, Stats baseStats,
                    VariantStats variantStats,
                    Move[] moves,
                    PokemonFactory pokemonFactory) {
        this.name = name;
        this.types = types;
        this.nature = nature;
        this.baseStats = baseStats;
        this.variantStats = variantStats;
        this.moves = moves;
        this.pokemonFactory = pokemonFactory;
    }

    public static Pokemon from(String name, Type[] types, Nature nature, Stats baseStats,
                               Stats individualValues, Stats effortValues, Level level,
                               Move[] moves,
                               PokemonFactory pokemonFactory) {
        return new Pokemon(
                name, types, nature, baseStats,
                VariantStats.from(individualValues, effortValues, level),
                moves,
                pokemonFactory);
    }

    public ComputedStatsPokemon getComputedStatsPokemon() {
        return pokemonFactory.createFrom(this);
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

    public Move[] getMoves() {
        return moves;
    }

    public boolean hasType(Type type) {
        return Stream.of(types)
                .anyMatch(t -> t == type);
    }

    public Type[] getTypes() {
        return types;
    }
}
