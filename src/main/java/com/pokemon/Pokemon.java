package com.pokemon;

import com.pokemon.battle.ComputedStatsPokemon;
import com.pokemon.battle.PokemonFactory;
import com.pokemon.moves.Move;
import com.pokemon.stats.*;

import java.util.Objects;
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

    public boolean hasType(Type type) {
        return Stream.of(types)
                .anyMatch(t -> t == type);
    }

    public boolean isImmuneTo(Type type) {
        return Stream.of(types)
                .map(t -> t.getSensibilityForMoveType(type))
                .anyMatch(sensibility -> sensibility == Sensibility.IMMUNE);
    }

    public Type[] getTypes() {
        return types;
    }

    public Nature getNature() {
        return nature;
    }

    public Stats getBaseStats() {
        return baseStats;
    }

    public VariantStats getVariantStats() {
        return variantStats;
    }

    public Move[] getMoves() {
        return moves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(name, pokemon.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                '}';
    }
}
