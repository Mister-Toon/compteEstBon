package lpmms.ceb;


public class Difference implements Operateur {

    /* La regex d'une différence devra être composée de:
     *     1 à 3 chiffres
     *     zéro ou plusieurs fois le caractère d'espacement
     *     le caractère "-"
     *     zéro ou plusieurs fois le caractère d'espacement
     *     1 à 3 chiffres
     */
    private String regexDiff = "^\\s*\\d{1,5}\\s*[-]\\s*\\d{1,5}\\s*$";

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
    public String getRegex() {
        return this.regexDiff;
    }

    @Override
    public char getSymbole() {
        return '-';
    }
}
