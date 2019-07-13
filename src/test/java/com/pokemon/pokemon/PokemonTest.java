package com.pokemon.pokemon;

import com.pokemon.stats.Sensibility;
import com.pokemon.stats.Stats;
import com.pokemon.stats.Type;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PokemonTest {

    private Type firstType;
    private Type secondType;
    private Type thirdType;
    private Pokemon pokemon;

    private class PokemonMock extends Pokemon {
        PokemonMock(String name, Type[] types, Stats baseStats) {
            super(name, types, baseStats);
        }
    }

    @Before
    public void initTest() {
        this.firstType = mock(Type.class);
        this.secondType = mock(Type.class);
        this.thirdType = mock(Type.class);
        this.pokemon = new PokemonMock(
                "name",
                new Type[]{
                        firstType,
                        secondType
                },
                mock(Stats.class)
        );
    }

    @Test
    public void should_have_type_when_type_is_one_of_pokemon_types() {
        boolean hasFirstType = pokemon.hasType(firstType);
        boolean hasSecondType = pokemon.hasType(secondType);

        assertThat(hasFirstType).isTrue();
        assertThat(hasSecondType).isTrue();
    }

    @Test
    public void should_not_have_type_when_type_is_not_one_of_pokemon_types() {
        boolean hasFirstType = pokemon.hasType(thirdType);

        assertThat(hasFirstType).isFalse();
    }

    @Test
    public void should_be_immune_if_one_of_the_pokemon_types_is_immune_to_move_type() {
        Type moveType = thirdType;

        when(firstType.getSensibilityForMoveType(moveType)).thenReturn(Sensibility.NEUTRAL);
        when(secondType.getSensibilityForMoveType(moveType)).thenReturn(Sensibility.IMMUNE);

        boolean isImmune = pokemon.isImmuneTo(moveType);

        assertThat(isImmune).isTrue();
    }

    @Test
    public void should_not_be_immune_if_none_of_the_pokemon_types_is_immune_to_move_type() {
        Type moveType = thirdType;

        when(firstType.getSensibilityForMoveType(moveType)).thenReturn(Sensibility.NEUTRAL);
        when(secondType.getSensibilityForMoveType(moveType)).thenReturn(Sensibility.SENSITIVE);

        boolean isImmune = pokemon.isImmuneTo(moveType);

        assertThat(isImmune).isFalse();
    }

    @Test
    public void should_get_sensibility_factor_to_move_type_as_multiplication_of_each_pokemon_type_sensibility_factor_to_move_type() {
        Type moveType = thirdType;

        when(firstType.getSensibilityForMoveType(moveType)).thenReturn(Sensibility.NEUTRAL);
        when(secondType.getSensibilityForMoveType(moveType)).thenReturn(Sensibility.RESISTANT);

        Double factor = pokemon.getSensibilityFactorToType(moveType);

        Double expectedFactor = Sensibility.NEUTRAL.getDamageCoefficient() * Sensibility.RESISTANT.getDamageCoefficient();
        assertThat(factor).isEqualTo(expectedFactor);
    }
}