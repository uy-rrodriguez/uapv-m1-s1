<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>

<%
    /*
     *   6 – Suivant une condition (par exemple si le nombre de fois où on a chargé la page est pair ou
     *       impair), faire un include ou un forward de page. (C1 transparent 82, page 42). Faites la différence
     *       entre jsp:include et <%@include en déclarant et affectant une variable dans le fichier initial, puis en ré
     *       utilisant cette variable (sans la re déclarer) dans le fichier inclus.
     */
%>

<!DOCTYPE html>
<html>
<head>
    <title>Ex6 - Inclusion et Forwarding</title>
</head>

<body>

<%!
    int compteur = 0;
%>
<%
    compteur++;

    if (compteur % 2 == 0) {
%>

        <h1>Inclusion avec jsp:include</h1>
        <i>Avec flush="false" l'inclusion lance une exception parce qu'on ne peut pas accéder aux variables dans la page "mère"</i>
        <br />
        <i>Avec flush="true" le serveur arrête l'exécution de la page, du coup on ne voit pas ce qui devrait venir après.</i>
        <br />
        <!-- jsp:include page="ex6_include.jsp" flush="true" / -->

        <br />
        <br />

        <h1>Inclusion avec @ include</h1>
        <%@ include file="ex6_include.jsp" %>

<%
    }
    else {
%>

        <jsp:forward page="/ex6_forward.jsp" />

<%
    }
%>

</body>
</html>
