package com.pokemon.stats;

public class BaseStats extends Stats {
    private BaseStats(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        super(hp, attack, defense, specialAttack, specialDefense, speed);
    }

    public static BaseStats of(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        return new BaseStats(hp, attack, defense, specialAttack, specialDefense, speed);
    }
}
