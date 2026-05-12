public class Eau extends Element {

    public Eau(int x, int y) {
        super(x, y);
    }

    @Override
    public TypeElement getType() {
        return TypeElement.EAU;
    }

    @Override
    public String getSymbole() {
        return "E";
    }

    @Override
    public Element creerCopie(int x, int y) {
        return new Eau(x, y);
    }

    @Override
    public boolean domine(Element autre) {
        return autre.getType() == TypeElement.FEU;
    }

    @Override
    public String getNom() {
        return "Eau";
    }
}