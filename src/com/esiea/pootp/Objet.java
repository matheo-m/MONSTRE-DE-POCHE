package com.esiea.pootp;

public class Objet {
    private String nom;
    private String type; // Potion ou Médicament
    private int effet;

    public Objet(String nom, String type, int effet) {
        this.nom = nom;
        this.type = type;
        this.effet = effet;
    }

    public void utiliser(Monstre cible) {
        if (type.equals("Potion")) {
            cible.setPointsDeVie(cible.getPointsDeVie() + effet);
            System.out.println(cible.getNom() + " récupère " + effet + " PV.");
        } else if (type.equals("Médicament")) {
            cible.retirerEtat();
            System.out.println(cible.getNom() + " est guéri de son état !");
        }
    }

    @Override
    public String toString() {
        return "Objet{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", effet=" + effet +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public int getEffet() {
        return effet;
    }
}
