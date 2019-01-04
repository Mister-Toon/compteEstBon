package lpmms.ceb;


public class Somme implements Operateur{

    /* La regex d'une somme devra être composée de:
     *     1 à 3 chiffres
     *     zéro ou plusieurs fois le caractère d'espacement
     *     le caractère "+"
     *     zéro ou plusieurs fois le caractère d'espacement
     *     1 à 3 chiffres
     */
    private String regexSomme = "^\\s*\\d{1,5}\\s*[+]\\s*\\d{1,5}\\s*$";

    @Override
    public int calculer(int opGauche, int opDroit) {
        return opGauche + opDroit;
    }

    @Override
    public String getRegex() {
        return this.regexSomme;
    }

    @Override
    public char getSymbole() {
        return '+';
    }
}
