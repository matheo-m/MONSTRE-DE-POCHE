package com.esiea.pootp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Combat {
    private Monstre joueur1Actuel;
    private Monstre joueur2Actuel;
    private List<Objet> inventaireJoueur1;
    private List<Objet> inventaireJoueur2;

    public Combat(Monstre joueur1, Monstre joueur2, List<Objet> inventaireJoueur1, List<Objet> inventaireJoueur2) {
        this.joueur1Actuel = joueur1;
        this.joueur2Actuel = joueur2;
        this.inventaireJoueur1 = inventaireJoueur1;
        this.inventaireJoueur2 = inventaireJoueur2;
    }

    public void lancerCombat() {
        Scanner scanner = new Scanner(System.in);

        while (joueur1Actuel.getPointsDeVie() > 0 && joueur2Actuel.getPointsDeVie() > 0) {
            System.out.println("\n--- Tour de combat ---");
            System.out.println("Joueur 1 : " + joueur1Actuel);
            System.out.println("Joueur 2 : " + joueur2Actuel);

            // Choix des actions pour le joueur 1
            System.out.println("Joueur 1, choisissez une action :");
            System.out.println("1. Attaquer");
            System.out.println("2. Utiliser un objet");
            System.out.println("3. Changer de monstre");
            int choix1 = scanner.nextInt();

            // Choix des actions pour le joueur 2
            System.out.println("Joueur 2, choisissez une action :");
            System.out.println("1. Attaquer");
            System.out.println("2. Utiliser un objet");
            System.out.println("3. Changer de monstre");
            int choix2 = scanner.nextInt();

            // Résolution des actions
            if (choix1 == 1) {
                joueur1Actuel.attaquer(joueur2Actuel, joueur1Actuel.getCapacites().get(0)); // Exemple : première attaque
                joueur1Actuel.attaquer(joueur2Actuel, joueur1Actuel.getCapacites().get(0));

            } else if (choix1 == 2) {
                utiliserObjet(inventaireJoueur1, joueur1Actuel);
            }

            if (choix2 == 1) {
                joueur2Actuel.attaquer(joueur1Actuel, joueur2Actuel.getCapacites().get(0)); // Exemple : première attaque
            } else if (choix2 == 2) {
                utiliserObjet(inventaireJoueur2, joueur2Actuel);
            }

            // Appliquer les effets des états
            joueur1Actuel.appliquerEffetsEtat();
            joueur2Actuel.appliquerEffetsEtat();

            // Vérification des points de vie
            if (joueur1Actuel.getPointsDeVie() <= 0) {
                System.out.println("Le joueur 2 a gagné !");
                break;
            }
            if (joueur2Actuel.getPointsDeVie() <= 0) {
                System.out.println("Le joueur 1 a gagné !");
                break;
            }
        }

        scanner.close();
    }

    private void utiliserObjet(List<Objet> inventaire, Monstre cible) {
        if (inventaire.isEmpty()) {
            System.out.println("Aucun objet disponible !");
            return;
        }

        System.out.println("Choisissez un objet :");
        for (int i = 0; i < inventaire.size(); i++) {
            System.out.println((i + 1) + ". " + inventaire.get(i).toString());
        }

        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt() - 1;

        if (choix >= 0 && choix < inventaire.size()) {
            Objet objetChoisi = inventaire.get(choix);
            objetChoisi.utiliser(cible);
            inventaire.remove(choix); // Supprime l'objet après utilisation
        } else {
            System.out.println("Choix invalide !");
        }
    }

}
