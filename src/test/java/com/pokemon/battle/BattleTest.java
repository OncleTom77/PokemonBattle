package com.pokemon.battle;

import com.pokemon.Pokemon;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BattleTest {

    @Test
    public void should_have_turns_until_second_pokemon_faints() {
        Pokemon firstPokemon = mock(Pokemon.class);
        Pokemon secondPokemon = mock(Pokemon.class);
        ComputedStatsPokemon firstComputedStatsPokemon = mock(ComputedStatsPokemon.class);
        ComputedStatsPokemon secondComputedStatsPokemon = mock(ComputedStatsPokemon.class);
        TurnFactory turnFactory = mock(TurnFactory.class);
        Turn firstPokemonAttackerTurn = mock(Turn.class);
        Turn secondPokemonAttackerTurn = mock(Turn.class);

        when(firstPokemon.getComputedStatsPokemon()).thenReturn(firstComputedStatsPokemon);
        when(secondPokemon.getComputedStatsPokemon()).thenReturn(secondComputedStatsPokemon);
        when(turnFactory.createFrom(firstComputedStatsPokemon, secondComputedStatsPokemon)).thenReturn(firstPokemonAttackerTurn);
        when(turnFactory.createFrom(secondComputedStatsPokemon, firstComputedStatsPokemon)).thenReturn(secondPokemonAttackerTurn);
        when(firstComputedStatsPokemon.isKO()).thenReturn(false, false, false);
        when(secondComputedStatsPokemon.isKO()).thenReturn(false, false, true);
        when(firstComputedStatsPokemon.isFasterThan(secondComputedStatsPokemon)).thenReturn(true);

        Pokemon winner = new Battle(turnFactory).fight(firstPokemon, secondPokemon);

        verify(firstPokemon).getComputedStatsPokemon();
        verify(secondPokemon).getComputedStatsPokemon();
        verify(firstComputedStatsPokemon, times(2)).isKO();
        verify(secondComputedStatsPokemon, times(3)).isKO();
        verify(firstPokemonAttackerTurn, times(3)).compute();
        verify(secondPokemonAttackerTurn, times(2)).compute();

        assertThat(winner).isEqualTo(firstPokemon);
    }

    @Test
    public void should_have_turns_until_first_pokemon_faints() {
        Pokemon firstPokemon = mock(Pokemon.class);
        Pokemon secondPokemon = mock(Pokemon.class);
        ComputedStatsPokemon firstComputedStatsPokemon = mock(ComputedStatsPokemon.class);
        ComputedStatsPokemon secondComputedStatsPokemon = mock(ComputedStatsPokemon.class);
        TurnFactory turnFactory = mock(TurnFactory.class);
        Turn firstPokemonAttackerTurn = mock(Turn.class);
        Turn secondPokemonAttackerTurn = mock(Turn.class);

        when(firstPokemon.getComputedStatsPokemon()).thenReturn(firstComputedStatsPokemon);
        when(secondPokemon.getComputedStatsPokemon()).thenReturn(secondComputedStatsPokemon);
        when(turnFactory.createFrom(firstComputedStatsPokemon, secondComputedStatsPokemon)).thenReturn(firstPokemonAttackerTurn);
        when(turnFactory.createFrom(secondComputedStatsPokemon, firstComputedStatsPokemon)).thenReturn(secondPokemonAttackerTurn);
        when(firstComputedStatsPokemon.isKO()).thenReturn(false, false, true);
        when(secondComputedStatsPokemon.isKO()).thenReturn(false, false, false);
        when(firstComputedStatsPokemon.isFasterThan(secondComputedStatsPokemon)).thenReturn(true);

        Pokemon winner = new Battle(turnFactory).fight(firstPokemon, secondPokemon);

        verify(firstPokemon).getComputedStatsPokemon();
        verify(secondPokemon).getComputedStatsPokemon();
        verify(firstComputedStatsPokemon, times(3)).isKO();
        verify(secondComputedStatsPokemon, times(3)).isKO();
        verify(firstPokemonAttackerTurn, times(3)).compute();
        verify(secondPokemonAttackerTurn, times(3)).compute();

        assertThat(winner).isEqualTo(secondPokemon);
    }
}
