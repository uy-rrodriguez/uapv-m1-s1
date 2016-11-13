package tp2;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AVLTest {

//	Remplacer TYPE2 par une classe issue de votre modélisation représentant une donnée sous forme de chaîne de caractères
	public class StringData extends DonneeGenerique<String>{
		public StringData(String donnee) {
			super(donnee);
		}
	}

//  Remplacer TYPE4 par une classe issue de votre modélisation représentant un ABR contenant des données sous forme de chaînes de caractères	
	public class ArbreMot extends ABR<String>{
		
		public ArbreMot(StringData data) {
			super(data);
		}
	}
	
	@Test
	public void rotationDroite(){

		ArbreMot d = new ArbreMot(new StringData("d")); 
		d.insertionFeuille(new StringData("b"));
		d.insertionFeuille(new StringData("a"));
		d.insertionFeuille(new StringData("c"));
		d.insertionFeuille(new StringData("e"));

		Object b = d.recherche("b"); 
				
		d.rotationDroite();
		
		assertEquals(d.pere, b);
		
		// Ajouter vos tests
		
	}
	
	@Test
	public void rotationGauche(){
		
		ArbreMot b = new ArbreMot(new StringData("b")); 
		b.insertionFeuille(new StringData("a"));
		b.insertionFeuille(new StringData("d"));
		b.insertionFeuille(new StringData("c"));
		b.insertionFeuille(new StringData("e"));

		Object d = b.recherche("d"); 
				
		b.rotationGauche();
		
		assertEquals(b.pere, d);
		
		// Ajouter vos tests
		
	}
	
	@Test
	public void rotationGaucheDroite(){

		ABR<String> racine = new ArbreMot(new StringData("f"));
		
		racine.insertionFeuille(new StringData("b"));
		racine.insertionFeuille(new StringData("a"));
		racine.insertionFeuille(new StringData("d"));
		racine.insertionFeuille(new StringData("c"));
		racine.insertionFeuille(new StringData("e"));
		racine.insertionFeuille(new StringData("g"));
		
		int h1 = racine.hauteur();
		
		racine.rotationGaucheDroite();
		racine = racine.pere;
		
		assertEquals(h1, racine.hauteur() + 1);
		
		// Ajouter vos tests
		
	}
	
	@Test
	public void rotationDroiteGauche(){

		ABR<String> racine = new ArbreMot(new StringData("b"));
		
		racine.insertionFeuille(new StringData("a"));
		racine.insertionFeuille(new StringData("f"));
		racine.insertionFeuille(new StringData("d"));
		racine.insertionFeuille(new StringData("c"));
		racine.insertionFeuille(new StringData("e"));
		racine.insertionFeuille(new StringData("g"));
		
		int h1 = racine.hauteur();
		
		racine.rotationDroiteGauche();
		racine = racine.pere;
		
		assertEquals(h1, racine.hauteur() + 1);
		
		// Ajouter vos tests
		
	}
	
	@Test
	public void desequilibre(){

		ArbreMot b = new ArbreMot(new StringData("b")); 
		b.insertionFeuille(new StringData("a"));
		b.insertionFeuille(new StringData("f"));
		b.insertionFeuille(new StringData("d"));
		b.insertionFeuille(new StringData("c"));
		b.insertionFeuille(new StringData("e"));
		b.insertionFeuille(new StringData("g"));
		
		assertEquals(-2, b.desequilibre());
		
		// Ajouter vos tests
		
	}
	
	@Test
	public void reequilibrage(){

		ArbreMot d = new ArbreMot(new StringData("d")); 
		d.insertionFeuille(new StringData("c"));
		d.insertionFeuille(new StringData("b"));
		d.insertionFeuille(new StringData("a"));
		d.insertionFeuille(new StringData("e"));
		
		int h1 = d.hauteur();
		
		d.reequilibrage();
		
		assertEquals(h1, d.hauteur() + 1);
		
		// Ajouter vos tests
		
		
	}
	
	@Test
	public void insertionFeuilleEquilibre(){
		
		ABR<String> racine = new ABR<String>(new StringData("f")); 
		racine.insertionFeuilleEquilibre(new StringData("e"));
		racine.insertionFeuilleEquilibre(new StringData("d"));
		racine = racine.pere;
		
		racine.insertionFeuilleEquilibre(new StringData("c"));
		racine.insertionFeuilleEquilibre(new StringData("b"));		
		racine.insertionFeuilleEquilibre(new StringData("a"));
		racine = racine.pere;
		
		ArbreMot f2 = new ArbreMot(new StringData("f")); 
		f2.insertionFeuille(new StringData("e"));
		f2.insertionFeuille(new StringData("d"));
		f2.insertionFeuille(new StringData("c"));
		f2.insertionFeuille(new StringData("b"));
		f2.insertionFeuille(new StringData("a"));
		
		// Les arbres contiennent les même éléments
		assertEquals(racine.toString(), f2.toString());
		assertEquals(f2.hauteur(), racine.hauteur() + 3);
		
		// Ajouter vos tests
	}

}
