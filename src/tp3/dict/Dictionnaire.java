package tp3.dict;

import tp3.Hachage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tp3.DonneeGenerique;

public abstract class Dictionnaire {
	protected int m;
	protected Hachage<String> table;
	
	// Le nombre moyen de comparaisons pour trouver un mot existant dans le dictionnaire
	private int comparaisonsTrouverMot;
	
	// Le nombre moyen de comparaisons pour determiner qu’un mot n’existe pas dans le dictionnaire
	private int comparaisonsMotInexistant;
	
	// Le nombre de comparaisons total
	private int comparaisonsTotal;
	
	// Le temps d’execution. 
	private long tempsExecution;
	
	
	public Dictionnaire(int m) {
		this.m = m;
		table = null;
		comparaisonsTrouverMot = -1;
		comparaisonsMotInexistant = -1;
		comparaisonsTotal = -1;
		tempsExecution = -1;
	}
	
	public void load(String pathDict) {
		try {
	        InputStream is = getClass().getResourceAsStream(pathDict);
	        BufferedReader buff = new BufferedReader(
	                new InputStreamReader(is, "ISO-8859-1"));
	        
			String ligne;
			while ((ligne = buff.readLine()) != null) {
				table.add(new DonneeGenerique<String>(ligne));
			}
			
			buff.close();
		}
		catch (IOException e) {
			System.out.println("Erreur lors de la lecture du fichier '" + pathDict + "'. " + e.getMessage());
		}
	}
	
	public boolean testText(String pathText) {
		long start = System.currentTimeMillis();
		
		try {
	        InputStream is = getClass().getResourceAsStream(pathText);
	        Scanner sc = new Scanner(is);
			
	        Pattern exclure = Pattern.compile("[0-9]");
	        Matcher matcher;
	        
			while (sc.hasNext()) {
				String mot = sc.next();
				mot = mot.toLowerCase();
				
				// On ignore les mots avec des chiffres
				matcher = exclure.matcher(mot);
				
				if (! matcher.find() && ! table.recherche(new DonneeGenerique<String>(mot))) {
					System.out.println("'" + mot + "' n'existe pas !");
				}
			}
			
			/*
			
	        
			while (sc.hasNext()) {
				String mot = sc.next();
				
				// Il faut supprimer tous ce qui est point, virgule, exclamation, etc.
				mot = mot.replaceAll("[,\\.;!?:]$", "");
				
				
				if (mot.length() >= 3 && ) {
					System.out.println(mot);
					
					if (! table.recherche(new DonneeGenerique<String>(mot))) {
						System.out.println("'" + mot + "' n'existe pas !");
						//return false;
					}
				}
			}
			*/
			
			sc.close();
			is.close();
		}
		catch (IOException e) {
			System.out.println("Erreur lors de la lecture du fichier '" + pathText + "'. " + e.getMessage());
			return false;
		}
		
		this.tempsExecution = System.currentTimeMillis() - start;
		return true;
	}
	
	public String toString() {
		return "Resultat m = " + m + "\n"
				+ "\t Moyenne comparaisons trouver mot       " + comparaisonsTrouverMot + "\n"
				+ "\t Moyenne comparaisons mot inexistant    " + comparaisonsMotInexistant + "\n"
				+ "\t Nombre comparaisons total              " + comparaisonsTotal + "\n"
				+ "\t Temps d'execution                      " + tempsExecution + "\n";
	}
}
