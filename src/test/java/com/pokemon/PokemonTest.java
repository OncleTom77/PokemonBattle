package com.pokemon;

import com.pokemon.battle.ComputedStatsPokemon;
import com.pokemon.battle.PokemonFactory;
import com.pokemon.moves.Move;
import com.pokemon.stats.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PokemonTest {

    @Test
    public void should_get_proper_ComputedStatsPokemon() {
        PokemonFactory pokemonFactory = mock(PokemonFactory.class);
        Pokemon pokemon = Pokemon.from(
                "Bulbasaur",
                new Type[]{
                        Type.GRASS,
                        Type.POISON
                },
                Nature.Hardy,
                Stats.of(45, 49, 49, 65, 65, 45),
                Stats.of(28, 4, 17, 30, 27, 31),
                Stats.of(0, 0, 0, 0, 0, 0),
                Level.of(15),
                new Move[]{
                        mock(Move.class)
                },
                pokemonFactory
        );
        ComputedStatsPokemon mockComputedStatsPokemon = mock(ComputedStatsPokemon.class);
        when(pokemonFactory.createFrom(pokemon)).thenReturn(mockComputedStatsPokemon);

        ComputedStatsPokemon computedStatsPokemon = pokemon.getComputedStatsPokemon();

        verify(pokemonFactory).createFrom(pokemon);
        assertThat(computedStatsPokemon).isEqualTo(mockComputedStatsPokemon);
    }

    @Test
    public void should_have_type_when_type_is_one_of_pokemon_types() {
        Pokemon pokemon = Pokemon.from(
                "name",
                new Type[]{Type.NORMAL, Type.GRASS},
                Nature.Hardy,
                mock(Stats.class),
                mock(Stats.class),
                mock(Stats.class),
                mock(Level.class),
                new Move[]{mock(Move.class)},
                mock(PokemonFactory.class)
        );

        boolean hasFirstType = pokemon.hasType(Type.NORMAL);
        boolean hasSecondType = pokemon.hasType(Type.GRASS);

        assertThat(hasFirstType).isTrue();
        assertThat(hasSecondType).isTrue();
    }

    @Test
    public void should_not_have_type_when_type_is_not_one_of_pokemon_types() {
        Pokemon pokemon = Pokemon.from(
                "name",
                new Type[]{Type.NORMAL, Type.GRASS},
                Nature.Hardy,
                mock(Stats.class),
                mock(Stats.class),
                mock(Stats.class),
                mock(Level.class),
                new Move[]{mock(Move.class)},
                mock(PokemonFactory.class)
        );

        boolean hasFirstType = pokemon.hasType(Type.FIRE);

        assertThat(hasFirstType).isFalse();
    }

    @Test
    public void should_be_immune_if_one_of_the_pokemon_types_is_immune_to_move_type() {
        Type firstType = mock(Type.class);
        Type secondType = mock(Type.class);
        Pokemon pokemon = Pokemon.from(
                "name",
                new Type[]{firstType, secondType},
                Nature.Hardy,
                mock(Stats.class),
                mock(Stats.class),
                mock(Stats.class),
                mock(Level.class),
                new Move[]{mock(Move.class)},
                mock(PokemonFactory.class)
        );
        Type moveType = Type.NORMAL;

        when(firstType.getSensibilityForMoveType(moveType)).thenReturn(Sensibility.NEUTRAL);
        when(secondType.getSensibilityForMoveType(moveType)).thenReturn(Sensibility.IMMUNE);

        boolean isImmune = pokemon.isImmuneTo(moveType);

        assertThat(isImmune).isTrue();
    }

    @Test
    public void should_not_be_immune_if_none_of_the_pokemon_types_is_immune_to_move_type() {
        Type firstType = mock(Type.class);
        Type secondType = mock(Type.class);
        Pokemon pokemon = Pokemon.from(
                "name",
                new Type[]{firstType, secondType},
                Nature.Hardy,
                mock(Stats.class),
                mock(Stats.class),
                mock(Stats.class),
                mock(Level.class),
                new Move[]{mock(Move.class)},
                mock(PokemonFactory.class)
        );
        Type moveType = Type.NORMAL;

        when(firstType.getSensibilityForMoveType(moveType)).thenReturn(Sensibility.NEUTRAL);
        when(secondType.getSensibilityForMoveType(moveType)).thenReturn(Sensibility.SENSITIVE);

        boolean isImmune = pokemon.isImmuneTo(moveType);

        assertThat(isImmune).isFalse();
    }
}