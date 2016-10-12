<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.io.*" %>

<html>
<head>
    <title>Ex2 - Params URL et données serveur</title>
</head>

<body>

<%
    /*
        2 – Ecrire une page JSP qui commence par identifier un nom passé en paramètre de l’URL, puis qui
        affiche le jour de la semaine correspondant à la date du serveur, ainsi que toutes les propriétés du
        système (System.getProperties()).
    */

    String param = request.getParameter("nom");

    Date now = new Date();
    DateFormat dateFormat = new SimpleDateFormat("E");

    Properties props = new Properties(System.getProperties());
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(os);
    props.list(printStream);

%>
    <table>
        <tr>
            <th>Paramètre par GET</th>
            <td><%= param %></td>
        </tr>
        <tr>
            <th>Jour de la semaine</th>
            <td><%= dateFormat.format(now) %></td>
        </tr>
        <tr>
            <th>Liste de propriétés</th>
            <td><pre><%= os.toString() %></pre></td>
        </tr>
    </table>

</body>
</html>


