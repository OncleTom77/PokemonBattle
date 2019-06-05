package com.pokemon.moves;

import com.pokemon.battle.DamageCategory;
import com.pokemon.stats.Type;

public class Ember extends Move {
    public Ember() {
        super("Ember",
                "The target is attacked with small flames. It may also leave the target with a burn.",
                Type.FIRE,
                DamageCategory.Special,
                40,
                100,
                25,
                false
        );
    }
}
