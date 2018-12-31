package com.pokemon;

import com.pokemon.stats.*;

public class VariantStats {
    private final BaseStats baseStats;
    private final IndividualValues individualValues;
    private final EffortValues effortValues;
    private final Level level;
    private final Nature nature;

    public VariantStats(BaseStats baseStats, IndividualValues individualValues, EffortValues effortValues, Level level, Nature nature) {
        this.baseStats = baseStats;
        this.individualValues = individualValues;
        this.effortValues = effortValues;
        this.level = level;
        this.nature = nature;
    }

    public BaseStats getBaseStats() {
        return baseStats;
    }

    public IndividualValues getIndividualValues() {
        return individualValues;
    }

    public EffortValues getEffortValues() {
        return effortValues;
    }

    public Level getLevel() {
        return level;
    }

    public Nature getNature() {
        return nature;
    }
}
