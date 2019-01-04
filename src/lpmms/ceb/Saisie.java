package lpmms.ceb;

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

    /** Booléen mis à true tant que la saisie est active, pour que le message fin ne puisse pas être propagé deux fois */
    private boolean enCours;

    /** Liste des opérandes disponibles */
    private Vector<Operande> operandesDisponibles;

    /** Nombre à trouver */
    private int cible;

    // Objets de type Somme, Produit et Difference pour obtenir les regex lors de la saisie
    Somme somme = new Somme();
    Produit produit = new Produit();
    Difference difference = new Difference();

    /**
     * Constructeur par défaut
     */
    public Saisie() {

        this.fin = false;
        this.enCours = true;

    }

    /**
     * Au début de l'exécution, un message indiquant à l'utilisateur qu'il peut commencer à saisir des calculs est
     * affiché, puis un chrono est lancé et enfin la gestion de la saisie débute.
     * @param compte le compte que l'utilisateur doit résoudre
     * @param tpsResolution le temps imparti pour résoudre le compte
     */
    public void start(Compte compte, int tpsResolution){

        // On récupère la liste des opérandes initiales
        this.operandesDisponibles = compte.getOperandes();
        // On récupère le nombre à trouver
        this.cible = compte.getAResoudre();


        // On affiche un message pour indiquer à l'utilisateur qu'il peut commencer à saisir ses calculs
        System.out.println("Debut de la saisie, vous avez " + tpsResolution
                           + " secondes... (Vous pouvez taper 'stop' pour arreter la saisie)\n");

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

            /*
             * On utilise les regex pour détecter à quelle opération on a affaire puis on appele les méthodes de gestion
             * des opérandes disponibles
             */

            if(reponse.matches("stop")) {
                fin(chrono);
            } else if (reponse.matches(somme.getRegex())) {

                String[] operandes = reponse.split("[" + String.valueOf(somme.getSymbole()) + "]");
                interpreteSaisie(operandes, somme, chrono);

            } else if (reponse.matches(produit.getRegex())) {

                String[] operandes = reponse.split("[" + String.valueOf(produit.getSymbole()) + "]");
                interpreteSaisie(operandes, produit, chrono);

            } else if (reponse.matches(difference.getRegex())) {

                String[] operandes = reponse.split("[" + String.valueOf(difference.getSymbole()) + "]");
                interpreteSaisie(operandes, difference, chrono);

            } else {
                System.out.println("La saisie n'a pas ete reconnue (Syntaxe : operandeG operateur operandeD)");
            }
        }
    }

    /**
     * Prend en argument un tableau de taille 2 contenant un opérande gauche et droit, un opérateur et une liste
     * d'opérandes disponibles. Appelle la méthode qui retire et ajoute des opérandes à la liste puis affiche les
     * opérandes restants.
     * @param operandes un tableau de taille 2 contenant un opérande gauche et un opérande droit
     * @param oper l'opérateur de l'opération
     * @param chrono l'objet Chrono qui gère le temps alloué à la résolution
     */
    private void interpreteSaisie(String[] operandes, Operateur oper, Chrono chrono){
        int operandeGauche = Integer.parseInt(operandes[0].trim());
        int operandeDroit = Integer.parseInt(operandes[1].trim());

        if (oper instanceof Difference && operandeGauche < operandeDroit) {
            System.out.println("ERREUR: L'operande gauche doit etre supérieur à l'operande droit lors d'une soustraction");
        } else {
            // Si l'opérande n'a pas pu être calculé, on affiche une erreur, sinon on affiche les opérandes restants
            if(!gereDisponibles(operandeGauche, operandeDroit, oper, chrono)){
                System.out.println("ERREUR: un des opérandes ne fait pas partie des opérandes disponibles");
            }
            System.out.println("A trouver : " + this.cible + " -> Operandes restants : " + this.operandesDisponibles);
            if (this.operandesDisponibles.size() == 1) {
                fin(chrono);
            }
        }
    }

    /**
     * Si les deux opérandes passés en arguments existent dans la liste des opérandes disponibles, effectue le calcul
     * avec l'opérateur puis ajoute le résultat à la liste.
     * @param operandeG l'opérande de gauche de l'opération
     * @param operandeD l'opérande de droite de l'opération
     * @param oper l'opérateur de l'opération
     * @param chrono l'objet Chrono qui gère le temps alloué à la résolution
     * @return true si le calcul et l'ajout ont pu être effectués, false sinon
     */
    private boolean gereDisponibles(int operandeG, int operandeD, Operateur oper, Chrono chrono){
        Operande operandeGTrouve = null;
        Operande operandeDTrouve = null;

        // On cherche les deux opérandes dans la liste des opérandes disponibles
        operandeGTrouve = checkOperandePresent(operandeG);
        operandeDTrouve = checkOperandePresent(operandeD);

        /*
         * Si les deux opérandes ont été trouvés, on les enlève de la liste des opérandes disponibles et
         * on retourne true, sinon, on retourne false
         * Si un seul des deux opérandes n'a pas été trouvé, on remet l'autre dans la liste.
         */
        if(operandeGTrouve != null && operandeDTrouve != null){
            // Enlève les deux opérandes initiaux de la liste des opérandes disponibles
            this.operandesDisponibles.remove(operandeGTrouve);
            this.operandesDisponibles.remove(operandeDTrouve);

            // Ajoute le nouvel opérande calculé à la liste des opérandes disponibles
            OperandeCalcule nvlOperande = new OperandeCalcule(operandeGTrouve, oper, operandeDTrouve);
            this.operandesDisponibles.add(nvlOperande);

            // Si le nouvel opérande calculé est égal à la cible, on arrête la saisie
            if (nvlOperande.getValeur() == this.cible) {
                fin(chrono);
            }

            return true;
        } else if (operandeGTrouve != null && operandeDTrouve == null) {
            this.operandesDisponibles.add(operandeGTrouve);
            return false;
        } else if (operandeGTrouve == null && operandeDTrouve != null) {
            this.operandesDisponibles.add(operandeDTrouve);
            return false;
        } else {
            return false;
        }
    }

    /**
     * Parcours la liste des opérandes disponibles et vérifie si l'opérande passé en argument est présent
     * @param operande opérande à chercher dans la liste des opérandes disponibles
     * @return null si l'opérande n'était pas présent, ou l'opérande si il a été trouvé dans la liste
     */
    private Operande checkOperandePresent(int operande) {

        for (int i = 0; i<this.operandesDisponibles.size(); i++) {
            if (this.operandesDisponibles.get(i).getValeur() == operande) {
                return this.operandesDisponibles.remove(i);
            }
        }
        return null;
    }

    /**
     * Interruption avant la fin du chrono (interruption manuelle, résultat final trouvé ou un seul opérande restant.
     * Permet d'arrêter le Thread the chronométrage.
     * @param chrono l'objet chrono à arrêter
     */
    private void fin(Chrono chrono) {
        chrono.interrupt();
        fin();
    }

    /**
     * Message envoyé par le chrono pour signaler la fin du temps imparti pour la saisie
     * Après la fin, on vérifie si l'utilisateur a trouvé la bonne valeur
     */
    public void fin() {

        if (enCours) {

            this.enCours = false;
            this.fin = true;

            boolean exact = false;
            boolean proche = false;
            System.out.println("Fin de la saisie...");

            // On vérifie si un résultat exact ou proche a été trouvé
            for (Operande oper : this.operandesDisponibles) {
                if (oper.getValeur() == this.cible) {
                    exact = true;
                } else if (Math.abs(oper.getValeur() - this.cible) <= 5) {
                    proche = true;
                }
            }

            // On affiche une phrase correspondant à un résultat exact, proche ou pas de résultat
            if (exact) {
                System.out.println("FELICITATIONS: Vous avez trouve un bon resultat !");
            } else if (proche) {
                System.out.println("FELICITATIONS: Vous avez trouve un resultat proche !");
            } else {
                System.out.println("DOMMAGE: Vous n'avez pas trouve de bon resultat...");
            }
        }
    }
}
