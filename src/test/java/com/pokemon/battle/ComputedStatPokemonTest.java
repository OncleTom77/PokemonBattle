package com.pokemon.battle;

import com.pokemon.Pokemon;
import com.pokemon.stats.*;
import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ComputedStatPokemonTest {

    @Test
    public void should_create_computedStatsPokemon_accordingly_to_given_pokemon() {
        Pokemon pokemon = Pokemon.from(
                "Butterfree",
                new Type[]{
                        Type.Bug,
                        Type.Flying
                },
                Nature.Modest,
                Stats.of(60, 45, 50, 80, 80, 70),
                Stats.of(28, 4, 17, 30, 27, 31),
                Stats.of(4, 0, 0, 254, 0, 252),
                Level.of(53),
                new Move[]{
                        Move.Gust
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
}