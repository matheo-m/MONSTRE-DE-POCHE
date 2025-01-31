# Java-TP-MonstreDePoche Mathéo Moussé
## Comment jouer au jeu

### Prérequis
Avant de lancer le jeu, assurez-vous d'avoir les fichiers `Monstres.txt` et `Attaques.txt` disponibles sur votre système. Vous devrez modifier le chemin absolu de ces fichiers dans le code source avant de lancer le jeu.

### Étapes pour jouer
1. **Modifier les chemins des fichiers**:
    - Ouvrez le fichier `Main.java`.
    - Modifiez les chemins des fichiers `Monstres.txt` et `Attaques.txt` dans les lignes suivantes :
      ```java
      List<Monstre> monstres = ChargeurFichier
                 .chargerMonstres("C:/Users/.../Java/Java-TP-MonstreDePoche/Monstres.txt");
      List<Attaque> attaques = ChargeurFichier
                 .chargerAttaques("C:/Users/.../Java/Java-TP-MonstreDePoche/Attaques.txt");
      ```
    - Remplacez les chemins par les chemins absolus où se trouvent vos fichiers `Monstres.txt` et `Attaques.txt`.

2. **Lancer le jeu**:
    - Exécutez la classe `Main` pour démarrer le jeu.
    - Suivez les instructions à l'écran pour choisir vos monstres et leurs attaques.

### Règles du jeu
- Chaque joueur choisit 3 monstres parmi une liste (Vous pouvez rajouter des monstres ou des attaques, attention de respecter le bon format : ) ).
- Chaque monstre peut avoir jusqu'à 4 attaques.
- Les joueurs ont chacun les mêmes objets ajouté dans le main dans leur inventaire.
- Une fois les préparatifs terminés, le combat commence automatiquement.

Amusez-vous bien en jouant au jeu Monstre de Poche !