<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>

<%
    /*
     *   4 – Afficher le nombre de fois où on a demandé à charger la page.
     *   Faire ensuite la différence avec une simple variable déclarée en Java. (C1 transparent 77, page 39)
     */
%>

<html>
<head>
    <title>Ex4 - Attributs de classe</title>
</head>

<body>

<%!
    // Déclaration de variable statique
    int compteur = 0;
%>

<%
    // Compteur de visites
    compteur++;
%>

<%
    // Variable qui est redéfini à chaque visite
    int varInstance = 0;
    varInstance++;
%>


    <%= "Nombre de requêtes reçues (variable statique) : " + compteur %>
    <br />
    <%= "Variable créée à chaque visite (variable d'instance) : " + varInstance %>

</body>
</html>


