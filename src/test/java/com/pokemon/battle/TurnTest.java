package com.pokemon.battle;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TurnTest {

    @Test
    public void should_toto() {
        InTurnPokemon firstPokemon = mock(InTurnPokemon.class);
        InTurnPokemon secondPokemon = mock(InTurnPokemon.class);

        when(firstPokemon.isFasterThan(secondPokemon)).thenReturn(true);

        Optional<InTurnPokemon> potentialWinner = Turn.of(firstPokemon, secondPokemon)
                .resolveWithPotentialWinner();

        InOrder orderVerifier = Mockito.inOrder(firstPokemon, secondPokemon);
        orderVerifier.verify(firstPokemon).makeMoveOn(secondPokemon);
        orderVerifier.verify(secondPokemon).makeMoveOn(firstPokemon);
    }
}
