public class Feu extends Element {
    public Feu(int x, int y) {
        super(x, y);
    }

    @Override
    public TypeElement getType() {
        return TypeElement.FEU;
    }
}