package com.pokemon.moves;

import com.pokemon.battle.DamageCategory;
import com.pokemon.stats.Type;

public class Gust extends Move {
    public Gust() {
        super("Gust",
                "A gust of wind is whipped up by wings and launched at the target to inflict damage.",
                Type.FLYING,
                DamageCategory.Special,
                40,
                100,
                35,
                false
        );
    }
}
