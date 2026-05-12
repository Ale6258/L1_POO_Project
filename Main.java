import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        afficherMenu();
        int choix = demanderEntier(scanner, Terminal.GRAS + "Votre choix : " + Terminal.RESET);

        if (choix == 1) {
            jouerModeBase(scanner);
        } else if (choix == 2) {
            jouerModeComplet(scanner);
        } else if (choix == 3) {
            jouerModeDefi(scanner);
        } else {
            Terminal.erreur("Choix invalide.");
        }

        scanner.close();
    }

    private static void afficherMenu() {
        Terminal.titre("ELEMENT CLASH");

        System.out.println(Terminal.GRAS + "Modes disponibles" + Terminal.RESET);
        System.out.println(Terminal.GRIS + "  1 │ " + Terminal.RESET + "Mode de base");
        System.out.println(Terminal.GRIS + "  2 │ " + Terminal.RESET + "Mode complet");
        System.out.println(Terminal.GRIS + "  3 │ " + Terminal.RESET + "Mode défi");
        Terminal.ligneVide();
    }

    /*
    * Mode de base :
    * cinq tours, deux lots de cinq éléments à chaque tour,
    * score final calculé avec Feu × Eau × Plante.
    */

    private static void jouerModeBase(Scanner scanner) {
        Grille grille = new Grille();
        boolean premierPlacement = true;

        for (int tour = 1; tour <= 5; tour++) {
            Terminal.section("Tour " + tour);

            Lot lot1 = new Lot(5);
            Lot lot2 = new Lot(5);

            afficherLots(lot1, lot2);

            int choixLot = demanderChoixLot(scanner);
            Lot lotChoisi;

            if (choixLot == 1) {
                lotChoisi = lot1;
            } else {
                lotChoisi = lot2;
            }

            while (!lotChoisi.estVide()) {
                Element element = lotChoisi.retirerElement();

                Terminal.ligneVide();
                System.out.println(
                    Terminal.GRAS + "Élément à placer : " + Terminal.RESET
                    + Terminal.couleurElement(element)
                );

                placerPieceAvecSaisie(scanner, grille, element, premierPlacement);

                grille.appliquerInteractionsDepuis(element);

                premierPlacement = false;

                grille.afficherGrille();
            }
        }

        int score = Score.calculerScoreBase(grille);

        afficherFinDePartie(grille, score);
        Score.afficherDetailsScoreBase(grille);
        MeilleurScore.afficherEtMettreAJour(score);
    }

    /*
    * Mode complet :
    * ajoute les mages aux tours 1, 3 et 5.
    * Les mages ne déclenchent pas d'interactions mais modifient le score final.
    */

    private static void jouerModeComplet(Scanner scanner) {
        Grille grille = new Grille();
        boolean premierPlacement = true;

        ArrayList<Mage> mages = creerMagesAleatoires();
        int indiceMage = 0;

        for (int tour = 1; tour <= 5; tour++) {
            Terminal.section("Tour " + tour);

            boolean tourAvecMage = tour == 1 || tour == 3 || tour == 5;

            if (tourAvecMage) {
                Mage mage = mages.get(indiceMage);
                indiceMage++;

                Terminal.ligneVide();
                System.out.println(
                    Terminal.GRAS + "Mage à placer : " + Terminal.RESET
                    + Terminal.JAUNE + mage.getNom() + Terminal.RESET
                );

                placerPieceAvecSaisie(scanner, grille, mage, premierPlacement);
                premierPlacement = false;

                grille.afficherGrille();
            }

            int tailleLot;

            if (tourAvecMage) {
                tailleLot = 4;
            } else {
                tailleLot = 5;
            }

            Lot lot1 = new Lot(tailleLot);
            Lot lot2 = new Lot(tailleLot);

            afficherLots(lot1, lot2);

            int choixLot = demanderChoixLot(scanner);
            Lot lotChoisi;

            if (choixLot == 1) {
                lotChoisi = lot1;
            } else {
                lotChoisi = lot2;
            }

            while (!lotChoisi.estVide()) {
                Element element = lotChoisi.retirerElement();

                Terminal.ligneVide();
                System.out.println(
                    Terminal.GRAS + "Élément à placer : " + Terminal.RESET
                    + Terminal.couleurElement(element)
                );

                placerPieceAvecSaisie(scanner, grille, element, premierPlacement);

                grille.appliquerInteractionsDepuis(element);

                premierPlacement = false;

                grille.afficherGrille();
            }
        }

        int score = Score.calculerScoreComplet(grille);

        afficherFinDePartie(grille, score);
        Score.afficherDetailsScoreComplet(grille);
        MeilleurScore.afficherEtMettreAJour(score);
    }

    /*
    * Extension personnelle :
    * mode défi avec un objectif de score choisi par le joueur.
    */

    private static void jouerModeDefi(Scanner scanner) {
        Grille grille = new Grille();
        boolean premierPlacement = true;

        int objectif = demanderObjectifDefi(scanner);

        Terminal.section("Objectif du défi");
        System.out.println(
            Terminal.GRAS + "Score à atteindre : " + Terminal.RESET
            + Terminal.JAUNE + objectif + Terminal.RESET
        );

        for (int tour = 1; tour <= 5; tour++) {
            Terminal.section("Tour " + tour);

            Lot lot1 = new Lot(5);
            Lot lot2 = new Lot(5);

            afficherLots(lot1, lot2);

            int choixLot = demanderChoixLot(scanner);
            Lot lotChoisi;

            if (choixLot == 1) {
                lotChoisi = lot1;
            } else {
                lotChoisi = lot2;
            }

            while (!lotChoisi.estVide()) {
                Element element = lotChoisi.retirerElement();

                Terminal.ligneVide();
                System.out.println(
                    Terminal.GRAS + "Élément à placer : " + Terminal.RESET
                    + Terminal.couleurElement(element)
                );

                placerPieceAvecSaisie(scanner, grille, element, premierPlacement);

                grille.appliquerInteractionsDepuis(element);

                premierPlacement = false;

                grille.afficherGrille();

                int scoreActuel = Score.calculerScoreBase(grille);
                afficherProgressionDefi(scoreActuel, objectif);
            }
        }

        int score = Score.calculerScoreBase(grille);

        afficherFinDePartie(grille, score);
        Score.afficherDetailsScoreBase(grille);
        afficherResultatDefi(score, objectif);
        MeilleurScore.afficherEtMettreAJour(score);
    }

    private static int demanderObjectifDefi(Scanner scanner) {
        Terminal.section("Mode défi");

        System.out.println("Choisis un objectif de score à atteindre.");
        System.out.println(Terminal.GRIS + "Conseil : entre 300 et 700 pour un défi raisonnable." + Terminal.RESET);
        Terminal.ligneVide();

        int objectif;

        do {
            objectif = demanderEntier(
                scanner,
                Terminal.GRAS + "Objectif de score : " + Terminal.RESET
            );

            if (objectif <= 0) {
                Terminal.erreur("L'objectif doit être positif.");
            }

        } while (objectif <= 0);

        return objectif;
    }

    private static void afficherProgressionDefi(int scoreActuel, int objectif) {
        System.out.println(
            Terminal.GRAS + "Score actuel : " + Terminal.RESET
            + Terminal.JAUNE + scoreActuel + Terminal.RESET
            + Terminal.GRIS + " / " + objectif + Terminal.RESET
        );
    }

    private static void afficherResultatDefi(int score, int objectif) {
        Terminal.section("Résultat du défi");

        if (score >= objectif) {
            Terminal.succes("Défi réussi.");
        } else {
            Terminal.erreur("Défi échoué.");
        }

        System.out.println(
            Terminal.GRAS + "Objectif : " + Terminal.RESET
            + objectif
        );

        System.out.println(
            Terminal.GRAS + "Score obtenu : " + Terminal.RESET
            + score
        );
    }

    private static void afficherLots(Lot lot1, Lot lot2) {
        Terminal.ligneVide();

        System.out.println(Terminal.GRAS + "Lot 1" + Terminal.RESET);
        lot1.afficherLot();

        Terminal.ligneVide();

        System.out.println(Terminal.GRAS + "Lot 2" + Terminal.RESET);
        lot2.afficherLot();

        Terminal.ligneVide();
    }

    private static void afficherFinDePartie(Grille grille, int score) {
        Terminal.titre("FIN DE LA PARTIE");

        grille.afficherGrille();

        System.out.println(
            Terminal.GRAS + "Score final : " + Terminal.RESET
            + Terminal.JAUNE + score + Terminal.RESET
        );
    }

    private static void placerPieceAvecSaisie(
        Scanner scanner,
        Grille grille,
        Piece piece,
        boolean premierPlacement
    ) {
        int x;
        int y;
        boolean placementValide;

        do {
            x = demanderEntier(
                scanner,
                Terminal.GRIS + "  Ligne   (0-4) : " + Terminal.RESET
            );

            y = demanderEntier(
                scanner,
                Terminal.GRIS + "  Colonne (0-4) : " + Terminal.RESET
            );

            if (premierPlacement) {
                placementValide = Verification.verifierPlacementInitial(grille, x, y);
            } else {
                placementValide = Verification.verifierPlacement(grille, x, y);
            }

        } while (!placementValide);

        grille.placerPiece(piece, x, y);
    }

    private static ArrayList<Mage> creerMagesAleatoires() {
        ArrayList<Mage> mages = new ArrayList<Mage>();

        mages.add(new Pyromancien(0, 0));
        mages.add(new Hydromancien(0, 0));
        mages.add(new Druide(0, 0));
        mages.add(new Geomancien(0, 0));

        Collections.shuffle(mages);

        return mages;
    }

    private static int demanderChoixLot(Scanner scanner) {
        int choix;

        do {
            choix = demanderEntier(
                scanner,
                Terminal.GRAS + "Choisissez un lot (1 ou 2) : " + Terminal.RESET
            );
        } while (choix != 1 && choix != 2);

        return choix;
    }

    private static int demanderEntier(Scanner scanner, String message) {
        System.out.print(message);

        while (!scanner.hasNextInt()) {
            Terminal.erreur("Veuillez entrer un nombre entier.");
            scanner.next();
            System.out.print(message);
        }

        return scanner.nextInt();
    }
}