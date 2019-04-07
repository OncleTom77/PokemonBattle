package com.pokemon.moves;

import com.pokemon.battle.DamageCategory;
import com.pokemon.stats.Type;

import java.util.Random;

public class VineWhip extends Move {
    public VineWhip() {
        this(new Random());
    }

    VineWhip(Random randomGenerator) {
        super("Vine Whip",
                "The target is struck with slender, whiplike vines to inflict damage.",
                Type.GRASS,
                DamageCategory.Physical,
                45,
                100,
                25,
                false,
                randomGenerator
        );
    }
}
