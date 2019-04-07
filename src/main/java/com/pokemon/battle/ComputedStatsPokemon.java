package com.pokemon.battle;

import com.pokemon.Pokemon;
import com.pokemon.stats.Nature;
import com.pokemon.stats.Stats;

import java.util.Objects;
import java.util.Random;

public class ComputedStatsPokemon {
    private final Pokemon pokemon;
    private final Stats stats;
    private final Random randomGenerator;

    private ComputedStatsPokemon(Pokemon pokemon, Stats stats, Random randomGenerator) {
        this.pokemon = pokemon;
        this.stats = stats;
        this.randomGenerator = randomGenerator;
    }

    protected ComputedStatsPokemon(ComputedStatsPokemon computedStatsPokemon) {
        this.pokemon = computedStatsPokemon.pokemon;
        this.stats = computedStatsPokemon.stats;
        this.randomGenerator = computedStatsPokemon.randomGenerator;
    }

    public static ComputedStatsPokemon from(Pokemon pokemon, Stats stats, Random randomGenerator) {
        return new ComputedStatsPokemon(pokemon, stats, randomGenerator);
    }

    public static ComputedStatsPokemon from(Pokemon pokemon, Random randomGenerator) {
        return from(pokemon, getComputedStats(pokemon), randomGenerator);
    }

    public static ComputedStatsPokemon from(Pokemon pokemon, Stats stats) {
        return from(pokemon, stats, new Random());
    }

    public static ComputedStatsPokemon from(Pokemon pokemon) {
        return from(pokemon, new Random());
    }

    private static Stats getComputedStats(Pokemon pokemon) {
        int hp = computeHPFrom(
                pokemon.getBaseStats().getHp(),
                pokemon.getVariantStats().getIndividualValues().getHp(),
                pokemon.getVariantStats().getEffortValues().getHp(),
                pokemon.getVariantStats().getLevel().getValue()
        );

        int attack = computeOtherStatsFrom(
                pokemon.getBaseStats().getAttack(),
                pokemon.getVariantStats().getIndividualValues().getAttack(),
                pokemon.getVariantStats().getEffortValues().getAttack(),
                pokemon.getVariantStats().getLevel().getValue(),
                pokemon.getNature().getMultiplierValueFor(Nature.Stat.Attack)
        );

        int defense = computeOtherStatsFrom(
                pokemon.getBaseStats().getDefense(),
                pokemon.getVariantStats().getIndividualValues().getDefense(),
                pokemon.getVariantStats().getEffortValues().getDefense(),
                pokemon.getVariantStats().getLevel().getValue(),
                pokemon.getNature().getMultiplierValueFor(Nature.Stat.Defense)
        );

        int specialAttack = computeOtherStatsFrom(
                pokemon.getBaseStats().getSpecialAttack(),
                pokemon.getVariantStats().getIndividualValues().getSpecialAttack(),
                pokemon.getVariantStats().getEffortValues().getSpecialAttack(),
                pokemon.getVariantStats().getLevel().getValue(),
                pokemon.getNature().getMultiplierValueFor(Nature.Stat.SpecialAttack)
        );

        int specialDefense = computeOtherStatsFrom(
                pokemon.getBaseStats().getSpecialDefense(),
                pokemon.getVariantStats().getIndividualValues().getSpecialDefense(),
                pokemon.getVariantStats().getEffortValues().getSpecialDefense(),
                pokemon.getVariantStats().getLevel().getValue(),
                pokemon.getNature().getMultiplierValueFor(Nature.Stat.SpecialDefense)
        );

        int speed = computeOtherStatsFrom(
                pokemon.getBaseStats().getSpeed(),
                pokemon.getVariantStats().getIndividualValues().getSpeed(),
                pokemon.getVariantStats().getEffortValues().getSpeed(),
                pokemon.getVariantStats().getLevel().getValue(),
                pokemon.getNature().getMultiplierValueFor(Nature.Stat.Speed)
        );

        return Stats.of(hp, attack, defense, specialAttack, specialDefense, speed);
    }

    private static int computeOtherStatsFrom(int base, int individual, int effort, int level, float natureMultiplier) {
        effort /= 4;
        return (int) (((2 * base + individual + effort) * level / 100 + 5) * natureMultiplier);
    }

    private static int computeHPFrom(int base, int individual, int effort, int level) {
        effort /= 4;
        return (2 * base + individual + effort) * level / 100 + level + 10;
    }

    public boolean isFasterThan(ComputedStatsPokemon other) {
        if (stats.getSpeed() == other.stats.getSpeed()) {
            return randomGenerator.nextInt(2) == 0;
        }
        return stats.getSpeed() > other.stats.getSpeed();
    }

    public boolean isKO() {
        throw new UnsupportedOperationException();
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public Stats getStats() {
        return stats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputedStatsPokemon that = (ComputedStatsPokemon) o;
        return Objects.equals(pokemon, that.pokemon) &&
                Objects.equals(stats, that.stats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pokemon, stats);
    }

    @Override
    public String toString() {
        return "ComputedStatsPokemon{" +
                "pokemon=" + pokemon +
                ", stats=" + stats +
                '}';
    }

    public void removeHp(int hp) {
        throw new UnsupportedOperationException();
    }
}
