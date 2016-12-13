package tp3.dict;

import tp3.HachageDouble;

public class DictionnaireDouble extends Dictionnaire {
	
	public DictionnaireDouble(int m, int k) {
		super(m);
		this.table = new HachageDouble<String>(m, k);
	}

}
