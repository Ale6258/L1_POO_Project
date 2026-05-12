public class Geomancien extends Mage {

    public Geomancien(int x, int y) {
        super(x, y);
    }

    @Override
    public TypeMage getTypeMage() {
        return TypeMage.GEOMANCIEN;
    }

    @Override
    public String getSymbole() {
        return "G";
    }

    @Override
    public String getNom() {
        return "Geomancien";
    }

    @Override
    public int calculerBonus(Grille grille) {
        int bonus = 0;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {

                if (dx != 0 || dy != 0) {
                    int x = getX() + dx;
                    int y = getY() + dy;

                    if (grille.estDansGrille(x, y) && grille.getPiece(x, y) != null) {
                        bonus++;
                    }
                }
            }
        }

        return bonus;
    }
}