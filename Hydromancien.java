public class Hydromancien extends Mage {

    public Hydromancien(int x, int y) {
        super(x, y);
    }

    @Override
    public TypeMage getTypeMage() {
        return TypeMage.HYDROMANCIEN;
    }

    @Override
    public String getSymbole() {
        return "H";
    }

    @Override
    public int calculerBonus(Grille grille) {
        int bonus = 0;

        int x = getX();
        int y = getY();

        for (int i = 0; i < grille.getTaille(); i++) {
            if (i != x) {
                bonus += compterSiEau(grille.getPiece(i, y));
            }
        }

        for (int j = 0; j < grille.getTaille(); j++) {
            if (j != y) {
                bonus += compterSiEau(grille.getPiece(x, j));
            }
        }

        return bonus;
    }

    private int compterSiEau(Piece piece) {
        if (piece != null && piece.estUnElement()) {
            Element element = piece.enElement();

            if (element.getType() == TypeElement.EAU) {
                return 1;
            }
        }

        return 0;
    }

    @Override
    public String getNom() {
        return "Hydromancien";
    }
}