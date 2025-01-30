package com.esiea.pootp;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private String nom;
    private List<Monstre> monstres;
    private List<Objet> objets;

    public Joueur(String nom) {
        this.nom = nom;
        this.monstres = new ArrayList<>();
        this.objets = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public List<Monstre> getMonstres() {
        return monstres;
    }

    public List<Objet> getObjets() {
        return objets;
    }

    public void ajouterMonstre(Monstre monstre) {
        monstres.add(monstre);
    }

    public void ajouterObjet(Objet objet) {
        objets.add(objet);
    }

    public boolean hasMonstresEnVie() {
        for (Monstre monstre : monstres) {
            if (monstre.getPointsDeVie() > 0) {
                return true; // retourne vrai si un monstre est en vie
            }
        }

        return false;
    }
    
    @Override
    public String toString() {
        String result = "Joueur{" +
                "nom='" + nom + '\'' +
                ", monstres=[";
        
        for (Monstre monstre : monstres) {
            result += "\n  " + monstre;
        }
        
        result += "\n], objets=[";
        
        for (Objet objet : objets) {
            result += "\n  " + objet;
        }
        
        result += "\n]}";
        
        return result;
    }

}
