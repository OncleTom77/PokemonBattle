package com.pokemon.battle;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class TurnTest {

    @Test
    public void should_toto() {
        ComputedStatsPokemon firstPokemon = mock(ComputedStatsPokemon.class);
        ComputedStatsPokemon secondPokemon = mock(ComputedStatsPokemon.class);

        Turn.of(firstPokemon, secondPokemon)
                .compute();
    }
}
