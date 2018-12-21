/*
 * Niveau.java                              21 nov. 2018
 * LPMMS2018-2019, aucun droits d'auteur, ni copyright, ni copyleft
 */
package lpmms.ceb;

import java.util.List;

/** 
 * TODO comment class responsibilities
 * @author frederic.barrios
 *
 */
public class Niveau {

    // TODO état d'instance
    
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
        
        // TODO Auto-generated constructor stub
        // TODO initialiser les champs pour les tirages
    }
    
    /**
     * Fabrication (DP factory method) d'un Compte de ce niveau
     * par des tirages aléatoires du nombre à calculer ainsi que des opérandes 
     * initiales.
     * @return le compte fabriqué
     */
    public Compte fabriquer() {
        // bouchon (user story)
        return new Compte(
                       777,
                       List.of(new OperandeInitiale(2),
                               new OperandeInitiale(1),
                               new OperandeInitiale(6),
                               new OperandeInitiale(100),
                               new OperandeInitiale(2),
                               new OperandeInitiale(3)
                       ),
                       45,
                       35 
                   );
    }

}
