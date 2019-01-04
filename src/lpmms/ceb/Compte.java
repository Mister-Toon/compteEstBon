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
    private Vector<Operande> opInitiales;
    
    /** Les opérandes disponibles à un instant t */
    private Vector<Operande> disponibles;

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
                  List<OperandeInitial> disponibles,
                  int tpsReflexion, 
                  int tpsEnonce) {
        
        this.aResoudre = aResoudre;
        // TODO clonage pour conserver l'état initial ???
        this.opInitiales = new Vector<Operande>(disponibles);
        this.disponibles = new Vector<Operande>(disponibles);
        this.tpsReflexion = tpsReflexion;
        this.tpsEnonce = tpsEnonce;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder affichageCompte = new StringBuilder("Opérandes : ");
        for (Operande dispo : disponibles) {
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
    public Vector<Operande> getOpInitiales(){
        return opInitiales;
    }

}
