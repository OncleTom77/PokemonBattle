package com.pokemon.pokemon;

import com.pokemon.stats.Stats;
import com.pokemon.stats.Type;

public class Charmander extends Pokemon {
    public Charmander() {
        super("Charmander",
                new Type[]{
                        Type.FIRE
                },
                Stats.of(39, 52, 43, 60, 50, 65)
        );
    }
}
