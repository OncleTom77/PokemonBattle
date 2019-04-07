package com.pokemon;

import com.pokemon.stats.Level;
import com.pokemon.stats.Stats;

public class VariantStats {
    private final Stats individualValues;
    private final Stats effortValues;
    private final Level level;

    VariantStats(Stats individualValues, Stats effortValues, Level level) {
        this.individualValues = individualValues;
        this.effortValues = effortValues;
        this.level = level;
    }

    static VariantStats from(Stats individualValues, Stats effortValues, Level level) {
        return new VariantStats(individualValues, effortValues, level);
    }

    public Stats getIndividualValues() {
        return individualValues;
    }

    public Stats getEffortValues() {
        return effortValues;
    }

    public Level getLevel() {
        return level;
    }
}
