package com.esiea.pootp;

import java.util.Random;

public class Attaque {
    private String nom;
    private String type;
    private int puissance;
    private int nbUtilisations;
    private double probabiliteEchec;

    public Attaque(String nom, String type, int puissance, int nbUtilisations, double probabiliteEchec) {
        this.nom = nom;
        this.type = type;
        this.puissance = puissance;
        this.nbUtilisations = nbUtilisations;
        this.probabiliteEchec = probabiliteEchec;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public void setNbUtilisations(int nbUtilisations) {
        this.nbUtilisations = nbUtilisations;
    }

    public void setProbabiliteEchec(double probabiliteEchec) {
        this.probabiliteEchec = probabiliteEchec;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isRate() {
        Random random = new Random();
        return random.nextDouble() < probabiliteEchec;
    }

    public int getPuissance() {
        return puissance;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Attaque{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", puissance=" + puissance +
                ", nbUtilisations=" + nbUtilisations +
                ", probabiliteEchec=" + probabiliteEchec +
                '}';
    }
}
