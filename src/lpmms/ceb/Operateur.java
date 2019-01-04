/*
 * Operateur.java                              23 nov. 2018
 * LPMMS2018-2019, aucun droits d'auteur, ni copyright, ni copyleft
 */
package lpmms.ceb;

import java.util.regex.Pattern;

/**
 * Bouchon : pour les opérandes calculés
 * TODO comment class responsibilities
 * @author frederic.barrios
 *
 */
public interface Operateur {

    /** Permet de créer une opérande calculée à partir de deux autres opérandes */
    public int calculer(int opGauche, int opDroit);

    /** Retourne le pattern lié à l'opérateur */
    public String getRegex();

    /** Retourne le symbole de l'opérateur */
    public char getSymbole();
}
