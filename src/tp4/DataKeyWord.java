package tp4;

import java.util.ArrayList;
import java.util.List;

public class DataKeyWord extends DonneeGenerique<String> {

	List<String> pages; // Ensemble de pages où ce mot-clé est mentioné
	
	public DataKeyWord(String valeur) {
		super(valeur);
		pages = new ArrayList<>();
	}

}
