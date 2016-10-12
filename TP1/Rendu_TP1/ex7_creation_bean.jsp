<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>

<jsp:useBean id="utilisateur" class="user.UserData" scope="session"></jsp:useBean>

<jsp:setProperty name="utilisateur" property="*" />

<%
    /*
    utilisateur.setNom(request.getParameter("nom"));
    utilisateur.setAge(Integer.parseInt(request.getParameter("age")));
    utilisateur.setEmail(request.getParameter("email"));
    */
%>

<!DOCTYPE html>
<html>
<head>
    <title>Ex7 - Utilisation de Beans, Création</title>
</head>

<body>
    <h3>Les données ont été stockées dans le Bean</h3>
    <br />
    <a href="ex7_affichage_bean.jsp">Afficher l'utilisateur</a>
</body>
</html>

