package com.esiea.pootp;

public abstract class AbstractAttack {
    protected String name;
    protected String[] type = {"normale","foudre", "eau", "terre", "feu", "nature"};
    protected int nbUse;
    protected int power;
    protected int fail;

    public int Degats(){
        return 0;
    }
 
}
