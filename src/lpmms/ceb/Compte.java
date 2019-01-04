/*
 * Compte.java                              23 nov. 2018
 * LPMMS2018-2019, aucun droits d'auteur, ni copyright, ni copyleft
 */
package lpmms.ceb;

import java.util.List;
import java.util.Vector;

/** 
 * TODO comment class responsibilities
 * @author frederic.barrios
 */
public class Compte {

    /** Le nombre � calculer � partir des op�randes */
    private int aResoudre;

    /** Les op�randes initiales */
    private Vector<Operande> opDisponibles;

    /** tps max de r�flexion avant r�ponse (en s) */
    private int tpsReflexion;

    /**  tps max d'�nonc� de la solution (en s) */
    private int tpsEnonce;

    /**
     * Le compte initalement � r�soudre
     * @param aResoudre    le nombre � calculer
     * @param disponibles  les op�randes initiales
     * @param tpsReflexion tps max de r�flexion avant r�ponse (en s)
     * @param tpsEnonce    tps max d'�nonc� de la solution (en s)
     */
    public Compte(int aResoudre, 
                  List<Operande> disponibles,
                  int tpsReflexion, 
                  int tpsEnonce) {
        
        this.aResoudre = aResoudre;
        // TODO clonage pour conserver l'�tat initial ???
        this.opDisponibles = new Vector<>(disponibles);
        this.tpsReflexion = tpsReflexion;
        this.tpsEnonce = tpsEnonce;
    }

    /**
     * Constructeur qui permet de faire une copie profonde d'un autre �l�ment Compte
     * @param autre objet Compte � copier
     */
    public Compte(Compte autre) {
        this(autre.getAResoudre(), autre.getOperandes(), autre.getTpsReflexion(), autre.getTpsEnonce());
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder affichageCompte = new StringBuilder("Op�randes : ");
        for (Operande dispo : opDisponibles) {
            affichageCompte.append('\t').append(dispo);
        }
        affichageCompte.append("\nA trouver : ").append(aResoudre).append('\n');
        
        return affichageCompte.toString();
    }

    /**
     * @return aResoudre
     */
    public int getAResoudre(){
        return aResoudre;
    }

    /**
     * @return opInitiales
     */
    public Vector<Operande> getOperandes(){
        return opDisponibles;
    }

    /** @return tpsReflexion */
    public int getTpsReflexion() {
        return tpsReflexion;
    }

    /** @return tpxEnonce */
    public int getTpsEnonce() {
        return tpsEnonce;
    }
}
