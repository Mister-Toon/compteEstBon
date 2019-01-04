package lpmms.ceb;


public class Produit implements Operateur {

    /* La regex d'un produit devra être composée de:
     *     1 à 3 chiffres
     *     zéro ou plusieurs fois le caractère d'espacement
     *     le caractère "x"
     *     zéro ou plusieurs fois le caractère d'espacement
     *     1 à 3 chiffres
     */
    private String regexProduit = "^\\s*\\d{1,5}\\s*[*]\\s*\\d{1,5}\\s*$";

    @Override
    public int calculer(int opGauche, int opDroit) {
        return opGauche * opDroit;
    }

    @Override
    public String getRegex() {
        return this.regexProduit;
    }

    @Override
    public char getSymbole() {
        return '*';
    }
}
