package tp3;

import static org.junit.Assert.*;

import org.junit.Test;

public class HachageLineaireTest {
	
//	Remplacer TYPE1 par une classe issue de votre modélisation représentant une donnée chaîne de caractères
	public class StringData extends DonneeGenerique<String> {

		public StringData(String donnee) {
			super(donnee);
		}
	}
	

//  Remplacer TYPE2 par une classe issue de votre modélisation représentant une table de Hachage avec gestion de collisions par hachage linéaire et contenant des données sous forme de chaînes de caractères	
	public class HachageLineaireString extends HachageLineaire<String>{

		public HachageLineaireString(int m) {
			super(m);
		}
	}
	
	@Test
	public void testH(){

		HachageLineaireString hls = new HachageLineaireString(100);
		assertEquals(98, hls.h(new StringData("test")));

		hls = new HachageLineaireString(100);
		assertEquals(13, hls.h(new StringData("hachage")));

		hls = new HachageLineaireString(5);
		assertEquals(3, hls.h(new StringData("test")));
		assertEquals(3, hls.h(new StringData("hachage")));
		assertEquals(4, hls.h(new StringData("ceri")));
		assertEquals(4, hls.h(new StringData("algorithmique")));
		assertEquals(1, hls.h(new StringData("avancée")));
		
	}
	
	@Test
	public void testAddAndToString(){
		
		HachageLineaireString hls = new HachageLineaireString(5);
		assertEquals("", hls.toString());

		hls.add(new StringData("test"));
		assertEquals("3 : test\n", hls.toString());

		hls.add(new StringData("ceri"));
		assertEquals("3 : test\n4 : ceri\n", hls.toString());

		hls.add(new StringData("hachage"));
		assertEquals("0 : hachage\n3 : test\n4 : ceri\n", hls.toString());

	}
	
	
	@Test
	public void testRecherche(){
		
		HachageLineaireString hcs = new HachageLineaireString(5);
		assertEquals(false, hcs.recherche(new StringData("test")));

		hcs.add(new StringData("test"));
		assertEquals(true, hcs.recherche(new StringData("test")));

		hcs.add(new StringData("avancée"));
		assertEquals(true, hcs.recherche(new StringData("test")));
		assertEquals(true, hcs.recherche(new StringData("avancée")));
		
	}

}
