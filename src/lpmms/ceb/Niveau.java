/*
 * Niveau.java                              21 nov. 2018
 * LPMMS2018-2019, aucun droits d'auteur, ni copyright, ni copyleft
 */
package lpmms.ceb;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/** 
 * TODO comment class responsibilities
 * @author frederic.barrios
 *
 */
public class Niveau {

    /** Libelle décrivant le niveau */
    private String libelle;

    /** Opérandes de ce niveau */
    private int[] jeuOperandes;

    /** Nombre d'opérandes de ce niveau */
    private int nbOperandes;

    /** Valeur minimale du nombre à calculer */
    private int min;

    /** Valeur maximale du nombre à calculer */
    private int max;

    /** Temps de réflexion donné au joueur pour un niveau */
    private int tpsReflexion;

    /** Temps pendant lequel sera énoncé la solution */
    private int tpsEnonce;

    /**
     * TODO comment initialization state
     * @param libelle nom de ce niveau
     * @param jeuOperandes plaques disponibles pour tirage
     * @param nbOperandes nombre d'opérandes initiales 
     * @param min min de valeur du nombre à calculer
     * @param max max de valeur du nombre à calculer
     * @param tpsReflexion tps max de réflexion avant réponse (en s)
     * @param tpsEnonce tps max d'énoncé de la solution (en s)
     */
    public Niveau(String libelle,
                  int[] jeuOperandes,
                  int   nbOperandes,
                  int   min,
                  int   max,
                  int tpsReflexion,
                  int tpsEnonce) {
        
        // Initialisation des champs d'un niveau
        this.libelle = libelle;

        this.jeuOperandes = jeuOperandes;

        this.nbOperandes = nbOperandes;

        this.min = min;

        this.max = max;

        this.tpsReflexion = tpsReflexion;

        this.tpsEnonce = tpsEnonce;
    }
    
    /**
     * Fabrication (DP factory method) d'un Compte de ce niveau
     * par des tirages aléatoires du nombre à calculer ainsi que des opérandes 
     * initiales.
     * @return le compte fabriqué
     */
    public Compte fabriquer() {

        Random rand = new Random();

        // On génère un nombre aléatoire compris entre 0 et max - min
        int nbAResoudre = rand.nextInt(this.max - this.min);

        // On ajoute min au résultat pour obtenir un nombre compris entre min et max
        nbAResoudre += this.min;

        // On génère une liste avec le tableau des opérandes initiales
        Vector<OperandeInitiale> jeuInitial = new Vector<OperandeInitiale>();
        for (int nombre : this.jeuOperandes) {
            jeuInitial.add(new OperandeInitiale(nombre));
        }

        return new Compte(
                       nbAResoudre,
                       jeuInitial,
                       this.tpsReflexion,
                       this.tpsEnonce
                   );
    }

    @Override
    public String toString() {
        return "Niveau : \n" +
                "\tLibelle = " + libelle + '\n' +
                "\tNombre d'opérandes initiales = " + nbOperandes + '\n' +
                "\tOpérandes initiales = " + Arrays.toString(jeuOperandes) + '\n' +
                "\tNombre min à trouver = " + min + '\n' +
                "\tNombre max à trouver = " + max + '\n' +
                "\tTemps de réflexion autorisé = " + tpsReflexion + " secondes\n" +
                "\tTemps énoncé réponse = " + tpsEnonce + " secondes\n";
    }
}
