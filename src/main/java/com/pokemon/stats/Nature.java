package com.pokemon.stats;

public enum Nature {
    Docile(Stat.None, Stat.None),
    Hardy(Stat.None, Stat.None),
    Modest(Stat.SpecialAttack, Stat.Attack);

    private final Stat increasedStat;
    private final Stat decreasedStat;

    Nature(Stat increasedStat, Stat decreasedStat) {
        this.increasedStat = increasedStat;
        this.decreasedStat = decreasedStat;
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
        Attack, Defense, SpecialAttack, SpecialDefense, Speed, None
    }
}
