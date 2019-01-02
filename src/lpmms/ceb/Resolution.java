package lpmms.ceb;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Résout un compte de manière récursive
 * @author robin.hortala
 */
public class Resolution {

    // On créé les objets des opérateurs qui seront utilisés dans la résolution
    private static Operateur somme = new Somme();
    private static Operateur produit = new Produit();
    private static Operateur difference = new Difference();

    // On fait un tableau avec les trois opérateurs pour les parcourir pendant un traitement
    private static Operateur[] operateurs = {somme, produit, difference};

    private static ArrayList<Vector<Operande>> resultats = new ArrayList<>();

    /**
     * Prend un compte à résoudre en argument et effectue les calculs possibles de manière récursive
     * jusquà ce qu'un résultat soit trouvé. Si, à la fin du parcours, aucun résultat exact n'a été trouvé,
     * on prend le résultat avec la valeur la plus proche de la cible
     * @param aResoudre le compte à résoudre
     */
    public static void resoudre(Compte aResoudre){

        // Les opérandes disponibles pour atteindre le nombre cible
        Vector<Operande> opInitiales = aResoudre.getOpInitiales();

        // Le nombre que l'on chercher à obtenir
        int cible = aResoudre.getAResoudre();

        // On lance de parcours récursif par niveau sur les opérandes initiaux
        traiter(opInitiales, cible);

        // On affiche les opérandes du résultat
        for (Vector<Operande> resultat : resultats) {
            for(Operande oper : resultat) {

                if(oper.getValeur() == cible){
                    System.out.print(oper.getValeur());
                    System.out.print("\n");
                }
            }
        }

    }

    /**
     * Traite un "niveau", c'est à dire un ensemble d'opérandes, par exemple, le premier niveau traité lors de la
     * résolution est constitué des opérandes de départ.
     * Le traitement d'un niveau consiste à réaliser toutes les opérations possibles pour celui-ci.
     * @param niveau le niveau à traiter
     * @return le niveau dans lequel une opérande avec un résultat
     */
    public static void traiter(Vector<Operande> niveau, int cible){

        /*
         * Parcours récursif des opérandes du niveau
         * Conditions d'arrêt :
         *     - Il ne reste plus qu'un opérande disponible
         *     - On a atteint le nombre cible
         */
        if (niveau.size() > 1) {

            /*
             * On enlève le premier opérande du niveau pour l'additionner, le multiplier et le soustraire
             * à tous les autres opérandes du niveau
             */
            Operande opGauche = niveau.remove(0);

            // On parcours tous les opérandes du niveau pour effectuer les calculs
            for (int i = 0; i < niveau.size(); i++ ) {
                Operande opDroit = niveau.get(i);

                // On parcours le tableau des opérateurs pour lancer toutes les opérations entre les deux opérandes
                for (Operateur oper : operateurs){

                    // On créé le niveau suivant qui sera traité récursivement
                    Vector<Operande> niveauSuivant = (Vector<Operande>) niveau.clone();

                    /*
                     * Lors de la création de l'opérande calculé, si l'opération est une différence et que l'opérande
                     * gauche est inférieur à l'opérande droit, une IllegalArgumentException sera levé, dans ce cas,
                     * on remet l'opérande gauche à la fin du niveau suivant.
                     */
                    try {

                        // On créé l'opérande calculé
                        Operande nvOperandeCalcule = new OperandeCalcule(opGauche, oper, opDroit);

                        // On ajoute l'opérande calculé au niveau suivant
                        niveauSuivant.add(nvOperandeCalcule);
                        // On enlève l'opérande droit du niveau suivant et on ajoute l'opérande calculé
                        niveauSuivant.remove(opDroit);

                        /*
                         * On vérifie si le nouvel opérande est égal à la valeur de la cible, si c'est le cas on ajoute
                         */
                        if(nvOperandeCalcule.getValeur() == cible){

                            /*
                             * Si on a trouve un résultat exact, on retourne le niveau suivant (il contient l'opérande
                             * calculé
                             */
                            resultats.add(niveauSuivant);

                        } else {

                            // On lance le traitement suivant par récursivité
                            traiter(niveauSuivant, cible);

                        }

                    } catch (IllegalArgumentException e) {
                        niveauSuivant.add(opGauche);
                        traiter(niveauSuivant, cible);
                    }
                }
            }

        } else {
            resultats.add(niveau);
        }
    }

}
