import java.util.ArrayList;

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
    }

    // Ajouter une attaque à la liste
    public void ajouterAttaque(Attaque attaque) {
        if (capacites.size() < 4) {
            capacites.add(attaque);
        } else {
            System.out.println("Ce monstre ne peut avoir que 4 attaques !");
        }
    }

    // Méthode pour attaquer un autre monstre
    public void attaquer(Monstre cible, Attaque attaque) {
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
    }

    // Calcul des dégâts (simplifié pour cette étape)
    private int calculerDegats(Attaque attaque, Monstre cible) {
        return (int) ((attaque.getPuissance() * this.attaque / cible.defense) * 1.5); // Simplification
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

    // Afficher les informations du monstre
    @Override
    public String toString() {
        return "Monstre{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", pointsDeVie=" + pointsDeVie +
                ", attaque=" + attaque +
                ", defense=" + defense +
                ", vitesse=" + vitesse +
                ", etat='" + etat + '\'' +
                '}';
    }
}
