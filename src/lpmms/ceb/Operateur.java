/*
 * Operateur.java                              23 nov. 2018
 * LPMMS2018-2019, aucun droits d'auteur, ni copyright, ni copyleft
 */
package lpmms.ceb;

import java.util.regex.Pattern;

/**
 * Bouchon : pour les op�randes calcul�s
 * TODO comment class responsibilities
 * @author frederic.barrios
 *
 */
public interface Operateur {

    /** Permet de cr�er une op�rande calcul�e � partir de deux autres op�randes */
    public int calculer(int opGauche, int opDroit);

    /** Retourne le pattern li� � l'op�rateur */
    public String getRegex();

    /** Retourne le symbole de l'op�rateur */
    public char getSymbole();
}
