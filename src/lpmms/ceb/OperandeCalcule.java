/*
 * OperandeCalcule.java                              23 nov. 2018
 * LPMMS2018-2019, aucun droits d'auteur, ni copyright, ni copyleft
 */
package lpmms.ceb;

/** 
 * TODO comment class responsibilities
 * @author frederic.barrios
 *
 */
public class OperandeCalcule implements Operande {

    /** Valeur de cette opérande */
    private int valeur;

    /** Operande gauche */
    private Operande gauche;

    /** Operande droit */
    private Operande droit;

    /** L'opérateur utilisé pour calculer l'opérande courant */
    private Operateur oper;

    /**
     * @param gauche opérande gauche de l'opérande calculée
     * @param oper opérateur de l'opérande calculée
     * @param droit opérande droite de l'opérande calculée
     */
    public OperandeCalcule(Operande gauche, Operateur oper, Operande droit) {

        /*
         * Afin d'éviter des problèmes lors d'une soustraction, si l'opérande gauche est inférieur à l'opérande droit,
         * on les permute
         */
        if (gauche.getValeur() < droit.getValeur()) {
            this.droit = gauche;
            this.gauche = droit;
        } else {
            this.droit = droit;
            this.gauche = gauche;
        }
        this.oper = oper;

        this.valeur = oper.calculer(this.gauche.getValeur(), this.droit.getValeur());
    }

    /* (non-Javadoc)
     * @see lpmms.ceb.Operande#getValeur()
     */
    @Override
    public int getValeur() {
        return valeur;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        /*
         * Pour le ToString d'un Operande calculé, on renvoie son calcul ainsi que le calcul de tous les opérandes
         * calculés impliqués dans son obtention
         */
        StringBuilder sb = new StringBuilder();

        // Si les opérandes gauche et droits sont des opérandes calculés, on les affiche
        if (gauche instanceof OperandeCalcule) {
            sb.append(gauche.toString());
        }
        if (droit instanceof OperandeCalcule) {
            sb.append(droit.toString());
        }


         // Affichage du calcul sous forme -> "gauche" "oper" "droit" " = " "valeur"
        sb.append(gauche.getValeur());
        sb.append(" ");
        sb.append(oper.getSymbole());
        sb.append(" ");
        sb.append(droit.getValeur());
        sb.append(" = ");
        sb.append(getValeur());
        sb.append("\n");

        return sb.toString();

    }

}
