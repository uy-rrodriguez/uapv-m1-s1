package tp4;

import java.util.ArrayList;
import java.util.List;

public class WebPage {

	private String adresse;
	private double rank;
	private List<WebPage> inputArc; 	// Pages menant à celle-ci
	private List<WebPage> outputArc; 	// Pages accesibles à partir de celle-ci
	
	/*
	 * Getters et Setters
	 */
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public double getRank() {
		return rank;
	}

	public void setRank(double rank) {
		this.rank = rank;
	}

	public List<WebPage> getInputArc() {
		return inputArc;
	}

	public void setInputArc(List<WebPage> inputArc) {
		this.inputArc = inputArc;
	}

	public List<WebPage> getOutputArc() {
		return outputArc;
	}

	public void setOutputArc(List<WebPage> outputArc) {
		this.outputArc = outputArc;
	}

	
	/**
	 * Constructeur
	 * 
	 * @param adresse
	 */
	public WebPage(String adresse) {
		super();
		this.rank = -1;
		this.adresse = adresse;
		inputArc = new ArrayList<>();
		outputArc = new ArrayList<>();
	}
	
}
