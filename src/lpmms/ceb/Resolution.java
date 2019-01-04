package lpmms.ceb;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

/**
 * Resout un compte de manière recursive
 * @author robin.hortala
 */
public class Resolution {

    private static Scanner sc = new Scanner(System.in);

    // On cree les objets des operateurs qui seront utilises dans la resolution
    private static Operateur somme = new Somme();
    private static Operateur produit = new Produit();
    private static Operateur difference = new Difference();

    // On fait un tableau avec les trois operateurs pour les parcourir pendant un traitement
    private static Operateur[] operateurs = {somme, produit, difference};

    // Le tableau resultats contiendra des niveaux qui contiennent un resultat exact
    private static ArrayList<Vector<Operande>> resultats = new ArrayList<>();

    // La liste resultats approximatifs contiendra le niveau qui contient la valeur la plus proche de la cible
    private static Vector<Operande> resultatApprox = new Vector<>();
    // Cette variable stocke l'écart entre la valeur cible et le meilleur résultat approché
    private static int meilleurEcart = 999999;

    /**
     * Prend un compte à resoudre en argument et effectue les calculs possibles de manière recursive
     * jusquà ce qu'un resultat soit trouve. Si, à la fin du parcours, aucun resultat exact n'a ete trouve,
     * on prend le resultat avec la valeur la plus proche de la cible
     * @param aResoudre le compte à resoudre
     */
    public static void resoudre(Compte aResoudre){

        // Les operandes disponibles pour atteindre le nombre cible
        Vector<Operande> opInitiales = aResoudre.getOperandes();

        // Le nombre que l'on chercher à obtenir
        int cible = aResoudre.getAResoudre();

        // On lance de parcours recursif par niveau sur les operandes initiaux
        traiter(opInitiales, cible);

        /*
         * Si des resultats exacts on ete trouvés, on affiche le nombre de résultats exacts trouvés
         * Puis on propose à l'utilisateur de voir les résultats exacts et on recupère sa réponse
         * Si l'utilisateur veut voir les résultats exacts, on les affiche
         * Sinon, de la même manière, on lui propose d'afficher le résultat le plus proche
         */
        if (resultats.size() != 0) {

            System.out.println(resultats.size() + " resultats exacts on ete trouves");

            System.out.print("Voulez vous afficher les resultats exacts ? [o/n] ");
            afficheResultats(cible, resultats);
        } else {
            System.out.println("Aucun resultat exact trouve ...");
            System.out.print("Voulez vous afficher le resultat le plus proche ? [o/n] ");
            afficheResultats(cible, new ArrayList<>(List.of(resultatApprox)));
        }
    }

    /**
     * Traite un "niveau", c'est à dire un ensemble d'operandes, par exemple, le premier niveau traite lors de la
     * resolution est constitue des operandes de depart.
     * Le traitement d'un niveau consiste à realiser toutes les operations possibles pour celui-ci.
     * @param niveau le niveau à traiter
     * @return le niveau dans lequel une operande avec un resultat
     */
    public static void traiter(Vector<Operande> niveau, int cible){

        /*
         * Parcours recursif des operandes du niveau
         * Conditions d'arrêt :
         *     - Il ne reste plus qu'un operande disponible
         *     - On a atteint le nombre cible
         */
        if (niveau.size() > 1) {

            /*
             * On enlève le premier operande du niveau pour l'additionner, le multiplier et le soustraire
             * à tous les autres operandes du niveau
             */
            Operande opGauche = niveau.remove(0);

            // On parcours tous les operandes du niveau pour effectuer les calculs
            for (int i = 0; i < niveau.size(); i++ ) {
                Operande opDroit = niveau.get(i);

                // On parcours le tableau des operateurs pour lancer toutes les operations entre les deux operandes
                for (Operateur oper : operateurs){

                    // On cree le niveau suivant qui sera traite recursivement
                    Vector<Operande> niveauSuivant = (Vector<Operande>) niveau.clone();

                    /*
                     * Lors de la creation de l'operande calcule, si l'operation est une difference et que l'operande
                     * gauche est inferieur à l'operande droit, une IllegalArgumentException sera leve, dans ce cas,
                     * on remet l'operande gauche à la fin du niveau suivant.
                     */
                    try {

                        // On cree l'operande calcule
                        Operande nvOperandeCalcule = new OperandeCalcule(opGauche, oper, opDroit);

                        // On ajoute l'operande calcule au niveau suivant
                        niveauSuivant.add(nvOperandeCalcule);
                        // On enlève l'operande droit du niveau suivant et on ajoute l'operande calcule
                        niveauSuivant.remove(opDroit);

                        /*
                         * On verifie si le nouvel operande est egal à la valeur de la cible, si c'est le cas on ajoute
                         */
                        if(nvOperandeCalcule.getValeur() == cible){

                            /*
                             * Si on a trouve un resultat exact, on retourne le niveau suivant (il contient l'operande
                             * calcule
                             */
                            resultats.add(niveauSuivant);

                        } else {

                            // On lance le traitement suivant par recursivite
                            traiter(niveauSuivant, cible);

                        }

                    } catch (IllegalArgumentException e) {
                        niveauSuivant.add(opGauche);
                    }
                }
            }

        } else {
            /*
             * Pour chaque operande du niveau final, si l'ecart entre l'operande et la cible est inferieur à la valeur
             * la plus proche courante, le résultat devient la nouvelle valeur la plus proche
             */
            for (Operande o : niveau) {
                if (Math.abs(o.getValeur() - cible) < meilleurEcart) {
                    meilleurEcart = Math.abs(o.getValeur() - cible);
                    resultatApprox = niveau;
                }
            }
        }
    }

    /**
     * Affiche les résultats d'un tableau de résultats après l'avoir proposé à l'utilisateur
     * Afin d'afficher seulement les calculs menant au résultat final, ou les valeurs proches. On vérifie pour chaque
     * opérande d'un niveau qu'il ait un écart d'au plus 5 avec la valeur cible et qu'elle soit un opérande calculé
     * @param cible le nomre à trouver dans ce compte
     * @param tableauResultats le tableau contenant les résultats
     */
    private static void afficheResultats(int cible, ArrayList<Vector<Operande>> tableauResultats) {
        String reponse = sc.nextLine();

        if (reponse.equals("o")) {
            // On affiche les operandes du resultat
            for (Vector<Operande> resultat : tableauResultats) {
                for(Operande oper : resultat) {

                    if(Math.abs(oper.getValeur() - cible) <= 5 && oper instanceof OperandeCalcule){
                        System.out.println(((OperandeCalcule) oper).getCalculs());
                    }
                }
            }
        }
    }

}
