public class Druide extends Mage {

    public Druide(int x, int y) {
        super(x, y);
    }

    @Override
    public TypeMage getTypeMage() {
        return TypeMage.DRUIDE;
    }

    @Override
    public String getSymbole() {
        return "D";
    }

    @Override
    public int calculerBonus(Grille grille) {
        int bonus = 0;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {

                if (dx != 0 || dy != 0) {
                    int x = getX() + dx;
                    int y = getY() + dy;

                    if (grille.estDansGrille(x, y)) {
                        bonus += compterSiPlante(grille.getPiece(x, y));
                    }
                }
            }
        }

        return bonus;
    }

    private int compterSiPlante(Piece piece) {
        if (piece != null && piece.estUnElement()) {
            Element element = piece.enElement();

            if (element.getType() == TypeElement.PLANTE) {
                return 1;
            }
        }

        return 0;
    }

    @Override
    public String getNom() {
        return "Druide";
    }
}