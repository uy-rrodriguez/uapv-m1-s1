package tp4;

import java.util.HashMap;
import java.util.List;

public class SearchEngine {

	HashMap<String, WebPage> allPages; // Ensemble des pages web dans new.txt, index́ees par leur adresse
	MyDataStructure allKeywords; // Ensemble des mots-cles figurant dans index_small.txt
	String pathNewTxt;
	String pathIndexTxt;
	
	public SearchEngine(String pathNewTxt, String pathIndexTxt) {
		this.pathNewTxt = pathNewTxt;
		this.pathIndexTxt = pathIndexTxt;
	}
	
	/**
	 * Effectue la lecture du fichier contenant les pages webs (et donc le remplissage de la
	 * HashMap)
	 */
	public void readWebPages() {}
	
	/**
	 *  Effectue la lecture du fichier contenant les mots-clés (et donc le remplissage de
	 *  l’attribut MyDataStructure)
	 */
	public void readKeyWord() {}
	
	/**
	 * Calcule le graphe associé à la recherche repŕesentée par les mots-clés de la liste passée
	 * en argument
	 * @return
	 */
	public HashMap<String, WebPage> getSearchSubGraph(List<String> keywords) {
		return null;
	}
	
	/**
	 * Effectue le calcul du rang de chaque page passée	en argument en fonction de la recherche
	 * (l’argument List<String> contient les mots-clés de la recherche)
	 * @param webpages
	 * @param keywords
	 */
	public void computeRanks(List<WebPage> webpages, List<String> keywords) {}
}
