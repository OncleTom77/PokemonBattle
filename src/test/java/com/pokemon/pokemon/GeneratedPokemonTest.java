package com.pokemon.pokemon;

import com.pokemon.moves.Ember;
import com.pokemon.moves.Move;
import com.pokemon.stats.Level;
import com.pokemon.stats.Nature;
import com.pokemon.stats.Stats;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GeneratedPokemonTest {

    private Pokemon pokemon;
    private Nature nature;
    private Stats individualValues;
    private Stats effortValues;
    private Level level;
    private Move[] moves;
    private Random randomGenerator;

    private Stats baseStats;

    @Before
    public void initTest() {
        pokemon = mock(Pokemon.class);
        nature = mock(Nature.class);
        individualValues = mock(Stats.class);
        effortValues = mock(Stats.class);
        level = mock(Level.class);
        moves = new Move[]{new Ember()};
        randomGenerator = mock(Random.class);
        baseStats = mock(Stats.class);

        when(pokemon.getBaseStats()).thenReturn(baseStats);
    }

    @Test
    public void should_create_computedStatsPokemon_accordingly_to_given_pokemon() {
        when(baseStats.getHp()).thenReturn(60);
        when(baseStats.getAttack()).thenReturn(45);
        when(baseStats.getDefense()).thenReturn(50);
        when(baseStats.getSpecialAttack()).thenReturn(80);
        when(baseStats.getSpecialDefense()).thenReturn(80);
        when(baseStats.getSpeed()).thenReturn(70);

        when(individualValues.getHp()).thenReturn(28);
        when(individualValues.getAttack()).thenReturn(4);
        when(individualValues.getDefense()).thenReturn(17);
        when(individualValues.getSpecialAttack()).thenReturn(30);
        when(individualValues.getSpecialDefense()).thenReturn(27);
        when(individualValues.getSpeed()).thenReturn(31);

        when(effortValues.getHp()).thenReturn(4);
        when(effortValues.getAttack()).thenReturn(0);
        when(effortValues.getDefense()).thenReturn(0);
        when(effortValues.getSpecialAttack()).thenReturn(254);
        when(effortValues.getSpecialDefense()).thenReturn(0);
        when(effortValues.getSpeed()).thenReturn(252);

        when(level.getValue()).thenReturn(53);

        when(nature.getMultiplierValueFor(Nature.Stat.Attack)).thenReturn(0.9f);
        when(nature.getMultiplierValueFor(Nature.Stat.Defense)).thenReturn(1f);
        when(nature.getMultiplierValueFor(Nature.Stat.SpecialAttack)).thenReturn(1.1f);
        when(nature.getMultiplierValueFor(Nature.Stat.SpecialDefense)).thenReturn(1f);
        when(nature.getMultiplierValueFor(Nature.Stat.Speed)).thenReturn(1f);

        GeneratedPokemon generatedPokemon = GeneratedPokemon.of(
                pokemon,
                nature,
                individualValues,
                effortValues,
                level,
                moves,
                randomGenerator
        );

        InGameStats expectedInGameStats = InGameStats.of(
                Stats.of(
                        141,
                        48,
                        67,
                        152,
                        104,
                        129
                )
        );

        assertThat(generatedPokemon.getInGameStats()).isEqualTo(expectedInGameStats);
    }

}