import java.applet.Applet;
import java.awt.Graphics;


public class Affiche extends Applet {

    /**
     * Initialisation : quand l'applet est chargée. Ne se produit qu’une fois dans la vie de l’applet.
     *
     */
    public void init() {}


    /**
     * Démarrage : lors du lancement ou de la reprise de l’applet
     *
     */
    public void start() {}


    /**
     * Arrêt : quand on quitte la page contenant l’applet
     *
     */
    public void stop() {}


    /**
     * Destruction : quand on quitte le navigateur. Ne se produit qu’une fois dans la vie de l’applet.
     *
     */
    public void destroy() {}


    /**
     * Affichage
     *
     */
    public void paint(Graphics g) {
        g.drawString("Début de l'Applet", 0, 0);
        g.drawString("Fin de l'Applet", 10, 10);
    }
}
