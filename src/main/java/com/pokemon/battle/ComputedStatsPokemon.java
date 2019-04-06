package com.pokemon.battle;

import com.pokemon.Pokemon;
import com.pokemon.stats.Nature;
import com.pokemon.stats.Stats;

import java.util.Objects;
import java.util.Random;

public class ComputedStatsPokemon {
    private final Stats stats;
    private final Random randomGenerator;

    private ComputedStatsPokemon(Stats stats, Random randomGenerator) {
        this.stats = stats;
        this.randomGenerator = randomGenerator;
    }

    public static ComputedStatsPokemon from(Stats stats, Random randomGenerator) {
        return new ComputedStatsPokemon(stats, randomGenerator);
    }

    public static ComputedStatsPokemon of(Pokemon pokemon) {
        int hp = computeHPFrom(
                pokemon.getBaseStats().getHp(),
                pokemon.getVariantStats().getIndividualValues().getHp(),
                pokemon.getVariantStats().getEffortValues().getHp(),
                pokemon.getVariantStats().getLevel().getLevel()
        );

        int attack = computeOtherStatsFrom(
                pokemon.getBaseStats().getAttack(),
                pokemon.getVariantStats().getIndividualValues().getAttack(),
                pokemon.getVariantStats().getEffortValues().getAttack(),
                pokemon.getVariantStats().getLevel().getLevel(),
                pokemon.getNature().getMultiplierValueFor(Nature.Stat.Attack)
        );

        int defense = computeOtherStatsFrom(
                pokemon.getBaseStats().getDefense(),
                pokemon.getVariantStats().getIndividualValues().getDefense(),
                pokemon.getVariantStats().getEffortValues().getDefense(),
                pokemon.getVariantStats().getLevel().getLevel(),
                pokemon.getNature().getMultiplierValueFor(Nature.Stat.Defense)
        );

        int specialAttack = computeOtherStatsFrom(
                pokemon.getBaseStats().getSpecialAttack(),
                pokemon.getVariantStats().getIndividualValues().getSpecialAttack(),
                pokemon.getVariantStats().getEffortValues().getSpecialAttack(),
                pokemon.getVariantStats().getLevel().getLevel(),
                pokemon.getNature().getMultiplierValueFor(Nature.Stat.SpecialAttack)
        );

        int specialDefense = computeOtherStatsFrom(
                pokemon.getBaseStats().getSpecialDefense(),
                pokemon.getVariantStats().getIndividualValues().getSpecialDefense(),
                pokemon.getVariantStats().getEffortValues().getSpecialDefense(),
                pokemon.getVariantStats().getLevel().getLevel(),
                pokemon.getNature().getMultiplierValueFor(Nature.Stat.SpecialDefense)
        );

        int speed = computeOtherStatsFrom(
                pokemon.getBaseStats().getSpeed(),
                pokemon.getVariantStats().getIndividualValues().getSpeed(),
                pokemon.getVariantStats().getEffortValues().getSpeed(),
                pokemon.getVariantStats().getLevel().getLevel(),
                pokemon.getNature().getMultiplierValueFor(Nature.Stat.Speed)
        );

        return from(
                Stats.of(hp, attack, defense, specialAttack, specialDefense, speed),
                new Random()
        );
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputedStatsPokemon that = (ComputedStatsPokemon) o;
        return Objects.equals(stats, that.stats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stats);
    }

    @Override
    public String toString() {
        return "ComputedStatsPokemon{" +
                "stats=" + stats +
                '}';
    }
}
