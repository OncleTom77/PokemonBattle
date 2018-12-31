package com.pokemon.stats;

public class IndividualValues extends Stats {
    private IndividualValues(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        super(hp, attack, defense, specialAttack, specialDefense, speed);
    }

    public static IndividualValues of(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        return new IndividualValues(hp, attack, defense, specialAttack, specialDefense, speed);
    }
}
