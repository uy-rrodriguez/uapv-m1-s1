package tp4;

public abstract class Hachage<T> {
	public int m; 					// Max elements dans la table
	protected Object tableau[];		// Table contenant les elements
	
	public Hachage(int m) {
		this.m = m;
		this.tableau = new Object[m];
	}

	public abstract int h(T d);
	public abstract void add(T d);
	public abstract boolean recherche(T d);
}
