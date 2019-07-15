package com.pokemon.stats;

import org.junit.Test;

import static com.pokemon.stats.Nature.Stat.*;
import static org.assertj.core.api.Assertions.assertThat;

public class NatureTest {

    @Test
    public void should_get_neutral_multiplier_value_if_neutral_nature() {
        Nature neutralNature = new Nature() {};

        float result = neutralNature.getMultiplierValueFor(Attack);
        assertThat(result).isEqualTo(1);

        result = neutralNature.getMultiplierValueFor(Speed);
        assertThat(result).isEqualTo(1);

        result = neutralNature.getMultiplierValueFor(SpecialDefense);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void should_get_increasing_multiplier_value_if_nature_increase_the_given_stat() {
        Nature neutralNature = new Nature(SpecialAttack, SpecialDefense) {};

        float result = neutralNature.getMultiplierValueFor(SpecialAttack);
        assertThat(result).isEqualTo(1.1f);
    }

    @Test
    public void should_get_decreasing_multiplier_value_if_nature_decrease_the_given_stat() {
        Nature neutralNature = new Nature(SpecialAttack, SpecialDefense) {};

        float result = neutralNature.getMultiplierValueFor(SpecialDefense);
        assertThat(result).isEqualTo(0.9f);
    }
}