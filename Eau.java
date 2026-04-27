public class Eau extends Element {
    public Eau(int x, int y) {
        super(x, y);
    }

    @Override
    public TypeElement getType() {
        return TypeElement.EAU;
    }
}