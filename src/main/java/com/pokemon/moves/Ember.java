package com.pokemon.moves;

import com.pokemon.battle.DamageCategory;
import com.pokemon.stats.Type;

import java.util.Random;

public class Ember extends Move {
    public Ember() {
        this(new Random());
    }

    Ember(Random randomGenerator) {
        super("Ember",
                "The target is attacked with small flames. It may also leave the target with a burn.",
                Type.FIRE,
                DamageCategory.Special,
                40,
                100,
                25,
                false,
                randomGenerator
        );
    }
}
