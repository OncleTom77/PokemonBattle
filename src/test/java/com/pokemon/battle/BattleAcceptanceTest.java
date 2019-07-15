package com.pokemon.battle;

import com.pokemon.moves.Ember;
import com.pokemon.moves.Move;
import com.pokemon.moves.VineWhip;
import com.pokemon.pokemon.Bulbasaur;
import com.pokemon.pokemon.Charmander;
import com.pokemon.pokemon.GeneratedPokemon;
import com.pokemon.stats.Level;
import com.pokemon.stats.Nature;
import com.pokemon.stats.Stats;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BattleAcceptanceTest {

    @Test
    public void should_battle() {
        GeneratedPokemon bulbasaur = GeneratedPokemon.of(
                new Bulbasaur(),
                Nature.HARDY,
                Stats.of(28, 4, 17, 30, 27, 31),
                Level.of(15),
                new Move[]{
                        new VineWhip()
                }
        );

        GeneratedPokemon charmander = GeneratedPokemon.of(
                new Charmander(),
                Nature.DOCILE,
                Stats.of(8, 14, 1, 31, 23, 5),
                Level.of(15),
                new Move[]{
                        new Ember()
                }
        );

        GeneratedPokemon winner = new Battle().fight(bulbasaur, charmander);

        assertThat(winner).isEqualTo(charmander);
    }
}
