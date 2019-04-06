package com.pokemon;

import com.pokemon.battle.ComputedStatsPokemon;
import com.pokemon.battle.ComputedStatsPokemonFactory;
import com.pokemon.battle.Move;
import com.pokemon.stats.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PokemonTest {

    @Test
    public void getComputedStatsPokemon() {
        ComputedStatsPokemonFactory computedStatsPokemonFactory = mock(ComputedStatsPokemonFactory.class);
        Pokemon pokemon = Pokemon.from(
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
                computedStatsPokemonFactory
        );
        ComputedStatsPokemon mockComputedStatsPokemon = mock(ComputedStatsPokemon.class);
        when(computedStatsPokemonFactory.createFrom(pokemon)).thenReturn(mockComputedStatsPokemon);

        ComputedStatsPokemon computedStatsPokemon = pokemon.getComputedStatsPokemon();

        verify(computedStatsPokemonFactory).createFrom(pokemon);
        assertThat(computedStatsPokemon).isEqualTo(mockComputedStatsPokemon);
    }
}