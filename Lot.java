import java.util.Random;

public class Lot {
  private String[] lot;

  Lot() {
    this.lot = new String[5];

    for (int i = 0; i < 5; i++) {
      this.lot[i] = creerElementAleatoire();
    }
  }

  private String creerElementAleatoire() {
    Random rand = new Random();

    int type = rand.nextInt(3);

    switch (type) {
      case 0:
        return "Eau";
      case 1:
        return "Feu";
      case 2:
        return "Plante";
      default:
        return "Eau";
    }
  }

  public String getLotX(int x) {
    return this.lot[x];
  }
 
  public void AfficherLot() {
    for (int i = 0; i < 5; i++) {
      System.out.println(this.lot[i]);
    }
  }
}
