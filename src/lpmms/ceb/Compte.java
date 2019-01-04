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

    /** Le nombre à calculer à partir des opérandes */
    private int aResoudre;

    /** Les opérandes initiales */
    private Vector<Operande> opDisponibles;

    /** tps max de réflexion avant réponse (en s) */
    private int tpsReflexion;

    /**  tps max d'énoncé de la solution (en s) */
    private int tpsEnonce;

    /**
     * Le compte initalement à résoudre
     * @param aResoudre    le nombre à calculer
     * @param disponibles  les opérandes initiales
     * @param tpsReflexion tps max de réflexion avant réponse (en s)
     * @param tpsEnonce    tps max d'énoncé de la solution (en s)
     */
    public Compte(int aResoudre, 
                  List<Operande> disponibles,
                  int tpsReflexion, 
                  int tpsEnonce) {
        
        this.aResoudre = aResoudre;
        // TODO clonage pour conserver l'état initial ???
        this.opDisponibles = new Vector<>(disponibles);
        this.tpsReflexion = tpsReflexion;
        this.tpsEnonce = tpsEnonce;
    }

    /**
     * Constructeur qui permet de faire une copie profonde d'un autre élément Compte
     * @param autre objet Compte à copier
     */
    public Compte(Compte autre) {
        this(autre.getAResoudre(), autre.getOperandes(), autre.getTpsReflexion(), autre.getTpsEnonce());
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder affichageCompte = new StringBuilder("Opérandes : ");
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
