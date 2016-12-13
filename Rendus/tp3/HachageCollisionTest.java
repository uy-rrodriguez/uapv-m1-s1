package tp3;

import static org.junit.Assert.*;

import org.junit.Test;

public class HachageCollisionTest {
	
//	Remplacer TYPE1 par une classe issue de votre modélisation représentant une donnée chaîne de caractères
	public class StringData extends DonneeGenerique<String> {

		public StringData(String donnee) {
			super(donnee);
		}
	}
	

//  Remplacer TYPE2 par une classe issue de votre modélisation représentant une table de Hachage avec gestion de données par collision et contenant des données sous forme de chaînes de caractères	
	public class HachageCollisionString extends HachageCollision<String> {

		public HachageCollisionString(int m, int a) {
			super(m, a);
		}
	}
	
	@Test
	public void testH() {

		HachageCollisionString hcs = new HachageCollisionString(100, 2);
		assertEquals(6, hcs.h(new StringData("test")));

		hcs = new HachageCollisionString(100, 3);
		assertEquals(9, hcs.h(new StringData("hachage")));

		hcs = new HachageCollisionString(5, 3);
		assertEquals(1, hcs.h(new StringData("test")));
		assertEquals(4, hcs.h(new StringData("hachage")));
		assertEquals(3, hcs.h(new StringData("CERI")));
		assertEquals(2, hcs.h(new StringData("algorithmique")));
		assertEquals(1, hcs.h(new StringData("avancée")));
		
	}
	
	@Test
	public void testAddAndGetListSize(){
		
		HachageCollisionString hcs = new HachageCollisionString(5, 3);
		//assertEquals("5 entrée(s) contenant 0 élément(s)\n", hcs.getListsSize());

		hcs.add(new StringData("test"));
		assertEquals("4 entrée(s) contenant 0 élément(s)\n1 entrée(s) contenant 1 élément(s)\n", hcs.getListsSize());

		hcs.add(new StringData("test"));
		assertEquals("4 entrée(s) contenant 0 élément(s)\n1 entrée(s) contenant 1 élément(s)\n", hcs.getListsSize());

		hcs.add(new StringData("hachage"));
		assertEquals("3 entrée(s) contenant 0 élément(s)\n2 entrée(s) contenant 1 élément(s)\n", hcs.getListsSize());

		hcs.add(new StringData("avancée"));
		assertEquals("3 entrée(s) contenant 0 élément(s)\n1 entrée(s) contenant 1 élément(s)\n1 entrée(s) contenant 2 élément(s)\n", hcs.getListsSize());

	}
	
	@Test
	public void testRecherche(){
		
		HachageCollisionString hcs = new HachageCollisionString(5, 3);
		assertEquals(false, hcs.recherche(new StringData("test")));

		hcs.add(new StringData("test"));
		assertEquals(true, hcs.recherche(new StringData("test")));

		hcs.add(new StringData("avancée"));
		assertEquals(true, hcs.recherche(new StringData("test")));
		assertEquals(true, hcs.recherche(new StringData("avancée")));
		
	}

}
