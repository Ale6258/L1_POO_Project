import java.util.LinkedList;
import java.util.Queue;

public class Grille {
    private static final int TAILLE = 5;

    private Piece[][] cases;

    public Grille() {
        this.cases = new Piece[TAILLE][TAILLE];
    }

    public int getTaille() {
        return TAILLE;
    }

    public boolean estDansGrille(int x, int y) {
        return x >= 0 && x < TAILLE && y >= 0 && y < TAILLE;
    }

    public boolean estVide(int x, int y) {
        return estDansGrille(x, y) && cases[x][y] == null;
    }

    public Piece getPiece(int x, int y) {
        if (!estDansGrille(x, y)) {
            return null;
        }

        return cases[x][y];
    }

    public void placerPiece(Piece piece, int x, int y) {
        if (!estDansGrille(x, y)) {
            System.out.println("Position hors limites.");
            return;
        }

        if (!estVide(x, y)) {
            System.out.println("Position déjà occupée.");
            return;
        }

        cases[x][y] = piece;
        piece.setPosition(x, y);
    }

    public void remplacerPiece(Piece piece, int x, int y) {
        if (!estDansGrille(x, y)) {
            return;
        }

        cases[x][y] = piece;
        piece.setPosition(x, y);
    }

    /*
    * Applique les réactions en chaîne à partir d'un élément placé.
    * Chaque élément transformé est ajouté dans une file afin qu'il
    * puisse à son tour interagir avec ses voisins.
    */

    public void appliquerInteractionsDepuis(Element element) {
        Queue<Element> aTraiter = new LinkedList<Element>();
        aTraiter.add(element);

        while (!aTraiter.isEmpty()) {
            Element courant = aTraiter.poll();

            int x = courant.getX();
            int y = courant.getY();

            traiterVoisin(courant, x - 1, y, aTraiter);
            traiterVoisin(courant, x + 1, y, aTraiter);
            traiterVoisin(courant, x, y - 1, aTraiter);
            traiterVoisin(courant, x, y + 1, aTraiter);
        }
    }

    /*
    * Vérifie si l'élément courant domine son voisin.
    * Si c'est le cas, le voisin est remplacé par une copie de l'élément courant.
    */

    private void traiterVoisin(Element courant, int x, int y, Queue<Element> aTraiter) {
        if (!estDansGrille(x, y)) {
            return;
        }

        Piece pieceVoisine = getPiece(x, y);

        if (pieceVoisine == null) {
            return;
        }

        if (pieceVoisine.estUnElement()) {
            Element voisin = pieceVoisine.enElement();

            if (courant.domine(voisin)) {
                Element nouvelElement = courant.creerCopie(x, y);
                remplacerPiece(nouvelElement, x, y);
                aTraiter.add(nouvelElement);
            }
        }
    }

    public void afficherGrille() {
        System.out.println();

        System.out.print("      ");
        for (int i = 0; i < TAILLE; i++) {
            System.out.print(Terminal.GRIS + i + "   " + Terminal.RESET);
        }
        System.out.println();

        System.out.print("    ┌");
        for (int i = 0; i < TAILLE; i++) {
            System.out.print("───");
            if (i < TAILLE - 1) {
                System.out.print("┬");
            }
        }
        System.out.println("┐");

        for (int i = 0; i < TAILLE; i++) {
            System.out.print(Terminal.GRIS + "  " + i + Terminal.RESET + " │");

            for (int j = 0; j < TAILLE; j++) {
                System.out.print(" " + Terminal.couleurSymbole(cases[i][j]) + " │");
            }

            System.out.println();

            if (i < TAILLE - 1) {
                System.out.print("    ├");
                for (int j = 0; j < TAILLE; j++) {
                    System.out.print("───");
                    if (j < TAILLE - 1) {
                        System.out.print("┼");
                    }
                }
                System.out.println("┤");
            }
        }

        System.out.print("    └");
        for (int i = 0; i < TAILLE; i++) {
            System.out.print("───");
            if (i < TAILLE - 1) {
                System.out.print("┴");
            }
        }
        System.out.println("┘");

        System.out.println();
    }
}