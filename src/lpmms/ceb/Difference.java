package lpmms.ceb;

import java.util.regex.Pattern;

public class Difference implements Operateur {

    /* La regex d'une différence devra être composée de:
     *     1 à 3 chiffres
     *     zéro ou plusieurs fois le caractère d'espacement
     *     le caractère "-"
     *     zéro ou plusieurs fois le caractère d'espacement
     *     1 à 3 chiffres
     */
    Pattern regexDiff = Pattern.compile("\\d{1,3}\\s*[-]\\s*\\d{1,3}");

    @Override
    public int calculer(int operandeGauche, int operandeDroit) {
        // On vérifie que l'opérande gauche et droit ne sont pas égaux
        if (operandeGauche != operandeDroit) {

            return operandeGauche - operandeDroit;

        // Sinon, on renvoie une valeur d'erreur
        } else {
            throw new IllegalArgumentException("L'opérande gauche et droit ne peuvent être égaux");
        }
    }

    @Override
    public Pattern getRegex() {
        return this.regexDiff;
    }

    @Override
    public char getSymbole() {
        return '-';
    }
}
