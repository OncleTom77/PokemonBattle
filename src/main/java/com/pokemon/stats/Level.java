package com.pokemon.stats;

public class Level {
    private final int value;

    private Level(int value) {
        this.value = value;
    }

    public static Level of(int value) {
        return new Level(value);
    }

    public int getValue() {
        return value;
    }
}
