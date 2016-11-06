package tp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * On souhaite appliquer la classe ABR au stockage des mots d’un dictionnaire de la langue.
 * On stockera ces mots sous la forme d’un ABR pour faciliter la recherche ultérieure.
 * Le dictionnaire est donné par le ﬁchier "francais.mots".
 *  
 * @author Ricci
 *
 */
public class Dico {
	 
	private ABR<StringData> abr = null;
	
	private class StringData extends DonneeGenerique<String> {
		public StringData(String donnee) {
			super(donnee);
		}
	}
	
	/**
	 * Cree un dictionnaire vide.
	 * 
	 */
	public Dico() {
		// On crée un abr avec un mot et puis on supprime le mot pour avoir l'ABR vide
		abr = new ABR<>(new StringData("temp"));
		abr.suppression("temp");
	}
	
	/**
	 * Cree un dictionnaire a partir d'un fichier avec des mots.
	 * Utilise "ISO-8859-1" comme encoding par defaut.
	 * 
	 * @param pathFichier
	 */
	public Dico(String pathFichier) {
		this(pathFichier, "ISO-8859-1");
	}
	
	/**
	 * Cree un dictionnaire a partir d'un fichier avec les mots.
	 * 
	 * @param pathFichier
	 */
	public Dico(String pathFichier, String encoding) {
		try {
	        InputStream is = getClass().getResourceAsStream(pathFichier);
	        BufferedReader buff = new BufferedReader(
	                new InputStreamReader(is, encoding));
	        
			String ligne = buff.readLine();
			if (ligne != null) {
				abr = new ABR<>(new StringData(ligne));
			}
			
			while ((ligne = buff.readLine()) != null) {
				abr.insertionFeuille(new StringData(ligne));
			}
			
			buff.close();
		}
		catch (IOException e) {
			System.out.println("Erreur lors de la lecture du fichier. " + e.getMessage());
		}
	}
	
	public boolean isEmpty() {
		return abr == null;
	}
	
	public void ajouterMot(String mot) {
		StringData data = new StringData(mot);
		
		if (! data.cle.equals("")) {
			abr.insertionFeuille(data);
		}
	}
	
	public void supprimerMot(String mot) {
		abr.suppression(mot);
	}
	
	/**
	 * @param autreDict
	 * @return
	 */
	public boolean contenuDans(Dico autreDict) {
		return abr.contenuDans(autreDict.abr);
	}
	
	/**
	 * @param autreDict
	 * @return
	 */
	public boolean equivalent(Dico autreDict) {
		return abr.equivalent(autreDict.abr);
	}
	
	/**
	 * @param autreDict
	 */
	public void fusion(Dico autreDict) {
		abr.fusion(autreDict.abr);
	}
	
	/**
	 * @return
	 */
	public int hauteur() {
		return abr.hauteur();
	}
	
	/**
	 * Retourne le maximum de l'ABR contenu.
	 * 
	 * @return
	 */
	public String dernierMot() {
		return abr.maximum().donnee.toString();
	}
	
	/**
	 * Retourne le minimum de l'ABR contenu.
	 * 
	 * @return
	 */
	public String premierMot() {
		return abr.minimum().donnee.toString();
	}
	
	/**
	 * Utilise la methode recherche d'un ABR pour savoir si un mot appartient a un dictionnaire.
	 * 
	 * @param mot
	 * @return
	 */
	public boolean existeMot(String mot) {
		return (abr.recherche(mot) != null);
	}
	
	/**
	 * Retourne le mot correspondant au mot recu. Cette methode sert a verifier que le mot a bien ete stockee.
	 * 
	 * @param mot
	 * @return
	 */
	public String recupererMot(String mot) {
		ABR<StringData> noeud = abr.recherche(mot);
		if (noeud != null)
			return (String) noeud.donnee.valeur;
		else
			return null;
	}
	
	public String toString() {
		return abr.toString();
	}
}
