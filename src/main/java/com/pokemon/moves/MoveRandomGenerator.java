package com.pokemon.moves;

import java.util.Random;

class MoveRandomGenerator {

    private static final int ACCURACY_BOUND = 100;
    private static final int CRITICAL_HIT_BOUND = 16;
    private static final int DAMAGE_FACTOR_MINIMUM_VALUE = 85;
    private static final int DAMAGE_FACTOR_BOUND = 16;

    private final Random randomGenerator;

    MoveRandomGenerator() {
        this.randomGenerator = new Random();
    }

    MoveRandomGenerator(Random randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    int nextAccuracyValue() {
        return randomGenerator.nextInt(ACCURACY_BOUND);
    }

    int nextCriticalHitValue() {
        return randomGenerator.nextInt(CRITICAL_HIT_BOUND);
    }

    double nextDamageFactor() {
        return (DAMAGE_FACTOR_MINIMUM_VALUE + randomGenerator.nextInt(DAMAGE_FACTOR_BOUND)) / 100.;
    }
}
