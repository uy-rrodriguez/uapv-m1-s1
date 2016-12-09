package tp4;

public class HachageDouble<T> extends Hachage<T> {
	
	private int k;
	private boolean plein;
	
	public HachageDouble(int m, int k) {
		super(m);
		this.k = k;
		this.plein = false;
	}

	@Override
	public int h(DonneeGenerique d) {
		return Math.abs(d.cle.hashCode()) % this.m;
	}

	@Override
	public void add(DonneeGenerique d) {
		if (!this.plein) {
			int h = this.h(d);
			
			int i = 0;
			boolean trouve = false;
			while (i < m && this.tableau[(h + k*i) % m] != null)
				i++;
			
			if (i < m)
				this.tableau[(h + k*i) % m] = d;
			else
				this.plein = true;
		}
	}

	@Override
	public boolean recherche(DonneeGenerique d) {
		int h = this.h(d);
		int i = 0;
		boolean trouve = false;
		
		while (i < m && !trouve) {
			Object o = this.tableau[(h + k*i) % m];
			if (o != null) {
				DonneeGenerique<T> d2 = (DonneeGenerique) o;
				if (d.compareTo(d2) == 0)
					trouve = true;
			}
			else
				return false;	// On peut faire ceci parce qu'il n'y a pas de suppression.
								// Il n'y a donc pas des cases vides entre la case h(d)
								// et la position actuel de l'element d dans le tableau.
								// Sinon il faudrait parcourir tout le tableau pour pouvoir
								// dire qu'il n'existe pas.
		}
			
		return trouve;
	}

	
	public String toString() {
		StringBuffer buff = new StringBuffer("");
		for (int i = 0; i < m; i++) {
			Object o = this.tableau[i];
			if (o != null) {
				DonneeGenerique<T> d = (DonneeGenerique) o;
				buff.append(i + " : " + d + "\n");
			}
		}
		
		return buff.toString();
	}
}
