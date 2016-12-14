package tp4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class SearchEngine {

	protected HashMap<String, WebPage> webpages; 	// Ensemble des pages web dans new.txt, index́ees par leur adresse
	protected MyDataStructure keyWords; 			// Ensemble des mots-cles figurant dans index_small.txt
	protected String pathFilePages;
	protected String pathFileKeywords;
	
	/*
	 * Getters et Setters
	 */
	public HashMap<String, WebPage> getWebpages() {
		return webpages;
	}

	public void setWebpages(HashMap<String, WebPage> webPages) {
		this.webpages = webPages;
	}

	public MyDataStructure getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(MyDataStructure keyWords) {
		this.keyWords = keyWords;
	}

	public String getPathFilePages() {
		return pathFilePages;
	}

	public void setPathFilePages(String pathFilePages) {
		this.pathFilePages = pathFilePages;
	}

	public String getPathFileKeywords() {
		return pathFileKeywords;
	}

	public void setPathFileKeywords(String pathFileKeywords) {
		this.pathFileKeywords = pathFileKeywords;
	}

	
	/**
	 * Constructeur
	 * 
	 * @param pathFilePages
	 * @param pathFileKeywords
	 */
	public SearchEngine(String pathFilePages, String pathFileKeywords) {
		this.pathFilePages = pathFilePages;
		this.pathFileKeywords = pathFileKeywords;
		this.webpages = new HashMap<>();
		this.keyWords = new MyDataStructure(30 * 10 ^ 3, 7);
	}
	
	
	/**
	 * Effectue la lecture du fichier contenant les pages webs (et donc le remplissage de la
	 * HashMap)
	 */
	public void readWebPages() {
		try {

			InputStream is = getClass().getResourceAsStream(this.pathFilePages);
			Reader r = new InputStreamReader(is, "ISO-8859-1");
			BufferedReader buff = new BufferedReader(r);
			
			String line;
			String [] parts;
			
			Pattern fromRegex = Pattern.compile("^\\(from (https://|http://|./)(.*)\\)$");
			Pattern urlRegex = Pattern.compile("^(https://|http://|./)(.*)$");
			Matcher fromMatcher;
			
			// On ignore la premiere ligne
			buff.readLine();
			
			// Lecture des lignes restantes
			while ((line = buff.readLine()) != null) {
				LogUtil.debug("Traitement ligne");
				
				parts = line.split("\t");
				
				/*
				 * Parts contient les champs du fichier new.txt.
				 * Les 7 premiers champs ne nous interessent pas.
				 * Le 8eme est une URL, le dernier est le "from".
				 * Les champs entre le 8eme et le dernier (s'il y en a, ce sont des autres URLs) 
				 */
				
				int start = 7;
				int last = parts.length-1;
				
				/*
				 *  Traitement referrer.
				 *  	fromOK indique au reste du code que le from existe pour cette ligne
				 *  	si fromOK
				 *  		=> from va stocker l'URL de la page qui reference cette ligne
				 *  		=> fromPage va stocker la page web representant l'URL 
				 */
				String from = parts[last];
				fromMatcher = fromRegex.matcher(from);
				boolean fromOK = fromMatcher.find();
				WebPage fromPage = null;
				
				if (fromOK) {
					LogUtil.debug("\tfromOK = true");
					from = fromMatcher.group(2);
					if (this.webpages.containsKey(from)) {
						LogUtil.debug("\tFrom " + from + " existant");
						fromPage = this.webpages.get(from);
					}
					else {
						LogUtil.debug("\tNouveau from " + from);
						fromPage = new WebPage(from);
						this.webpages.put(from, fromPage);
					}
				}
				
				/*
				 * Traitement des URLs dans cette ligne
				 */
				for (int i = start; i < last; i++) {
					// Parsing de l'URL
					Matcher matcherPage = urlRegex.matcher(parts[i]);
					if (matcherPage.find()) {
					
						// Creation d'une nouvelle page web ou recuperation si elle existe
						String url = matcherPage.group(2);
						WebPage page;
						if (this.webpages.containsKey(url)) {
							LogUtil.debug("\tPage " + url + " existante");
							page = this.webpages.get(url);
						}
						else {
							LogUtil.debug("\tNouvelle page " + url);
							page = new WebPage(url);
						}
						
						// Ajout du from dans la liste d'arcs entrant
						// et de la page dans la liste d'arcs sortant du from
						if (fromOK) {
							LogUtil.debug("\tfromOK => ajout de from dans la liste d'arcs entrant");
							List<WebPage> inArc = page.getInputArc();
							if (! inArc.contains(fromPage)) {
								LogUtil.debug("\t\tfrom ajoute dans inputArc");
								inArc.add(fromPage);
							}
							
							List<WebPage> outArc = fromPage.getOutputArc();
							if (! outArc.contains(page)) {
								LogUtil.debug("\t\tpage ajoute dans outputArc");
								outArc.add(page);
							}
						}
						
						this.webpages.put(url, page);
					}
					else {
						LogUtil.debug("\tLa page '" + parts[i] + "' n'est pas une URL valide");
					}
				}
				
				LogUtil.debug();
			}
			
			buff.close();
			r.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  Effectue la lecture du fichier contenant les mots-clés (et donc le remplissage de
	 *  l’attribut MyDataStructure)
	 */
	public void readKeyWords() {
		try {
			InputStream is = getClass().getResourceAsStream(this.pathFileKeywords);
			Reader r = new InputStreamReader(is, "ISO-8859-1");
			BufferedReader buff = new BufferedReader(r);
			String keyword;
			String line;
			
			Pattern urlRegex = Pattern.compile("^\\t[0-9]+ (.+)$");
			Matcher matcher;
			
			/*
			 * On lit une ligne (keyword)
			 * Puis toutes les pages associes
			 * On saute les deux lignes finales
			 * Et enfin on boucle dans le grand while pour le prochain keyword
			 */
			while ((keyword = buff.readLine()) != null && ! keyword.trim().isEmpty()) {
				LogUtil.debug(keyword);
				
				// URLs
				line = buff.readLine();
				matcher = urlRegex.matcher(line);
				while (matcher.find()) {
					LogUtil.debug("\t" + matcher.group(1));
					line = buff.readLine();
					matcher = urlRegex.matcher(line);
				}
				
				// A la fin, on a deja lu la premiere des deux lignes qu'il faut sauter. On ignore la deuxieme.
				buff.readLine();
			}
			
			buff.close();
			r.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Calcule le graphe associé à la recherche repŕesentée par les mots-clés de la liste passée
	 * en argument.
	 * 
	 * @return
	 */
	public List<WebPage> getSearchSubGraph(List<String> keyWords) {
		return null;//TODO
	}
	
	/**
	 *  Permet d’effectuer une recherche avec un ou plusieurs mots-cles et renvoie une liste de pages web
	 *  ordonnees en fonction de leur rang pour cette recherche (la premiere page correspondant a celle
	 *  possedant le rang le plus eleve).
	 * 
	 * @param keyWords
	 * @return
	 */
	public List<WebPage> search(List<String> keyWords) {
		return null;//TODO
	}
	
	/**
	 * Effectue le calcul du rang de chaque page passée	en argument en fonction de la recherche
	 * (l’argument List<String> contient les mots-clés de la recherche).
	 * 
	 * @param webpages
	 * @param keyWords
	 */
	public abstract void computeRanks(List<WebPage> webpages, List<String> keyWords);

}
