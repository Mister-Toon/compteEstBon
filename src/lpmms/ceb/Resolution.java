package lpmms.ceb;

import java.util.Vector;

/**
 * Résout un compte de manière récursive
 * @author robin.hortala
 */
public class Resolution {

    /**
     * Prend un compte à résoudre en argument et effectue les calculs possibles de manière récursive
     * jusquà ce qu'un résultat soit trouvé. Si, à la fin du parcours, aucun résultat exact n'a été trouvé,
     * on prend le résultat avec la valeur la plus proche de la cible
     * @param aResoudre le compte à résoudre
     */
    public void resoudre(Compte aResoudre){

        // Le nombre que l'on chercher à obtenir
        int cible = aResoudre.getAResoudre();

        // Les opérandes disponibles pour atteindre le nombre cible
        Vector<Operande> opInitiales = aResoudre.getOpInitiales();

        // trouve sera mis à true si on atteint le nombre cible, cela permet d'arrêter le parcours récursif
        boolean trouve = false;

        int nbOperations = 0;

        /*
         * Parcours récursif des opérandes initiales
         * Conditions d'arrêt :
         *     - Il ne reste plus qu'une opérande disponible
         *     - On a atteint le nombre cible
         * Dans le deuxième cas, on ne cherche pas à parcourir tout l'arbre des possibilités
         */
        while(trouve == false){

        }

    }

    /**
     * Traite un "niveau", c'est à dire un ensemble d'opérandes, par exemple, le premier niveau traité lors de la
     * résolution est constitué des opérandes de départ.
     * Le traitement du niveau consiste à réaliser toutes les opérations possibles à celui-ci.
     * @param niveau le niveau à traiter
     */
    public void traiter(Vector<Operande> niveau){
        while (niveau.size() > 1) {

            Operande opGauche = niveau.remove(0);

            for (Operande opDroit : niveau) {

                Vector<Operande> niveauSuivant = niveau;
                Operateur somme = new Somme();
                niveauSuivant.add(new OperandeCalcule(opGauche, somme, opDroit));
                traiter(niveauSuivant);

                niveauSuivant = niveau;
                Operateur produit = new Produit();
                niveauSuivant.add(new OperandeCalcule(opGauche, produit, opDroit));
                traiter(niveauSuivant);

                niveauSuivant = niveau;
                Operateur difference = new Difference();
                niveauSuivant.add(new OperandeCalcule(opGauche, difference, opDroit));
                traiter(niveauSuivant);

            }

        }
    }

}
