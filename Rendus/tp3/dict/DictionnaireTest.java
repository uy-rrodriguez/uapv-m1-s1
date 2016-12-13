package tp3.dict;


import org.junit.Test;

public class DictionnaireTest {
	private static final String DICT_FRANCAIS = "francais.mots.mixed";
	private static final String TEXTE_MONDE = "ArticleLeMonde.fr";
	private static final String TEXTE_MONDE_2 = "ArticleLeMondeModifie.fr";
	
	/* *********************************  CHAINAGE  ************************************************** */
	@Test
	public void testChainage300() {
		System.out.println("Test hash chainage; m = 300 * 10^3 (" + (300 * (10^3)) + "); a = 5.");
		Dictionnaire d = new DictionnaireChainage(300 * (10^3), 5);
		
		System.out.println("Chargement du dictionnaire");
		d.load(DICT_FRANCAIS);
		
		// LeMonde
		System.out.println("Test du fichier " + TEXTE_MONDE);
		boolean res = d.testText(TEXTE_MONDE);
		
		System.out.println("Resultat : " + res);
		
		System.out.println("Statistiques :");
		System.out.println(d.toString());
		System.out.println("");

		// LeMondeModifie
		System.out.println("Test du fichier " + TEXTE_MONDE_2);
		res = d.testText(TEXTE_MONDE_2);
		
		System.out.println("Resultat : " + res);
		
		System.out.println("Statistiques :");
		System.out.println(d.toString());
		
		System.out.println("--------------------------------------------------------------------\n");
	}
	
	@Test
	public void testChainage400() {
		System.out.println("Test hash chainage; m = 400 * 10^3 (" + (400 * (10^3)) + "); a = 5.");
		Dictionnaire d = new DictionnaireChainage(400 * (10^3), 5);
		
		System.out.println("Chargement du dictionnaire");
		d.load(DICT_FRANCAIS);
		
		// LeMonde
		System.out.println("Test du fichier " + TEXTE_MONDE);
		boolean res = d.testText(TEXTE_MONDE);
		
		System.out.println("Resultat : " + res);
		
		System.out.println("Statistiques :");
		System.out.println(d.toString());
		System.out.println("");

		// LeMondeModifie
		System.out.println("Test du fichier " + TEXTE_MONDE_2);
		res = d.testText(TEXTE_MONDE_2);
		
		System.out.println("Resultat : " + res);
		
		System.out.println("Statistiques :");
		System.out.println(d.toString());
		
		System.out.println("--------------------------------------------------------------------\n");
	}
	
	@Test
	public void testChainage1000() {
		System.out.println("Test hash chainage; m = 1000 * 10^3 (" + (1000 * (10^3)) + "); a = 5.");
		Dictionnaire d = new DictionnaireChainage(1000 * (10^3), 5);
		
		System.out.println("Chargement du dictionnaire");
		d.load(DICT_FRANCAIS);
		
		// LeMonde
		System.out.println("Test du fichier " + TEXTE_MONDE);
		boolean res = d.testText(TEXTE_MONDE);
		
		System.out.println("Resultat : " + res);
		
		System.out.println("Statistiques :");
		System.out.println(d.toString());
		System.out.println("");

		// LeMondeModifie
		System.out.println("Test du fichier " + TEXTE_MONDE_2);
		res = d.testText(TEXTE_MONDE_2);
		
		System.out.println("Resultat : " + res);
		
		System.out.println("Statistiques :");
		System.out.println(d.toString());
		
		System.out.println("--------------------------------------------------------------------\n");
	}
	
	/* *********************************  LINEAIRE  ************************************************** */
	
	@Test
	public void testLineaire300() {
		System.out.println("Test hash lineaire; m = 300 * 10^3 (" + (300 * (10^3)) + ").");
		Dictionnaire d = new DictionnaireLineaire(300 * (10^3));
		
		System.out.println("Chargement du dictionnaire");
		d.load(DICT_FRANCAIS);
		
		// LeMonde
		System.out.println("Test du fichier " + TEXTE_MONDE);
		boolean res = d.testText(TEXTE_MONDE);
		
		System.out.println("Resultat : " + res);
		
		System.out.println("Statistiques :");
		System.out.println(d.toString());
		System.out.println("");

		// LeMondeModifie
		System.out.println("Test du fichier " + TEXTE_MONDE_2);
		res = d.testText(TEXTE_MONDE_2);
		
		System.out.println("Resultat : " + res);
		
		System.out.println("Statistiques :");
		System.out.println(d.toString());
		
		System.out.println("--------------------------------------------------------------------\n");
	}
	
