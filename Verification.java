public class Verification {

  static boolean verifierPlacement(Grille grille, String typeElement, int ligne, int colonne) {
    if (ligne < 0 || ligne > 4 || colonne < 0 || colonne > 4) {
      System.out.println("Position hors limites. Veuillez choisir une position entre 0 et 4.");
      return false;
    }
    if (grille.getXY(ligne, colonne) != 0) {
      System.out.println("Position déjà occupée. Veuillez choisir une autre position.");
      return false;
    }

    // index out of range
    if (grille.getXY(ligne+1, colonne) == 0 || grille.getXY(ligne-1, colonne) == 0 || grille.getXY(ligne, colonne+1) == 0 || grille.getXY(ligne, colonne-1) == 0) {
      System.out.println("L'élément doit être entouré d'autres éléments sur la grille. Veuillez choisir une position adjacente à un élément déjâ présent sur la grille.");
      return false;
    }
    return true;
  }

  static boolean verifierPlacementInitial(Grille grille, String typeElement, int ligne, int colonne) {
    if (ligne < 0 || ligne > 4 || colonne < 0 || colonne > 4) {
      System.out.println("Position hors limites. Veuillez choisir une position entre 0 et 4.");
      return false;
    }
    return true;
  }
}