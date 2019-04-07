package com.pokemon.moves;

import com.pokemon.battle.DamageCategory;
import com.pokemon.stats.Type;

import java.util.Random;

public class Gust extends Move {
    Gust() {
        this(new Random());
    }

    Gust(Random randomGenerator) {
        super("Gust",
                "A gust of wind is whipped up by wings and launched at the target to inflict damage.",
                Type.FLYING,
                DamageCategory.Special,
                40,
                100,
                35,
                false,
                randomGenerator
        );
    }
}
