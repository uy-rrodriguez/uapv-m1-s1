package tp3.dict;

import static org.junit.Assert.*;

import org.junit.Test;

public class DictionnaireTest {
	private static final String DICT_FRANCAIS = "francais.mots.mixed";
	private static final String TEXTE_MONDE = "ArticleLeMonde.fr";
	private static final String TEXTE_MONDE_2 = "ArticleLeMondeModifie.fr";
	
	@Test
	public void testChainageA() {
		System.out.println("Test hash chainage; m = 300 * 10^3 (" + (300 * (10^3)) + "); a = 5.");
		Dictionnaire d = new DictionnaireChainage(300 * (10^3), 5);
		
		System.out.println("Chargement du dictionnaire");
		d.load(DICT_FRANCAIS);
		
		System.out.println("Test du fichier " + TEXTE_MONDE);
		boolean res = d.testText(TEXTE_MONDE);
		
		System.out.println("Resultat : " + res);
		
		System.out.println("Statistiques :");
		System.out.println(d.toString());
		
		System.out.println("--------------------------------------------------------------------\n");
	}
	
}
