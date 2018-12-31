package com.pokemon.stats;

public class EffortValues extends Stats {
    private EffortValues(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        super(hp, attack, defense, specialAttack, specialDefense, speed);
    }

    public static EffortValues of(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        return new EffortValues(hp, attack, defense, specialAttack, specialDefense, speed);
    }
}
