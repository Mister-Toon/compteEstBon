/*
 * Operande.java                              23 nov. 2018
 * LPMMS2018-2019, aucun droits d'auteur, ni copyright, ni copyleft
 */
package lpmms.ceb;

/** 
 * Opérande du compte est bon, disponible pour les calculs
 * @author frederic.barrios
 */
public interface Operande {

    /** @return valeur (initiale ou calculée) de cet opérande */
    public int getValeur();
    
    /**
     * Profondeur de calcul de cet opérande c'est-à-dire le nombre
     * d'opérations ayant conduit à cet opérande
     * @return <ul><li>0 si opérande non calculée</li>
     *             <li>nb de calculs infixe dont est issu cet opérande</li>
     *         </ul>
     */
    public default int getProfondeur() {
        return 0;  // opérande non issue d'un calcul
    }
    
    /**
     * Mise sous format texte de cet opérande
     * @return valeur en base 10 sous forme texte
     */
    public default String toText() {
        return Integer.toString(getValeur());
    }
}
