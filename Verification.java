public class Verification {

    public static boolean verifierPlacementInitial(Grille grille, int x, int y) {
        if (!grille.estDansGrille(x, y)) {
            Terminal.erreur("Position hors limites. Choisis une ligne et une colonne entre 0 et 4.");
            return false;
        }

        if (!grille.estVide(x, y)) {
            Terminal.erreur("Cette case est déjà occupée.");
            return false;
        }

        return true;
    }

    public static boolean verifierPlacement(Grille grille, int x, int y) {
        if (!grille.estDansGrille(x, y)) {
            Terminal.erreur("Position hors limites. Choisis une ligne et une colonne entre 0 et 4.");
            return false;
        }

        if (!grille.estVide(x, y)) {
            Terminal.erreur("Cette case est déjà occupée.");
            return false;
        }

        if (!aVoisinOrthogonalOccupe(grille, x, y)) {
            Terminal.erreur("La pièce doit toucher une case déjà occupée horizontalement ou verticalement.");
            return false;
        }

        return true;
    }

    private static boolean aVoisinOrthogonalOccupe(Grille grille, int x, int y) {
        return estOccupe(grille, x - 1, y)
            || estOccupe(grille, x + 1, y)
            || estOccupe(grille, x, y - 1)
            || estOccupe(grille, x, y + 1);
    }

    private static boolean estOccupe(Grille grille, int x, int y) {
        return grille.estDansGrille(x, y) && !grille.estVide(x, y);
    }
}