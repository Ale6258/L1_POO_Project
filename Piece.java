public abstract class Piece {
    private int x;
    private int y;

    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean estUnElement() {
        return false;
    }

    public Element enElement() {
        return null;
    }

    public boolean estUnMage() {
        return false;
    }

    public Mage enMage() {
        return null;
    }

    public abstract String getSymbole();
}