package tp4;

import java.util.ArrayList;
import java.util.List;

public class WebPage {

	String adresse;
	double rang;
	List<WebPage> referrers; // Pages menant à celle-ci
	List<WebPage> links; 	 // Pages accesibles à partir de celle-ci
	
	public WebPage(String adresse) {
		super();
		this.adresse = adresse;
		referrers = new ArrayList<>();
		links = new ArrayList<>();
	}
	
}
