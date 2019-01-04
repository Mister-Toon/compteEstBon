/*
 * OperandeInitial.java                              23 nov. 2018
 * LPMMS2018-2019, aucun droits d'auteur, ni copyright, ni copyleft
 */
package lpmms.ceb;

/** 
 * Opérande dont la valeur est fixée initialement,
 * peut servir de "plaque" initiale dans un compte est bon
 * @author frederic.barrios
 *
 */
public class OperandeInitial implements Operande {

    /** Valeur de cette opérande */
    private int valeur;

    /**
     * Une opérande tirée lors de la fabrication d'un compte est bon
     * @param init valeur de cet opérande
     */
    public OperandeInitial(int init) {
        valeur = init;
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

        return String.valueOf(this.valeur);

    }
}