	@Test
	public void testLineaire400() {
		System.out.println("Test hash lineaire; m = 400 * 10^3 (" + (400 * (10^3)) + ").");
		Dictionnaire d = new DictionnaireLineaire(400 * (10^3));
		
		System.out.println("Chargement du dictionnaire");
		d.load(DICT_FRANCAIS);
		
		// LeMonde
		System.out.println("Test du fichier " + TEXTE_MONDE);
		boolean res = d.testText(TEXTE_MONDE);
		
		System.out.println("Resultat : " + res);
		
		System.out.println("Statistiques :");
		System.out.println(d.toString());
		System.out.println("");

		// LeMondeModifie
		System.out.println("Test du fichier " + TEXTE_MONDE_2);
		res = d.testText(TEXTE_MONDE_2);
		
		System.out.println("Resultat : " + res);
		
		System.out.println("Statistiques :");
		System.out.println(d.toString());
		
		System.out.println("--------------------------------------------------------------------\n");
	}
	
	@Test
	public void testLineaire1000() {
		System.out.println("Test hash lineaire; m = 1000 * 10^3 (" + (1000 * (10^3)) + ").");
		Dictionnaire d = new DictionnaireLineaire(1000 * (10^3));
		
		System.out.println("Chargement du dictionnaire");
		d.load(DICT_FRANCAIS);
		
		// LeMonde
		System.out.println("Test du fichier " + TEXTE_MONDE);
		boolean res = d.testText(TEXTE_MONDE);
		
		System.out.println("Resultat : " + res);
		
		System.out.println("Statistiques :");
		System.out.println(d.toString());
		System.out.println("");

		// LeMondeModifie
		System.out.println("Test du fichier " + TEXTE_MONDE_2);
		res = d.testText(TEXTE_MONDE_2);
		
		System.out.println("Resultat : " + res);
		
		System.out.println("Statistiques :");
		System.out.println(d.toString());
		
		System.out.println("--------------------------------------------------------------------\n");
	}
	
	
	/* *********************************  DOUBLE  **************************************************** */
	
	@Test
	public void testDouble300() {
		System.out.println("Test hash double; m = 300 * 10^3 (" + (300 * (10^3)) + "); k = 7.");
		Dictionnaire d = new DictionnaireDouble(300 * (10^3), 7);
		
		System.out.println("Chargement du dictionnaire");
		d.load(DICT_FRANCAIS);
		
		// LeMonde
		System.out.println("Test du fichier " + TEXTE_MONDE);
		boolean res = d.testText(TEXTE_MONDE);
		
		System.out.println("Resultat : " + res);
		
		System.out.println("Statistiques :");
		System.out.println(d.toString());
		System.out.println("");

		// LeMondeModifie
		System.out.println("Test du fichier " + TEXTE_MONDE_2);
		res = d.testText(TEXTE_MONDE_2);
		
		System.out.println("Resultat : " + res);
		
		System.out.println("Statistiques :");
		System.out.println(d.toString());
		
		System.out.println("--------------------------------------------------------------------\n");
	}
	
	@Test
	public void testDouble400() {
		System.out.println("Test hash double; m = 400 * 10^3 (" + (400 * (10^3)) + "); k = 7.");
		Dictionnaire d = new DictionnaireDouble(400 * (10^3), 7);
		
		System.out.println("Chargement du dictionnaire");
		d.load(DICT_FRANCAIS);
		
		// LeMonde
		System.out.println("Test du fichier " + TEXTE_MONDE);
		boolean res = d.testText(TEXTE_MONDE);
		
		System.out.println("Resultat : " + res);
		
		System.out.println("Statistiques :");
		System.out.println(d.toString());
		System.out.println("");

		// LeMondeModifie
		System.out.println("Test du fichier " + TEXTE_MONDE_2);
		res = d.testText(TEXTE_MONDE_2);
		
		System.out.println("Resultat : " + res);
		
		System.out.println("Statistiques :");
		System.out.println(d.toString());
		
		System.out.println("--------------------------------------------------------------------\n");
	}
	
	@Test
	public void testDouble1000() {
		System.out.println("Test hash double; m = 1000 * 10^3 (" + (1000 * (10^3)) + "); k = 7.");
		Dictionnaire d = new DictionnaireDouble(1000 * (10^3), 7);
		
		System.out.println("Chargement du dictionnaire");
		d.load(DICT_FRANCAIS);
		
		// LeMonde
		System.out.println("Test du fichier " + TEXTE_MONDE);
		boolean res = d.testText(TEXTE_MONDE);
		
		System.out.println("Resultat : " + res);
		
		System.out.println("Statistiques :");
		System.out.println(d.toString());
		System.out.println("");

		// LeMondeModifie
		System.out.println("Test du fichier " + TEXTE_MONDE_2);
		res = d.testText(TEXTE_MONDE_2);
		
		System.out.println("Resultat : " + res);
		
		System.out.println("Statistiques :");
		System.out.println(d.toString());
		
		System.out.println("--------------------------------------------------------------------\n");
	}
	
}
