package com.pokemon.stats;

public enum Sensibility {
    IMMUNE(0),
    RESISTANT(0.5),
    NEUTRAL(1),
    SENSITIVE(2);

    private final double damageCoefficient;

    Sensibility(double damageCoefficient) {
        this.damageCoefficient = damageCoefficient;
    }

    public double getDamageCoefficient() {
        return damageCoefficient;
    }
}
