public abstract class Mage extends Piece {

    public Mage(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean estUnMage() {
        return true;
    }

    @Override
    public Mage enMage() {
        return this;
    }

    public abstract TypeMage getTypeMage();

    public abstract int calculerBonus(Grille grille);

    public abstract String getNom();
}