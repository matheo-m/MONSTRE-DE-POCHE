package com.esiea.pootp;

import java.util.List;
import java.util.Scanner;

public class Combat {
    private Joueur joueur1;
    private Joueur joueur2;
    private Monstre monstreJoueur1;
    private Monstre monstreJoueur2;

    public Combat(Joueur joueur1, Joueur joueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.monstreJoueur1 = joueur1.getMonstres().get(0);
        this.monstreJoueur2 = joueur2.getMonstres().get(0);
    }

    public void lancerCombat() {

        Scanner scanner = new Scanner(System.in);

        while (monstreJoueur1.getPointsDeVie() > 0 && monstreJoueur2.getPointsDeVie() > 0) {

            System.out.println("\n-------------Tour de combat :-------------\n");
            System.out.println("Joueur 1: " + joueur1.toString());
            System.out.println("Joueur 2: " + joueur2.toString());

            System.out.println("Joueur 1, choisissez une action :");
            System.out.println("1. Attaquer");
            System.out.println("2. Utiliser un objet");
            System.out.println("3. Changer de monstre");
            int actionJoueur1 = scanner.nextInt();

            System.out.println("Joueur 2, choisissez une action :");
            System.out.println("1. Attaquer");
            System.out.println("2. Utiliser un objet");
            System.out.println("3. Changer de monstre");
            int actionJoueur2 = scanner.nextInt();

            // Changement de monstres
            if (actionJoueur1 == 3) {
                monstreJoueur1 = changerMonstre(joueur1);
            }
            if (actionJoueur2 == 3) {
                monstreJoueur2 = changerMonstre(joueur2);
            }

            // Utilisation d'objets
            if (actionJoueur1 == 2) {
                utiliserObjet(joueur1.getObjets(), monstreJoueur1);
            }
            if (actionJoueur2 == 2) {
                utiliserObjet(joueur2.getObjets(), monstreJoueur2);
            }

            // Attaques
            if (actionJoueur1 == 1 && actionJoueur2 == 1) {
                // if (monstreJoueur1.getVitesse() >= monstreJoueur2.getVitesse()) {
                //     monstreJoueur1.attaquer(monstreJoueur2);
                //     if (monstreJoueur2.getPointsDeVie() > 0) {
                //         monstreJoueur2.attaquer(monstreJoueur1);
                //     }
                // } else {
                //     monstreJoueur2.attaquer(monstreJoueur1);
                //     if (monstreJoueur1.getPointsDeVie() > 0) {
                //         monstreJoueur1.attaquer(monstreJoueur2);
                //     }
                // }
                System.out.println("Attaque simultanée non implémentée !");
            } else if (actionJoueur1 == 1) {
                // monstreJoueur1.attaquer(monstreJoueur2);
                System.out.println("Attaque joueur 1 non implémentée !");
            } else if (actionJoueur2 == 1) {
                // monstreJoueur2.attaquer(monstreJoueur1);
                System.out.println("Attaque joueur 2 non implémentée !");
            }

            // Vérification des points de vie
            if (monstreJoueur1.getPointsDeVie() <= 0) {
                System.out.println(monstreJoueur1.getNom() + " est KO !");
                if (!joueur1.hasMonstresEnVie()) { // si aucun monstre n'est en vie
                    System.out.println("Joueur 2 a gagné !");
                    break;
                }
            }
            if (monstreJoueur2.getPointsDeVie() <= 0) {
                System.out.println(monstreJoueur2.getNom() + " est KO !");
                if (!joueur2.hasMonstresEnVie()) { // si aucun monstre n'est en vie
                    System.out.println("Joueur 1 a gagné !");
                    break;
                }
            }
        }

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

    private Monstre changerMonstre(Joueur joueur) {

        List<Monstre> monstres = joueur.getMonstres();
        
        System.out.println("Choisissez un monstre :");
        for (int i = 0; i < monstres.size(); i++) {
            System.out.println(
                    (i + 1) + ". " + monstres.get(i).getNom() + " (PV: " + monstres.get(i).getPointsDeVie() + ")");
        }

        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt() - 1;

        if (choix >= 0 && choix < monstres.size() && monstres.get(choix).getPointsDeVie() > 0) {
            return monstres.get(choix);
        } else {
            System.out.println("Choix invalide ou monstre KO !");
            return changerMonstre(joueur);
        }
    }


}
