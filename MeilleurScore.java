import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * Extension personnelle :
 * sauvegarde le meilleur score global dans un fichier texte.
 */

public class MeilleurScore {
    private static final String NOM_FICHIER = "meilleur_score.txt";

    public static int lireMeilleurScore() {
        File fichier = new File(NOM_FICHIER);

        if (!fichier.exists()) {
            return 0;
        }

        try {
            Scanner scanner = new Scanner(fichier);

            if (scanner.hasNextInt()) {
                int score = scanner.nextInt();
                scanner.close();
                return score;
            }

            scanner.close();
        } catch (IOException e) {
            Terminal.erreur("Impossible de lire le meilleur score.");
        }

        return 0;
    }

    public static void sauvegarderMeilleurScore(int score) {
        try {
            FileWriter writer = new FileWriter(NOM_FICHIER);
            writer.write(String.valueOf(score));
            writer.close();
        } catch (IOException e) {
            Terminal.erreur("Impossible de sauvegarder le meilleur score.");
        }
    }

    public static void afficherEtMettreAJour(int score) {
        int meilleurScore = lireMeilleurScore();

        Terminal.ligneVide();

        if (score > meilleurScore) {
            Terminal.succes("Nouveau meilleur score : " + score);
            sauvegarderMeilleurScore(score);
        } else {
            System.out.println(
                Terminal.GRAS + "Meilleur score : " + Terminal.RESET
                + Terminal.JAUNE + meilleurScore + Terminal.RESET
            );
        }
    }
}