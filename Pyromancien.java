public class Pyromancien extends Mage {

    public Pyromancien(int x, int y) {
        super(x, y);
    }

    @Override
    public TypeMage getTypeMage() {
        return TypeMage.PYROMANCIEN;
    }

    @Override
    public String getSymbole() {
        return "Y";
    }

    @Override
    public int calculerBonus(Grille grille) {
        int bonus = 0;

        bonus += compterDirection(grille, -1, -1);
        bonus += compterDirection(grille, -1, 1);
        bonus += compterDirection(grille, 1, -1);
        bonus += compterDirection(grille, 1, 1);

        return bonus;
    }

    private int compterDirection(Grille grille, int dx, int dy) {
        int bonus = 0;

        int x = getX() + dx;
        int y = getY() + dy;

        while (grille.estDansGrille(x, y)) {
            Piece piece = grille.getPiece(x, y);

            if (piece != null && piece.estUnElement()) {
                Element element = piece.enElement();

                if (element.getType() == TypeElement.FEU) {
                    bonus++;
                }
            }

            x += dx;
            y += dy;
        }

        return bonus;
    }

    @Override
    public String getNom() {
        return "Pyromancien";
    }
}