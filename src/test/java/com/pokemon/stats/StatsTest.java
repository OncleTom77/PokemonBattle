package com.pokemon.stats;

import com.pokemon.battle.DamageCategory;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StatsTest {

    @Test
    public void should_get_attack_stat_as_offensive_stat_for_physical_damage_category() {
        Stats stats = Stats.of(1, 2, 3, 4, 5, 6);

        int offensiveStat = stats.getOffensiveStatForDamageCategory(DamageCategory.Physical);

        assertThat(offensiveStat).isEqualTo(stats.getAttack());
    }

    @Test
    public void should_get_special_attack_stat_as_offensive_stat_for_special_damage_category() {
        Stats stats = Stats.of(1, 2, 3, 4, 5, 6);

        int offensiveStat = stats.getOffensiveStatForDamageCategory(DamageCategory.Special);

        assertThat(offensiveStat).isEqualTo(stats.getSpecialAttack());
    }

    @Test
    public void should_get_defense_stat_as_defensive_stat_for_physical_damage_category() {
        Stats stats = Stats.of(1, 2, 3, 4, 5, 6);

        int defensiveStat = stats.getDefensiveStatForDamageCategory(DamageCategory.Physical);

        assertThat(defensiveStat).isEqualTo(stats.getDefense());
    }

    @Test
    public void should_get_special_defense_stat_as_defensive_stat_for_special_damage_category() {
        Stats stats = Stats.of(1, 2, 3, 4, 5, 6);

        int defensiveStat = stats.getDefensiveStatForDamageCategory(DamageCategory.Special);

        assertThat(defensiveStat).isEqualTo(stats.getSpecialDefense());
    }
}