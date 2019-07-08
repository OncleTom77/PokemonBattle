package com.pokemon.stats;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Level level = (Level) o;
        return value == level.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
