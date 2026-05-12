public abstract class Element extends Piece {
    private int x;
    private int y;

    Element(int x, int y) {
        super(x,y);
    }
    public abstract TypeElement getType();
}
