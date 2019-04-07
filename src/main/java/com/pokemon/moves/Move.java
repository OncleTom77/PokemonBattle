package com.pokemon.moves;

import com.pokemon.Pokemon;
import com.pokemon.battle.ComputedStatsPokemon;
import com.pokemon.battle.DamageCategory;
import com.pokemon.stats.Stats;
import com.pokemon.stats.Type;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public abstract class Move {

    public static final double SAME_TYPE_ATTACK_BONUS_VALUE = 1.5;
    private final String name;
    private final String description;
    private final Type type;
    private final DamageCategory damageCategory;
    private final int power;
    private final int accuracy;
    private int powerPoint;
    private final boolean hasHighCriticalHitRatio;

    private final Random randomGenerator;

    Move(String name, String description, Type type, DamageCategory damageCategory, int power, int accuracy, int powerPoint, boolean hasHighCriticalHitRatio) {
        this(name, description, type, damageCategory, power, accuracy, powerPoint, hasHighCriticalHitRatio, new Random());
    }

    public Move(String name, String description, Type type, DamageCategory damageCategory, int power, int accuracy, int powerPoint, boolean hasHighCriticalHitRatio, Random randomGenerator) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.damageCategory = damageCategory;
        this.power = power;
        this.accuracy = accuracy;
        this.powerPoint = powerPoint;
        this.hasHighCriticalHitRatio = hasHighCriticalHitRatio;
        this.randomGenerator = randomGenerator;
    }

    public void execute(ComputedStatsPokemon attacker, ComputedStatsPokemon target) throws InsufficientPowerPointException {
        if(powerPoint <= 0) {
            throw new InsufficientPowerPointException();
        }

        // TODO: check type immunity

        // Accuracy check
        int randomAccuracyValue = randomGenerator.nextInt(100);
        if (randomAccuracyValue >= accuracy) {
            return;
        } else {
            powerPoint--;
        }

        // Critical Hit

        // Damage calculation
        int damage = calculateDamageBeforeModifiers(
                attacker.getPokemon().getVariantStats().getLevel().getValue(),
                attacker.getStats(),
                target.getStats()
        );

        damage = computeSameTypeAttackBonusModifier(damage, attacker.getPokemon());
        damage = computeTypeSensibilitiesModifier(damage, target.getPokemon().getTypes());

        // TODO: multiply damage by random number between 85 and 100 then divide by 100 before apply damage
        target.removeHp(damage);
    }

    private int computeTypeSensibilitiesModifier(int damage, Type[] targetTypes) {
        final AtomicReference<Double> modifier = new AtomicReference<>(1.0);
        Stream.of(targetTypes)
                .forEach(targetType -> {
                    double damageCoefficient = targetType
                            .getSensibilityForMoveType(type)
                            .getDamageCoefficient();
                    modifier.set(modifier.get() * damageCoefficient);
                });

        return (int) (damage * modifier.get());
    }

    private int computeSameTypeAttackBonusModifier(int damage, Pokemon pokemon) {
        return pokemon.hasType(type) ? (int) (damage * SAME_TYPE_ATTACK_BONUS_VALUE) : damage;
    }

    private int calculateDamageBeforeModifiers(int attackerLevel, Stats attackerStats, Stats targetStats) {
        int offensiveStat = getOffensiveStat(attackerStats);
        int defensiveStat = getDefensiveStat(targetStats);

        return (((2 * attackerLevel / 5 + 2) * offensiveStat * power / defensiveStat) / 50) + 2;
    }

    private int getOffensiveStat(Stats stats) {
        return damageCategory == DamageCategory.Physical
                ? stats.getAttack()
                : stats.getSpecialAttack();
    }

    private int getDefensiveStat(Stats stats) {
        return damageCategory == DamageCategory.Physical
                ? stats.getDefense()
                : stats.getSpecialDefense();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return power == move.power &&
                accuracy == move.accuracy &&
                powerPoint == move.powerPoint &&
                hasHighCriticalHitRatio == move.hasHighCriticalHitRatio &&
                Objects.equals(name, move.name) &&
                Objects.equals(description, move.description) &&
                type == move.type &&
                damageCategory == move.damageCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, type, damageCategory, power, accuracy, powerPoint, hasHighCriticalHitRatio);
    }
}
