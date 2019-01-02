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
public class OperandeCalcule extends OperandeInitiale {

    /** Valeur de cette opérande */
    private int valeur;

    /**
     * @param gauche opérande gauche de l'opérande calculée
     * @param oper opérateur de l'opérande calculée
     * @param droit opérande droite de l'opérande calculée
     */
    public OperandeCalcule(Operande gauche, Operateur oper, Operande droit) {
        super(oper.calculer(gauche.getValeur(), droit.getValeur()));
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
        return toText();
    }

}
