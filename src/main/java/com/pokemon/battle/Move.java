package com.pokemon.battle;

import com.pokemon.stats.Type;

public enum Move {
    VineWhip("Vine Whip",
            "The target is struck with slender, whiplike vines to inflict damage.",
            Type.Grass,
            DamageCategory.Physical,
            45,
            100,
            25
    ),
    Ember("Ember",
            "The target is attacked with small flames. It may also leave the target with a burn.",
            Type.Fire,
            DamageCategory.Special,
            40,
            100,
            25
    ),
    Gust("Gust",
            "A gust of wind is whipped up by wings and launched at the target to inflict damage.",
            Type.Flying,
            DamageCategory.Special,
            40,
            100,
            35);

    Move(String name, String description, Type type, DamageCategory damageCategory, int power, int accuracy, int powerPoint) {
    }
}
