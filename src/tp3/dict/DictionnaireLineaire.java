package tp3.dict;

import tp3.HachageLineaire;

public class DictionnaireLineaire extends Dictionnaire {
	
	public DictionnaireLineaire(int m) {
		super(m);
		this.table = new HachageLineaire<String>(m);
	}

}
