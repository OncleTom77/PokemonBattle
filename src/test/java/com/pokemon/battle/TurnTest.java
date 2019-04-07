package com.pokemon.battle;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TurnTest {

    @Test
    public void should_resolve_turn_with_first_pokemon_making_move_first_if_first_pokemon_is_faster() {
        InTurnPokemon firstPokemon = mock(InTurnPokemon.class);
        InTurnPokemon secondPokemon = mock(InTurnPokemon.class);

        when(firstPokemon.isFasterThan(secondPokemon)).thenReturn(true);
        when(firstPokemon.isKO()).thenReturn(false);
        when(secondPokemon.isKO()).thenReturn(false);

        Turn.of(firstPokemon, secondPokemon)
                .resolveWithPotentialWinner();

        verify(firstPokemon).isFasterThan(secondPokemon);
        InOrder orderVerifier = Mockito.inOrder(firstPokemon, secondPokemon);
        orderVerifier.verify(firstPokemon).makeMoveOn(secondPokemon);
        orderVerifier.verify(secondPokemon).makeMoveOn(firstPokemon);
    }

    @Test
    public void should_resolve_turn_with_second_pokemon_making_move_first_if_second_pokemon_is_faster() {
        InTurnPokemon firstPokemon = mock(InTurnPokemon.class);
        InTurnPokemon secondPokemon = mock(InTurnPokemon.class);

        when(firstPokemon.isFasterThan(secondPokemon)).thenReturn(false);
        when(firstPokemon.isKO()).thenReturn(false);
        when(secondPokemon.isKO()).thenReturn(false);

        Turn.of(firstPokemon, secondPokemon)
                .resolveWithPotentialWinner();

        verify(firstPokemon).isFasterThan(secondPokemon);
        InOrder orderVerifier = Mockito.inOrder(firstPokemon, secondPokemon);
        orderVerifier.verify(secondPokemon).makeMoveOn(firstPokemon);
        orderVerifier.verify(firstPokemon).makeMoveOn(secondPokemon);
    }

    @Test
    public void should_resolve_turn_without_a_winner() {
        InTurnPokemon firstPokemon = mock(InTurnPokemon.class);
        InTurnPokemon secondPokemon = mock(InTurnPokemon.class);

        when(firstPokemon.isFasterThan(secondPokemon)).thenReturn(true);
        when(firstPokemon.isKO()).thenReturn(false);
        when(secondPokemon.isKO()).thenReturn(false);

        Optional<InTurnPokemon> potentialWinner = Turn.of(firstPokemon, secondPokemon)
                .resolveWithPotentialWinner();

        verify(firstPokemon).isFasterThan(secondPokemon);
        verify(firstPokemon).makeMoveOn(secondPokemon);
        verify(secondPokemon).makeMoveOn(firstPokemon);
        verify(firstPokemon, times(2)).isKO();
        verify(secondPokemon, times(2)).isKO();
        assertThat(potentialWinner.isPresent()).isFalse();
    }

    @Test
    public void should_resolve_turn_with_faster_pokemon_as_winner_after_first_move() {
        InTurnPokemon firstPokemon = mock(InTurnPokemon.class);
        InTurnPokemon secondPokemon = mock(InTurnPokemon.class);

        when(firstPokemon.isFasterThan(secondPokemon)).thenReturn(true);
        when(firstPokemon.isKO()).thenReturn(false);
        when(secondPokemon.isKO()).thenReturn(true);

        Optional<InTurnPokemon> potentialWinner = Turn.of(firstPokemon, secondPokemon)
                .resolveWithPotentialWinner();

        verify(firstPokemon).isFasterThan(secondPokemon);
        verify(firstPokemon).makeMoveOn(secondPokemon);
        verify(secondPokemon, never()).makeMoveOn(firstPokemon);
        verify(firstPokemon).isKO();
        verify(secondPokemon).isKO();
        assertThat(potentialWinner.isPresent()).isTrue();
        assertThat(potentialWinner.get()).isEqualTo(firstPokemon);
    }

    @Test
    public void should_resolve_turn_with_faster_pokemon_as_winner_after_second_move() {
        InTurnPokemon firstPokemon = mock(InTurnPokemon.class);
        InTurnPokemon secondPokemon = mock(InTurnPokemon.class);

        when(firstPokemon.isFasterThan(secondPokemon)).thenReturn(true);
        when(firstPokemon.isKO()).thenReturn(false, false);
        when(secondPokemon.isKO()).thenReturn(false, true);

        Optional<InTurnPokemon> potentialWinner = Turn.of(firstPokemon, secondPokemon)
                .resolveWithPotentialWinner();

        verify(firstPokemon).isFasterThan(secondPokemon);
        verify(firstPokemon).makeMoveOn(secondPokemon);
        verify(secondPokemon).makeMoveOn(firstPokemon);
        verify(firstPokemon, times(2)).isKO();
        verify(secondPokemon, times(2)).isKO();
        assertThat(potentialWinner.isPresent()).isTrue();
        assertThat(potentialWinner.get()).isEqualTo(firstPokemon);
    }

    @Test
    public void should_resolve_turn_with_slower_pokemon_as_winner_after_first_move() {
        InTurnPokemon firstPokemon = mock(InTurnPokemon.class);
        InTurnPokemon secondPokemon = mock(InTurnPokemon.class);

        when(firstPokemon.isFasterThan(secondPokemon)).thenReturn(true);
        when(firstPokemon.isKO()).thenReturn(true);

        Optional<InTurnPokemon> potentialWinner = Turn.of(firstPokemon, secondPokemon)
                .resolveWithPotentialWinner();

        verify(firstPokemon).isFasterThan(secondPokemon);
        verify(firstPokemon).makeMoveOn(secondPokemon);
        verify(secondPokemon, never()).makeMoveOn(firstPokemon);
        verify(firstPokemon, times(1)).isKO();
        verify(secondPokemon, never()).isKO();
        assertThat(potentialWinner.isPresent()).isTrue();
        assertThat(potentialWinner.get()).isEqualTo(secondPokemon);
    }

    @Test
    public void should_resolve_turn_with_slower_pokemon_as_winner_after_second_move() {
        InTurnPokemon firstPokemon = mock(InTurnPokemon.class);
        InTurnPokemon secondPokemon = mock(InTurnPokemon.class);

        when(firstPokemon.isFasterThan(secondPokemon)).thenReturn(true);
        when(firstPokemon.isKO()).thenReturn(false, true);
        when(secondPokemon.isKO()).thenReturn(false);

        Optional<InTurnPokemon> potentialWinner = Turn.of(firstPokemon, secondPokemon)
                .resolveWithPotentialWinner();

        verify(firstPokemon).isFasterThan(secondPokemon);
        verify(firstPokemon).makeMoveOn(secondPokemon);
        verify(secondPokemon).makeMoveOn(firstPokemon);
        verify(firstPokemon, times(2)).isKO();
        verify(secondPokemon).isKO();
        assertThat(potentialWinner.isPresent()).isTrue();
        assertThat(potentialWinner.get()).isEqualTo(secondPokemon);
    }
}
