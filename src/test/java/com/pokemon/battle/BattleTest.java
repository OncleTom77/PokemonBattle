package com.pokemon.battle;

import com.pokemon.Pokemon;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BattleTest {

    @Test
    public void should_have_turns_until_a_pokemon_faints() {
        TurnFactory turnFactory = mock(TurnFactory.class);
        Pokemon firstPokemon = mock(Pokemon.class);
        Pokemon secondPokemon = mock(Pokemon.class);
        Turn firstPokemonAttackerTurn = mock(Turn.class);
        Turn secondPokemonAttackerTurn = mock(Turn.class);

        when(turnFactory.createFrom(firstPokemon, secondPokemon)).thenReturn(firstPokemonAttackerTurn);
        when(turnFactory.createFrom(secondPokemon, firstPokemon)).thenReturn(secondPokemonAttackerTurn);
        when(firstPokemon.isKO()).thenReturn(false, false, false);
        when(secondPokemon.isKO()).thenReturn(false, false, true);
        when(firstPokemon.getSpeed()).thenReturn(50);
        when(secondPokemon.getSpeed()).thenReturn(45);

        Pokemon winner = new Battle(turnFactory).fight(firstPokemon, secondPokemon);

        verify(firstPokemon, times(2)).isKO();
        verify(secondPokemon, times(3)).isKO();
        verify(firstPokemonAttackerTurn, times(3)).compute();
        verify(secondPokemonAttackerTurn, times(2)).compute();

        assertThat(winner).isEqualTo(firstPokemon);
    }
}
