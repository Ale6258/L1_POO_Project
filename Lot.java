import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/*
 * Un lot est représenté par une file d'éléments.
 * Les éléments sont retirés dans l'ordre où ils sont proposés au joueur.
 */

public class Lot {
    private Queue<Element> elements;

    public Lot(int taille) {
        this.elements = new LinkedList<Element>();

        for (int i = 0; i < taille; i++) {
            this.elements.add(creerElementAleatoire());
        }
    }

    private Element creerElementAleatoire() {
        Random random = new Random();
        int choix = random.nextInt(3);

        if (choix == 0) {
            return new Feu(0, 0);
        } else if (choix == 1) {
            return new Eau(0, 0);
        } else {
            return new Plante(0, 0);
        }
    }

    public Element retirerElement() {
        return this.elements.poll();
    }

    public boolean estVide() {
        return this.elements.isEmpty();
    }

    public void afficherLot() {
        int numero = 1;

        for (Element element : this.elements) {
            System.out.println(
                Terminal.GRIS + "  " + numero + " │ " + Terminal.RESET
                + Terminal.couleurElement(element)
            );
            numero++;
        }
    }

    public int getTaille() {
        return this.elements.size();
    }
}