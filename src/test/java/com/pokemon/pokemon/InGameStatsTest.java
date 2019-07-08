package com.pokemon.pokemon;

import com.pokemon.pokemon.InGameStats;
import com.pokemon.stats.Stats;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class InGameStatsTest {

    private Stats maximumStats;

    @Before
    public void initTest() {
        maximumStats = mock(Stats.class);
    }

    @Test
    public void should_be_faster_if_speed_stat_is_greater() {
        Random randomGenerator = mock(Random.class);
        InGameStats first = InGameStats.of(
                maximumStats,
                Stats.of(1, 1, 1, 1, 1, 2),
                randomGenerator
        );
        InGameStats second = InGameStats.of(
                maximumStats,
                Stats.of(1, 1, 1, 1, 1, 1),
                randomGenerator
        );

        boolean isFaster = first.isOwnSpeedAboveOther(second);

        verify(randomGenerator, never()).nextInt();
        assertThat(isFaster).isTrue();
    }

    @Test
    public void should_be_faster_if_speed_stat_is_equal_but_random_number_is_equal_to_zero() {
        Random randomGenerator = mock(Random.class);
        InGameStats first = InGameStats.of(
                maximumStats,
                Stats.of(1, 1, 1, 1, 1, 1),
                randomGenerator
        );
        InGameStats second = InGameStats.of(
                maximumStats,
                Stats.of(1, 1, 1, 1, 1, 1),
                randomGenerator
        );

        when(randomGenerator.nextInt(2)).thenReturn(0);

        boolean isFaster = first.isOwnSpeedAboveOther(second);

        verify(randomGenerator).nextInt(2);
        assertThat(isFaster).isTrue();
    }

    @Test
    public void should_be_slower_if_speed_stat_is_equal_but_random_number_is_equal_to_1() {
        Random randomGenerator = mock(Random.class);
        InGameStats first = InGameStats.of(
                maximumStats,
                Stats.of(1, 1, 1, 1, 1, 1),
                randomGenerator
        );
        InGameStats second = InGameStats.of(
                maximumStats,
                Stats.of(1, 1, 1, 1, 1, 1),
                randomGenerator
        );

        when(randomGenerator.nextInt(2)).thenReturn(1);

        boolean isFaster = first.isOwnSpeedAboveOther(second);

        verify(randomGenerator).nextInt(2);
        assertThat(isFaster).isFalse();
    }

    @Test
    public void should_remove_proper_amount_of_hp() {
        InGameStats inGameStats = InGameStats.of(
                maximumStats,
                Stats.of(100, 1, 1, 1, 1, 1)
        );

        inGameStats.removeHp(20);

        InGameStats expectedStats = InGameStats.of(
                maximumStats,
                Stats.of(80, 1, 1, 1, 1, 1)
        );

        assertThat(inGameStats).isEqualTo(expectedStats);
    }

    @Test
    public void should_get_0_hp_when_remove_more_hp_than_actual_hp() {
        InGameStats inGameStats = InGameStats.of(
                maximumStats,
                Stats.of(100, 1, 1, 1, 1, 1)
        );

        inGameStats.removeHp(110);

        InGameStats expectedStats = InGameStats.of(
                maximumStats,
                Stats.of(0, 1, 1, 1, 1, 1)
        );

        assertThat(inGameStats).isEqualTo(expectedStats);
    }

    @Test
    public void should_be_ko_if_hp_is_equal_to_0() {
        InGameStats inGameStats = InGameStats.of(
                maximumStats,
                Stats.of(0, 1, 1, 1, 1, 1)
        );

        boolean ko = inGameStats.isHpBelowOrEqualTo(0);

        assertThat(ko).isTrue();
    }

    @Test
    public void should_not_be_ko_if_hp_is_greater_than_0() {
        InGameStats inGameStats = InGameStats.of(
                maximumStats,
                Stats.of(1, 1, 1, 1, 1, 1)
        );

        boolean ko = inGameStats.isHpBelowOrEqualTo(0);

        assertThat(ko).isFalse();
    }
}