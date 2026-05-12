public abstract class Element extends Piece {

    public Element(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean estUnElement() {
        return true;
    }

    @Override
    public Element enElement() {
        return this;
    }

    public abstract TypeElement getType();

    public abstract Element creerCopie(int x, int y);

    public abstract boolean domine(Element autre);

    public abstract String getNom();
}