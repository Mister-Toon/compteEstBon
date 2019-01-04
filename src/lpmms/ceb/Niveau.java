/*
 * Niveau.java                              21 nov. 2018
 * LPMMS2018-2019, aucun droits d'auteur, ni copyright, ni copyleft
 */
package lpmms.ceb;

import java.util.*;

/** 
 * TODO comment class responsibilities
 * @author frederic.barrios
 *
 */
public class Niveau {

    /** Tableau � partir duquel seront g�n�r�s les petits op�randes initiaux */
    private List<Integer> petitsOperandesInit;

    /** Tableau � partir duquel seront g�n�r�s les grands op�randes initiaux */
    private List<Integer> grandsOperandesInit;

    /** Libelle d�crivant le niveau */
    private String libelle;

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
     * @param nbOperandes nombre d'op�randes initiales 
     * @param min min de valeur du nombre � calculer
     * @param max max de valeur du nombre � calculer
     * @param tpsReflexion tps max de r�flexion avant r�ponse (en s)
     * @param tpsEnonce tps max d'�nonc� de la solution (en s)
     */
    public Niveau(String libelle,
                  int   nbOperandes,
                  int   min,
                  int   max,
                  int tpsReflexion,
                  int tpsEnonce) {
        
        // Initialisation des champs d'un niveau
        this.libelle = libelle;

        this.nbOperandes = nbOperandes;

        this.min = min;

        this.max = max;

        this.tpsReflexion = tpsReflexion;

        this.tpsEnonce = tpsEnonce;

        // On remplit les listes de petits et grands op�randes initialement disponibles
        petitsOperandesInit = new ArrayList<>(List.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10));
        grandsOperandesInit = new ArrayList<>(List.of(25, 25, 50, 50, 75, 75, 100, 100));
    }
    
    /**
     * Fabrication (DP factory method) d'un Compte de ce niveau
     * par des tirages al�atoires du nombre � calculer ainsi que des op�randes 
     * initiales.
     * @return le compte fabriqu�
     */
    public Compte fabriquer() {


        Vector<OperandeInitial> jeuInitial =  genererTirage();

        int nbAResoudre = genererEntAleatoire(this.min, this.max);

        return new Compte(
                       nbAResoudre,
                       jeuInitial,
                       this.tpsReflexion,
                       this.tpsEnonce
                   );
    }

    @Override
    public String toString() {
        return "\tLibell� = " + libelle + '\n' +
               "\tNombre d'op�randes initiales = " + nbOperandes + '\n' +
               "\tNombre min � trouver = " + min + '\n' +
               "\tNombre max � trouver = " + max + '\n' +
               "\tTemps de r�flexion autoris� = " + tpsReflexion + " secondes\n" +
               "\tTemps �nonc� r�ponse = " + tpsEnonce + " secondes\n";
    }

    /**
     * G�n�re un tirage, c'est a dire une liste de petits et grands op�randes al�atoirement pris des tableaux
     * d'op�randes initialement disponibles
     * @return le tirage
     */
    private Vector<OperandeInitial> genererTirage(){
        Vector<OperandeInitial> jeuInitial = new Vector<>();

        /*
         * Le nombre de petits op�randes qui seront tir�s correspond � 2/3 du nombre d'op�randes totaux, arrondi au
         * sup�rieur
         */
        int nbPetitsOperandes = (int) Math.ceil(((double)this.nbOperandes)/3.0*2.0);
        // Le nombre de grands op�randes qui seront tir�s d�pend du nombre de petits op�randes
        int nbGrandsOperandes = this.nbOperandes - nbPetitsOperandes;

        /*
         * On prend nbPetitsOperandes depuis la liste des petits op�randes initiaux et on cr�� des objets OperandeInitial
         * qu'on met dans le tableau jeuInitial.
         * On fait ensuite la m�me chose pour les grands op�randes.
         */
        genererOperandes(jeuInitial, nbPetitsOperandes, petitsOperandesInit);
        genererOperandes(jeuInitial, nbGrandsOperandes, grandsOperandesInit);

        return jeuInitial;
    }

    /**
     * On prend nbATirer op�randes depuis la liste d'op�randes initiaux listOperInit et on cr�� des objets OperandeInitial
     * qu'on met dans la liste aRemplir
     * @param aRemplir liste � remplir avec des op�randes al�atoirement s�lectionn�s
     * @param nbATirer nombre d'op�randes � mettre dans aRemplir
     * @param listOperInit liste d'op�randes disponibles pour la s�lection al�atoire
     */
    private void genererOperandes(Vector<OperandeInitial> aRemplir, int nbATirer, List<Integer> listOperInit) {

        for (int i = 0; i < nbATirer; i++) {
            // Pour tirer un op�rande initial al�atoirement on g�n�re un index al�atoire
            int indexAleatoire = genererEntAleatoire(0, listOperInit.size()-1);

            // On enl�ve l'op�rande de la liste pour qu'il ne soit pas tir� deux fois
            int grandOperande = listOperInit.remove(indexAleatoire);

            // On ajoute un objet OperandeInitial avec la valeur de l'op�rande tir� al�atoirement � la liste � remplir
            aRemplir.add(new OperandeInitial(grandOperande));

        }
    }

    /**
     * G�n�re un nombre al�atoire entre min et max, permet de g�n�rer al�atoirement un nombre � r�soudre dans le compte
     * @param min le plus petit nombre pouvant �tre g�n�r�
     * @param max le plus grand nombre pouvant �tre g�n�r�
     * @return un entier correspondant au nombre cible g�n�r� al�atoirement
     */
    private int genererEntAleatoire(int min, int max){

        Random rand = new Random();

        // On g�n�re un nombre al�atoire compris entre 0 et max - min
        int nbAResoudre = rand.nextInt(max - min);

        // On ajoute min au r�sultat pour obtenir un nombre compris entre min et max
        nbAResoudre += min;

        return nbAResoudre;

    }

    /**
     * @return tpsReflexion
     */
    public int getTpsReflexion() {
        return tpsReflexion;
    }
}
