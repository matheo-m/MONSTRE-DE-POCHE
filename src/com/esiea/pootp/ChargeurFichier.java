package com.esiea.pootp;

import java.io.*;
import java.util.*;

public class ChargeurFichier {

    public static List<Monstre> chargerMonstres(String cheminFichier) {
        List<Monstre> monstres = new ArrayList<>();

        try (BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            Monstre monstreTemporaire = null;

            while ((ligne = lecteur.readLine()) != null) {
                ligne = ligne.trim();

                if (ligne.equals("Monster")) {
                    monstreTemporaire = new Monstre("", "", 0, 0, 0, 0);
                } else if (ligne.equals("EndMonster")) {
                    if (monstreTemporaire != null) {
                        monstres.add(monstreTemporaire);
                        monstreTemporaire = null;
                    }
                } else if (monstreTemporaire != null) {
                    String[] champs = ligne.split(" ");
                    switch (champs[0]) {
                        case "Name":
                            monstreTemporaire.setNom(champs[1]);
                            break;
                        case "Type":

                            switch (champs[1]) {
                                case "Electric":
                                    monstreTemporaire.setType("Foudre");
                                    break;
                                case "Water":
                                    monstreTemporaire.setType("Eau");
                                    break;
                                case "Ground":
                                    monstreTemporaire.setType("Terre");
                                    break;
                                case "Fire":
                                    monstreTemporaire.setType("Feu");
                                    break;
                                case "Grass":
                                    monstreTemporaire.setType("Plante");
                                    break;
                                case "Bug":
                                    monstreTemporaire.setType("Insecte");
                                    break;
                                default:
                                    monstreTemporaire.setType(champs[1]);
                                    break;
                            }
                            break;
                        case "HP":
                            monstreTemporaire.setPointsDeVie(Integer.parseInt(champs[1]));
                            break;
                        case "Speed":
                            monstreTemporaire.setVitesse(Integer.parseInt(champs[1]));
                            break;
                        case "Attack":
                            monstreTemporaire.setAttaque(Integer.parseInt(champs[1]));
                            break;
                        case "Defense":
                            monstreTemporaire.setDefense(Integer.parseInt(champs[1]));
                            break;
                        case "Paralysis":
                            monstreTemporaire.setProbabiliteParalysie(Double.parseDouble(champs[1]));
                            break;
                        case "Burn":
                            monstreTemporaire.setProbabiliteBrulure(Double.parseDouble(champs[1]));
                            break;
                        case "Poison":
                            monstreTemporaire.setProbabiliteEmpoisonnement(Double.parseDouble(champs[1]));
                            break;
                        case "Flood":
                            monstreTemporaire.setProbabiliteInondation(Double.parseDouble(champs[1]));
                            break;
                        case "Fall":
                            monstreTemporaire.setProbabiliteChute(Double.parseDouble(champs[1]));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des monstres : " + e.getMessage());
        }

        return monstres;
    }

    public static List<Attaque> chargerAttaques(String cheminFichier) {
        List<Attaque> attaques = new ArrayList<>();

        try (BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            Attaque attaqueTemporaire = null;

            while ((ligne = lecteur.readLine()) != null) {
                ligne = ligne.trim();

                if (ligne.equals("Attack")) {
                    attaqueTemporaire = new Attaque("", "", 0, 0, 0.0);
                } else if (ligne.equals("EndAttack")) {
                    if (attaqueTemporaire != null) {
                        attaques.add(attaqueTemporaire);
                        attaqueTemporaire = null;
                    }
                } else if (attaqueTemporaire != null) {
                    String[] champs = ligne.split(" ");
                    switch (champs[0]) {
                        case "Name":
                            attaqueTemporaire.setNom(champs[1]);
                            break;
                            case "Type":

                            switch (champs[1]) {
                                case "Electric":
                                    attaqueTemporaire.setType("Foudre");
                                    break;
                                case "Water":
                                    attaqueTemporaire.setType("Eau");
                                    break;
                                case "Ground":
                                    attaqueTemporaire.setType("Terre");
                                    break;
                                case "Fire":
                                    attaqueTemporaire.setType("Feu");
                                    break;
                                case "Grass":
                                    attaqueTemporaire.setType("Plante");
                                    break;
                                case "Bug":
                                    attaqueTemporaire.setType("Insecte");
                                    break;
                                default:
                                    attaqueTemporaire.setType(champs[1]);
                                    break;
                            }
                            break;
                        case "Power":
                            attaqueTemporaire.setPuissance(Integer.parseInt(champs[1]));
                            break;
                        case "NbUse":
                            attaqueTemporaire.setNbUtilisations(Integer.parseInt(champs[1]));
                            break;
                        case "Fail":
                            attaqueTemporaire.setProbabiliteEchec(Double.parseDouble(champs[1]));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des attaques : " + e.getMessage());
        }

        return attaques;
    }

}
