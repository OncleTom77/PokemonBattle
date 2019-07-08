package com.pokemon.battle;

import com.pokemon.pokemon.GeneratedPokemon;
import com.pokemon.moves.Move;
import com.pokemon.pokemon.PokemonFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BattleTest {

    private TurnFactory turnFactory;
    private PokemonFactory pokemonFactory;
    private Turn turn;

    private GeneratedPokemon firstPokemon;
    private Move firstPokemonMove;
    private InTurnPokemon inTurnFirstPokemon;

    private GeneratedPokemon secondPokemon;
    private Move secondPokemonMove;
    private InTurnPokemon inTurnSecondPokemon;

    @Before
    public void initTest() {
        turnFactory = mock(TurnFactory.class);
        pokemonFactory = mock(PokemonFactory.class);
        turn = mock(Turn.class);

        firstPokemon = mock(GeneratedPokemon.class);
        firstPokemonMove = mock(Move.class);
        inTurnFirstPokemon = mock(InTurnPokemon.class);
        when(firstPokemon.getMove(0)).thenReturn(firstPokemonMove);
        when(pokemonFactory.createFrom(firstPokemon, firstPokemonMove)).thenReturn(inTurnFirstPokemon);
        when(inTurnFirstPokemon.getGeneratedPokemon()).thenReturn(firstPokemon);

        secondPokemon = mock(GeneratedPokemon.class);
        secondPokemonMove = mock(Move.class);
        inTurnSecondPokemon = mock(InTurnPokemon.class);
        when(secondPokemon.getMove(0)).thenReturn(secondPokemonMove);
        when(pokemonFactory.createFrom(secondPokemon, secondPokemonMove)).thenReturn(inTurnSecondPokemon);
        when(inTurnSecondPokemon.getGeneratedPokemon()).thenReturn(secondPokemon);

        when(turnFactory.createFrom(inTurnFirstPokemon, inTurnSecondPokemon)).thenReturn(turn);
    }

    @Test
    public void should_have_turns_until_second_pokemon_faints() {
        Optional<InTurnPokemon> noWinner = Optional.empty();
        Optional<InTurnPokemon> firstWinner = Optional.of(inTurnFirstPokemon);

        when(turn.resolveWithPotentialWinner()).thenReturn(noWinner, noWinner, firstWinner);

        GeneratedPokemon winner = new Battle(turnFactory, pokemonFactory).fight(firstPokemon, secondPokemon);

        verify(pokemonFactory, times(3)).createFrom(firstPokemon, firstPokemonMove);
        verify(pokemonFactory, times(3)).createFrom(secondPokemon, secondPokemonMove);
        verify(turnFactory, times(3)).createFrom(inTurnFirstPokemon, inTurnSecondPokemon);
        verify(turn, times(3)).resolveWithPotentialWinner();

        assertThat(winner).isEqualTo(firstPokemon);
    }

    @Test
    public void should_have_turns_until_first_pokemon_faints() {
        Optional<InTurnPokemon> noWinner = Optional.empty();
        Optional<InTurnPokemon> secondWinner = Optional.of(inTurnSecondPokemon);

        when(turn.resolveWithPotentialWinner()).thenReturn(noWinner, noWinner, secondWinner);

        GeneratedPokemon winner = new Battle(turnFactory, pokemonFactory).fight(firstPokemon, secondPokemon);

        verify(pokemonFactory, times(3)).createFrom(firstPokemon, firstPokemonMove);
        verify(pokemonFactory, times(3)).createFrom(secondPokemon, secondPokemonMove);
        verify(turnFactory, times(3)).createFrom(inTurnFirstPokemon, inTurnSecondPokemon);
        verify(turn, times(3)).resolveWithPotentialWinner();

        assertThat(winner).isEqualTo(secondPokemon);
    }
}
