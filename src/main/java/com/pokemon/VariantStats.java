package com.pokemon;

import com.pokemon.stats.*;

class VariantStats {
    private final IndividualValues individualValues;
    private final EffortValues effortValues;
    private final Level level;
    private final Nature nature;

    VariantStats(IndividualValues individualValues, EffortValues effortValues, Level level, Nature nature) {
        this.individualValues = individualValues;
        this.effortValues = effortValues;
        this.level = level;
        this.nature = nature;
    }
}
