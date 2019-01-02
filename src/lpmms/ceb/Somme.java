package lpmms.ceb;

import java.util.regex.Pattern;

public class Somme implements Operateur{

    /* La regex d'une somme devra être composée de:
     *     1 à 3 chiffres
     *     zéro ou plusieurs fois le caractère d'espacement
     *     le caractère "+"
     *     zéro ou plusieurs fois le caractère d'espacement
     *     1 à 3 chiffres
     */
    Pattern regexSomme = Pattern.compile("\\d{1,3}\\s*[+]\\s*\\d{1,3}");

    @Override
    public int calculer(int operandeGauche, int operandeDroit) {
        return operandeGauche + operandeDroit;
    }

    @Override
    public Pattern getRegex() {
        return regexSomme;
    }

    @Override
    public char getSymbole() {
        return '+';
    }
}
