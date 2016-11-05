package tp1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class DicoTest {

	//private static final String DICT_FRANCAIS = "francais.mots.mixed";
	private static final String DICT_FRANCAIS = "francais.mots";
	
	private void log(String texte) {
		System.out.println(texte);
	}
	
	@Rule
	public TestName nom = new TestName();
	
	@Before
	public void setUp() throws Exception {
		log("---------- " + nom.getMethodName() + " ----------");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("_________________________________________________\n");
	}
	
	
	@Test
	public void testIsEmpty() {
		log("Creation du dictionnaire avec un fichier.");
		Dico d = new Dico(DICT_FRANCAIS);
		assertFalse(d.isEmpty());
	}

	@Test
	public void testAjouterMot() {
		log("Creation du dictionnaire avec un fichier.");
		Dico d = new Dico(DICT_FRANCAIS);
		
		log("Test de mot inexistant");
		String mot = "nexistepas";
		assertFalse(d.existeMot(mot));
		
		log("Ajout du mot et nouveau test");
		d.ajouterMot(mot);
		assertTrue(d.existeMot(mot));
		assertEquals(mot, d.recupererMot(mot));
	}

	@Test
	public void testSupprimerMot() {
		log("Creation du dictionnaire avec un fichier.");
		Dico d = new Dico(DICT_FRANCAIS);
		
		log("Test de mot existant");
		String mot = "lapin"; 
		assertTrue(d.existeMot(mot));
		assertEquals("lapin", d.recupererMot(mot));
		
		log("Suppression du mot et nouveau test");
		d.supprimerMot(mot);
		assertFalse(d.existeMot(mot));
		assertNull(d.recupererMot(mot));
	}

	@Test
	public void testContenuDans() {
		log("Creation du dictionnaire D1 avec un fichier.");
		Dico d1 = new Dico(DICT_FRANCAIS);
		
		log("Creation d'un autre dictionnaire D2 vide et ajout de mots");
		Dico d2 = new Dico();
		d2.ajouterMot("lapin");
		d2.ajouterMot("arbre");
		
		log("Test D2 cree correctement");
		assertEquals("arbre, lapin, ", d2.toString());
		
		log("Creation d'un troisieme dictionnaire D3 et ajout d'un mot inexistant dans D1");
		Dico d3 = new Dico();
		d3.ajouterMot("nexistepas");
		
		log("Test D3 contenu dans D1");
		assertFalse(d3.contenuDans(d1));
		
		log("Test D2 contenu dans D1");
		assertTrue(d2.contenuDans(d1));
	}

	@Test
	public void testEquivalent() {
		log("Creation du dictionnaire D1 avec un fichier.");
		Dico d1 = new Dico(DICT_FRANCAIS);
		
		log("Creation d'un autre dictionnaire D2 avec le meme fichier.");
		Dico d2 = new Dico(DICT_FRANCAIS);
		
		log("Test equivalents");
		assertTrue(d1.equivalent(d2));
		
		log("Suppresion d'un mot dans D2 et test d'equivalents");
		String mot = "lapin";
		d2.supprimerMot(mot);
		assertFalse(d1.equivalent(d2));
		
		log("Ajout du mot supprime et nouveau test");
		d2.ajouterMot(mot);
		assertTrue(d2.equivalent(d1));
	}

	@Test
	public void testFusion() {
		log("Creation du dictionnaire D1 avec un fichier.");
		Dico d1 = new Dico(DICT_FRANCAIS);
		
		log("Creation d'un autre dictionnaire D2 vide et ajout d'un mot inexistant dans D1.");
		Dico d2 = new Dico();
		String mot = "nexistepas";
		d2.ajouterMot(mot);
		
		log("Fusion des deux dictionnaires");
		d1.fusion(d2);
		
		log("Test D1 contient le nouveau mot");
		assertTrue(d1.existeMot(mot));
		
		log("Test D2 contenu dans D1");
		assertTrue(d2.contenuDans(d1));
	}

	@Test
	public void testHauteur() {
		log("Creation du dictionnaire vide et ajout de mots.");
		Dico d = new Dico();
		d.ajouterMot("lapin");
		
		log("Test d'hauteur avec un seul mot");
		assertEquals(1, d.hauteur());
		
		log("Test d'hauteur avec deux mots");
		d.ajouterMot("pierre");
		assertEquals(2, d.hauteur());
		
		log("Test d'hauteur avec trois mots");
		d.ajouterMot("zut");
		assertEquals(3, d.hauteur());
	}

	@Test
	public void testDernierMot() {
		log("Creation du dictionnaire avec un fichier.");
		Dico d = new Dico(DICT_FRANCAIS);
		
		log("Test de dernier mot");
		assertEquals("zythums", d.dernierMot());
	}

	@Test
	public void testPremierMot() {
		log("Creation du dictionnaire avec un fichier.");
		Dico d = new Dico(DICT_FRANCAIS);
		
		log("Test de premier mot");
		assertEquals("a", d.premierMot());
	}

	@Test
	public void testToString() {
		log("Creation du dictionnaire vide et ajout de mots.");
		Dico d = new Dico();
		d.ajouterMot("lapin");
		d.ajouterMot("pierre");
		d.ajouterMot("manuel");
		
		log("Test de toString");
		String dicoString = d.toString();
		assertEquals("lapin, manuel, pierre, ", dicoString);
	}

}
