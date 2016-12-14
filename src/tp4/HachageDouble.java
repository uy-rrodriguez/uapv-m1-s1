package tp4;

public class HachageDouble<T extends DonneeGenerique> extends Hachage<T> {
	
	private int k;
	private boolean plein;
	
	public HachageDouble(int m, int k) {
		super(m);
		this.k = k;
		this.plein = false;
	}

	@Override
	public int h(T d) {
		return Math.abs(d.cle.hashCode()) % this.m;
	}

	@Override
	public void add(T d) {
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
	public boolean recherche(T d) {
		int h = this.h(d);
		int i = 0;
		boolean trouve = false;
		
		while (i < m && !trouve) {
			Object o = this.tableau[(h + k*i) % m];
			if (o != null) {
				T d2 = (T) o;
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
	
	public T get(T d) {
		int h = this.h(d);
		int i = 0;
		
		while (i < m) {
			Object o = this.tableau[(h + k*i) % m];
			if (o != null) {
				T d2 = (T) o;
				if (d.compareTo(d2) == 0) {
					return d2;
				}
			}
			else
				return null;	// On peut faire ceci parce qu'il n'y a pas de suppression.
								// Il n'y a donc pas des cases vides entre la case h(d)
								// et la position actuel de l'element d dans le tableau.
								// Sinon il faudrait parcourir tout le tableau pour pouvoir
								// dire qu'il n'existe pas.
		}
		
		return null;
	}

	
	public String toString() {
		StringBuffer buff = new StringBuffer("");
		for (int i = 0; i < m; i++) {
			Object o = this.tableau[i];
			if (o != null) {
				T d = (T) o;
				buff.append(i + " : " + d + "\n");
			}
		}
		
		return buff.toString();
	}
}
