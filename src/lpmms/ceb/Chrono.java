package lpmms.ceb;

/**
 * Chrono qui gère le temps donné au joueur pour résoudre un niveau
 */
public class Chrono extends Thread {

    /**
     * L'instance de saisie appelant le chrono
     */
    private Saisie sender;

    /**
     * Le temps imparti pour saisir une solution
     */
    private int tpsResolution;

    /**
     * Lors de l'initialisation, on passe à Chrono l'instance de saisie qui l'a appelé et le temps imparti, lorsque
     * le temps imparti est écoulé, il peut envoyer un message à son appelant pour lui dire que le temps de saisie est
     * terminé.
     * @param sender l'instance de saisie appelant le chrono
     * @param tpsResolution le temps imparti pour saisir une solution
     */
    public Chrono(Saisie sender, int tpsResolution) {

        this.sender = sender;
        this.tpsResolution = tpsResolution;

    }

    /**
     * Lors du lancement du Chrono, il dort pendant le temps imparti à la résolution puis, à la fin de ce temps, envoie
     * un message à l'instance de saisie qui l'a appelé
     */
    @Override
    public void run() {

        try {
            // Le temps résolution est en secondes, on multiplie par 1000 pour obtenir des millisecondes
            Thread.sleep(this.tpsResolution * 1000);
            this.sender.fin();

        } catch (InterruptedException e) {
            // Interruption du thread par l'utilisateur
        }

    }
}
