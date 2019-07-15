package com.pokemon.pokemon;

import com.pokemon.battle.DamageCategory;
import com.pokemon.moves.Move;
import com.pokemon.stats.Level;
import com.pokemon.stats.Nature;
import com.pokemon.stats.Stats;
import com.pokemon.stats.Type;

import java.util.Random;

public class GeneratedPokemon {

    private final Pokemon pokemon;

    // Generated once
    private final Nature nature;
    private final Stats individualValues;

    // Change in game
    private Stats effortValues;
    private Level level;
    private Move[] moves;
    private InGameStats inGameStats;

    private GeneratedPokemon(Pokemon pokemon, Nature nature, Stats individualValues, Stats effortValues, Level level, Move[] moves, Random randomGenerator) {
        this.pokemon = pokemon;
        this.nature = nature;
        this.individualValues = individualValues;
        this.effortValues = effortValues;
        this.level = level;
        this.moves = moves;

        computeInGameStats(randomGenerator);
    }

    public static GeneratedPokemon of(Pokemon pokemon, Nature nature, Stats individualValues, Stats effortValues, Level level, Move[] moves, Random randomGenerator) {
        return new GeneratedPokemon(pokemon,
                nature,
                individualValues,
                effortValues,
                level,
                moves,
                randomGenerator
        );
    }

    public static GeneratedPokemon of(Pokemon pokemon, Nature nature, Stats individualValues, Stats effortValues, Level level, Move[] moves) {
        return of(pokemon,
                nature,
                individualValues,
                effortValues,
                level,
                moves,
                new Random()
        );
    }

    public static GeneratedPokemon of(Pokemon pokemon, Nature nature, Stats individualValues, Level level, Move[] moves) {
        return of(pokemon,
                nature,
                individualValues,
                Stats.of(0, 0, 0, 0, 0, 0),
                level,
                moves,
                new Random()
        );
    }

    private void computeInGameStats(Random randomGenerator) {
        int hp = computeHPFrom(
                pokemon.getBaseStats().getHp(),
                individualValues.getHp(),
                effortValues.getHp(),
                level.getValue()
        );

        int attack = computeOtherStatsFrom(
                pokemon.getBaseStats().getAttack(),
                individualValues.getAttack(),
                effortValues.getAttack(),
                level.getValue(),
                nature.getMultiplierValueFor(Nature.Stat.Attack)
        );

        int defense = computeOtherStatsFrom(
                pokemon.getBaseStats().getDefense(),
                individualValues.getDefense(),
                effortValues.getDefense(),
                level.getValue(),
                nature.getMultiplierValueFor(Nature.Stat.Defense)
        );

        int specialAttack = computeOtherStatsFrom(
                pokemon.getBaseStats().getSpecialAttack(),
                individualValues.getSpecialAttack(),
                effortValues.getSpecialAttack(),
                level.getValue(),
                nature.getMultiplierValueFor(Nature.Stat.SpecialAttack)
        );

        int specialDefense = computeOtherStatsFrom(
                pokemon.getBaseStats().getSpecialDefense(),
                individualValues.getSpecialDefense(),
                effortValues.getSpecialDefense(),
                level.getValue(),
                nature.getMultiplierValueFor(Nature.Stat.SpecialDefense)
        );

        int speed = computeOtherStatsFrom(
                pokemon.getBaseStats().getSpeed(),
                individualValues.getSpeed(),
                effortValues.getSpeed(),
                level.getValue(),
                nature.getMultiplierValueFor(Nature.Stat.Speed)
        );

        inGameStats = InGameStats.of(
                Stats.of(hp, attack, defense, specialAttack, specialDefense, speed),
                randomGenerator
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


    public boolean isFasterThan(GeneratedPokemon other) {
        return inGameStats.isOwnSpeedAboveOther(other.inGameStats);
    }

    public boolean isKO() {
        return inGameStats.isHpBelowOrEqualTo(0);
    }

    public void removeHp(int hp) {
        inGameStats.removeHp(hp);
    }

    public int getOffensiveStatForDamageCategory(DamageCategory damageCategory) {
        return inGameStats.getOffensiveStatForDamageCategory(damageCategory);
    }

    public int getDefensiveStatForDamageCategory(DamageCategory damageCategory) {
        return inGameStats.getDefensiveStatForDamageCategory(damageCategory);
    }

    public boolean hasType(Type type) {
        return pokemon.hasType(type);
    }

    public boolean isImmuneTo(Type type) {
        return pokemon.isImmuneTo(type);
    }

    public Double getSensibilityFactorToType(Type moveType) {
        return pokemon.getSensibilityFactorToType(moveType);
    }

    InGameStats getInGameStats() {
        return inGameStats;
    }

    public Level getLevel() {
        return level;
    }

    public Move getMove(int position) {
        return moves[position];
    }
}
