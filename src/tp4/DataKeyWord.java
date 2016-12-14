package tp4;

import java.util.ArrayList;
import java.util.List;

public class DataKeyWord extends DonneeGenerique<String> {

	private List<String> webpages; // Ensemble de pages où ce mot-clé est mentioné
	
	/*
	 * Getters et Setters
	 */
	public List<String> getWebpages() {
		return webpages;
	}

	public void setWebpages(List<String> webpages) {
		this.webpages = webpages;
	}

	
	/**
	 * Constructeur
	 * 
	 * @param valeur
	 */
	public DataKeyWord(String valeur) {
		super(valeur);
		webpages = new ArrayList<>();
	}

}
