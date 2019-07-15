package com.pokemon.stats;

public abstract class Nature {

    // @formatter:off
    public static Nature DOCILE = new Nature() {};
    public static Nature HARDY = new Nature() {};
    public static Nature MODEST = new Nature(Stat.SpecialAttack, Stat.Attack) {};
    // @formatter:on

    private final Stat increasedStat;
    private final Stat decreasedStat;

    protected Nature(Stat increasedStat, Stat decreasedStat) {
        this.increasedStat = increasedStat;
        this.decreasedStat = decreasedStat;
    }

    protected Nature() {
        this.increasedStat = null;
        this.decreasedStat = null;
    }

    public float getMultiplierValueFor(Stat stat) {
        if (increasedStat == stat) {
            return 1.1f;
        } else if (decreasedStat == stat) {
            return 0.9f;
        }

        return 1;
    }

    public enum Stat {
        Attack, Defense, SpecialAttack, SpecialDefense, Speed
    }
}
