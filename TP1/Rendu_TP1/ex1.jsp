<%@ page language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.io.*" %>
<%
    /*
     *   1 – Ecrire une page JSP qui appelle une page web quelconque (par exemple l’accueil du CERI).
     *   En Java, on déclare une java.net.URL, construite avec l'URL de la page à accéder, puis on ouvre une
     *   URLConnection avec la méthode openConnection() et enfin on accède au flux d'entrée (InputStream)
     *   de cette connexion avec getInputStream() de cette classe URLConnection.
     *   Rappel sur les flux en Java : on peut lire avec la méthode readLine() sur un BufferedReader construit
     *   avec un InputStreamReader lui-même construit avec un InputStream.
     */

    URL url = new URL("http://ceri.univ-avignon.fr");
    URLConnection conn = url.openConnection();
    InputStream is = conn.getInputStream();
    InputStreamReader reader = new InputStreamReader(is);
    BufferedReader buff = new BufferedReader(reader);

    String ligne = buff.readLine();
    while(ligne != null) {
%>
        <%= ligne %>
<%
        ligne = buff.readLine();
    }

    // Fermeture de la connection
    is.close();
%>
