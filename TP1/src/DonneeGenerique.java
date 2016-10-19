/*
    Cette classe represente une donne contenue dans un ABR.
    C'est une classe generique qui va stocker une cle et la donnee elle meme.
*/

import java.lang.String;
import java.util.Comparable;

class DonneeGenerique<T> implements Comparable<T> {
    String cle;
    T valeur;

    public DonneeGenerique(T valeur) {
        this.valeur = valeur;
        this.cle = valeur.toString().normalize();
    }

    /*
    public String getCle() {
        return this.cle;
    }

    public T getValeur() {
        return this.valeur;
    }
    */

    @implements
    public int compareTo(T o) {
        return this.cle.compareTo(o);
    }

    @override
    public String toString() {
        return this.valeur.toString();
    }
}
