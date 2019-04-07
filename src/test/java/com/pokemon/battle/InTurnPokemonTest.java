package com.pokemon.battle;

import com.pokemon.moves.Move;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InTurnPokemonTest {

    @Test
    public void should_call_move_execute_method() {
        Move selectedMove = mock(Move.class);
        InTurnPokemon firstInTurnPokemon = InTurnPokemon.from(
                mock(ComputedStatsPokemon.class),
                selectedMove
        );
        InTurnPokemon secondInTurnPokemon = mock(InTurnPokemon.class);

        firstInTurnPokemon.makeMoveOn(secondInTurnPokemon);

        verify(selectedMove).execute(firstInTurnPokemon, secondInTurnPokemon);
    }
}