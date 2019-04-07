package com.pokemon;

import com.pokemon.battle.ComputedStatsPokemon;
import com.pokemon.battle.PokemonFactory;
import com.pokemon.moves.Move;
import com.pokemon.stats.Level;
import com.pokemon.stats.Nature;
import com.pokemon.stats.Stats;
import com.pokemon.stats.Type;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PokemonTest {

    @Test
    public void getComputedStatsPokemon() {
        PokemonFactory pokemonFactory = mock(PokemonFactory.class);
        Pokemon pokemon = Pokemon.from(
                "Bulbasaur",
                new Type[]{
                        Type.GRASS,
                        Type.POISON
                },
                Nature.Hardy,
                Stats.of(45, 49, 49, 65, 65, 45),
                Stats.of(28, 4, 17, 30, 27, 31),
                Stats.of(0, 0, 0, 0, 0, 0),
                Level.of(15),
                new Move[]{
                        mock(Move.class)
                },
                pokemonFactory
        );
        ComputedStatsPokemon mockComputedStatsPokemon = mock(ComputedStatsPokemon.class);
        when(pokemonFactory.createFrom(pokemon)).thenReturn(mockComputedStatsPokemon);

        ComputedStatsPokemon computedStatsPokemon = pokemon.getComputedStatsPokemon();

        verify(pokemonFactory).createFrom(pokemon);
        assertThat(computedStatsPokemon).isEqualTo(mockComputedStatsPokemon);
    }
}