import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Grille grille = new Grille();

    for (int tour = 0; tour < 5; tour++) {
      Lot lot1 = new Lot();
      Lot lot2 = new Lot();

      System.out.println("Lot 1:");
      lot1.AfficherLot();

      System.out.println("Lot 2:");
      lot2.AfficherLot();

      int choix = demanderChoixLot(sc);
      Lot lotChoisi = (choix == 1) ? lot1 : lot2;

      placerLot(sc, grille, lotChoisi, choix);
    }

    sc.close();
  }

  private static int demanderChoixLot(Scanner sc) {
    int choix;

    do {
      System.out.println("Choix du lot (1 ou 2) :");
      choix = sc.nextInt();
    } while (choix != 1 && choix != 2);

    return choix;
  }

  private static void placerLot(Scanner sc, Grille grille, Lot lot, int choixLot) {
    for (int j = 0; j < 5; j++) {
      int ligne;
      int colonne;
      String typeElement = lot.getLotX(j);

      do {
        System.out.printf(
          "Choix de la ligne (0-4) pour l'element %d du lot %d :%n",
          j, choixLot
        );
        ligne = sc.nextInt();

        System.out.printf(
          "Choix de la colonne (0-4) pour l'element %d du lot %d :%n",
          j, choixLot
        );
        colonne = sc.nextInt();

      } while (!Verification.verifierPlacement(grille, typeElement, ligne, colonne));

      int value = convertirElementEnValeur(typeElement);
      grille.setXY(ligne, colonne, value);
    }
  }

  private static int convertirElementEnValeur(String typeElement) {
    if (typeElement.equals("Eau")) {
      return 0;
    } else if (typeElement.equals("Feu")) {
      return 1;
    } else {
      return 2;
    }
  }
}