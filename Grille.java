public class Grille {
  private int[][] grille;

  Grille() {
    this.grille = new int[5][5];
  }

  public int[][] getGrille() {
    return this.grille;
  }

  public void setXY(int x, int y, int value) {
    this.grille[x][y] = value;
  }
}
