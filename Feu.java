public class Feu extends Element {

    public Feu(int x, int y) {
        super(x, y);
    }

    @Override
    public TypeElement getType() {
        return TypeElement.FEU;
    }

    @Override
    public String getSymbole() {
        return "F";
    }

    @Override
    public Element creerCopie(int x, int y) {
        return new Feu(x, y);
    }

    @Override
    public boolean domine(Element autre) {
        return autre.getType() == TypeElement.PLANTE;
    }

    @Override
    public String getNom() {
        return "Feu";
    }
}