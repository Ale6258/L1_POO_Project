public class Druide extends Mage{
  
  public Druide(int x, int y){
    super(x,y);
  }

  public int Gain(){
    Piece [][] jeu=getGrille();
    int x= this.getX();
    int y= this.getY();
    int gain = 0;

    // ici je parcours les cases adjacentes avec 2 boucles for 
    for(int i=-1;i<=1;i++){
      for(int j=-1;j<=1;j++){
        if(i!=0 && j!=0){
          int tempX = x+i;
          int tempY = y+j;

          //je vérifie que la case actuelle est bien dans la grille 
          if(tempX >= 0 && tempX<5 && tempY >= 0 && tempY<5){
            if(jeu[tempX][tempY] instanceof Plante{
              gain++;
            }
          }         
        }
      }
    }

    return gain;
  }
}
