package com.pokemon.stats;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TypeTest {

    @Test
    public void should_get_proper_value_for_given_types() {
        Sensibility sensibility = Type.NORMAL.getSensibilityForMoveType(Type.FIGHTING);
        assertThat(sensibility).isEqualTo(Sensibility.SENSITIVE);

        sensibility = Type.FIGHTING.getSensibilityForMoveType(Type.NORMAL);
        assertThat(sensibility).isEqualTo(Sensibility.NEUTRAL);
    }
}