package com.pokemon.pokemon;

import com.pokemon.stats.Stats;
import com.pokemon.stats.Type;

public class Bulbasaur extends Pokemon {

    public Bulbasaur() {
        super("Bulbasaur",
                new Type[]{
                        Type.GRASS,
                        Type.POISON
                },
                Stats.of(45, 49, 49, 65, 65, 45)
        );
    }
}
