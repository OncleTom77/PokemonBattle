package com.pokemon.battle;

import com.pokemon.Pokemon;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BattleTest {

    @Test
    public void should_have_turns_until_second_pokemon_faints() {
        Pokemon firstPokemon = mock(Pokemon.class);
        Pokemon secondPokemon = mock(Pokemon.class);
        PokemonFactory pokemonFactory = mock(PokemonFactory.class);
        InTurnPokemon firstInTurnPokemon = mock(InTurnPokemon.class);
        InTurnPokemon secondInTurnPokemon = mock(InTurnPokemon.class);
        ComputedStatsPokemon firstComputedStatsPokemon = mock(ComputedStatsPokemon.class);
        ComputedStatsPokemon secondComputedStatsPokemon = mock(ComputedStatsPokemon.class);
        TurnFactory turnFactory = mock(TurnFactory.class);
        Turn turn = mock(Turn.class);
        Optional<InTurnPokemon> noWinner = Optional.empty();
        Optional<InTurnPokemon> firstWinner = Optional.of(firstInTurnPokemon);
        Move[] moves = new Move[]{Move.VineWhip};

        when(firstPokemon.getComputedStatsPokemon()).thenReturn(firstComputedStatsPokemon);
        when(secondPokemon.getComputedStatsPokemon()).thenReturn(secondComputedStatsPokemon);
        when(firstComputedStatsPokemon.getPokemon()).thenReturn(firstPokemon);
        when(firstPokemon.getMoves()).thenReturn(moves);
        when(secondComputedStatsPokemon.getPokemon()).thenReturn(secondPokemon);
        when(secondPokemon.getMoves()).thenReturn(moves);
        when(pokemonFactory.createFrom(firstComputedStatsPokemon, moves[0])).thenReturn(firstInTurnPokemon);
        when(pokemonFactory.createFrom(secondComputedStatsPokemon, moves[0])).thenReturn(secondInTurnPokemon);
        when(turnFactory.createFrom(firstInTurnPokemon, secondInTurnPokemon)).thenReturn(turn);
        when(turn.resolveWithPotentialWinner()).thenReturn(noWinner, noWinner, firstWinner);
        when(firstInTurnPokemon.getPokemon()).thenReturn(firstPokemon);

        Pokemon winner = new Battle(turnFactory, pokemonFactory).fight(firstPokemon, secondPokemon);

        verify(firstPokemon).getComputedStatsPokemon();
        verify(secondPokemon).getComputedStatsPokemon();
        verify(firstComputedStatsPokemon, times(3)).getPokemon();
        verify(firstPokemon, times(3)).getMoves();
        verify(secondComputedStatsPokemon, times(3)).getPokemon();
        verify(secondPokemon, times(3)).getMoves();
        verify(pokemonFactory, times(3)).createFrom(firstComputedStatsPokemon, firstComputedStatsPokemon.getPokemon().getMoves()[0]);
        verify(pokemonFactory, times(3)).createFrom(secondComputedStatsPokemon, secondComputedStatsPokemon.getPokemon().getMoves()[0]);
        verify(turnFactory, times(3)).createFrom(firstInTurnPokemon, secondInTurnPokemon);
        verify(turn, times(3)).resolveWithPotentialWinner();
        verify(firstInTurnPokemon).getPokemon();

        assertThat(winner).isEqualTo(firstPokemon);
    }

    @Test
    public void should_have_turns_until_first_pokemon_faints() {
        Pokemon firstPokemon = mock(Pokemon.class);
        Pokemon secondPokemon = mock(Pokemon.class);
        PokemonFactory pokemonFactory = mock(PokemonFactory.class);
        InTurnPokemon firstInTurnPokemon = mock(InTurnPokemon.class);
        InTurnPokemon secondInTurnPokemon = mock(InTurnPokemon.class);
        ComputedStatsPokemon firstComputedStatsPokemon = mock(ComputedStatsPokemon.class);
        ComputedStatsPokemon secondComputedStatsPokemon = mock(ComputedStatsPokemon.class);
        TurnFactory turnFactory = mock(TurnFactory.class);
        Turn turn = mock(Turn.class);
        Optional<InTurnPokemon> noWinner = Optional.empty();
        Optional<InTurnPokemon> secondWinner = Optional.of(secondInTurnPokemon);
        Move[] moves = new Move[]{Move.VineWhip};

        when(firstPokemon.getComputedStatsPokemon()).thenReturn(firstComputedStatsPokemon);
        when(secondPokemon.getComputedStatsPokemon()).thenReturn(secondComputedStatsPokemon);
        when(firstComputedStatsPokemon.getPokemon()).thenReturn(firstPokemon);
        when(firstPokemon.getMoves()).thenReturn(moves);
        when(secondComputedStatsPokemon.getPokemon()).thenReturn(secondPokemon);
        when(secondPokemon.getMoves()).thenReturn(moves);
        when(pokemonFactory.createFrom(firstComputedStatsPokemon, moves[0])).thenReturn(firstInTurnPokemon);
        when(pokemonFactory.createFrom(secondComputedStatsPokemon, moves[0])).thenReturn(secondInTurnPokemon);
        when(turnFactory.createFrom(firstInTurnPokemon, secondInTurnPokemon)).thenReturn(turn);
        when(turn.resolveWithPotentialWinner()).thenReturn(noWinner, noWinner, secondWinner);
        when(secondInTurnPokemon.getPokemon()).thenReturn(secondPokemon);

        Pokemon winner = new Battle(turnFactory, pokemonFactory).fight(firstPokemon, secondPokemon);

        verify(firstPokemon).getComputedStatsPokemon();
        verify(secondPokemon).getComputedStatsPokemon();
        verify(firstComputedStatsPokemon, times(3)).getPokemon();
        verify(firstPokemon, times(3)).getMoves();
        verify(secondComputedStatsPokemon, times(3)).getPokemon();
        verify(secondPokemon, times(3)).getMoves();
        verify(pokemonFactory, times(3)).createFrom(firstComputedStatsPokemon, firstComputedStatsPokemon.getPokemon().getMoves()[0]);
        verify(pokemonFactory, times(3)).createFrom(secondComputedStatsPokemon, secondComputedStatsPokemon.getPokemon().getMoves()[0]);
        verify(turnFactory, times(3)).createFrom(firstInTurnPokemon, secondInTurnPokemon);
        verify(turn, times(3)).resolveWithPotentialWinner();
        verify(secondInTurnPokemon).getPokemon();

        assertThat(winner).isEqualTo(secondPokemon);
    }
}
