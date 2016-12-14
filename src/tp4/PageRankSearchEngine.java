package tp4;

import java.util.List;

public class PageRankSearchEngine extends SearchEngine {

	public PageRankSearchEngine(String pathFilePages, String pathFileKeywords, double doble, int integer) {
		super(pathFilePages, pathFileKeywords);
	}

	@Override
	public void computeRanks(List<WebPage> webpages, List<String> keywords) {
		// TODO Auto-generated method stub

	}

	
	public static void main(String[] args) {
		System.out.println("Main PageRankSearchEngine");
		SearchEngine e = new PageRankSearchEngine("new.txt", "index_small.txt", 0, 0);
		e.readWebPages();
		e.readKeyWords();
		
	}
}
