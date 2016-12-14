package tp4;

public class LogUtil {
	private static final boolean DEBUG = true;
	
	public static void log(String texte) {
		System.out.println(texte);
	}
	
	public static void debug(String texte) {
		if (DEBUG) log(texte);
	}
	
	public static void debug() {
		LogUtil.debug("");
	}
}
