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
                BaseStats.of(45, 49, 49, 65, 65, 45),
                IndividualValues.of(28, 4, 17, 30, 27, 31),
                EffortValues.of(0, 0, 0, 0, 0, 0),
                Level.of(20),
                Nature.Hardy
        );
        Pokemon charmander = Pokemon.from(
                "Charmander",
                new Type[]{
                        Type.Fire
                },
                BaseStats.of(39, 52, 43, 60, 50, 65),
                IndividualValues.of(8, 14, 1, 31, 23, 5),
                EffortValues.of(0, 0, 0, 0, 0, 0),
                Level.of(20),
                Nature.Docile
        );

        String[] toto = new String[]{"", ""};

        Pokemon winner = new Battle().fight(bulbasaur, charmander);

        assertThat(winner).isEqualTo(bulbasaur);
    }
}
