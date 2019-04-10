package com.pokemon.battle;

import com.pokemon.moves.Move;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InTurnPokemonTest {

    @Test
    public void should_call_move_execute_method() {
        Move selectedMove = mock(Move.class);
        ComputedStatsPokemon firstComputedStatsPokemon = mock(ComputedStatsPokemon.class);
        InTurnPokemon firstInTurnPokemon = InTurnPokemon.from(
                firstComputedStatsPokemon,
                selectedMove
        );
        ComputedStatsPokemon secondComputedStatsPokemon = mock(ComputedStatsPokemon.class);
        InTurnPokemon secondInTurnPokemon = InTurnPokemon.from(
                secondComputedStatsPokemon,
                selectedMove
        );

        firstInTurnPokemon.makeMoveOn(secondInTurnPokemon);

        verify(selectedMove).execute(firstComputedStatsPokemon, secondComputedStatsPokemon);
    }
}