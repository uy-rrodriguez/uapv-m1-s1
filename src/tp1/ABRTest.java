package tp1;

import static org.junit.Assert.*;

import org.junit.Test;


public class ABRTest {

	// Remplacer TYPE1 par une classe issue de votre modélisation représentant une donnée entière
	public class IntData extends DonneeGenerique<Integer> {
		public IntData(Integer donnee) {
			super(donnee);
		}
	}

	// Remplacer TYPE2 par une classe issue de votre modélisation représentant une donnée sous forme de chaîne de caractères
	public class StringData extends DonneeGenerique<String> {
		public StringData(String donnee) {
			super(donnee);
		}
	}

	// Remplacer TYPE3 par une classe issue de votre modélisation représentant un ABR contenant des données entières
	public class ArbreEntier extends ABR<Integer> {

		public ArbreEntier(IntData data) {
			super(data);
		}
	}

	// Remplacer TYPE4 par une classe issue de votre modélisation représentant un ABR contenant des données sous forme de chaînes de caractères
	public class ArbreMot extends ABR<String> {

		public ArbreMot(StringData data) {
			super(data);
		}
	}

	@Test
	public void insererFeuilleEtAffichageEntier(){

		/* Création d'un arbre contenant des entiers */
		ArbreEntier a1 = new ArbreEntier(new IntData(1));

		a1.insertionFeuille(new IntData(0));
		a1.insertionFeuille(new IntData(2));
		a1.insertionFeuille(new IntData(4));
		a1.insertionFeuille(new IntData(1));
		a1.insertionFeuille(new IntData(3));

		/* Test du contenu de l'arbre */
		assertEquals("0, 1, 2, 3, 4, ", a1.toString());

	}

	@Test
	public void insererFeuilleEtAffichageString(){

		/* Création d'un arbre contenant des chaînes de caractères */
		ArbreMot a2 = new ArbreMot(new StringData("test"));

		a2.insertionFeuille(new StringData("CERI"));
		a2.insertionFeuille(new StringData("alternance"));
		a2.insertionFeuille(new StringData("M1"));
		a2.insertionFeuille(new StringData("CERI"));
		a2.insertionFeuille(new StringData("algorithmique"));
		a2.insertionFeuille(new StringData("avancée"));

		/* Test du contenu de l'arbre */
		assertEquals("algorithmique, alternance, avancée, CERI, M1, test, ", a2.toString());

	}

	@Test
	public void recherche(){

		ArbreEntier a1 = new ArbreEntier(new IntData(1));

		IntData de2 = new IntData(2);
		a1.insertionFeuille(new IntData(0));
		a1.insertionFeuille(de2);
		a1.insertionFeuille(new IntData(4));
		a1.insertionFeuille(new IntData(3));

		/* Recherche dans l'arbre, une donnée de clé "2" */
		assertNotEquals(null, a1.recherche("2"));
		assertEquals(a1.recherche("2").donnee, de2);

	}

	@Test
	public void minMax(){

		ArbreEntier a1 = new ArbreEntier(new IntData(1));

		a1.insertionFeuille(new IntData(0));
		a1.insertionFeuille(new IntData(2));
		a1.insertionFeuille(new IntData(4));
		a1.insertionFeuille(new IntData(3));

		assertEquals(a1.minimum().donnee.cle, "0");
		assertEquals(a1.maximum().donnee.cle, "4");

	}

	@Test
	public void hauteur(){

		ArbreEntier a1 = new ArbreEntier(new IntData(1));
		assertEquals(a1.hauteur(), 1);

		a1.insertionFeuille(new IntData(2));
		assertEquals(a1.hauteur(), 2);
		a1.insertionFeuille(new IntData(4));
		assertEquals(a1.hauteur(), 3);

	}

	@Test
	public void suppression(){

		ArbreEntier a = new ArbreEntier(new IntData(1));

		a.insertionFeuille(new IntData(2));
		a.insertionFeuille(new IntData(4));
		a.insertionFeuille(new IntData(3));

		a.suppression("4");
		assertEquals("1, 2, 3, ", a.toString());
	}

    
	@Test
	public void equivalent(){

		ArbreEntier a1 = new ArbreEntier(new IntData(1));

		a1.insertionFeuille(new IntData(2));
		a1.insertionFeuille(new IntData(4));
		a1.insertionFeuille(new IntData(3));

		ArbreEntier a2 = new ArbreEntier(new IntData(4));

		a2.insertionFeuille(new IntData(3));
		a2.insertionFeuille(new IntData(1));
		a2.insertionFeuille(new IntData(2));

		assertTrue(a1.equivalent(a2));

	}

	@Test
	public void contenueDans(){

		ArbreEntier a1 = new ArbreEntier(new IntData(1));

		a1.insertionFeuille(new IntData(2));

		ArbreEntier a2 = new ArbreEntier(new IntData(4));

		a2.insertionFeuille(new IntData(3));
		a2.insertionFeuille(new IntData(1));
		a2.insertionFeuille(new IntData(2));

		assertTrue(a1.contenuDans(a2));

	}

	/*
	@Test
	public void fusion(){

		ArbreEntier a1 = new ArbreEntier(new IntData(1));

		a1.insertionFeuille(new IntData(2));
		a1.insertionFeuille(new IntData(4));
		a1.insertionFeuille(new IntData(3));

		ArbreEntier a2 = new ArbreEntier(new IntData(4));

		a2.insertionFeuille(new IntData(8));
		a2.insertionFeuille(new IntData(5));
		a2.insertionFeuille(new IntData(1));

		a1.fusion(a2);

		assertEquals("1, 2, 3, 4, 5, 8, ", a1.toString());

	}
    */
}
