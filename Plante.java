public class Plante extends Element {
    public Plante(int x, int y) {
        super(x, y);
    }

    @Override
    public TypeElement getType() {
        return TypeElement.PLANTE;
    }
}