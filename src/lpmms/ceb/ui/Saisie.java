package lpmms.ceb.ui;

import lpmms.ceb.*;

import java.util.Scanner;
import java.util.Vector;

/**
 * Permet à l'utilisateur de saisir une solution dans un temps imparti
 * Cette classe lance un chrono puis gère les opérations de saisie de l'utilisateur
 */
public class Saisie {

    /** Gestion de la saisie de l'utilisateur */
    Scanner sc = new Scanner(System.in);

    /** Booléen mit à false lors de l'initialisation puis à true quand le temps imparti est écoulé */
    private boolean fin;

    // Objets de type Somme, Produit et Difference pour obtenir les regex lors de la saisie
    Somme somme = new Somme();
    Produit produit = new Produit();
    Difference difference = new Difference();

    /**
     * Constructeur par défaut
     */
    public Saisie() {

        this.fin = false;

    }

    /**
     * Au début de l'exécution, un message indiquant à l'utilisateur qu'il peut commencer à saisir des calculs est
     * affiché, puis un chrono est lancé et enfin la gestion de la saisie débute.
     * @param aResoudre le compte que l'utilisateur doit résoudre
     * @param tpsResolution le temps imparti pour résoudre le compte
     */
    public void start(Compte aResoudre, int tpsResolution){

        Vector<Operande> operandesDisponibles = aResoudre.getOpInitiales();

        System.out.println("Debut de la saisie, vous avez" + tpsResolution + " secondes...\n");

        // Lancement du chrono en parallèle
        Chrono chrono = new Chrono(this, tpsResolution);
        chrono.start();

        /*
         * Boucle de saisie principale.
         * Ici, lors de la saisie d'une chaîne par l'utilisateur, elle est comparée aux regex des différentes opérations
         * possibles, si une d'elles correspond, le calcul est effectué.
         */
        while(!this.fin) {

            System.out.print("Saisie : ");
            String reponse = sc.nextLine();

            // On utilise les regex pour détecter à quelle opération on a affaire
            if (reponse.matches(somme.getRegex())) {

                String[] operandes = reponse.split("[+]");
                int operandeGauche = Integer.parseInt(operandes[0]);
                int operandeDroit = Integer.parseInt(operandes[1]);

            } else if (reponse.matches(produit.getRegex())) {

            } else if (reponse.matches(difference.getRegex())) {

            } else {
                System.out.println("La saisie n'a pas ete reconnue (Syntaxe : operandeG operateur operandeD)");
            }
        }

    }

    public void fin() {

        this.fin = true;
        System.out.println("Fin de la saisie...");

    }

    private void enleverOperande(int operande, Vector<Operande> operandesDisponibles){

        boolean gaucheTrouve = false;
        boolean droitTrouve = false;

        // On parcours la liste des opérandes disponibles, si une opérande est trouvée, on l'enlève de la liste
        for (int i = 0; i<operandesDisponibles.size(); i++) {
            if (operandesDisponibles.get(i).getValeur() == operande) {
                
                operandesDisponibles.remove(i);
            }
        }

    }

}
