package com.pokemon.stats;

import static com.pokemon.stats.Sensibility.*;

public abstract class Type {

    // @formatter:off
    public static Type NORMAL = new Type(0) {};
    public static Type FIRE = new Type(1) {};
    public static Type WATER = new Type(2) {};
    public static Type ELECTRIC = new Type(3) {};
    public static Type GRASS = new Type(4) {};
    public static Type ICE = new Type(5) {};
    public static Type FIGHTING = new Type(6) {};
    public static Type POISON = new Type(7) {};
    public static Type GROUND = new Type(8) {};
    public static Type FLYING = new Type(9) {};
    public static Type PSYCHIC = new Type(10) {};
    public static Type BUG = new Type(11) {};
    public static Type ROCK = new Type(12) {};
    public static Type GHOST = new Type(13) {};
    public static Type DRAGON = new Type(14) {};
    public static Type DARK = new Type(15) {};
    public static Type STEEL = new Type(16) {};

    private static Sensibility[][] matrix = new Sensibility[][]{
        //           NORMAL     FIRE        WATER       ELECTRIC    GRASS       ICE         FIGHT       POISON      GROUND      FLYING      PSY         BUG         ROCK        GHOST       DRAGON      DARK        STEEL
        /*NORMAL*/  {NEUTRAL,   NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    RESISTANT,  IMMUNE,     NEUTRAL,    NEUTRAL,    RESISTANT},
        /*FIRE*/    {NEUTRAL,   RESISTANT,  RESISTANT,  NEUTRAL,    SENSITIVE,  SENSITIVE,  NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    SENSITIVE,  RESISTANT,  NEUTRAL,    RESISTANT,  NEUTRAL,    SENSITIVE},
        /*WATER*/   {NEUTRAL,   SENSITIVE,  RESISTANT,  NEUTRAL,    RESISTANT,  NEUTRAL,    NEUTRAL,    NEUTRAL,    SENSITIVE,  NEUTRAL,    NEUTRAL,    NEUTRAL,    SENSITIVE,  NEUTRAL,    RESISTANT,  NEUTRAL,    NEUTRAL},
        /*ELECTRIC*/{NEUTRAL,   NEUTRAL,    SENSITIVE,  RESISTANT,  RESISTANT,  NEUTRAL,    NEUTRAL,    NEUTRAL,    IMMUNE,     SENSITIVE,  NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    RESISTANT,  NEUTRAL,    NEUTRAL},
        /*GRASS*/   {NEUTRAL,   RESISTANT,  SENSITIVE,  NEUTRAL,    RESISTANT,  NEUTRAL,    NEUTRAL,    RESISTANT,  SENSITIVE,  RESISTANT,  NEUTRAL,    RESISTANT,  SENSITIVE,  NEUTRAL,    RESISTANT,  NEUTRAL,    RESISTANT},
        /*ICE*/     {NEUTRAL,   RESISTANT,  RESISTANT,  NEUTRAL,    SENSITIVE,  RESISTANT,  NEUTRAL,    NEUTRAL,    SENSITIVE,  SENSITIVE,  NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    SENSITIVE,  NEUTRAL,    RESISTANT},
        /*FIGHT*/   {SENSITIVE, NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    SENSITIVE,  NEUTRAL,    RESISTANT,  NEUTRAL,    RESISTANT,  RESISTANT,  RESISTANT,  SENSITIVE,  IMMUNE,     NEUTRAL,    SENSITIVE,  SENSITIVE},
        /*POISON*/  {NEUTRAL,   NEUTRAL,    NEUTRAL,    NEUTRAL,    SENSITIVE,  NEUTRAL,    NEUTRAL,    RESISTANT,  RESISTANT,  NEUTRAL,    NEUTRAL,    NEUTRAL,    RESISTANT,  RESISTANT,  NEUTRAL,    NEUTRAL,    IMMUNE},
        /*GROUND*/  {NEUTRAL,   SENSITIVE,  NEUTRAL,    SENSITIVE,  RESISTANT,  NEUTRAL,    NEUTRAL,    SENSITIVE,  NEUTRAL,    IMMUNE,     NEUTRAL,    RESISTANT,  SENSITIVE,  NEUTRAL,    NEUTRAL,    NEUTRAL,    SENSITIVE},
        /*FLYING*/  {NEUTRAL,   NEUTRAL,    NEUTRAL,    RESISTANT,  SENSITIVE,  NEUTRAL,    SENSITIVE,  NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    SENSITIVE,  RESISTANT,  NEUTRAL,    NEUTRAL,    NEUTRAL,    RESISTANT},
        /*PSYCHIC*/ {NEUTRAL,   NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    SENSITIVE,  SENSITIVE,  NEUTRAL,    NEUTRAL,    RESISTANT,  NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    IMMUNE,     RESISTANT},
        /*BUG*/     {NEUTRAL,   RESISTANT,  NEUTRAL,    NEUTRAL,    SENSITIVE,  NEUTRAL,    RESISTANT,  RESISTANT,  NEUTRAL,    RESISTANT,  SENSITIVE,  NEUTRAL,    NEUTRAL,    RESISTANT,  NEUTRAL,    SENSITIVE,  RESISTANT},
        /*ROCK*/    {NEUTRAL,   SENSITIVE,  NEUTRAL,    NEUTRAL,    NEUTRAL,    SENSITIVE,  RESISTANT,  NEUTRAL,    RESISTANT,  SENSITIVE,  NEUTRAL,    SENSITIVE,  NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    RESISTANT},
        /*GHOST*/   {IMMUNE,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    SENSITIVE,  NEUTRAL,    NEUTRAL,    SENSITIVE,  NEUTRAL,    RESISTANT,  NEUTRAL},
        /*DRAGON*/  {NEUTRAL,   NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    SENSITIVE,  NEUTRAL,    RESISTANT},
        /*DARK*/    {NEUTRAL,   NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    SENSITIVE,  NEUTRAL,    NEUTRAL,    SENSITIVE,  NEUTRAL,    RESISTANT,  NEUTRAL},
        /*STEEL*/   {NEUTRAL,   RESISTANT,  RESISTANT,  RESISTANT,  NEUTRAL,    SENSITIVE,  NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    NEUTRAL,    SENSITIVE,  NEUTRAL,    NEUTRAL,    NEUTRAL,    RESISTANT},
    };
    // @formatter:on

    private final int index;

    protected Type(int index) {
        this.index = index;
    }

    public Sensibility getSensibilityForMoveType(Type type) {
        return matrix[index][type.index];
    }
}
