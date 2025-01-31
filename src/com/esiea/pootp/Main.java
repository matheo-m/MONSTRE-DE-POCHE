package com.esiea.pootp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Création des joueurs
        Joueur joueur1 = new Joueur("Joueur 1");
        Joueur joueur2 = new Joueur("Joueur 2");

        // Chargement des monstres depuis le fichier
        // --------------------------------------------------------------------------------------------
        List<Monstre> monstres = ChargeurFichier
                .chargerMonstres("C:/Users/matheo/Documents/A_Etude/ESIEA/3A/Java/Java-TP-MonstreDePoche/Monstres.txt");
        List<Attaque> attaques = ChargeurFichier
                .chargerAttaques("C:/Users/matheo/Documents/A_Etude/ESIEA/3A/Java/Java-TP-MonstreDePoche/Attaques.txt");
        // --------------------------------------------------------------------------------------------

        // Choix des monstres pour chaque joueur
        choisirMonstresTemporairement(joueur1, monstres);
        choisirMonstresTemporairement(joueur2, monstres);

        // Ajout des attaques pour chaque monstre des joueurs
        ajouterAttaquesTemporairement(joueur1, attaques);
        ajouterAttaquesTemporairement(joueur2, attaques);

        // Ajout des objets pour chaque joueur
        joueur1.ajouterObjet(new Objet("Potion", "Potion", 20));
        joueur1.ajouterObjet(new Objet("Super Potion", "Potion", 50));
        joueur1.ajouterObjet(new Objet("Médicament", "Médicament", 0));

        joueur2.ajouterObjet(new Objet("Potion", "Potion", 20));
        joueur2.ajouterObjet(new Objet("Super Potion", "Potion", 50));
        joueur2.ajouterObjet(new Objet("Médicament", "Médicament", 0));

        // Lancement du combat
        Combat combat = new Combat(joueur1, joueur2);
        combat.lancerCombat();
    }

    private static void choisirMonstres(Joueur joueur, List<Monstre> monstres) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(joueur.getNom() + ", choisissez vos monstres :");
        for (int i = 0; i < 2; i++) { // chaque joueur choisit 2 monstres
            System.out.println("Choisissez le monstre " + (i + 1) + " :");
            for (int j = 0; j < monstres.size(); j++) {
                System.out.println((j + 1) + ". " + monstres.get(j).getNom() + " (" + monstres.get(j).getType() + ")");
            }
            int choix = scanner.nextInt() - 1;
            joueur.ajouterMonstre(monstres.get(choix));
            monstres.remove(choix); // pour ne pas choisir le même monstre
        }
    }

    // je fais une copie de la liste de monstres pour ne pas modifier la liste
    // originale
    // pour que les deux joueurs puissent choisir dans la même liste de monstres
    private static void choisirMonstresTemporairement(Joueur joueur, List<Monstre> monstres) {
        List<Monstre> copieMonstres = new ArrayList<>(monstres);
        choisirMonstres(joueur, copieMonstres);
    }

    private static void ajouterAttaques(Joueur joueur, List<Attaque> attaques) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(joueur.getNom() + ", choisissez les attaques de vos monstres :");

        for (Monstre monstre : joueur.getMonstres()) {
            for (int i = 0; i < 4; i++) { // chaque monstre a 4 attaques
                System.out.println("Choisissez l'attaque " + (i + 1) + " pour " + monstre.getNom() + " ("
                        + monstre.getType() + ") :");

                for (int j = 0; j < attaques.size(); j++) {
                    Attaque attaque = attaques.get(j);
                    // si l'attaque est du même type que le monstre ou de type "Normal" on l'affiche
                    if (attaque.getType().equals(monstre.getType()) || attaque.getType().equals("Normal")) {
                        System.out.println((j + 1) + ". " + attaque.getNom() + " (" + attaque.getType() + ")");
                    }
                }

                int choix = scanner.nextInt() - 1;
                Attaque attaqueChoisie = attaques.get(choix);
                if (attaqueChoisie.getType().equals(monstre.getType()) || attaqueChoisie.getType().equals("Normal")) {
                    monstre.ajouterAttaque(attaqueChoisie);
                    // attaques.remove(choix); // pour ne pas choisir la même attaque
                } else {
                    System.out.println("Attaque non compatible avec le type du monstre. Choisissez une autre attaque.");
                    i--; // pour choisir à nouveau une attaque
                }
            }
        }
    }

    // je fais une copie de la liste d'attaques pour ne pas modifier la liste
    // originale
    // pour que les deux joueurs puissent choisir dans la même liste d'attaques
    private static void ajouterAttaquesTemporairement(Joueur joueur, List<Attaque> attaques) {
        List<Attaque> copieAttaques = new ArrayList<>(attaques);
        ajouterAttaques(joueur, copieAttaques);
    }
}
