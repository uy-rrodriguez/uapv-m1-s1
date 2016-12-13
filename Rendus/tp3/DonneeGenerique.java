package tp3;

import java.lang.String;
import java.lang.Comparable;
import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 *   Cette classe represente une donne contenue dans un ABR.
 *   C'est une classe generique qui va stocker une cle et la donnee elle meme.
 */
public class DonneeGenerique<T> implements Comparable {
    String cle;
    T valeur;

    public DonneeGenerique(T valeur) {
        this.valeur = valeur;
        this.cle = valeur.toString();//this.normaliser(valeur.toString());
    }

    private String normaliser(String s) {
        s = s.toLowerCase();
        String strTemp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(strTemp).replaceAll("");
    }

    @Override
    public int compareTo(Object o) {
        return this.cle.compareTo(((DonneeGenerique) o).cle);
    }

    @Override
    public String toString() {
        return this.valeur.toString();
    }
}
