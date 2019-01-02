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
        // On verifie que le résultat sera supérieur à 0
        if(operandeGauche>operandeDroit){
            return operandeGauche - operandeDroit;
        // Sinon, on renvoie une valeur d'erreur
        } else {
            return -1;
        }
    }

    @Override
    public Pattern getRegex() {
        return regexDiff;
    }

    @Override
    public char getSymbole() {
        return '-';
    }
}