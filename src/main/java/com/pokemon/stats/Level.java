package com.pokemon.stats;

public class Level {
    private final int level;

    private Level(int level) {
        this.level = level;
    }

    public static Level of(int level) {
        return new Level(level);
    }

    public int getLevel() {
        return level;
    }
}
