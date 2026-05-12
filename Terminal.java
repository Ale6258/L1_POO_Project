public class Terminal {
    public static final String RESET = "\u001B[0m";

    public static final String GRAS = "\u001B[1m";
    public static final String FAIBLE = "\u001B[2m";

    public static final String ROUGE = "\u001B[31m";
    public static final String VERT = "\u001B[32m";
    public static final String JAUNE = "\u001B[33m";
    public static final String BLEU = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String VIOLET = "\u001B[35m";
    public static final String GRIS = "\u001B[90m";

    public static void titre(String texte) {
        System.out.println();
        System.out.println(CYAN + GRAS + "╔════════════════════════════════════╗" + RESET);
        System.out.println(CYAN + GRAS + "║ " + texte + RESET);
        System.out.println(CYAN + GRAS + "╚════════════════════════════════════╝" + RESET);
        System.out.println();
    }

    public static void section(String texte) {
        System.out.println();
        System.out.println(VIOLET + GRAS + "── " + texte + " ──" + RESET);
    }

    public static void info(String texte) {
        System.out.println(CYAN + texte + RESET);
    }

    public static void succes(String texte) {
        System.out.println(VERT + texte + RESET);
    }

    public static void erreur(String texte) {
        System.out.println(ROUGE + texte + RESET);
    }

    public static void avertissement(String texte) {
        System.out.println(JAUNE + texte + RESET);
    }

    public static void ligneVide() {
        System.out.println();
    }

    public static String couleurElement(Element element) {
        if (element.getType() == TypeElement.FEU) {
            return ROUGE + element.getNom() + RESET;
        } else if (element.getType() == TypeElement.EAU) {
            return CYAN + element.getNom() + RESET;
        } else {
            return VERT + element.getNom() + RESET;
        }
    }

    public static String couleurSymbole(Piece piece) {
        if (piece == null) {
            return GRIS + "." + RESET;
        }

        if (piece.estUnElement()) {
            Element element = piece.enElement();

            if (element.getType() == TypeElement.FEU) {
                return ROUGE + piece.getSymbole() + RESET;
            } else if (element.getType() == TypeElement.EAU) {
                return CYAN + piece.getSymbole() + RESET;
            } else {
                return VERT + piece.getSymbole() + RESET;
            }
        }

        if (piece.estUnMage()) {
            return JAUNE + piece.getSymbole() + RESET;
        }

        return piece.getSymbole();
    }
}