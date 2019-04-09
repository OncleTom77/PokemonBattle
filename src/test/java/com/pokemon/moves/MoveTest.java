package com.pokemon.moves;

import com.pokemon.Pokemon;
import com.pokemon.VariantStats;
import com.pokemon.battle.ComputedStatsPokemon;
import com.pokemon.battle.DamageCategory;
import com.pokemon.stats.Level;
import com.pokemon.stats.Sensibility;
import com.pokemon.stats.Stats;
import com.pokemon.stats.Type;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class MoveTest {

    private ComputedStatsPokemon attacker;
    private Pokemon attackerPokemon;
    private VariantStats attackerVariantStats;
    private Level attackerLevel;
    private Stats attackerStats;

    private ComputedStatsPokemon target;
    private Pokemon targetPokemon;
    private Stats targetStats;

    private Random randomGenerator;
    private Type targetType;
    private Type[] targetTypes;

    class MoveMock extends Move {
        MoveMock(String name, String description, Type type, DamageCategory damageCategory, int power, int accuracy, int powerPoint, boolean hasHighCriticalHitRatio, Random randomGenerator) {
            super(name, description, type, damageCategory, power, accuracy, powerPoint, hasHighCriticalHitRatio, randomGenerator);
        }

        MoveMock(Type grass, DamageCategory physical, int power, int accuracy, int powerPoint, boolean hasHighCriticalHitRatio, Random randomGenerator) {
            super("Move Mock", "Move description", grass, physical, power, accuracy, powerPoint, hasHighCriticalHitRatio, randomGenerator);
        }
    }

    @Before
    public void setUp() throws Exception {
        attacker = mock(ComputedStatsPokemon.class);
        attackerPokemon = mock(Pokemon.class);
        attackerVariantStats = mock(VariantStats.class);
        attackerLevel = mock(Level.class);
        attackerStats = mock(Stats.class);

        target = mock(ComputedStatsPokemon.class);
        targetPokemon = mock(Pokemon.class);
        targetStats = mock(Stats.class);
        targetType = mock(Type.class);
        targetTypes = new Type[]{targetType};

        randomGenerator = mock(Random.class);

        when(targetPokemon.isImmuneTo(Type.GRASS)).thenReturn(false);
        when(attacker.getStats()).thenReturn(attackerStats);
        when(attacker.getPokemon()).thenReturn(attackerPokemon);
        when(attackerPokemon.getVariantStats()).thenReturn(attackerVariantStats);
        when(attackerVariantStats.getLevel()).thenReturn(attackerLevel);
        when(target.getStats()).thenReturn(targetStats);
        when(target.getPokemon()).thenReturn(targetPokemon);

        doNothing().when(target).removeHp(anyInt());

        when(attackerLevel.getValue()).thenReturn(1);
        when(attackerStats.getAttack()).thenReturn(1);
        when(attackerStats.getSpecialAttack()).thenReturn(1);
        when(targetStats.getDefense()).thenReturn(1);
        when(targetStats.getSpecialDefense()).thenReturn(1);
        when(attackerPokemon.hasType(any())).thenReturn(false);
        when(targetPokemon.getTypes()).thenReturn(targetTypes);
        when(targetType.getSensibilityForMoveType(Type.GRASS)).thenReturn(Sensibility.NEUTRAL);
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
            verify(randomGenerator, never()).nextInt(100);
        }
    }

    @Test
    public void should_reduce_power_point_by_one_when_if_accuracy_value_is_lower_than_accuracy_stats_of_attacker_move() {
        Move move = new MoveMock(
                Type.GRASS,
                DamageCategory.Physical,
                45,
                50,
                25,
                false,
                randomGenerator
        );

        when(randomGenerator.nextInt(100)).thenReturn(49);

        move.execute(attacker, target);

        verify(randomGenerator).nextInt(100);
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

        when(randomGenerator.nextInt(100)).thenReturn(50);

        move.execute(attacker, target);

        verify(randomGenerator).nextInt(100);
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

        when(randomGenerator.nextInt(100)).thenReturn(50);
        when(attackerLevel.getValue()).thenReturn(20);
        when(attackerStats.getAttack()).thenReturn(10);
        when(targetStats.getDefense()).thenReturn(10);

        move.execute(attacker, target);

        verify(attackerLevel).getValue();
        verify(attacker).getStats();
        verify(target).getStats();
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

        when(randomGenerator.nextInt(100)).thenReturn(50);
        when(attackerLevel.getValue()).thenReturn(20);
        when(attackerStats.getSpecialAttack()).thenReturn(20);
        when(targetStats.getSpecialDefense()).thenReturn(10);

        move.execute(attacker, target);

        verify(attackerLevel).getValue();
        verify(attacker).getStats();
        verify(target).getStats();
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

        when(randomGenerator.nextInt(100)).thenReturn(50);
        when(attackerLevel.getValue()).thenReturn(20);
        when(attackerStats.getSpecialAttack()).thenReturn(20);
        when(targetStats.getSpecialDefense()).thenReturn(10);
        when(attackerPokemon.hasType(Type.GRASS)).thenReturn(true);

        move.execute(attacker, target);

        verify(attackerPokemon).hasType(Type.GRASS);
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

        when(randomGenerator.nextInt(100)).thenReturn(50);
        when(attackerLevel.getValue()).thenReturn(20);
        when(attackerStats.getSpecialAttack()).thenReturn(20);
        when(targetStats.getSpecialDefense()).thenReturn(10);
        when(targetType.getSensibilityForMoveType(Type.GRASS)).thenReturn(Sensibility.SENSITIVE);

        move.execute(attacker, target);

        verify(targetPokemon).getTypes();
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

        when(randomGenerator.nextInt(100)).thenReturn(50);
        when(attackerLevel.getValue()).thenReturn(20);
        when(attackerStats.getSpecialAttack()).thenReturn(20);
        when(targetStats.getSpecialDefense()).thenReturn(10);
        when(targetType.getSensibilityForMoveType(Type.GRASS)).thenReturn(Sensibility.RESISTANT);

        move.execute(attacker, target);

        verify(targetPokemon).getTypes();
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

        when(targetPokemon.isImmuneTo(Type.GRASS)).thenReturn(true);

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
}