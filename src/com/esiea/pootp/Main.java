package com.esiea.pootp;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Création des monstres
        Monstre pikachu = new Monstre("Pikachu", "Electric", 100, 50, 30, 60);
        Monstre salameche = new Monstre("Salamèche", "Fire", 120, 40, 35, 50);

        // Création des attaques
        Attaque eclair = new Attaque("Éclair", "Electric", 40, 10, 0.1);
        Attaque flamme = new Attaque("Flamme", "Fire", 45, 10, 0.1);

        // Association des attaques aux monstres
        pikachu.ajouterAttaque(eclair);
        salameche.ajouterAttaque(flamme);

        // Création des objets
        List<Objet> inventaireJoueur1 = new ArrayList<>();
        inventaireJoueur1.add(new Objet("Potion", "Potion", 20));
        inventaireJoueur1.add(new Objet("Médicament", "Médicament", 0));

        List<Objet> inventaireJoueur2 = new ArrayList<>();
        inventaireJoueur2.add(new Objet("Potion", "Potion", 20));

        // Lancement du combat
        Combat combat = new Combat(pikachu, salameche, inventaireJoueur1, inventaireJoueur2);
        combat.lancerCombat();

    }
}
