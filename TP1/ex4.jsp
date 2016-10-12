<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>Ex4 - Attributs de classe</title>
</head>

<body>

<%
    /*
        4 – Afficher le nombre de fois où on a demandé à charger la page.
        Faire ensuite la différence avec une simple variable déclarée en Java. (C1 transparent 77, page 39)
    */
%>
<%!
    int compteur = 0;
%>

<%
    int varInstance = 25;
%>

<%
    compteur++;
%>


    <%= "Nombre de requêtes reçues : " + compteur %>
    <br />
    <%= "Différence avec varInstance (=25) : " + (compteur - varInstance) %>

</body>
</html>


