package com.pokemon.moves;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MoveRandomGeneratorTest {

    private Random random;

    @Before
    public void setUp() throws Exception {
        random = mock(Random.class);
    }

    @Test
    public void should_call_random_generator_when_get_next_accuracy_value() {
        MoveRandomGenerator randomGenerator = new MoveRandomGenerator(random);

        when(random.nextInt(100)).thenReturn(99);

        int value = randomGenerator.nextAccuracyValue();

        verify(random).nextInt(100);
        assertThat(value).isEqualTo(99);
    }

    @Test
    public void should_call_random_generator_when_get_next_critical_hit_value() {
        MoveRandomGenerator randomGenerator = new MoveRandomGenerator(random);

        when(random.nextInt(16)).thenReturn(15);

        int value = randomGenerator.nextCriticalHitValue();

        verify(random).nextInt(16);
        assertThat(value).isEqualTo(15);
    }

    @Test
    public void should_call_random_generator_when_get_next_damage_factor() {
        MoveRandomGenerator randomGenerator = new MoveRandomGenerator(random);

        when(random.nextInt(16)).thenReturn(15);

        double value = randomGenerator.nextDamageFactor();

        verify(random).nextInt(16);
        assertThat(value).isEqualTo(1.);
    }
}