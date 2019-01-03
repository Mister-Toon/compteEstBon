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

    /** Libelle d�crivant le niveau */
    private String libelle;

    /** Op�randes de ce niveau */
    private int[] jeuOperandes;

    /** Nombre d'op�randes de ce niveau */
    private int nbOperandes;

    /** Valeur minimale du nombre � calculer */
    private int min;

    /** Valeur maximale du nombre � calculer */
    private int max;

    /** Temps de r�flexion donn� au joueur pour un niveau */
    private int tpsReflexion;

    /** Temps pendant lequel sera �nonc� la solution */
    private int tpsEnonce;

    /**
     * TODO comment initialization state
     * @param libelle nom de ce niveau
     * @param jeuOperandes plaques disponibles pour tirage
     * @param nbOperandes nombre d'op�randes initiales 
     * @param min min de valeur du nombre � calculer
     * @param max max de valeur du nombre � calculer
     * @param tpsReflexion tps max de r�flexion avant r�ponse (en s)
     * @param tpsEnonce tps max d'�nonc� de la solution (en s)
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
     * par des tirages al�atoires du nombre � calculer ainsi que des op�randes 
     * initiales.
     * @return le compte fabriqu�
     */
    public Compte fabriquer() {

        Random rand = new Random();

        // On g�n�re un nombre al�atoire compris entre 0 et max - min
        int nbAResoudre = rand.nextInt(this.max - this.min);

        // On ajoute min au r�sultat pour obtenir un nombre compris entre min et max
        nbAResoudre += this.min;

        // On g�n�re une liste avec le tableau des op�randes initiales
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
                "\tNombre d'op�randes initiales = " + nbOperandes + '\n' +
                "\tOp�randes initiales = " + Arrays.toString(jeuOperandes) + '\n' +
                "\tNombre min � trouver = " + min + '\n' +
                "\tNombre max � trouver = " + max + '\n' +
                "\tTemps de r�flexion autoris� = " + tpsReflexion + " secondes\n" +
                "\tTemps �nonc� r�ponse = " + tpsEnonce + " secondes\n";
    }
}
