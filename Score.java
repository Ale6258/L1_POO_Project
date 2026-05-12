public class Score {

    public static int calculerScoreBase(Grille grille) {
        int[] compteurs = compterElements(grille);

        int nbFeu = compteurs[0];
        int nbEau = compteurs[1];
        int nbPlante = compteurs[2];

        return nbFeu * nbEau * nbPlante;
    }

    public static int calculerScoreComplet(Grille grille) {
        int[] compteurs = compterElements(grille);
        int[] bonus = compterBonusMages(grille);

        int nbFeu = compteurs[0] + bonus[0];
        int nbEau = compteurs[1] + bonus[1];
        int nbPlante = compteurs[2] + bonus[2];

        int bonusGeomancien = bonus[3];

        int[] valeursAvecGeomancien = appliquerBonusGeomancien(
            nbFeu,
            nbEau,
            nbPlante,
            bonusGeomancien
        );

        nbFeu = valeursAvecGeomancien[0];
        nbEau = valeursAvecGeomancien[1];
        nbPlante = valeursAvecGeomancien[2];

        return nbFeu * nbEau * nbPlante;
    }

    public static void afficherDetailsScoreBase(Grille grille) {
        int[] compteurs = compterElements(grille);

        int nbFeu = compteurs[0];
        int nbEau = compteurs[1];
        int nbPlante = compteurs[2];

        int score = nbFeu * nbEau * nbPlante;

        Terminal.section("Résumé du score");

        System.out.println(Terminal.ROUGE + "Feu    : " + nbFeu + Terminal.RESET);
        System.out.println(Terminal.CYAN + "Eau    : " + nbEau + Terminal.RESET);
        System.out.println(Terminal.VERT + "Plante : " + nbPlante + Terminal.RESET);

        Terminal.ligneVide();

        System.out.println(
            Terminal.GRAS + "Calcul : " + Terminal.RESET
            + nbFeu + " × " + nbEau + " × " + nbPlante
            + " = " + Terminal.JAUNE + score + Terminal.RESET
        );
    }

    public static void afficherDetailsScoreComplet(Grille grille) {
        int[] compteurs = compterElements(grille);
        int[] bonus = compterBonusMages(grille);

        int nbFeuBase = compteurs[0];
        int nbEauBase = compteurs[1];
        int nbPlanteBase = compteurs[2];

        int bonusPyromancien = bonus[0];
        int bonusHydromancien = bonus[1];
        int bonusDruide = bonus[2];
        int bonusGeomancien = bonus[3];

        int nbFeu = nbFeuBase + bonusPyromancien;
        int nbEau = nbEauBase + bonusHydromancien;
        int nbPlante = nbPlanteBase + bonusDruide;

        int[] valeursAvecGeomancien = appliquerBonusGeomancien(
            nbFeu,
            nbEau,
            nbPlante,
            bonusGeomancien
        );

        nbFeu = valeursAvecGeomancien[0];
        nbEau = valeursAvecGeomancien[1];
        nbPlante = valeursAvecGeomancien[2];

        int score = nbFeu * nbEau * nbPlante;

        Terminal.section("Résumé du score");

        System.out.println(
            Terminal.ROUGE + "Feu    : " + nbFeuBase
            + " + " + bonusPyromancien + " bonus Pyromancien"
            + Terminal.RESET
        );

        System.out.println(
            Terminal.CYAN + "Eau    : " + nbEauBase
            + " + " + bonusHydromancien + " bonus Hydromancien"
            + Terminal.RESET
        );

        System.out.println(
            Terminal.VERT + "Plante : " + nbPlanteBase
            + " + " + bonusDruide + " bonus Druide"
            + Terminal.RESET
        );

        if (bonusGeomancien > 0) {
            System.out.println(
                Terminal.JAUNE + "Bonus Geomancien : " + bonusGeomancien
                + " point(s) ajoutés au type le plus faible"
                + Terminal.RESET
            );
        }

        Terminal.ligneVide();

        System.out.println(
            Terminal.GRAS + "Calcul final : " + Terminal.RESET
            + nbFeu + " × " + nbEau + " × " + nbPlante
            + " = " + Terminal.JAUNE + score + Terminal.RESET
        );
    }

    private static int[] compterElements(Grille grille) {
        int nbFeu = 0;
        int nbEau = 0;
        int nbPlante = 0;

        for (int i = 0; i < grille.getTaille(); i++) {
            for (int j = 0; j < grille.getTaille(); j++) {
                Piece piece = grille.getPiece(i, j);

                if (piece != null && piece.estUnElement()) {
                    Element element = piece.enElement();

                    if (element.getType() == TypeElement.FEU) {
                        nbFeu++;
                    } else if (element.getType() == TypeElement.EAU) {
                        nbEau++;
                    } else if (element.getType() == TypeElement.PLANTE) {
                        nbPlante++;
                    }
                }
            }
        }

        return new int[] { nbFeu, nbEau, nbPlante };
    }

    private static int[] compterBonusMages(Grille grille) {
        int bonusPyromancien = 0;
        int bonusHydromancien = 0;
        int bonusDruide = 0;
        int bonusGeomancien = 0;

        for (int i = 0; i < grille.getTaille(); i++) {
            for (int j = 0; j < grille.getTaille(); j++) {
                Piece piece = grille.getPiece(i, j);

                if (piece != null && piece.estUnMage()) {
                    Mage mage = piece.enMage();

                    if (mage.getTypeMage() == TypeMage.PYROMANCIEN) {
                        bonusPyromancien += mage.calculerBonus(grille);
                    } else if (mage.getTypeMage() == TypeMage.HYDROMANCIEN) {
                        bonusHydromancien += mage.calculerBonus(grille);
                    } else if (mage.getTypeMage() == TypeMage.DRUIDE) {
                        bonusDruide += mage.calculerBonus(grille);
                    } else if (mage.getTypeMage() == TypeMage.GEOMANCIEN) {
                        bonusGeomancien += mage.calculerBonus(grille);
                    }
                }
            }
        }

        return new int[] {
            bonusPyromancien,
            bonusHydromancien,
            bonusDruide,
            bonusGeomancien
        };
    }

    /*
    * Extension personnelle :
    * le Geomancien ajoute ses points au type d'élément le moins représenté.
    * Cela renforce l'objectif d'équilibrage entre Feu, Eau et Plante.
    */

    private static int[] appliquerBonusGeomancien(int nbFeu,int nbEau,int nbPlante,int bonusGeomancien) {
        for (int i = 0; i < bonusGeomancien; i++) {
            if (nbFeu <= nbEau && nbFeu <= nbPlante) {
                nbFeu++;
            } else if (nbEau <= nbFeu && nbEau <= nbPlante) {
                nbEau++;
            } else {
                nbPlante++;
            }
        }

        return new int[] { nbFeu,nbEau, nbPlante };
    }
}