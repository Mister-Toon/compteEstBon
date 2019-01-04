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

    /** Tableau à partir duquel seront générés les petits opérandes initiaux */
    private List<Integer> petitsOperandesInit;

    /** Tableau à partir duquel seront générés les grands opérandes initiaux */
    private List<Integer> grandsOperandesInit;

    /** Libelle décrivant le niveau */
    private String libelle;

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
     * @param nbOperandes nombre d'opérandes initiales 
     * @param min min de valeur du nombre à calculer
     * @param max max de valeur du nombre à calculer
     * @param tpsReflexion tps max de réflexion avant réponse (en s)
     * @param tpsEnonce tps max d'énoncé de la solution (en s)
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

        // On remplit les listes de petits et grands opérandes initialement disponibles
        petitsOperandesInit = new ArrayList<>(List.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10));
        grandsOperandesInit = new ArrayList<>(List.of(25, 25, 50, 50, 75, 75, 100, 100));
    }
    
    /**
     * Fabrication (DP factory method) d'un Compte de ce niveau
     * par des tirages aléatoires du nombre à calculer ainsi que des opérandes 
     * initiales.
     * @return le compte fabriqué
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
        return "\tLibellé = " + libelle + '\n' +
               "\tNombre d'opérandes initiales = " + nbOperandes + '\n' +
               "\tNombre min à trouver = " + min + '\n' +
               "\tNombre max à trouver = " + max + '\n' +
               "\tTemps de réflexion autorisé = " + tpsReflexion + " secondes\n" +
               "\tTemps énoncé réponse = " + tpsEnonce + " secondes\n";
    }

    /**
     * Génère un tirage, c'est a dire une liste de petits et grands opérandes aléatoirement pris des tableaux
     * d'opérandes initialement disponibles
     * @return le tirage
     */
    private Vector<OperandeInitial> genererTirage(){
        Vector<OperandeInitial> jeuInitial = new Vector<>();

        /*
         * Le nombre de petits opérandes qui seront tirés correspond à 2/3 du nombre d'opérandes totaux, arrondi au
         * supérieur
         */
        int nbPetitsOperandes = (int) Math.ceil(((double)this.nbOperandes)/3.0*2.0);
        // Le nombre de grands opérandes qui seront tirés dépend du nombre de petits opérandes
        int nbGrandsOperandes = this.nbOperandes - nbPetitsOperandes;

        /*
         * On prend nbPetitsOperandes depuis la liste des petits opérandes initiaux et on créé des objets OperandeInitial
         * qu'on met dans le tableau jeuInitial.
         * On fait ensuite la même chose pour les grands opérandes.
         */
        genererOperandes(jeuInitial, nbPetitsOperandes, petitsOperandesInit);
        genererOperandes(jeuInitial, nbGrandsOperandes, grandsOperandesInit);

        return jeuInitial;
    }

    /**
     * On prend nbATirer opérandes depuis la liste d'opérandes initiaux listOperInit et on créé des objets OperandeInitial
     * qu'on met dans la liste aRemplir
     * @param aRemplir liste à remplir avec des opérandes aléatoirement sélectionnés
     * @param nbATirer nombre d'opérandes à mettre dans aRemplir
     * @param listOperInit liste d'opérandes disponibles pour la sélection aléatoire
     */
    private void genererOperandes(Vector<OperandeInitial> aRemplir, int nbATirer, List<Integer> listOperInit) {

        for (int i = 0; i < nbATirer; i++) {
            // Pour tirer un opérande initial aléatoirement on génère un index aléatoire
            int indexAleatoire = genererEntAleatoire(0, listOperInit.size()-1);

            // On enlève l'opérande de la liste pour qu'il ne soit pas tiré deux fois
            int grandOperande = listOperInit.remove(indexAleatoire);

            // On ajoute un objet OperandeInitial avec la valeur de l'opérande tiré aléatoirement à la liste à remplir
            aRemplir.add(new OperandeInitial(grandOperande));

        }
    }

    /**
     * Génère un nombre aléatoire entre min et max, permet de générer aléatoirement un nombre à résoudre dans le compte
     * @param min le plus petit nombre pouvant être généré
     * @param max le plus grand nombre pouvant être généré
     * @return un entier correspondant au nombre cible généré aléatoirement
     */
    private int genererEntAleatoire(int min, int max){

        Random rand = new Random();

        // On génère un nombre aléatoire compris entre 0 et max - min
        int nbAResoudre = rand.nextInt(max - min);

        // On ajoute min au résultat pour obtenir un nombre compris entre min et max
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
