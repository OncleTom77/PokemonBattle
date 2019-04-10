package com.pokemon.battle;

import com.pokemon.Pokemon;
import com.pokemon.moves.Move;
import com.pokemon.stats.Level;
import com.pokemon.stats.Nature;
import com.pokemon.stats.Stats;
import com.pokemon.stats.Type;
import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ComputedStatsPokemonTest {

    @Test
    public void should_create_computedStatsPokemon_accordingly_to_given_pokemon() {
        Pokemon pokemon = Pokemon.from(
                "Butterfree",
                new Type[]{
                        Type.BUG,
                        Type.FLYING
                },
                Nature.Modest,
                Stats.of(60, 45, 50, 80, 80, 70),
                Stats.of(28, 4, 17, 30, 27, 31),
                Stats.of(4, 0, 0, 254, 0, 252),
                Level.of(53),
                new Move[]{
                        mock(Move.class)
                },
                new PokemonFactory()
        );
        ComputedStatsPokemon expectedComputedStatsPokemon = ComputedStatsPokemon.from(
                pokemon,
                Stats.of(
                        141,
                        48,
                        67,
                        152,
                        104,
                        129
                )
        );

        ComputedStatsPokemon computedStatsPokemon = ComputedStatsPokemon.from(pokemon);

        assertThat(computedStatsPokemon).isEqualTo(expectedComputedStatsPokemon);
    }

    @Test
    public void should_be_faster_if_speed_stat_is_greater() {
        Random randomGenerator = mock(Random.class);
        ComputedStatsPokemon first = ComputedStatsPokemon.from(
                null,
                Stats.of(1, 1, 1, 1, 1, 2),
                randomGenerator);
        ComputedStatsPokemon second = ComputedStatsPokemon.from(
                null,
                Stats.of(1, 1, 1, 1, 1, 1),
                randomGenerator);

        boolean isFaster = first.isFasterThan(second);

        verify(randomGenerator, never()).nextInt();
        assertThat(isFaster).isTrue();
    }

    @Test
    public void should_be_faster_if_speed_stat_is_equal_but_random_number_is_equal_to_zero() {
        Random randomGenerator = mock(Random.class);
        ComputedStatsPokemon first = ComputedStatsPokemon.from(
                null,
                Stats.of(1, 1, 1, 1, 1, 1),
                randomGenerator);
        ComputedStatsPokemon second = ComputedStatsPokemon.from(
                null,
                Stats.of(1, 1, 1, 1, 1, 1),
                randomGenerator);

        when(randomGenerator.nextInt(2)).thenReturn(0);

        boolean isFaster = first.isFasterThan(second);

        verify(randomGenerator).nextInt(2);
        assertThat(isFaster).isTrue();
    }

    @Test
    public void should_be_slower_if_speed_stat_is_equal_but_random_number_is_equal_to_1() {
        Random randomGenerator = mock(Random.class);
        ComputedStatsPokemon first = ComputedStatsPokemon.from(
                null,
                Stats.of(1, 1, 1, 1, 1, 1),
                randomGenerator);
        ComputedStatsPokemon second = ComputedStatsPokemon.from(
                null,
                Stats.of(1, 1, 1, 1, 1, 1),
                randomGenerator);

        when(randomGenerator.nextInt(2)).thenReturn(1);

        boolean isFaster = first.isFasterThan(second);

        verify(randomGenerator).nextInt(2);
        assertThat(isFaster).isFalse();
    }

    @Test
    public void should_remove_proper_amount_of_hp() {
        ComputedStatsPokemon computedStatsPokemon = ComputedStatsPokemon.from(
                mock(Pokemon.class),
                Stats.of(
                        100,
                        1,
                        1,
                        1,
                        1,
                        1
                )
        );

        computedStatsPokemon.removeHp(20);

        Stats expectedStats = Stats.of(
                80,
                1,
                1,
                1,
                1,
                1
        );
        assertThat(computedStatsPokemon.getStats()).isEqualTo(expectedStats);
    }

    @Test
    public void should_get_0_hp_when_remove_more_hp_than_actual_hp() {
        ComputedStatsPokemon computedStatsPokemon = ComputedStatsPokemon.from(
                mock(Pokemon.class),
                Stats.of(
                        100,
                        1,
                        1,
                        1,
                        1,
                        1
                )
        );

        computedStatsPokemon.removeHp(110);

        Stats expectedStats = Stats.of(
                0,
                1,
                1,
                1,
                1,
                1
        );
        assertThat(computedStatsPokemon.getStats()).isEqualTo(expectedStats);
    }

    @Test
    public void should_be_ko_if_hp_is_equal_to_0() {
        ComputedStatsPokemon computedStatsPokemon = ComputedStatsPokemon.from(
                mock(Pokemon.class),
                Stats.of(
                        0,
                        1,
                        1,
                        1,
                        1,
                        1
                )
        );

        boolean ko = computedStatsPokemon.isKO();

        assertThat(ko).isTrue();
    }

    @Test
    public void should_not_be_ko_if_hp_is_greater_than_0() {
        ComputedStatsPokemon computedStatsPokemon = ComputedStatsPokemon.from(
                mock(Pokemon.class),
                Stats.of(
                        1,
                        1,
                        1,
                        1,
                        1,
                        1
                )
        );

        boolean ko = computedStatsPokemon.isKO();

        assertThat(ko).isFalse();
    }
}