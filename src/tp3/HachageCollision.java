package tp3;

import java.util.ArrayList;
import java.util.List;

public class HachageCollision<T> extends Hachage<T> {
	private int a;		// Entier utilise pour le calcul de la fonction de hachage
	
	public HachageCollision(int m, int a) {
		super(m);
		this.a = a;
	}

	@Override
	public int h(DonneeGenerique d) {
		int x = 0;
		for (int i = 0; i < d.cle.length(); i++) {
			Character c = d.cle.charAt(i);
			int t = c.hashCode();
			x += Math.pow(this.a, i) * t;
		}
		return x % this.m;
	}

	@Override
	public void add(DonneeGenerique d) {
		int h = this.h(d);
		Object o = this.tableau[h];
		if (o == null) {
			List l = new ArrayList<DonneeGenerique<T>>();
			l.add(d);
			this.tableau[h] = l;
		}
		else {
			List l = (List) o;
			
			for (int i = 0; i < l.size(); i++) {
				DonneeGenerique<T> d2 = (DonneeGenerique) l.get(i);
				if (d2.compareTo(d) == 0)
					return;
			}
			
			l.add(d);
		}
	}

	@Override
	public boolean recherche(DonneeGenerique d) {
		int h = this.h(d);
		Object o = this.tableau[h];
		
		if (o == null) {
			return false;
		}
		else {
			List l = (List) o;
			for (int i = 0; i < l.size(); i++) {
				DonneeGenerique<T> d2 = (DonneeGenerique) l.get(i);
				if (d2.compareTo(d) == 0)
					return true;
			}
		}
		
		return false;
	}

	
	public String getListsSize() {
		int compteurs[] = new int[m];
		for (int i = 0; i < m; i++)
			compteurs[i] = 0;
		
		for (int i = 0; i < m; i++) {
			int t = 0;
			
			Object o = this.tableau[i];
			if (o != null)
				t = ((List) o).size();
			
			compteurs[t]++;
		}
		
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < m; i++) {
			int q = compteurs[i];
			if (q > 0 || i == 0)
				buff.append(q + " entrée(s) contenant " + i + " élément(s)\n");
		}
		
		return buff.toString();
	}
}
