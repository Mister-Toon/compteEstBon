package lpmms.ceb;

import java.util.regex.Pattern;

public class Produit implements Operateur {

    /* La regex d'un produit devra être composée de:
     *     1 à 3 chiffres
     *     zéro ou plusieurs fois le caractère d'espacement
     *     le caractère "x"
     *     zéro ou plusieurs fois le caractère d'espacement
     *     1 à 3 chiffres
     */
    Pattern regexProduit = Pattern.compile("\\d{1,3}\\s*[x]\\s*\\d{1,3}");

    @Override
    public int calculer(int opGauche, int opDroit) {
        return opGauche * opDroit;
    }

    @Override
    public Pattern getRegex() {
        return this.regexProduit;
    }

    @Override
    public char getSymbole() {
        return 'x';
    }
}
