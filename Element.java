public abstract class Element extends Piece {
    private int x;
    private int y;

    Element(int x, int y) {
        super(int x, int y );
    }
    public abstract TypeElement getType();
}
