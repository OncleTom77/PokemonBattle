package com.pokemon.stats;

import com.pokemon.battle.DamageCategory;

import java.util.Objects;

public class Stats {

    private final int hp;
    private final int attack;
    private final int defense;
    private final int specialAttack;
    private final int specialDefense;
    private final int speed;

    private Stats(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
    }

    public static Stats of(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        return new Stats(hp, attack, defense, specialAttack, specialDefense, speed);
    }

    public static Stats of(Stats stats) {
        return of(stats.hp, stats.attack, stats.defense, stats.specialAttack, stats.specialDefense, stats.speed);
    }

    public int getOffensiveStatForDamageCategory(DamageCategory damageCategory) {
        return damageCategory == DamageCategory.Physical
                ? attack
                : specialAttack;
    }

    public int getDefensiveStatForDamageCategory(DamageCategory damageCategory) {
        return damageCategory == DamageCategory.Physical
                ? defense
                : specialDefense;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats stats = (Stats) o;
        return hp == stats.hp &&
                attack == stats.attack &&
                defense == stats.defense &&
                specialAttack == stats.specialAttack &&
                specialDefense == stats.specialDefense &&
                speed == stats.speed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hp, attack, defense, specialAttack, specialDefense, speed);
    }

    @Override
    public String toString() {
        return "Stats{" +
                "hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                ", specialAttack=" + specialAttack +
                ", specialDefense=" + specialDefense +
                ", speed=" + speed +
                '}';
    }
}
