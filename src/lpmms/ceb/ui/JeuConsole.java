/*
 * JeuConsole.java                              21 nov. 2018
 * LPMMS2018-2019, aucun droits d'auteur, ni copyright, ni copyleft
 */
package lpmms.ceb.ui;

import lpmms.ceb.Compte;
import lpmms.ceb.Niveau;
import lpmms.ceb.Resolution;

/** 
 * Jeu du "Compte est bon" sur console texte
 * <ul>
 *     <li>Choix d'un niveau</li>
 *     <li>Génération d'un compte par ce niveau</li>
 *     <li>Demande de la solution sous forme texte au joueur</li>
 *     <li>Bilan et score</li>
 *     <li>Affichage des meilleures solutions calcul�es</li>
 * </ul>    
 * @author frederic.barrios
 */
public class JeuConsole {

    /** Compte à résoudre durant ce jeu console */
    private static Compte aCalculer;

    /** Eviter l'instanciation */
    private JeuConsole() {}
    
    /**
     * Jeu du compte est bon sur console texte standard
     * @param args unused
     */
    public static void main(String[] args) {
        
        // TODO choix du niveau
        //      itération 1 : pas de choix => niveau classique
        
        /* Définition du niveau standard */
        Niveau standard = new Niveau(
            "classique",
            6,    // opérandes initiales
            101,  // min de valeur du nombre à calculer
            999,  // max de valeur du nombre à calculer
            45,   // tps max de réflexion avant réponse (en s)
            35);  // tps max d'énoncé de la solution (en s)
                                     
        
        /* Tirage du compte à résoudre */
        aCalculer = standard.fabriquer();
        
        /* Affichage et début du chronométrage */
        System.out.println("Voici le niveau : \n" + standard);
        System.out.println("Voici le tirage : \n" + aCalculer);
        
        /* Demande de la solution */
        System.out.println("\n\nVotre solution : ");
        Saisie saisie = new Saisie();
        saisie.start(aCalculer, standard.getTpsReflexion());

        //System.out.println("Compte trouve : ");
        
        /* Affichage des meilleures solutions */
        //System.out.println("\n\nMeilleure solution trouvée par l'ordinateur : ");
        //Resolution.resoudre(aCalculer);
        
    }

}
