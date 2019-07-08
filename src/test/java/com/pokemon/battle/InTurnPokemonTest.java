package com.pokemon.battle;

import com.pokemon.pokemon.GeneratedPokemon;
import com.pokemon.moves.Move;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InTurnPokemonTest {

    @Test
    public void should_call_move_execute_method() {
        Move selectedMove = mock(Move.class);
        GeneratedPokemon firstComputedStatsPokemon = mock(GeneratedPokemon.class);
        InTurnPokemon firstInTurnPokemon = InTurnPokemon.from(
                firstComputedStatsPokemon,
                selectedMove
        );
        GeneratedPokemon secondComputedStatsPokemon = mock(GeneratedPokemon.class);
        InTurnPokemon secondInTurnPokemon = InTurnPokemon.from(
                secondComputedStatsPokemon,
                selectedMove
        );

        firstInTurnPokemon.makeMoveOn(secondInTurnPokemon);

        verify(selectedMove).execute(firstComputedStatsPokemon, secondComputedStatsPokemon);
    }
}