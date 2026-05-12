public class Plante extends Element {

    public Plante(int x, int y) {
        super(x, y);
    }

    @Override
    public TypeElement getType() {
        return TypeElement.PLANTE;
    }

    @Override
    public String getSymbole() {
        return "P";
    }

    @Override
    public Element creerCopie(int x, int y) {
        return new Plante(x, y);
    }

    @Override
    public boolean domine(Element autre) {
        return autre.getType() == TypeElement.EAU;
    }

    @Override
    public String getNom() {
        return "Plante";
    }
}