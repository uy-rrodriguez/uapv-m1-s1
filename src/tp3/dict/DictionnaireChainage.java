package tp3.dict;

import tp3.HachageCollision;

public class DictionnaireChainage extends Dictionnaire {
	
	public DictionnaireChainage(int m, int a) {
		super(m);
		this.table = new HachageCollision<String>(m, a);
	}

}
