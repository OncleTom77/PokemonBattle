package com.pokemon.moves;

import com.pokemon.battle.DamageCategory;
import com.pokemon.pokemon.GeneratedPokemon;
import com.pokemon.stats.Stats;
import com.pokemon.stats.Type;

import java.util.Objects;

public abstract class Move {

    private static final double SAME_TYPE_ATTACK_BONUS_VALUE = 1.5;
    private static final double CRITICAL_HIT_BONUS_VALUE = 1.5;

    private final String name;
    private final String description;
    private final Type type;
    private final DamageCategory damageCategory;
    private final int power;
    private final int accuracy;
    private int powerPoint;
    private final boolean hasHighCriticalHitRatio;

    private final MoveRandomGenerator randomGenerator;

    Move(String name, String description, Type type, DamageCategory damageCategory, int power, int accuracy, int powerPoint, boolean hasHighCriticalHitRatio) {
        this(name, description, type, damageCategory, power, accuracy, powerPoint, hasHighCriticalHitRatio, new MoveRandomGenerator());
    }

    public Move(String name, String description, Type type, DamageCategory damageCategory, int power, int accuracy, int powerPoint, boolean hasHighCriticalHitRatio, MoveRandomGenerator randomGenerator) {
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

    public void execute(GeneratedPokemon attacker, GeneratedPokemon target) throws InsufficientPowerPointException {
        if (powerPoint <= 0) {
            throw new InsufficientPowerPointException();
        }

        // Check type immunity
        if (target.isImmuneTo(type)) {
            return;
        }

        // Accuracy check
        int randomAccuracyValue = randomGenerator.nextAccuracyValue();
        if (randomAccuracyValue >= accuracy) {
            return;
        } else {
            powerPoint--;
        }

        // Critical Hit
        boolean isCriticalHit = isCriticalHit();

        // Damage calculation
        double damage = calculateDamageBeforeModifiers(
                attacker.getLevel().getValue(),
                attacker.getInGameStats().getCurrentStats(),
                target.getInGameStats().getCurrentStats()
        );

        damage = computeCriticalHitModifier(damage, isCriticalHit);
        damage = computeSameTypeAttackBonusModifier(damage, attacker);
        damage = computeTypeSensibilitiesModifier(damage, target);
        damage = computeRandomDamageFactor(damage);

        target.removeHp((int) damage);
    }

    private double computeRandomDamageFactor(double damage) {
        double factor = randomGenerator.nextDamageFactor();
        return damage * factor;
    }

    private double computeCriticalHitModifier(double damage, boolean isCriticalHit) {
        return isCriticalHit ? damage * CRITICAL_HIT_BONUS_VALUE : damage;
    }

    private boolean isCriticalHit() {
        return randomGenerator.nextCriticalHitValue(hasHighCriticalHitRatio) == 0;
    }

    private double computeTypeSensibilitiesModifier(double damage, GeneratedPokemon target) {
        Double finalModifier = target.getSensibilityFactorToType(type);

        return damage * finalModifier;
    }

    private double computeSameTypeAttackBonusModifier(double damage, GeneratedPokemon pokemon) {
        return pokemon.hasType(type) ? damage * SAME_TYPE_ATTACK_BONUS_VALUE : damage;
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
