package com.pokemon.pokemon;

import com.pokemon.stats.Sensibility;
import com.pokemon.stats.Stats;
import com.pokemon.stats.Type;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class Pokemon {

    private final String name;
    private final Type[] types;
    private final Stats baseStats;

    protected Pokemon(String name, Type[] types, Stats baseStats) {
        this.name = name;
        this.types = types;
        this.baseStats = baseStats;
    }

    boolean hasType(Type type) {
        return Stream.of(types)
                .anyMatch(t -> t == type);
    }

    boolean isImmuneTo(Type type) {
        return Stream.of(types)
                .map(t -> t.getSensibilityForMoveType(type))
                .anyMatch(sensibility -> sensibility == Sensibility.IMMUNE);
    }

    Type[] getTypes() {
        return types;
    }

    Stats getBaseStats() {
        return baseStats;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(name, pokemon.name) &&
                Arrays.equals(types, pokemon.types) &&
                Objects.equals(baseStats, pokemon.baseStats);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, baseStats);
        result = 31 * result + Arrays.hashCode(types);
        return result;
    }
}
