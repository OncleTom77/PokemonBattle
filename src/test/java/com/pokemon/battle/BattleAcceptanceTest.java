package com.pokemon.battle;

import com.pokemon.Pokemon;
import com.pokemon.stats.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BattleAcceptanceTest {

    @Test
    public void should_battle() {
        Pokemon bulbasaur = Pokemon.from(
                "Bulbasaur",
                new Type[]{
                        Type.Grass,
                        Type.Poison
                },
                Nature.Hardy,
                Stats.of(45, 49, 49, 65, 65, 45),
                Stats.of(28, 4, 17, 30, 27, 31),
                Stats.of(0, 0, 0, 0, 0, 0),
                Level.of(15),
                new Move[]{
                        Move.VineWhip
                },
                new ComputedStatsPokemonFactory()
        );
        Pokemon charmander = Pokemon.from(
                "Charmander",
                new Type[]{
                        Type.Fire
                },
                Nature.Docile,
                Stats.of(39, 52, 43, 60, 50, 65),
                Stats.of(8, 14, 1, 31, 23, 5),
                Stats.of(0, 0, 0, 0, 0, 0),
                Level.of(15),
                new Move[]{
                        Move.Ember
                },
                new ComputedStatsPokemonFactory()
        );

        Pokemon winner = new Battle().fight(bulbasaur, charmander);

        assertThat(winner).isEqualTo(bulbasaur);
    }
}
