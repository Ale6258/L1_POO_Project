public class Druide extends Mage{
  
  public Druide(int x, int y){
    super(x,y);
  }

  public int Gain(){
    Piece [][] jeu=getGrille();
    int x= this.getX();
    int y= this.getY();
    int gain = 0;

    // faut modifier si le mage est au bord
    if(jeu[x+1][y+1].getType() == PLANTE){
      gain++;
    }
    if(jeu[x+1][y].getType() == PLANTE){
      gain++;
    }
    if(jeu[x+1][y-1].getType() == PLANTE){
      gain++;
    }
    if(jeu[x][y-1].getType() == PLANTE){
      gain++;
    }
    if(jeu[x-1][y-1].getType() == PLANTE){
      gain++;
    }
    if(jeu[x-1][y].getType() == PLANTE){
      gain++;
    }
    if(jeu[x-1][y+1].getType() == PLANTE){
      gain++;
    }
    if(jeu[x][y+1].getType() == PLANTE){
      gain++;
    }

    return gain;
    

}
