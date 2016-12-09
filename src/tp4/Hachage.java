package tp4;

public abstract class Hachage<T> {
	public int m; 					// Max elements dans la table
	protected Object tableau[];		// Table contenant les elements
	
	public Hachage(int m) {
		this.m = m;
		this.tableau = new Object[m];
	}

	public abstract int h(DonneeGenerique<T> d);
	public abstract void add(DonneeGenerique<T> d);
	public abstract boolean recherche(DonneeGenerique<T> d);
}
