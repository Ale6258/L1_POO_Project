public class Grille {
  private int[][] grille;

  Grille() {
    this.grille = new int[5][5];
  }

  public int[][] getGrille() {
    return this.grille;
  }

  public int getXY(int x, int y) {
    return this.grille[x][y];
  }

  public void setXY(int x, int y, int value) {
    this.grille[x][y] = value;
  }

  void afficherGrille() {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        System.out.print(this.grille[i][j] + " ");
      }
      System.out.println();
    }
  }
}
