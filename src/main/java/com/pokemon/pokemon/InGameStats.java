package com.pokemon.pokemon;

import com.pokemon.stats.Stats;

import java.util.Objects;
import java.util.Random;

public class InGameStats {

    private final Stats maximumStats;
    private Stats currentStats;

    private final Random randomGenerator;

    private InGameStats(Stats maximumStats, Stats currentStats, Random randomGenerator) {
        this.maximumStats = maximumStats;
        this.currentStats = currentStats;
        this.randomGenerator = randomGenerator;
    }

    public static InGameStats of(Stats maximumStats, Stats currentStats, Random randomGenerator) {
        return new InGameStats(maximumStats, currentStats, randomGenerator);
    }

    public static InGameStats of(Stats maximumStats, Stats currentStats) {
        return new InGameStats(maximumStats, currentStats, new Random());
    }

    public static InGameStats of(Stats maximumStats) {
        return of(maximumStats, Stats.of(maximumStats));
    }

    public static InGameStats of(Stats maximumStats, Random randomGenerator) {
        return of(maximumStats, Stats.of(maximumStats), randomGenerator);
    }

    public void resetStats() {
        this.currentStats = Stats.of(maximumStats);
    }

    void removeHp(int hp) {
        int newHp = getNewHp(hp);

        currentStats = Stats.of(
                newHp,
                currentStats.getAttack(),
                currentStats.getDefense(),
                currentStats.getSpecialAttack(),
                currentStats.getSpecialDefense(),
                currentStats.getSpeed()
        );
    }

    private int getNewHp(int hpToRemove) {
        int currentHp = currentStats.getHp();
        int newHp = currentHp - hpToRemove;

        if (newHp < 0) {
            newHp = 0;
        }
        return newHp;
    }

    boolean isHpBelowOrEqualTo(int value) {
        return currentStats.getHp() <= value;
    }

    boolean isOwnSpeedAboveOther(InGameStats other) {
        if (currentStats.getSpeed() == other.currentStats.getSpeed()) {
            return randomGenerator.nextInt(2) == 0;
        }

        return currentStats.getSpeed() > other.currentStats.getSpeed();
    }

    public Stats getCurrentStats() {
        return currentStats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InGameStats that = (InGameStats) o;
        return Objects.equals(maximumStats, that.maximumStats) &&
                Objects.equals(currentStats, that.currentStats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maximumStats, currentStats);
    }
}
