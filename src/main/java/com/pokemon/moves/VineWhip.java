package com.pokemon.moves;

import com.pokemon.battle.DamageCategory;
import com.pokemon.stats.Type;

public class VineWhip extends Move {
    public VineWhip() {
        super("Vine Whip",
                "The target is struck with slender, whiplike vines to inflict damage.",
                Type.GRASS,
                DamageCategory.Physical,
                45,
                100,
                25,
                false
        );
    }
}
