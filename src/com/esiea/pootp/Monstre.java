package com.esiea.pootp;

import java.util.ArrayList;
import java.util.List;

public class Monstre {
    private String nom;
    private String type;
    private int pointsDeVie;
    private int attaque;
    private int defense;
    private int vitesse;
    private String etat;
    private ArrayList<Attaque> capacites;
    private int tourDansEtat;
    private double probabiliteParalysie;
    private double probabiliteEmpoisonnement;
    private double probabiliteBrulure;
    private double probabiliteInondation;
    private double probabiliteChute;

    public Monstre(String nom, String type, int pointsDeVie, int attaque, int defense, int vitesse) {
        this.nom = nom;
        this.type = type;
        this.pointsDeVie = pointsDeVie;
        this.attaque = attaque;
        this.defense = defense;
        this.vitesse = vitesse;
        this.etat = "Normal"; // Par défaut, pas d'état négatif.
        this.capacites = new ArrayList<>();
        this.tourDansEtat = 0;

        this.probabiliteParalysie = 0.0;
        this.probabiliteEmpoisonnement = 0.0;
        this.probabiliteBrulure = 0.0;
        this.probabiliteInondation = 0.0;
        this.probabiliteChute = 0.0;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    // Ajouter une attaque à la liste
    public void ajouterAttaque(Attaque attaque) {
        if (capacites.size() < 4) {
            capacites.add(attaque);
        } else {
            System.out.println("Ce monstre ne peut avoir que 4 attaques !");
        }
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    public ArrayList<Attaque> getCapacites() {
        return capacites;
    }

    // Méthode pour attaquer un autre monstre
    public void attaquer(Monstre lanceur, Monstre cible, Attaque attaque) {
        if (!capacites.contains(attaque)) {
            System.out.println("Cette attaque n'appartient pas à ce monstre !");
            return;
        }

        if (attaque.isRate()) {
            System.out.println(nom + " a raté son attaque !");
            return;
        }

        int degats = calculerDegats(attaque, cible);
        cible.prendreDegats(degats);
        System.out.println(nom + " inflige " + degats + " dégâts à " + cible.nom);

        // Monstre de type Foudre
        if (this.type.equals("Foudre")) {
            double chance = Math.random();
            if (chance < this.probabiliteParalysie) {
            cible.changerEtat("Paralysé");
            System.out.println(cible.nom + " est paralysé !");
            }
        }

        // Monstre de type Eau
        if (this.type.equals("Eau")) {
            double chance = Math.random();
            if (chance < this.probabiliteInondation) {
                cible.changerEtat("Inondé");
                System.out.println(lanceur.nom + " a inondé le terrain !");
            }
        }
        if (cible.etat.equals("Inondé") && !cible.type.equals("Eau")) {
            double chance = Math.random();
            if (chance < this.probabiliteChute) {
                cible.prendreDegats(cible.attaque / 4);
                System.out.println(cible.nom + " a glissé et subit " + (cible.attaque / 4) + " dégâts !");
            }
        }

        // Monstre de type Terre
        if (this.type.equals("Terre") && attaque.getType().equals("Terre")) {
            int toursCache = (int) (Math.random() * 3) + 1; // Nombre aléatoire entre 1 et 3
            this.defense *= 2; // Double la défense
            System.out.println(nom + " se cache sous terre pour " + toursCache + " tours, doublant sa défense !");

            
        }

        // Monstre de type Feu
        if (this.type.equals("Feu") && attaque.getType().equals("Feu")) {
            double chance = Math.random();
            if (chance < this.probabiliteBrulure) {
            cible.changerEtat("Brûlé");
            System.out.println(cible.nom + " est brûlé !");
            }
        }
        if (cible.etat.equals("Brûlé") && cible.type.equals("Eau")) {
            cible.retirerEtat();
            System.out.println(cible.nom + " est soigné de sa brûlure grâce au terrain inondé !");
        }

        // Monstre de type Nature
        if (this.type.equals("Plante") || this.type.equals("Insecte")) {
            // Soin si terrain inondé
            if (cible.etat.equals("Inondé")) {
                int soin = this.pointsDeVie / 20;
                this.pointsDeVie += soin;
                System.out.println(nom + " récupère " + soin + " points de vie grâce au terrain inondé !");
            }
        }

        // Monstre de type Plante
        if (this.type.equals("Plante")) {
            double chance = Math.random();
            if (chance < 0.3) { // 30% de chance de se soigner
            this.retirerEtat();
            System.out.println(nom + " se soigne et retire ses altérations d'état !");
            }
        }

        // Monstre de type Insecte
        if (this.type.equals("Insecte") && attaque.getType().equals("Insecte")) {
            double chance = Math.random();
            if (chance < this.probabiliteEmpoisonnement) {
            cible.changerEtat("Empoisonné");
            System.out.println(cible.nom + " est empoisonné !");
            }
        }

    }

    private int calculerDegats(Attaque attaque, Monstre cible) {
        double avantage = calculerAvantage(attaque.getType(), cible.type);
        double coeff = 0.85 + (Math.random() * 0.15); // Coeff aléatoire entre 0.85 et 1
        return (int) ((((double) (attaque.getPuissance() * this.attaque) / cible.defense) + 2) * avantage * coeff);
    }

    // Méthode pour prendre des dégâts
    public void prendreDegats(int degats) {
        this.pointsDeVie -= degats;
        if (this.pointsDeVie < 0)
            this.pointsDeVie = 0;
    }

    // Changer l'état
    public void changerEtat(String nouvelEtat) {
        this.etat = nouvelEtat;
    }

    public void appliquerEffetsEtat() {
        switch (etat) {
            case "Paralysé":
                System.out.println(nom + " est paralysé ! Il pourrait ne pas attaquer ce tour.");
                break;
            case "Brûlé":
                int degatsBrulure = attaque / 10; // 10% de l'attaque
                this.pointsDeVie -= degatsBrulure;
                System.out.println(nom + " subit " + degatsBrulure + " dégâts à cause de la brûlure.");
                break;
            case "Empoisonné":
                int degatsPoison = attaque / 10; // 10% de l'attaque
                this.pointsDeVie -= degatsPoison;
                System.out.println(nom + " subit " + degatsPoison + " dégâts à cause du poison.");
                break;
            default:
                // Pas d'effet si état = "Normal"
                break;
        }
        tourDansEtat++;
    }

    public void retirerEtat() {
        this.etat = "Normal";
        this.tourDansEtat = 0;
    }

    private double calculerAvantage(String typeAttaque, String typeCible) {
        if ((typeAttaque.equals("Feu") && typeCible.equals("Eau")) ||
                (typeAttaque.equals("Eau") && typeCible.equals("Foudre")) ||
                (typeAttaque.equals("Foudre") && typeCible.equals("Terre")) ||
                (typeAttaque.equals("Terre") && typeCible.equals("Nature")) ||
                (typeAttaque.equals("Nature") && typeCible.equals("Feu"))) {
            return 0.5; // Faible contre
        }
        if ((typeAttaque.equals("Eau") && typeCible.equals("Feu")) ||
                (typeAttaque.equals("Foudre") && typeCible.equals("Eau")) ||
                (typeAttaque.equals("Terre") && typeCible.equals("Foudre")) ||
                (typeAttaque.equals("Nature") && typeCible.equals("Terre")) ||
                (typeAttaque.equals("Feu") && typeCible.equals("Nature"))) {
            return 2.0; // Fort contre
        }
        return 1.0; // Pas d'avantage
    }

    @Override
    public String toString() {
        return "- Monstre{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", pointsDeVie=" + pointsDeVie +
                ", attaque=" + attaque +
                ", defense=" + defense +
                ", vitesse=" + vitesse +
                ", etat='" + etat + '\'' +
                '}';
    }

    public void setProbabiliteParalysie(double double1) {
        this.probabiliteParalysie = double1;
    }

    public void setProbabiliteEmpoisonnement(double double1) {
        this.probabiliteEmpoisonnement = double1;
    }

    public void setProbabiliteBrulure(double double1) {
        this.probabiliteBrulure = double1;
    }

    public void setProbabiliteInondation(double double1) {
        this.probabiliteInondation = double1;
    }

    public void setProbabiliteChute(double double1) {
        this.probabiliteChute = double1;
    }

}
