package tp3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HachageDoubleTest {
	
//	Remplacer TYPE1 par une classe issue de votre modélisation représentant une donnée chaîne de caractères
	public class StringData extends DonneeGenerique<String> {

		public StringData(String donnee) {
			super(donnee);
		}
	}
	

//  Remplacer TYPE2 par une classe issue de votre modélisation représentant une table de Hachage avec gestion de collisions par hachage double et contenant des données sous forme de chaînes de caractères	
	public class HachageDoubleString extends HachageDouble<String> {

		public HachageDoubleString(int m, int k) {
			super(m, k);
		}
	}
	
	@Test
	public void testH(){

		HachageDoubleString hls = new HachageDoubleString(100, 7);
		assertEquals(98, hls.h(new StringData("test")));

		hls = new HachageDoubleString(100, 7);
		assertEquals(13, hls.h(new StringData("hachage")));

		hls = new HachageDoubleString(5, 3);
		assertEquals(3, hls.h(new StringData("test")));
		assertEquals(3, hls.h(new StringData("hachage")));
		assertEquals(4, hls.h(new StringData("ceri")));
		assertEquals(4, hls.h(new StringData("algorithmique")));
		assertEquals(1, hls.h(new StringData("avancée")));
		
	}
	
	@Test
	public void testAddAndToString(){
		
		HachageDoubleString hls = new HachageDoubleString(5, 3);
		assertEquals("", hls.toString());

		hls.add(new StringData("test"));
		assertEquals("3 : test\n", hls.toString());

		hls.add(new StringData("ceri"));
		assertEquals("3 : test\n4 : ceri\n", hls.toString());

		hls.add(new StringData("hachage"));
		assertEquals("1 : hachage\n3 : test\n4 : ceri\n", hls.toString());

	}
	
	
	@Test
	public void testRecherche(){
		
		HachageDoubleString hcs = new HachageDoubleString(5, 3);
		assertEquals(false, hcs.recherche(new StringData("test")));

		hcs.add(new StringData("test"));
		assertEquals(true, hcs.recherche(new StringData("test")));

		hcs.add(new StringData("avancée"));
		assertEquals(true, hcs.recherche(new StringData("test")));
		assertEquals(true, hcs.recherche(new StringData("avancée")));
		
	}

}
