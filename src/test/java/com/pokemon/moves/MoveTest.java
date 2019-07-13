package com.pokemon.moves;

import com.pokemon.pokemon.GeneratedPokemon;
import com.pokemon.pokemon.InGameStats;
import com.pokemon.battle.DamageCategory;
import com.pokemon.stats.Level;
import com.pokemon.stats.Sensibility;
import com.pokemon.stats.Stats;
import com.pokemon.stats.Type;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class MoveTest {

    private GeneratedPokemon attacker;
    private Level attackerLevel;
    private InGameStats attackerInGameStats;
    private Stats attackerStats;

    private GeneratedPokemon target;
    private InGameStats targetInGameStats;
    private Stats targetStats;

    private MoveRandomGenerator randomGenerator;

    class MoveMock extends Move {
        MoveMock(Type type, DamageCategory damageCategory, int power, int accuracy, int powerPoint, boolean hasHighCriticalHitRatio, MoveRandomGenerator randomGenerator) {
            super("Move Mock", "Move description", type, damageCategory, power, accuracy, powerPoint, hasHighCriticalHitRatio, randomGenerator);
        }
    }

    @Before
    public void setUp() throws Exception {
        attacker = mock(GeneratedPokemon.class);
        attackerLevel = mock(Level.class);
        attackerInGameStats = mock(InGameStats.class);
        attackerStats = mock(Stats.class);

        target = mock(GeneratedPokemon.class);
        targetInGameStats = mock(InGameStats.class);
        targetStats = mock(Stats.class);

        randomGenerator = mock(MoveRandomGenerator.class);

        when(target.isImmuneTo(Type.GRASS)).thenReturn(false);
        when(attacker.getInGameStats()).thenReturn(attackerInGameStats);
        when(attackerInGameStats.getCurrentStats()).thenReturn(attackerStats);
        when(attacker.getLevel()).thenReturn(attackerLevel);
        when(target.getInGameStats()).thenReturn(targetInGameStats);
        when(targetInGameStats.getCurrentStats()).thenReturn(targetStats);

        doNothing().when(target).removeHp(anyInt());

        when(attackerLevel.getValue()).thenReturn(1);
        when(attackerStats.getAttack()).thenReturn(1);
        when(attackerStats.getSpecialAttack()).thenReturn(1);
        when(targetStats.getDefense()).thenReturn(1);
        when(targetStats.getSpecialDefense()).thenReturn(1);
        when(randomGenerator.nextAccuracyValue()).thenReturn(50);
        when(randomGenerator.nextCriticalHitValue(false)).thenReturn(1);
        when(randomGenerator.nextDamageFactor()).thenReturn(1.);
        when(attacker.hasType(any())).thenReturn(false);
        when(target.getSensibilityFactorToType(any(Type.class))).thenReturn(1.0);
    }

    @Test
    public void should_throw_InsufficientPowerPointException_when_execute_move_without_enough_power_point() {
        Move move = new MoveMock(
                Type.GRASS,
                DamageCategory.Physical,
                45,
                50,
                0,
                false,
                randomGenerator
        );

        try {
            move.execute(attacker, target);
            fail("Expected InsufficientPowerPointException");
        } catch (InsufficientPowerPointException e) {
            verify(randomGenerator, never()).nextAccuracyValue();
        }
    }

    @Test
    public void should_reduce_power_point_by_one_when_accuracy_value_is_lower_than_accuracy_stats_of_attacker_move() {
        Move move = new MoveMock(
                Type.GRASS,
                DamageCategory.Physical,
                45,
                50,
                25,
                false,
                randomGenerator
        );

        when(randomGenerator.nextAccuracyValue()).thenReturn(49);

        move.execute(attacker, target);

        verify(randomGenerator).nextAccuracyValue();
        Move expectedMove = new MoveMock(
                Type.GRASS,
                DamageCategory.Physical,
                45,
                50,
                24,
                false,
                randomGenerator
        );
        assertThat(move).isEqualTo(expectedMove);
    }

    @Test
    public void should_not_hit_target_if_accuracy_value_is_greater_than_or_equal_accuracy_stats_of_attacker_move() {
        Move move = new MoveMock(
                Type.GRASS,
                DamageCategory.Physical,
                45,
                50,
                25,
                false,
                randomGenerator
        );

        move.execute(attacker, target);

        Move expectedMove = new MoveMock(
                Type.GRASS,
                DamageCategory.Physical,
                45,
                50,
                25,
                false,
                randomGenerator
        );
        verify(target, never()).removeHp(anyInt());
        assertThat(move).isEqualTo(expectedMove);
    }

    @Test
    public void should_inflict_physical_damage_to_target() {
        Move move = new MoveMock(
                Type.GRASS,
                DamageCategory.Physical,
                45,
                100,
                25,
                false,
                randomGenerator
        );

        when(attackerLevel.getValue()).thenReturn(20);
        when(attackerStats.getAttack()).thenReturn(10);
        when(targetStats.getDefense()).thenReturn(10);

        move.execute(attacker, target);

        verify(attackerLevel).getValue();
        verify(attacker).getInGameStats();
        verify(targetInGameStats).getCurrentStats();
        verify(attackerStats).getAttack();
        verify(targetStats).getDefense();
        verify(attackerStats, never()).getSpecialAttack();
        verify(targetStats, never()).getSpecialDefense();
        verify(target).removeHp(11);
    }

    @Test
    public void should_inflict_special_damage_to_target() {
        Move move = new MoveMock(
                Type.GRASS,
                DamageCategory.Special,
                45,
                100,
                25,
                false,
                randomGenerator
        );

        when(attackerLevel.getValue()).thenReturn(20);
        when(attackerStats.getSpecialAttack()).thenReturn(20);
        when(targetStats.getSpecialDefense()).thenReturn(10);

        move.execute(attacker, target);

        verify(attackerLevel).getValue();
        verify(attacker).getInGameStats();
        verify(attackerInGameStats).getCurrentStats();
        verify(target).getInGameStats();
        verify(targetInGameStats).getCurrentStats();
        verify(attackerStats, never()).getAttack();
        verify(targetStats, never()).getDefense();
        verify(attackerStats).getSpecialAttack();
        verify(targetStats).getSpecialDefense();
        verify(target).removeHp(20);
    }

    @Test
    public void should_inflict_damage_to_target_with_same_type_attack_bonus_when_the_move_shares_a_type_with_the_attacker() throws InsufficientPowerPointException {
        Move move = new MoveMock(
                Type.GRASS,
                DamageCategory.Physical,
                45,
                100,
                25,
                false,
                randomGenerator
        );

        when(attackerLevel.getValue()).thenReturn(20);
        when(attackerStats.getSpecialAttack()).thenReturn(20);
        when(targetStats.getSpecialDefense()).thenReturn(10);
        when(attacker.hasType(Type.GRASS)).thenReturn(true);

        move.execute(attacker, target);

        verify(attacker).hasType(Type.GRASS);
        verify(target).removeHp(16);
    }

    @Test
    public void should_inflict_double_damage_to_target_when_the_target_type_is_sensible_to_the_move() throws InsufficientPowerPointException {
        Move move = new MoveMock(
                Type.GRASS,
                DamageCategory.Physical,
                45,
                100,
                25,
                false,
                randomGenerator
        );

        when(attackerLevel.getValue()).thenReturn(20);
        when(attackerStats.getSpecialAttack()).thenReturn(20);
        when(targetStats.getSpecialDefense()).thenReturn(10);
        when(target.getSensibilityFactorToType(Type.GRASS)).thenReturn(2.0);

        move.execute(attacker, target);

        verify(target).getSensibilityFactorToType(Type.GRASS);
        verify(target).removeHp(22);
    }

    @Test
    public void should_inflict_half_damage_to_target_when_the_target_type_is_resistant_to_the_move() throws InsufficientPowerPointException {
        Move move = new MoveMock(
                Type.GRASS,
                DamageCategory.Physical,
                45,
                100,
                25,
                false,
                randomGenerator
        );

        when(attackerLevel.getValue()).thenReturn(20);
        when(attackerStats.getSpecialAttack()).thenReturn(20);
        when(targetStats.getSpecialDefense()).thenReturn(10);
        when(target.getSensibilityFactorToType(Type.GRASS)).thenReturn(0.5);

        move.execute(attacker, target);

        verify(target).getSensibilityFactorToType(Type.GRASS);
        verify(target).removeHp(5);
    }

    @Test
    public void should_not_inflict_damage_to_target_when_the_target_type_is_immune_to_the_move() throws InsufficientPowerPointException {
        Move move = new MoveMock(
                Type.GRASS,
                DamageCategory.Physical,
                45,
                100,
                25,
                false,
                randomGenerator
        );

        when(target.isImmuneTo(Type.GRASS)).thenReturn(true);

        move.execute(attacker, target);

        Move expectedMove = new MoveMock(
                Type.GRASS,
                DamageCategory.Physical,
                45,
                100,
                25,
                false,
                randomGenerator
        );
        assertThat(move).isEqualTo(expectedMove);
        verify(target, never()).removeHp(anyInt());
    }

    @Test
    public void should_inflict_50_percent_more_damage_when_it_is_a_critical_hit() {
        Move move = new MoveMock(
                Type.GRASS,
                DamageCategory.Physical,
                45,
                100,
                25,
                false,
                randomGenerator
        );

        when(randomGenerator.nextCriticalHitValue(false)).thenReturn(0);
        when(attackerLevel.getValue()).thenReturn(20);
        when(attackerStats.getAttack()).thenReturn(10);
        when(targetStats.getDefense()).thenReturn(10);

        move.execute(attacker, target);

        verify(randomGenerator, times(1)).nextCriticalHitValue(false);
        verify(target).removeHp(16);
    }

    @Test
    public void should_get_next_critical_hit_value_with_high_critical_hit_ratio_when_the_move_has_high_ciritcal_hit_ratio() {
        Move move = new MoveMock(
                Type.GRASS,
                DamageCategory.Physical,
                45,
                100,
                25,
                true,
                randomGenerator
        );

        move.execute(attacker, target);

        verify(randomGenerator, times(1)).nextCriticalHitValue(true);
    }

    @Test
    public void should_inflict_85_percent_damage_when_random_damage_factor_is_85() {
        Move move = new MoveMock(
                Type.GRASS,
                DamageCategory.Physical,
                45,
                100,
                25,
                false,
                randomGenerator
        );

        when(randomGenerator.nextDamageFactor()).thenReturn(.85);
        when(attackerLevel.getValue()).thenReturn(20);
        when(attackerStats.getAttack()).thenReturn(10);
        when(targetStats.getDefense()).thenReturn(10);

        move.execute(attacker, target);

        verify(target).removeHp(9);
    }
}